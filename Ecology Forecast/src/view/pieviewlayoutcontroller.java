package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;

public class pieviewlayoutcontroller {
	
	@FXML
	private PieChart piechart1;
	
	@FXML
	private PieChart piechart2;
	
	public static ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
	
	public static ObservableList<PieChart.Data> pieChartData2 = FXCollections.observableArrayList();
		
	
	public pieviewlayoutcontroller()
    {
    	
    }
	
	@FXML
	public void initialize()
	{
		piechart1.setData(pieChartData);
		piechart2.setData(pieChartData2);	
	}
	
	public static void spawndata() 
	{
	
		pieChartData.add(new PieChart.Data("Heck Cattle", 490));
		pieChartData.add(new PieChart.Data("Red Deer", 1500));
		pieChartData.add(new PieChart.Data("Konik Horse", 400));
		pieChartData.add(new PieChart.Data("Geese", 5000));		
			
		pieChartData2.add(new PieChart.Data("Heck Cattle", 490));
		pieChartData2.add(new PieChart.Data("Red Deer", 1500));
		pieChartData2.add(new PieChart.Data("Konik Horse", 400));
		pieChartData2.add(new PieChart.Data("Geese", 5000));	
		
	}
	
	public static void cleardata()
	{
		pieChartData.clear();
		pieChartData2.clear();
	}

}
