package view;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import application.Main;


public class rootcontroller {
	
	private String[][] data;

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


    // Reference to the main application.
    private Main main;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public rootcontroller() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {


    	
        //
   	
    	
        // Initialize the person table with the two columns.
        //firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        //lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(Main main) {
    	
    	
        this.main = main;
        main.primaryStage.setResizable(false);
        
        rightnavigation.lookupAll(".split-pane-divider").stream()
        .forEach(div ->  div.setMouseTransparent(true) );
        
        scenenavigation.lookupAll(".split-pane-divider").stream()
        .forEach(div ->  div.setMouseTransparent(true) );
        
        try {
			data = Main.getData();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //population.setPadding(new Insets(15,15,50,0));
        population.setVgap(8);
        population.setHgap(10);
        
        int rows = data.length;
        
        for (int i=1; i<rows; i++)
        {
        	Label animallabel = new Label (data[i][0]);
        	animallabel.setPrefWidth(100);
        	population.add(animallabel, 0, i, 1, 1);
        	Slider sli = new Slider(0,1000, Integer.parseInt(data[i][1]));
        	sli.setPrefWidth(100);
        	
        	population.add(sli, 1, i, 1, 1);
        	TextField total = new TextField(data[i][1]);
        	total.setPrefWidth(50);
        	population.add(total, 2, i, 1, 1);        	
        }

        	

        //population.add(animallabel, 0, 0);


        // Add observable list data to the table
        //personTable.setItems(mainApp.getPersonData());
    }
}
