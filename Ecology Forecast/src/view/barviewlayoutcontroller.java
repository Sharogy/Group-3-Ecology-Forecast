package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import interfaces.icontroller;
import interfaces.imodel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import math.Predator_Model;
import model.Animal;

public class barviewlayoutcontroller implements icontroller {
	
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
	
	public void spawndata(ObservableList<Animal> animallist, int timeperiod, imodel im, boolean grassmode, boolean predatormode, int wolfcount, boolean competitive )
	{
		if (predatormode == false)
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
				List<Integer> anidata = im.calculate(animallist, ani, timeperiod, grassmode, predatormode);		
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
		if (predatormode == true)
		{
			for (int i = 0; i< timeperiod+1; i++)
			{
				categories.add("Year " + String.valueOf(i));
			}
			xAxis.setCategories(FXCollections.<String>observableArrayList(categories));
			
			bardatacollection = new ArrayList<List<Integer>>();
			Predator_Model pm = new Predator_Model();
			
			List<List<Integer>>anidata = pm.calculate(animallist, timeperiod, grassmode, im, wolfcount, competitive);
			List<Integer> predator = pm.getPredPopulation();
			anidata.add(predator);
			
			for (int i = 0; i< anidata.size(); i++)
			{
				bardatacollection.add(anidata.get(i));
			}
			
			List<Animal> newanimallist = new ArrayList<Animal>();
    		for (Animal ani: animallist)
    		{
    			if (ani.getType().equalsIgnoreCase("Primary"))
    			{
    				newanimallist.add(ani);
    			}
    		}
    		for (Animal ani: animallist)
    		{
    			if (!ani.getType().equalsIgnoreCase("Primary"))
    			{
    				newanimallist.add(ani);
    			}
    		}
    		Animal ani = new Animal("Gray Wolves", 0, 0.0, 0.0, 0.0, 0, 0, null, 0.0, null);
    		newanimallist.add(ani);
    		
			for (int i = 0; i< newanimallist.size(); i++)
			{
				XYChart.Series<String, Number> series = new XYChart.Series<>();
				series.setName(newanimallist.get(i).getName());
				for (int j = 0; j < timeperiod + 1; j++)
				{
					series.getData().add(new XYChart.Data<>(categories.get(j),bardatacollection.get(i).get(j)));
				}
				stackedbarchart.getData().add(series);			
			}
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
