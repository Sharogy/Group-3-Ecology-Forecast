package view;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import application.Main;
import interfaces.icontroller;
import interfaces.imodel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML; 
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import math.Exponential_Model;
import math.Predator_Model;
import model.Animal;

/**
 * The controller for the birthday statistics view.
 * 
 * @author Marco Jakob
 */
public class lineviewlayoutcontroller implements icontroller {

    @FXML
    public LineChart<String, Integer> linechart;

    @FXML
    private CategoryAxis xAxis;
    
    @FXML
    private NumberAxis yAxis;

    
    public  XYChart.Series series;
    public  String test = "test string";

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    public lineviewlayoutcontroller()
    {
    	
    }

    @FXML
    private void initialize() {

        xAxis.setLabel("Time in Years");
        yAxis.setLabel("Total number of animals");
        xAxis.setAnimated(false);
    }
    
    public void cleardata()
    {
    	if (series != null)
    	{
    		series.getData().clear();
    		linechart.getData().clear();
    	}    	
    }
    
    
    public void spawndata(ObservableList<Animal> animallist, int timeperiod, imodel im, boolean grassmode, boolean predatormode, int packcount, boolean competitive)
    {
    	List<Integer> anidata = null;
    	if (predatormode == false)
    	{
    		for (int j = 0; j < animallist.size(); j++)
        	{
       			anidata = im.calculate(animallist, animallist.get(j), timeperiod, grassmode, predatormode);
           	
            	series = new Series<String, Number>();
                series.setName(animallist.get(j).getName());
             	for (int i = 0; i < anidata.size(); i++)
             	{
             		String year = "Year " + Integer.toString(i);
             		series.getData().add(new XYChart.Data(year, anidata.get(i)));
             		//System.out.println(anidata.get(i));
             	}         	
             	linechart.getData().add(series);        	
        	}
    	}
    	if (predatormode == true)
    	{
    		Predator_Model pm = new Predator_Model();
    		List<List<Integer>> anidata2 = pm.calculate(animallist, timeperiod, grassmode, im, packcount, competitive);
    		//System.out.println(anidata2.get(0));
    		List<Animal> newanimallist = new ArrayList<Animal>();
    		List<Integer> predator = pm.getPredPopulation();   
    		anidata2.add(predator);
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
    		
    		for (int j = 0; j < anidata2.size(); j++)
        	{        
    			if (j<anidata2.size()-1)
    			{
    				series = new Series<String, Number>();
                    series.setName(newanimallist.get(j).getName());
                 	for (int i = 0; i < anidata2.get(j).size(); i++)
                 	{
                 		String year = "Year " + Integer.toString(i);
                 		series.getData().add(new XYChart.Data(year, anidata2.get(j).get(i)));
                 		//System.out.println(anidata2.get(j).get(i));
                 	}         	
                 	linechart.getData().add(series);     
    			}
    			else
    			{
    				series = new Series<String, Number>();
                    series.setName("Gray Wolves");
                 	for (int i = 0; i < anidata2.get(j).size(); i++)
                 	{
                 		String year = "Year " + Integer.toString(i);
                 		series.getData().add(new XYChart.Data(year, anidata2.get(j).get(i)));
                 		//System.out.println(anidata2.get(j).get(i));
                 	}         	
                 	linechart.getData().add(series);     
    			}
        	} 
    	} 	     
    }
    /**
     * Sets the persons to show the statistics for.
     * 
     * @param persons
     */
    public void setAnimalData(List<Animal> animal) {

    }
    
    
}