package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import view.animaleditcontroller;
import view.deletelayoutcontroller;
import view.rootcontroller;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import Util.AlertBox;
import Util.Dataminer;
import Util.Datawriter;
import Util.Settings;

import model.Animal;
import model.AnimalFactory;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.prefs.Preferences;

//Initial Stage setup
public class Main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    //private boolean running;
    private rootcontroller controller;
    private ObservableList<Animal> animallist = FXCollections.observableArrayList();

    public Main()
    {   	
    }
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Animal Population Forecast");

        initRootLayout();
        this.primaryStage.setOnCloseRequest(e -> {
        	e.consume();
        	safeExit();
        });
               
    }
    
    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/rootlayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        File file = getAnimalFilePath();
    }
    
    public void showDeleteAnimals()
    {
    	try
    	{
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(getClass().getResource("/view/deletelayout.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            
            Stage deletestage = new Stage();
            deletestage.setTitle("Deleting Animals");
            deletestage.initModality(Modality.WINDOW_MODAL);
            deletestage.initOwner(primaryStage);
            
            Scene scene = new Scene(page);
            deletestage.setScene(scene);
            
            deletelayoutcontroller controller = loader.getController();
            controller.setDeletestage(deletestage);
            controller.deleteAnimal();
            controller.setMainApp(this);
            
            deletestage.showAndWait();
    		
    	} catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public boolean showEditAnimals(Animal animal)
    {
    	try
    	{
    		FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/animaleditlayout.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage editStage = new Stage();
            editStage.setTitle("Edit Animal");
            editStage.initModality(Modality.WINDOW_MODAL);
            editStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            editStage.setScene(scene);

            // Set the person into the controller.
            animaleditcontroller controller = loader.getController();
            controller.setDialogStage(editStage);
            controller.setAnimal(animal);

            // Show the dialog and wait until the user closes it
            editStage.showAndWait();

            return controller.isOkClicked();
    	}
    	catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    

    public static void main(String[] args) {
        launch(args);
    }
    
    public void safeExit()
    {
    	Boolean answer = AlertBox.display("Exiting Program", "Are you sure you want to exit?");
    	if (answer)
    	{
        	//running = false;
        	this.primaryStage.close();
        	System.exit(0); 		
    	}
    }
    
    public rootcontroller getRoot()
    {
    	return controller;
    }
    
    public ObservableList<Animal> getAnimals()
    {
    	return animallist;
    }
    
    
    public void loadAnimalDataFromFile(File file) {
        try {
            Dataminer.setpath(file.getAbsolutePath());
            AnimalFactory af = AnimalFactory.getInstance();
            animallist.addAll(af.getAnimals());
            
            setAnimalFilePath(file);
            
        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load data");
            alert.setContentText("Could not load data from file:\n" + file.getPath());

            alert.showAndWait();
        	e.printStackTrace();
        }
    }

    /**
     * Saves the current person data to the specified file.
     * 
     * @param file
     */
    public void saveAnimalDataToFile(File file) {
    	
    	try {
    		
    		Datawriter.setpath(file.getAbsolutePath());
    	
    		Datawriter.writeData(animallist);
        
    		// Save the file path to the registry.
    		setAnimalFilePath(file);
            
            
        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save data");
            alert.setContentText("Could not save data to file:\n" + file.getPath());

            alert.showAndWait();
        }
    }
    
    //returns file preference, aka where the file was last opened
    public File getAnimalFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }
    
    // sets the file path of the current file.
    public void setAnimalFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

            // Update the stage title.
            primaryStage.setTitle("Animal Population Forecast - " + file.getName());
        } else {
            prefs.remove("filePath");

            // Update the stage title.
            primaryStage.setTitle("Animal Population Forecast");
        }
    }
}