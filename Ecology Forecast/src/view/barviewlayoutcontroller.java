package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import math.imodel;
import model.Animal;

public class barviewlayoutcontroller {
	
	//Defining the x axis               
	@FXML
	private StackedBarChart<String, Number> stackedbarchart;
	
	@FXML 
	private CategoryAxis xAxis;
	
	@FXML
	private NumberAxis yAxis;
	
	private List<String> categories = new ArrayList<String>();
	private List<List<Integer>> bardatacollection; 

	public barviewlayoutcontroller()
    {
    	
    }
	
	@FXML
	public void initialize()
	{  
		//Defining the x axis
		xAxis.setLabel("Time in Years");  

		//Defining the y axis 
		yAxis.setLabel("Population");
	}
	
	public void spawndata(ObservableList<Animal> animallist, int timeperiod, imodel im )
	{
		for (int i = 0; i< timeperiod+1; i++)
		{
			categories.add("Year " + String.valueOf(i));
		}
		xAxis.setCategories(FXCollections.<String>observableArrayList(categories));
		
		bardatacollection = new ArrayList<List<Integer>>();
		for (int i = 0; i< animallist.size(); i++)
		{
			Animal ani = animallist.get(i);
			List<Integer> anidata = im.calculate(ani, timeperiod);		
			bardatacollection.add(anidata);
		}
		
		for (int i = 0; i< animallist.size(); i++)
		{
			XYChart.Series<String, Number> series = new XYChart.Series<>();
			series.setName(animallist.get(i).getName());
			for (int j = 0; j < timeperiod + 1; j++)
			{
				series.getData().add(new XYChart.Data<>(categories.get(j),bardatacollection.get(i).get(j)));
			}
			stackedbarchart.getData().add(series);			
		}
	}
	
	public void cleardata()
	{
		categories.clear();
		stackedbarchart.getData().forEach(a -> a.getData().forEach(b -> b.setYValue(0)));
		stackedbarchart.getData().clear();
		stackedbarchart.layout();
	}

}
