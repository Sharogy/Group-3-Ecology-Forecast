package view;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;

import interfaces.icontroller;
import interfaces.imodel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import math.Predator_Model;
import model.Animal;
import model.Predator;
import model.Results;


public class statviewlayoutcontroller implements icontroller{
	
	
	@FXML
	TableView<ObservableList> stattable;
	@FXML
	TableView<ObservableList> stattable2;
	
	public TableColumn [] tableColumns;
	public TableColumn [] tableColumns2;
	private List<String> columns;
	private List<String> columns2;
	
	private List<List<Integer>> result;
	private List<Integer> preddata;
	
    @FXML
    private Label modellabel;
    @FXML
    private Label grasslabel;
    @FXML
    private Label predatorlabel;

	/* Fills 'columns' and 'data' */
	//parseCSV("C:/list.csv");
	
	public statviewlayoutcontroller()
    {    	
    }

    @FXML
    private void initialize() 
    {  	
    }
    
    public void spawndata(ObservableList<Animal> animallist, int timeperiod, imodel im, boolean grassmode, boolean predatormode, int wolfcount, boolean competitive)
    {
    	//Table 1
    	
    	columns = new ArrayList<String>();
    	columns.add("Animals");
    	columns.add("Starting Pop");
    	
    	modellabel.setText(im.getClass().getSimpleName());
    	if (grassmode)
    	{
    		grasslabel.setText("Yes");
    	}
    	else
    	{
    		grasslabel.setText("No");
    	}
    	if (predatormode)
    	{
    		predatorlabel.setText("Yes");
    	}
    	else 
    	{
    		predatorlabel.setText("No");
    	}
    	
    	
    	for (int i = 1; i<timeperiod+1; i++)
    	{
    		columns.add("Year " + String.valueOf(i) + " Pop");
    		
    	}
    	int columnIndex = 0;
		tableColumns = new TableColumn[columns.size()];  
		for(int i = 0; i<columns.size(); i++) 
		{
			final int j = i;
			TableColumn name = new TableColumn(columns.get(i));
			name.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                    return new SimpleStringProperty(param.getValue().get(j).toString());
                }
            });

			tableColumns[columnIndex++] = name;
		}
    	stattable.getColumns().addAll(tableColumns);
    	
    	//spawn data
    	ObservableList<ObservableList> anipopdata = FXCollections.observableArrayList();
    	if (!predatormode)
    	{
	    	for (int i = 0; i<animallist.size(); i++)
	    	{
	    		List<String> anidata = new ArrayList();
	    		anidata.add(animallist.get(i).getName());
	    		List<Integer> result = im.calculate(animallist, animallist.get(i), timeperiod, grassmode, predatormode);
	    		for (int j = 0; j< timeperiod+1; j++)
	    		{
	    			
	    			anidata.add(String.valueOf(result.get(j)));
	    		}
	    		
	    		ObservableList<String> row = FXCollections.observableArrayList();
	    		for (int j = 0; j < anidata.size(); j++)
	    		{
	    			row.add(anidata.get(j));
	    		}
	    		anipopdata.add(row);	    		
	    	}
    	}
    	else 
    	{
    		
    		Predator_Model pm = new Predator_Model();
    		List<List<Integer>> result = pm.calculate(animallist, timeperiod, grassmode, im, wolfcount, competitive);
    		
    		for (int i = 0; i<result.size(); i++)
    		{
    			List<String> anidata = new ArrayList();
    			anidata.add(animallist.get(i).getName());
    			//System.out.println(result.get(i));
    			for (int j = 0; j<result.get(i).size(); j++)
    			{
    				anidata.add(String.valueOf(result.get(i).get(j)));
    			}
    			ObservableList<String> row = FXCollections.observableArrayList();
    			for (int k = 0; k<anidata.size(); k++)
    			{
    				row.add(anidata.get(k));
    			}
    			anipopdata.add(row);
    		}  
    		preddata = pm.getPredPopulation();
    		List<String> predresult = new ArrayList();
    		predresult.add("Grey Wolves");
    		for (int i = 0; i<preddata.size(); i++)
    		{
    			predresult.add(String.valueOf(preddata.get(i)));
    		}
    		ObservableList<String> predrow = FXCollections.observableArrayList();
			for (int k = 0; k<predresult.size(); k++)
			{
				predrow.add(predresult.get(k));
			}
    		anipopdata.add(predrow);
    	}
    	stattable.setItems(anipopdata);
    	
    	
    	//Table 2
    	columns2 = new ArrayList<String>();
    	columns2.add("Name");
    	columns2.add("Pop");
    	columns2.add("Birth");
    	columns2.add("Death");
    	columns2.add("Consumption");
    	columns2.add("AvgWeight");
    	columns2.add("CarryingCapacity");
    	columns2.add("Type");
    	columns2.add("Preylikelihood");
    	//columns2.add("Notes");
//
    	int columnIndex2 = 0;
		tableColumns2 = new TableColumn[columns2.size()];  
		for(int i = 0; i<columns2.size(); i++) 
		{
			final int k = i;
			TableColumn name = new TableColumn(columns2.get(i));
			name.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                    return new SimpleStringProperty(param.getValue().get(k).toString());
                }
            });

			tableColumns2[columnIndex2++] = name;
		}
    	stattable2.getColumns().addAll(tableColumns2);
    	
    	//spawn data
    	ObservableList<ObservableList> anipopdata2 = FXCollections.observableArrayList();
    	for (int i = 0; i<animallist.size(); i++)
    	{
    		List<String> anidata2 = new ArrayList();
    		Animal ani = animallist.get(i);
    		anidata2.add(ani.getName());
    		anidata2.add(String.valueOf(ani.getNumber()));
    		anidata2.add(String.valueOf(ani.getBirthrate()));
    		anidata2.add(String.valueOf(ani.getDeathrate()));
    		anidata2.add(String.valueOf(ani.getConsumptionrate()));
    		anidata2.add(String.valueOf(ani.getAvgweight()));
    		anidata2.add(String.valueOf(ani.getCarrying()));
    		if (ani.getType().equalsIgnoreCase("Primary")){
    			anidata2.add(ani.getType());
    		}
    		else
    		{
    			anidata2.add("null");
    		}
    		anidata2.add(String.valueOf(ani.getPreylikelihood()));
    		//anidata2.add(ani.getNotes());
    		
    		ObservableList<String> row2 = FXCollections.observableArrayList();
    		for (int j = 0; j<anidata2.size(); j++)
    		{
    			row2.add(anidata2.get(j));
    		}
    		anipopdata2.add(row2);
    	}
    	Predator pred = new Predator();
    	List<String> preddata2 = new ArrayList();
    	preddata2.add(pred.getName());
    	preddata2.add(String.valueOf(wolfcount));
		preddata2.add(String.valueOf(pred.getBirthrate()));
		preddata2.add(String.valueOf(pred.getDeathrate()));
		preddata2.add(String.valueOf(pred.getConsumptionrate()));
		preddata2.add(String.valueOf(pred.getAvgweight()));
		preddata2.add("null");
		preddata2.add("null");
		preddata2.add("null");
		ObservableList<String> row2 = FXCollections.observableArrayList();
		for (int j = 0; j<preddata2.size(); j++)
		{
			row2.add(preddata2.get(j));
		}
		anipopdata2.add(row2);
    	stattable2.setItems(anipopdata2);
    	
    	stattable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
    	stattable2.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

    }
        
    public void cleardata()
    {
    	stattable.getColumns().clear();
    	stattable2.getColumns().clear();
    }
    
    public List<List<Integer>> getData()
    {
    	return result;
    }
    
    public List<Integer> getPredData()
    {
    	return preddata;
    }
}
