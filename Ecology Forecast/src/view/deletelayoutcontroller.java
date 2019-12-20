package view;

import Util.AlertBox;
import application.Main;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Animal;

public class deletelayoutcontroller {
	@FXML
	private GridPane animalgrid; 
	
	private Stage deletestage;
	
	private ObservableList<Animal> data;
	
	private Main main;
	
	/**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public deletelayoutcontroller() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

    }
    
    public void setDeletestage(Stage deletestage) {
        this.deletestage = deletestage;
    }
    
    public void deleteAnimal()
    {
    	data = main.getAnimals();
    	int rows = data.size();
            	
    	animalgrid.setVgap(8);
        animalgrid.setHgap(10);
     
    }
    
	@FXML
	private void confirmHandler()
	{
		Boolean answer = AlertBox.display("Deleting Animals", "Are you sure you want to delete?");
	
	}
	@FXML
	private void cancelHandler()
	{
		deletestage.close();
	}

	public void setMainApp(Main main) {
		this.main = main;
		// TODO Auto-generated method stub
		
	}

}
