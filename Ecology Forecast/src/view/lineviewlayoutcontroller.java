package view;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import model.Animal;

/**
 * The controller for the birthday statistics view.
 * 
 * @author Marco Jakob
 */
public class lineviewlayoutcontroller {

    @FXML
    public LineChart<String, Integer> linechart;

    @FXML
    private CategoryAxis xAxis;
    
    @FXML
    private NumberAxis yAxis;

    
    public static XYChart.Series series;
    public static String test = "test string";

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    public lineviewlayoutcontroller()
    {
    	
    }

    @FXML
    private void initialize() {
        // Get the animals 
//        String[] animalnames;
//        ObservableList<Animal> animals = main.getAnimals();
//        Iterator<Animal> iter = animals.iterator();
//        while (iter.hasNext())
//        {
//        	Names.add(iter.next().getName());
//        }
//                
//        // Assign the total as categories for the vertical axis.
//        // Assign the years as categories for the horizontal axis.
        String[] years = {"2019","2020","2021","2022","2023"};
//        xAxis.setCategories((ObservableList<String>) Arrays.asList(years));
        String[] total = {"0","50","100","150","200","250","300"};
        xAxis.setLabel("Time in Years");
        yAxis.setLabel("Total number of animals");
        series = new Series<String, Number>();
        series.setName("Population Forecast");
        
        
        linechart.getData().add(series);
        //linechart.getData().clear();
        
            
        //linechart.getData().add(series);
        
    }
    
    public static void cleardata()
    {
    	series.getData().clear();
    	
    }
    
    
    public static void spawndata()
    {
    	series.getData().add(new XYChart.Data("1", 23));
        series.getData().add(new XYChart.Data("2", 14));
        series.getData().add(new XYChart.Data("3", 15));
        series.getData().add(new XYChart.Data("4", 24));
        series.getData().add(new XYChart.Data("5", 34));
        series.getData().add(new XYChart.Data("6", 36));
        series.getData().add(new XYChart.Data("7", 22));
        series.getData().add(new XYChart.Data("8", 45));
        series.getData().add(new XYChart.Data("9", 43));
        series.getData().add(new XYChart.Data("10", 17));
        series.getData().add(new XYChart.Data("11", 29));
        series.getData().add(new XYChart.Data("12", 25));
    }
    /**
     * Sets the persons to show the statistics for.
     * 
     * @param persons
     */
    public void setAnimalData(List<Animal> animal) {
        // Count the number of people having their birthday in a specific month.
//        int[] monthCounter = new int[12];
//        for (Person p : persons) {
//            int month = p.getBirthday().getMonthValue() - 1;
//            monthCounter[month]++;
//        }
//        
//        
//
//        XYChart.Series<String, Integer> series = new XYChart.Series<>();
//        
//        // Create a XYChart.Data object for each month. Add it to the series.
//        for (int i = 0; i < monthCounter.length; i++) {
//            series.getData().add(new XYChart.Data<>(monthNames.get(i), monthCounter[i]));
//        }
//        
//        barChart.getData().add(series);
    }
    
    
}