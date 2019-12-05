package view;

import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;

public class barviewlayoutcontroller {
	
	//Defining the x axis               
	@FXML
	private StackedBarChart<String, Number> stackedbarchart;
	
	@FXML 
	private CategoryAxis xAxis;
	
	@FXML
	private NumberAxis yAxis;
	
	private static XYChart.Series<String, Number> series1;
	private static XYChart.Series<String, Number> series2;
	private static XYChart.Series<String, Number> series3;

	
	public barviewlayoutcontroller()
    {
    	
    }
	
	@FXML
	public void initialize()
	{  

		xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList
		   ("2020", "2021", "2022", "2023", "2024"))); 
		xAxis.setLabel("Time in Years");  

		//Defining the y axis 
		yAxis.setLabel("Population");
		
		//Setting the data to bar chart  
		
		series1 = new XYChart.Series<>(); 
		series1.setName("Heck Cattle"); 
		
		series2 = new XYChart.Series<>();  
		series2.setName("Red Deer"); 
		
		series3 = new XYChart.Series<>(); 
		series3.setName("Konik Horse"); 
		
		stackedbarchart.getData().addAll(series1, series2, series3); 
		
	}
	
	public static void spawndata()
	{
		//Prepare XYChart.Series objects by setting data 

		series1.getData().add(new XYChart.Data<>("2020", 107)); 
		series1.getData().add(new XYChart.Data<>("2021", 31)); 
		series1.getData().add(new XYChart.Data<>("2022", 635)); 
		series1.getData().add(new XYChart.Data<>("2023", 203)); 
		series1.getData().add(new XYChart.Data<>("2024", 2)); 
		
		
		series2.getData().add(new XYChart.Data<>("2020", 133)); 
		series2.getData().add(new XYChart.Data<>("2021", 156)); 
		series2.getData().add(new XYChart.Data<>("2022", 947)); 
		series2.getData().add(new XYChart.Data<>("2023", 408)); 
		series1.getData().add(new XYChart.Data<>("2024", 6));  

		
		series3.getData().add(new XYChart.Data<>("2020", 973)); 
		series3.getData().add(new XYChart.Data<>("2021", 914)); 
		series3.getData().add(new XYChart.Data<>("2022", 4054)); 
		series3.getData().add(new XYChart.Data<>("2023", 732)); 
		series1.getData().add(new XYChart.Data<>("2024", 34));
	}
	
	public static void cleardata()
	{
		series1.getData().clear();
		series2.getData().clear();
		series3.getData().clear();
	}

}
