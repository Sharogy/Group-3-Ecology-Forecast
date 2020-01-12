package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
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
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import math.CarryingCapacity_Model;
import math.Exponential_Model;
import math.Predator_Model;
import math.modelfactory;
import model.Animal;
import model.AnimalFactory;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.prefs.Preferences;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import Util.AlertBox;
import Util.Classminer;
import Util.Dataaccess;
import Util.Dataminer;
import Util.Datawriter;
import application.Main;
import interfaces.imodel;
import Util.AlertBox;


public class rootcontroller {
	
    @FXML
    private GridPane population;
    
    @FXML
    private Label header;
    @FXML
    private Label header2;
    
    @FXML
    private BorderPane borderpane;
    
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
    private TableColumn<Animal, String> animalnotesColumn;
    @FXML
    private TableColumn<Animal, Number> animaltotalColumn;
    
    private ObservableList<String> timeoptions = FXCollections.observableArrayList("1 Year", "2 Years", "3 Years", "4 Years", "5 Years", "6 Years", "7 Years", "8 Years", "9 Years", "10 Years");
    private ObservableList<String> modeloptions;
    private int timeperiod;
    @FXML
    private ComboBox<String> timebox;
    @FXML
    private ComboBox<String> modelbox;
    @FXML
    private CheckBox grassbox;
    @FXML
    private CheckBox predatorbox;
  
    @FXML
    private Label growthdata;
    @FXML
    private Label deathdata;
    @FXML
    private Label avgweightdata;
    @FXML
    private Label consumptiondata;
    
    @FXML
    private Label wolflabel;
    @FXML
    private Slider wolfslider;
    @FXML
    private Button wolfbutton;
    @FXML
    private TextField predpop;

    //Local attributes
    private Main main;
        
    private ObservableList<Animal> animallist;
    
    public static Boolean addoredit;
    
    private Dataaccess da = new Dataaccess();
    
    private FXMLLoader lineloader;
    private FXMLLoader pieloader;
    private FXMLLoader barloader;
    private FXMLLoader statloader;
    
    private AnchorPane linepane;
    private AnchorPane piepane;
    private AnchorPane barpane;
    private AnchorPane statpane;
    
    private lineviewlayoutcontroller linecontroller;
	private pieviewlayoutcontroller piecontroller;
	private barviewlayoutcontroller barcontroller;
	private statviewlayoutcontroller statcontroller;
    
    private String selectedtime = "3 Years";
    private String selectedmodel = "Exponential Model";
    private boolean competitive = false;
    private boolean selectedgrass = true;
    private boolean selectedpredator = false;
    
    private int wolfcount;
    
    private String theme = getClass().getResource("/themes/modena.css").toExternalForm();
    private String theme1 = getClass().getResource("/themes/caspian.css").toExternalForm();
    private String theme2 = getClass().getResource("/themes/Darktheme.css").toExternalForm();
  
    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    
    public rootcontroller() {
    }
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     * @throws IOException 
     */
    @FXML
    private void initialize() throws IOException {
    	//populated animal table
    	animalnameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    	animalnotesColumn.setCellValueFactory(cellData -> cellData.getValue().notesProperty());
    	animaltotalColumn.setCellValueFactory(cellData -> cellData.getValue().totalProperty());
    	showAnimalDetails(null);	
    	animalTable.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)-> showAnimalDetails(newValue));
    	
    	//loading configuration
    	loaddrawingboard();
    	loadview(lineloader, linepane);  
    	checkboxconfig();
    	spawncombobox(); 
    	modelsearch();
    	graphics();
    	header.setTextAlignment(TextAlignment.CENTER);
    	header2.setTextAlignment(TextAlignment.CENTER);
    	
		predpop.setVisible(false);
		wolflabel.setVisible(false);
		wolfslider.setVisible(false);
		wolfbutton.setVisible(false);
		
		sliderconfig();
		
		wolfslider.valueProperty().addListener(new ChangeListener<Number>() {
	        public void changed(ObservableValue<? extends Number> ov,
	            Number old_val, Number new_val) {
	        		wolfslider.setValue((int) Math.round(new_val.doubleValue()));
	                predpop.setText(String.valueOf((int) Math.round(new_val.doubleValue())));
	        }
	    });

		predpop.textProperty().addListener((observable, oldValue, newValue) -> {
			try {
				wolfslider.setValue((int) Math.round(Double.valueOf(newValue)));
			}
			catch (NumberFormatException e) {
				System.out.println("Wrong Value");
			}
		});
    }
    
    private void graphics() {
    }
    
    //search models in math package
        
    private void modelsearch()
    {
    	ArrayList<Class<?>> modellist;
    	ArrayList<String> models = new ArrayList<String>();
		try {
			modellist = Classminer.getClassesForPackage("math");
	    	for (int i = 0; i < modellist.size(); i++)
	    	{
	    		if (imodel.class.isAssignableFrom(modellist.get(i)))
	    		{
	    			String model;
	    			model = modellist.get(i).getSimpleName();
	    			model = model.replace("_", " ");
	    			models.add(model);
	    		}
	    	}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	modeloptions = FXCollections.observableArrayList(models);	
    	modelbox.setItems(modeloptions);
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
    	if (selectedmodel.equalsIgnoreCase("Competitive Model"))
    	{
    		competitive = true;
    	}
    	modelfactory mf = new modelfactory();
    	imodel im = mf.getModel(selectedmodel);
    	String[] split = selectedtime.split("\\s+");
    	timeperiod = Integer.valueOf(split[0]); 	
    	
    	linecontroller = lineloader.getController();
    	piecontroller = pieloader.getController();
    	barcontroller = barloader.getController();
    	statcontroller = statloader.getController();
    	    	
    	try {
    		if (animallist.size() == 0) 
    			{
        		throw new NullPointerException("Animalist is Empty");
    			}
    	

    			if (selectedpredator)
    				{
    				wolfcount = Integer.valueOf(predpop.getText());
    				}
    			//System.out.println(CarryingCapacity_Model.getCarrycapacity(animallist.get(0), animallist, selectedgrass));
		    	linecontroller.spawndata(animallist, timeperiod, im, selectedgrass, selectedpredator, wolfcount, competitive);
		    	piecontroller.spawndata(animallist, timeperiod, im, selectedgrass, selectedpredator, wolfcount, competitive);
		    	barcontroller.spawndata(animallist, timeperiod, im, selectedgrass, selectedpredator, wolfcount, competitive);
		    	statcontroller.spawndata(animallist, timeperiod, im, selectedgrass, selectedpredator, wolfcount, competitive);  		 	
    	}
    	catch (NumberFormatException e)
    	{
    		Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("Wrong number");
            alert.setHeaderText("The number of wolves is not an integer");
            alert.setContentText("Please input an integer for the number of wolves.");

            alert.showAndWait();
    	}	
    	catch (NullPointerException e)
		{
    		Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("No Animal Data");
            alert.setHeaderText("No Animal Data Selected");
            alert.setContentText("Please load the animal data from an existing file or load the preset data.");

            alert.showAndWait();
		}  
    }
    
    @FXML
    private void clearsimulation()
    {
    	linecontroller = lineloader.getController();
    	piecontroller = pieloader.getController();
    	barcontroller = barloader.getController();
    	statcontroller = statloader.getController();
    	linecontroller.cleardata();
    	piecontroller.cleardata();
    	barcontroller.cleardata();
    	statcontroller.cleardata();
    }
    

    
    // auto generate labels, sliders, text field based on the data entry.
    public void setMainApp(Main main) {
    	
    	
        this.main = main;
        
        main.getPrimaryStage().setResizable(false);
        
        rightnavigation.lookupAll(".split-pane-divider").stream()
        .forEach(div ->  div.setMouseTransparent(true) );
        
        scenenavigation.lookupAll(".split-pane-divider").stream()
        .forEach(div ->  div.setMouseTransparent(true) ); 
        
        animalTable.setItems(animallist);
        
        da.setAnimalFilePath(null);
             
    }
    
   
    // NAVIGATION BOARD FUNCTIONS
    
    @FXML
    private void handleloadpreset()
    {
    	animallist = da.loadAnimalPreset("Eco Data\\Ecodata.xlsx");
    	animalTable.setItems(animallist);
    	main.getPrimaryStage().setTitle("Animal Population Forecast - " + "Preset Data");  	
    }

	@FXML
    private void handleAddAnimal()
    {
    	Animal tempanimal = new Animal();
    	addoredit = true;
        boolean okClicked = main.showEditAnimals(tempanimal);
        if (okClicked) {
            animallist.add(tempanimal);
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
    		avgweightdata.setText(String.valueOf(animal.getAvgweight()));
    		consumptiondata.setText(String.valueOf(animal.getConsumptionrate()));
    		
    	}
    	else
    	{
    		growthdata.setText("");
    		deathdata.setText("");
    		consumptiondata.setText("");
    	}
    }
      
    private void checkboxconfig()
    {
    	grassbox.setSelected(true);
    	grassbox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov,
                    Boolean old_val, Boolean new_val) {
                    //System.out.println(grassbox.isSelected());
                    selectedgrass = grassbox.isSelected();
                 }
               });
    	predatorbox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov,
            		Boolean old_val, Boolean new_val) {
            		//System.out.println(predatorbox.isSelected());
            		selectedpredator = predatorbox.isSelected();
            		if (!selectedpredator)
            		{
            			predpop.setVisible(false);
            			wolflabel.setVisible(false);
            			wolfslider.setVisible(false);
            			wolfbutton.setVisible(false);
            		}
            		if (selectedpredator)
            		{
            			predpop.setVisible(true);
            			wolflabel.setVisible(true);
            			wolfslider.setVisible(true);
            			wolfbutton.setVisible(true);
            			predpop.setText("10");
            			wolfslider.setValue(10);
            		}
            }
        });
    }
         
    
    // FILE FUNCTIONS
    
    @FXML
    private void handleNew()
    {
    	animallist.clear();
    	da.setAnimalFilePath(null);
    }
    
    @FXML
    private void handleImport()
    {
    	FileChooser fileChooser = new FileChooser(); 	
    
    	fileChooser.setInitialDirectory(da.getAnimalFilePath());
    	FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XLSX files (*.xlsx)", "*.xlsx");
    	fileChooser.getExtensionFilters().add(extFilter);
    	
    	File file = fileChooser.showOpenDialog(main.getPrimaryStage());
    	
    	//da.setAnimalFilePath(null);
    	
    	if (file != null)
    	{
    		animallist = da.loadAnimalDataFromFile(file);
    		animalTable.setItems(animallist);
    		main.getPrimaryStage().setTitle("Animal Population Forecast - " + file.getName());
    	}    	
    }
    
    @FXML
    private void handleExport()
    {
    	FileChooser fileChooser = new FileChooser();
    	
    	fileChooser.setInitialDirectory(da.getAnimalFilePath());

    	FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XLSX files (*.xlsx)", "*.xlsx");
    	fileChooser.getExtensionFilters().add(extFilter);
    	
    	File file = fileChooser.showSaveDialog(main.getPrimaryStage());
    	
    	if (file!=null)
    	{
    		if (!file.getPath().endsWith(".xlsx"))
    		{
    			file = new File(file.getPath() + ".xlsx");
    		}
    		da.saveAnimalDataToFile(file, animallist);
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
    	alert.setContentText("Author: Ke Zhang\nContributor: Liam Wynne, Jinnous Fahimi, Judita Martinkutë\nStudentID: 607759\nFaculty: Inholland Diemen");
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
    
//Layouts
    @FXML
    private void handleLayoutdefault()
    {
    	borderpane.getStylesheets().remove(theme2);
    	borderpane.getStylesheets().remove(theme1);
    	borderpane.getStylesheets().add(theme);
    }
    
    @FXML
    private void handleLayoutlight()
    {
//Remove from scene the theme1(asumming you added to your scene when your app starts)
    	borderpane.getStylesheets().remove(theme2);
    	borderpane.getStylesheets().remove(theme);
    	borderpane.getStylesheets().add(theme1);
    }
    
    @FXML
    private void handleLayoutDark()
    {
    	borderpane.getStylesheets().remove(theme1);
    	borderpane.getStylesheets().remove(theme);
    	borderpane.getStylesheets().add(theme2);
//Remove from scene the theme1(asumming you added to your scene when your app starts)
    }
    
    // Combobox event and options
    private EventHandler<ActionEvent> timeevent = new EventHandler<ActionEvent>()
			{
				public void handle(ActionEvent e)
				{
					selectedtime = String.valueOf(timebox.getValue());
				}
			};
    private EventHandler<ActionEvent> modelevent = new EventHandler<ActionEvent>() 
    		{
    			public void handle(ActionEvent e)
    			{
    				selectedmodel = String.valueOf(modelbox.getValue());
    			}
    		};
    		
    

    
    private void spawncombobox() 
    {
    	timebox.setItems(timeoptions);
    	timebox.setVisibleRowCount(5);
    	timebox.setValue("3 Years");
    	timebox.setOnAction(timeevent);
    	
    	modelbox.setVisibleRowCount(5);
    	modelbox.setValue("Exponential Model");
    	modelbox.setOnAction(modelevent);	
    }
    
    private void sliderconfig()
    {
    	wolfslider.setMax(50);
    	wolfslider.setMin(0);
    	wolfslider.setBlockIncrement(1);
    	wolfslider.setShowTickLabels(true);
    	wolfslider.setShowTickMarks(true);    	
    }
    
    
   
}

