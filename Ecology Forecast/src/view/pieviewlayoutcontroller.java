package view;

import java.util.ArrayList;
import java.util.List;

import interfaces.icontroller;
import interfaces.imodel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.ComboBox;
import math.Predator_Model;
import model.Animal;

public class pieviewlayoutcontroller implements icontroller {
	
	@FXML
	private PieChart piechart1;	
	@FXML
	private PieChart piechart2;
	
	public ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
	
	public ObservableList<PieChart.Data> pieChartData2 = FXCollections.observableArrayList();
	
	@FXML
	private ComboBox popbox1;
	@FXML
	private ComboBox popbox2;
	
	private ObservableList<String> popoptions = FXCollections.observableArrayList();
    
    private String selectedpop1 = "Year 0 Population";
    private String selectedpop2 = "Year 1 Population";
    
    private ObservableList<Animal> animallist;
    private List<List<Integer>> piedatacollection; 
    private int timeperiod;
		
	
	public pieviewlayoutcontroller()
    {
    	
    }
	
	@FXML
	public void initialize()
	{
		piechart1.setData(pieChartData);
		piechart2.setData(pieChartData2);	
	}
	
	public void spawndata(ObservableList<Animal> animallist, int timeperiod, imodel im, boolean grassmode, boolean predatormode, int packcount, boolean competitive) 
	{
		this.animallist = animallist;
		this.timeperiod = timeperiod;
		piedatacollection = new ArrayList<List<Integer>>();
		
    	if (predatormode == false)
    	{
    		for (int i = 0; i< animallist.size(); i++)
    		{
    			Animal ani = animallist.get(i);
    			List<Integer> anidata = im.calculate(animallist, ani, timeperiod, grassmode, predatormode);		
    			piedatacollection.add(anidata);
    		}
    		spawncombobox(timeperiod);
    		for (int i = 0; i< timeperiod+1; i++)
    		{
    			popoptions.add("Year " + String.valueOf(i) + " " + "Population");
    		}
    		
    		popbox1.setItems(popoptions);
    		popbox2.setItems(popoptions);
    		
    		pieChartData.clear();
    		pieChartData2.clear();
    		
    		pieChartData.addAll(generatepiedata(animallist, 0, piedatacollection));
    		pieChartData2.addAll(generatepiedata(animallist, timeperiod, piedatacollection));
    	}
    	if (predatormode == true)
    	{
    		Predator_Model pm = new Predator_Model();
    		List<List<Integer>> anidata = pm.calculate(animallist, timeperiod, grassmode, im, packcount, competitive);
    		anidata.add(pm.getPredPopulation());
    		Animal ani = new Animal("Gray Wolves", 0, 0.0, 0.0, 0.0, 0.0, 0, null, null, null);
    		ObservableList<Animal> anilist = FXCollections.observableArrayList();
    		for (int i = 0; i<animallist.size(); i++)
    		{
    			anilist.add(animallist.get(i));
    		}
    		anilist.add(ani);
    		
    		for (int i = 0; i<anidata.size(); i++)
    		{
    			piedatacollection.add(anidata.get(i));
    		}
    		
    		spawncombobox(timeperiod);
    		
    		for (int i = 0; i< timeperiod+1; i++)
    		{
    			popoptions.add("Year " + String.valueOf(i) + " " + "Population");
    		}
    		
    		popbox1.setItems(popoptions);
    		popbox2.setItems(popoptions);
    		
    		pieChartData.clear();
    		pieChartData2.clear();
    		
    		pieChartData.addAll(generatepiedata(anilist, 0, piedatacollection));
    		pieChartData2.addAll(generatepiedata(anilist, timeperiod, piedatacollection));
    	}
	}
	
	
	public void cleardata()
	{
		pieChartData.clear();
		pieChartData2.clear();
		popbox1.getItems().clear();
		popbox2.getItems().clear();
	}
	
	// Combobox event and options
    private EventHandler<ActionEvent> popevent1 = new EventHandler<ActionEvent>()
			{
				public void handle(ActionEvent e)
				{
					selectedpop1 = String.valueOf(popbox1.getValue());
					String[] split = selectedpop1.split("\\s+");
					if (split.length>1)
					{
						int time = Integer.valueOf(split[1]);
						pieChartData.clear();
						pieChartData.addAll(generatepiedata(animallist, time, piedatacollection));	
					}
			    	
				}
			};
    private EventHandler<ActionEvent> popevent2 = new EventHandler<ActionEvent>() 
    		{
    			public void handle(ActionEvent e)
    			{
    				selectedpop2 = String.valueOf(popbox2.getValue());
    				String[] split = selectedpop2.split("\\s+");
    				if (split.length>1)
    				{
    					int time = Integer.valueOf(split[1]);
        				pieChartData2.clear();
        				pieChartData2.addAll(generatepiedata(animallist, time, piedatacollection));
    				}	    	
    			}	
    		};
    
    private void spawncombobox() 
    {
    
    	popbox1.setVisibleRowCount(5);
    	popbox1.setValue("Year 0 Population");
    	popbox1.setOnAction(popevent1);
    	
    	
    	popbox2.setVisibleRowCount(5);
    	popbox2.setValue("Year 1 Population");
    	popbox2.setOnAction(popevent2);	
    }
    private void spawncombobox(int year)
    {
    	String title = "Year " +  String.valueOf(year) + " Population";
    	popbox1.setVisibleRowCount(5);
    	popbox1.setValue("Year 0 Population");
    	popbox1.setOnAction(popevent1);
    	
    	
    	popbox2.setVisibleRowCount(5);
    	popbox2.setValue(title);
    	popbox2.setOnAction(popevent2);	
    }
    
    private ObservableList<PieChart.Data> generatepiedata(ObservableList<Animal> animallist, int year, List<List<Integer>> piedatacollection)
    {
    	ObservableList<PieChart.Data> pie = FXCollections.observableArrayList();    	
    	for (int i = 0; i < animallist.size(); i++)
    	{
    		pie.add(new PieChart.Data(String.valueOf(piedatacollection.get(i).get(year)) + " " + animallist.get(i).getName(), piedatacollection.get(i).get(year)));
    	}
    	return pie;
    }

}
