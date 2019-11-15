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

public class deletelayoutcontroller {
	@FXML
	private GridPane animalgrid; 
	
	private Stage deletestage;
	
	private String[][] data;
	
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
    	data = rootcontroller.getData();
    	int rows = data.length;
            	
    	animalgrid.setVgap(8);
        animalgrid.setHgap(10);
        
        for (int i=1; i<rows; i++)
        {
        	Label animallabel = new Label (data[i][0]);
        	animallabel.setPrefWidth(100);
        	animallabel.setPrefHeight(20);
        	animalgrid.add(animallabel, 0, i, 1, 1);
        	
        	CheckBox deletebox = new CheckBox();
        	animalgrid.add(deletebox, 1, i,1,1);      	
        }
    }
    
	@FXML
	private void confirmHandler()
	{
		Boolean answer = AlertBox.display("Deleting Animals", "Are you sure you want to delete?");
		if (answer)
		{			
			for (int i = 0; i<rootcontroller.getData().length-1; i++)
			{
				CheckBox checkbox = (CheckBox) animalgrid.getChildren().get(i*2+1);
				if (checkbox.isSelected())
				{
					data[i+1][1] = Integer.toString(0); 
					System.out.println(data[i+1][1]);
				}
			}
			rootcontroller.setData(data);
			rootcontroller root = main.getRoot();
			
			deletestage.close();
		}		
		else
		{
			deletestage.close();
		}		
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
