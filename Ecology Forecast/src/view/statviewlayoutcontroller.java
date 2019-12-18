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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import model.Animal;
import model.Results;

public class statviewlayoutcontroller implements icontroller{
	
	List<String> columns;
	
	@FXML
	TableView<ObservableList> stattable;
	
	public TableColumn [] tableColumns;

	/* Fills 'columns' and 'data' */
	//parseCSV("C:/list.csv");
	
	public statviewlayoutcontroller()
    {    	
    }

    @FXML
    private void initialize() 
    {  	
    }
    
    public void spawndata(ObservableList<Animal> animallist, int timeperiod, imodel im)
    {
    	//spawn columns
    	
    	columns = new ArrayList<String>();
    	columns.add("Animals");
    	columns.add("Starting Pop");
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
    	for (int i = 0; i<animallist.size(); i++)
    	{
    		List<String> anidata = new ArrayList();
    		anidata.add(animallist.get(i).getName());
    		for (int j = 0; j< timeperiod+1; j++)
    		{
    			List<Integer> result = im.calculate(animallist, animallist.get(i), timeperiod);
    			anidata.add(String.valueOf(result.get(j)));
    		}
    		
    		ObservableList<String> row = FXCollections.observableArrayList();
    		for (int j = 0; j < anidata.size(); j++)
    		{
    			row.add(anidata.get(j));
    		}
    		anipopdata.add(row);
    		
    	}
    	stattable.setItems(anipopdata);
    }
        
    public void cleardata()
    {
    	stattable.getColumns().clear();
    }
}
