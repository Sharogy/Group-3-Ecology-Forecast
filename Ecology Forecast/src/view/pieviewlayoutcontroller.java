package view;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import math.imodel;
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
	
	public void spawndata(ObservableList<Animal> animallist, int timeperiod, imodel im) 
	{
		this.animallist = animallist;
		this.timeperiod = timeperiod;
		piedatacollection = new ArrayList<List<Integer>>();
		for (int i = 0; i< animallist.size(); i++)
		{
			Animal ani = animallist.get(i);
			List<Integer> anidata = im.calculate(ani, timeperiod);		
			piedatacollection.add(anidata);
		}
		spawncombobox();
		for (int i = 0; i< timeperiod+1; i++)
		{
			popoptions.add("Year " + String.valueOf(i) + " " + "Population");
		}
		
		popbox1.setItems(popoptions);
		popbox2.setItems(popoptions);
		
		pieChartData.clear();
		pieChartData2.clear();
		
		pieChartData.addAll(generatepiedata(animallist, 0, piedatacollection));
		pieChartData2.addAll(generatepiedata(animallist, 1, piedatacollection));
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
