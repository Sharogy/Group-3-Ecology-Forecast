package view;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import model.Animal;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import Util.AlertBox;
import Util.Dataminer;
import application.Main;
import Util.AlertBox;


public class rootcontroller {
	
	private String[][] data;
	private static String[][] tempdata;

    @FXML
    private GridPane population;
    
    @FXML
    private Label testlabel;
    
    @FXML 
    private SplitPane scenenavigation;
    
    @FXML
    private SplitPane rightnavigation;
    
    @FXML
    private AnchorPane animalmenu;
    
    @FXML
    private AnchorPane functionmenu;
    
    @FXML
    private TableView<Animal> animalTable;
    @FXML 
    private TableColumn<Animal, String> animalnameColumn;
    @FXML
    private TableColumn<Animal, Number> animaltotalColumn;
    
    @FXML
    private Label growthdata;
    @FXML
    private Label deathdata;
    @FXML
    private Label consumptiondata;


    // Reference to the main application.
    private Main main;
    
    private static rootcontroller root = null;

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
     */
    @FXML
    private void initialize() {
    	animalnameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    	animaltotalColumn.setCellValueFactory(cellData -> cellData.getValue().totalProperty());
    	
    	showAnimalDetails(null);
    	
    	animalTable.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)-> showAnimalDetails(newValue));
    }
    

    
    // auto generate labels, sliders, text field based on the data entry.
    public void setMainApp(Main main) {
    	
    	
        this.main = main;
        animalTable.setItems(main.getAnimals());
        
        main.primaryStage.setResizable(false);
        
        rightnavigation.lookupAll(".split-pane-divider").stream()
        .forEach(div ->  div.setMouseTransparent(true) );
        
        scenenavigation.lookupAll(".split-pane-divider").stream()
        .forEach(div ->  div.setMouseTransparent(true) );  
        
    }
    
    @FXML
    private void handleAddAnimal()
    {
    	Animal tempanimal = new Animal();
        boolean okClicked = main.showEditAnimals(tempanimal);
        System.out.println(okClicked);
        //if (okClicked) {
         //   main.getAnimals().add(tempanimal);
        //}
    	
    }
    
    @FXML
    private void handleEditAnimal()
    {
    	Animal selectedAnimal = animalTable.getSelectionModel().getSelectedItem();
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
    	if (selectedIndex >=0)
    	{
    	animalTable.getItems().remove(selectedIndex);
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
      
        
    public static String[][] getData()
    {
    	return tempdata;
    }
    
    public static void setData(String[][] data)
    {
    	tempdata = data;
    }
}
