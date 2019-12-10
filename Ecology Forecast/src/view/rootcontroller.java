package view;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import math.Exmodel;
import model.Animal;
import model.AnimalFactory;
import model.SliderCell;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.prefs.Preferences;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import Util.AlertBox;
import Util.Dataminer;
import Util.Datawriter;
import application.Main;
import Util.AlertBox;


public class rootcontroller {
	
    @FXML
    private GridPane population;
    
    @FXML
    private Label testlabel;
    
    @FXML 
    private SplitPane scenenavigation;
    
    @FXML
    private SplitPane rightnavigation;
    
    @FXML
    private AnchorPane drawingboard;
    
    @FXML
    private AnchorPane functionmenu;
    
    @FXML
    private TableView<Animal> animalTable;
    @FXML 
    private TableColumn<Animal, String> animalnameColumn;
    @FXML
    private TableColumn<Animal, Number> animaltotalColumn;
    @FXML
    private TableColumn sliderColumn;
    
    @FXML
    private Label growthdata;
    @FXML
    private Label deathdata;
    @FXML
    private Label consumptiondata;


    // Reference to the main application.
    private Main main;
    
    private static rootcontroller root = null;
    
    private ObservableList<Animal> animallist;
    
    public static Boolean addoredit;
    
    private FXMLLoader lineloader;
    private FXMLLoader pieloader;
    private FXMLLoader barloader;
    private FXMLLoader statloader;
    
    private AnchorPane linepane;
    private AnchorPane piepane;
    private AnchorPane barpane;
    private AnchorPane statpane;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    
    public rootcontroller() {
    }
    
    public static rootcontroller getInstance() {
    	if (root == null)
    	{
    		root = new rootcontroller();   		
    	}
    	return root;
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     * @throws IOException 
     */
    @FXML
    private void initialize() throws IOException {
    	animalnameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    	animaltotalColumn.setCellValueFactory(cellData -> cellData.getValue().totalProperty());
    	
    	showAnimalDetails(null);
    	
    	animalTable.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)-> showAnimalDetails(newValue));
    	
    	loaddrawingboard();
    	loadview(lineloader, linepane);
    	
    	
    }
    
    //Populate the blackboard
    @FXML
    private void loaddrawingboard() throws IOException
    {
    	
    	lineloader = new FXMLLoader();
        lineloader.setLocation(getClass().getResource("/view/lineviewlayout.fxml"));
        pieloader = new FXMLLoader();
        pieloader.setLocation(getClass().getResource("/view/pieviewlayout.fxml"));
        barloader = new FXMLLoader();
        barloader.setLocation(getClass().getResource("/view/barviewlayout.fxml"));
        statloader = new FXMLLoader();
        statloader.setLocation(getClass().getResource("/view/statviewlayout.fxml"));      
                
        linepane = (AnchorPane) lineloader.load();
        piepane = (AnchorPane) pieloader.load();
        barpane = (AnchorPane) barloader.load();
        statpane = (AnchorPane) statloader.load();
  	           
    }

    
    private void loadview(FXMLLoader loader, AnchorPane pane)
    {
    	drawingboard.getChildren().setAll(pane); 	
    }
    
    
    
    @FXML
    private void startsimulation()
    {
    	clearsimulation();
    	
    	
    	lineviewlayoutcontroller linecontroller = lineloader.getController();
    	linecontroller.spawndata(animallist, 5);
    	pieviewlayoutcontroller.spawndata();
    	barviewlayoutcontroller.spawndata();
    	//statviewlayoutcontroller.spawndata();  
    	statviewlayoutcontroller statcontroller = statloader.getController();
    	statcontroller.spawn();
    }
    
    @FXML
    private void clearsimulation()
    {
    	lineviewlayoutcontroller linecontroller = lineloader.getController();
    	linecontroller.cleardata();
    	pieviewlayoutcontroller.cleardata();
    	barviewlayoutcontroller.cleardata();
    	//statviewlayoutcontroller.cleardata();
    	statviewlayoutcontroller controller = statloader.getController();
    	controller.clear();
    }
    

    
    // auto generate labels, sliders, text field based on the data entry.
    public void setMainApp(Main main) {
    	
    	
        this.main = main;
        
        main.getPrimaryStage().setResizable(false);
        
        rightnavigation.lookupAll(".split-pane-divider").stream()
        .forEach(div ->  div.setMouseTransparent(true) );
        
        scenenavigation.lookupAll(".split-pane-divider").stream()
        .forEach(div ->  div.setMouseTransparent(true) ); 
        
        animallist = main.getAnimals();
        animalTable.setItems(animallist);
             
    }
    
   
    // NAVIGATION BOARD FUNCTIONS
    
    @FXML
    private void handleloadpreset()
    {
    	main.loadAnimalPreset("Eco Data\\Ecodata.xlsx");
    }

	@FXML
    private void handleAddAnimal()
    {
    	Animal tempanimal = new Animal();
    	addoredit = true;
        boolean okClicked = main.showEditAnimals(tempanimal);
        if (okClicked) {
            main.getAnimals().add(tempanimal);
        }        
    }
    
    @FXML
    private void handleEditAnimal()
    {
    	Animal selectedAnimal = animalTable.getSelectionModel().getSelectedItem();
    	addoredit = false;
        if (selectedAnimal != null) {
            boolean okClicked = main.showEditAnimals(selectedAnimal);
            if (okClicked) {
                showAnimalDetails(selectedAnimal);
            }
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
    
    @FXML
    private void handleDeleteAnimal()
    {
    	int selectedIndex = animalTable.getSelectionModel().getSelectedIndex();
    	Animal selectedAnimal = animalTable.getSelectionModel().getSelectedItem();
    	if (selectedIndex >=0)
    	{
    	animalTable.getItems().remove(selectedIndex);	
    	animallist.remove(selectedAnimal);
    	}
    	else
    	{
    		Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Animal Selected");
            alert.setContentText("Please select an animal in the table.");

            alert.showAndWait();
    	}
    }
    
    private void showAnimalDetails(Animal animal)
    {
    	if (animal != null)
    	{
    		growthdata.setText(String.valueOf(animal.getGrowthrate()));
    		deathdata.setText(String.valueOf(animal.getDeathrate()));
    		consumptiondata.setText(String.valueOf(animal.getConsumptionrate()));
    		
    	}
    	else
    	{
    		growthdata.setText("");
    		deathdata.setText("");
    		consumptiondata.setText("");
    	}
    }
      
         
    
    // FILE FUNCTIONS
    
    @FXML
    private void handleNew()
    {
    	main.getAnimals().clear();
    	main.setAnimalFilePath(null);
    }
    
    @FXML
    private void handleImport()
    {
    	FileChooser fileChooser = new FileChooser();
    	FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XLSX files (*.xlsx)", "*.xlsx");
    	fileChooser.getExtensionFilters().add(extFilter);
    	
    	File file = fileChooser.showOpenDialog(main.getPrimaryStage());
    	
    	if (file != null)
    	{
    		main.loadAnimalDataFromFile(file);
    	}
    	
    }
    
    @FXML
    private void handleExport()
    {
    	FileChooser fileChooser = new FileChooser();
    	FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XLSX files (*.xlsx)", "*.xlsx");
    	fileChooser.getExtensionFilters().add(extFilter);
    	
    	File file = fileChooser.showSaveDialog(main.getPrimaryStage());
    	
    	if (file!=null)
    	{
    		if (!file.getPath().endsWith(".xlsx"))
    		{
    			file = new File(file.getPath() + ".xlsx");
    		}
    		main.saveAnimalDataToFile(file);
    	}    	
    }
    
    
    // VIEW FUNCTIONS
    @FXML
    private void handlelineView()   
    {
    	loadview(lineloader, linepane);
    }
    @FXML
    private void handlepieView()   
    {
    	loadview(pieloader, piepane);
    }
    @FXML
    private void handlebarView()   
    {
    	loadview(barloader, barpane);
    }
    @FXML
    private void handlestatView()   
    {
    	loadview(statloader, statpane);
    }
  
    
    // HELP FUNCTIONS
    
    @FXML
    private void handleAbout()
    {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Animal Population Forecast");
    	alert.setHeaderText("This program is developed for the nature reserve Oostvaardersplassen.");
    	alert.setContentText("Author: Ke Zhang\nStudentID: 607759\nFaculty: Inholland Diemen");
    	alert.showAndWait();
    }
    
    @FXML
    private void handleManual()
    {
    	if (Desktop.isDesktopSupported()) {
    	    try {
    	        File myFile = new File("Docs/Manual.pdf");
    	        Desktop.getDesktop().open(myFile);
    	    } catch (IOException ex) {
    	        // no application registered for PDFs
    	    }
    	}
    }
}
