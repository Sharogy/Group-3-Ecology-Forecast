package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import model.Results;

public class statviewlayoutcontroller {
	
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
    
    public void spawn()
    {
    	//spawn columns
    	
    	columns = new ArrayList<String>();
    	columns.add("Animals");
    	columns.add("Starting Pop");
    	columns.add("year 1 pop");
    	columns.add("year 2 pop");
    	columns.add("year 3 pop");
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
    	
    	List<String> one = Arrays.asList("cow","500","520","530","550");
    	List<String> two = Arrays.asList("cattle","1000","800","600","500");
    	List<String> three = Arrays.asList("horse","600","700","800","900");
//    	Results r1 = new Results("cow", 490, one);
//    	Results r2 = new Results("deer", 1500, two);
//    	Results r3 = new Results("horse", 400, three);
    	    	    	
    	ObservableList<ObservableList> popdata = FXCollections.observableArrayList();
    	
    
    	ObservableList<String> row = FXCollections.observableArrayList();
    	ObservableList<String> row2 = FXCollections.observableArrayList();
    	ObservableList<String> row3 = FXCollections.observableArrayList();
    	for(int j = 0; j<5; j++) 
		{
			row.add(one.get(j));
			row2.add(two.get(j));
			row3.add(three.get(j));
			
		}
	    popdata.add(row); 
	    popdata.add(row2);
	    popdata.add(row3);
	    
	    stattable.setItems(popdata);
	    //Item item = new Item("test", 1);
	    
	    //stattable.getItems().add(row);
	    //stattable.getItems().add(row2);
	   	
    }
        
    public void clear()
    {
    	stattable.getColumns().clear();
    }
}
