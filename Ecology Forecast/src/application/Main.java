package application;
	
import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import org.graalvm.compiler.phases.common.NodeCounterPhase.Stage;

import Util.AlertBox;
import Util.Dataminer;
import Util.Datawriter;
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
import model.Animal;
import model.AnimalFactory;
import view.animaleditcontroller;
import view.deletelayoutcontroller;
import view.rootcontroller;
<<<<<<< HEAD
=======
import Util.AlertBox;
import Util.Dataminer;
import Util.Datawriter;
import Util.Settings;

import model.Animal;
import model.AnimalFactory;
import java.util.Iterator;
import java.util.List;
import java.util.prefs.Preferences;
>>>>>>> branch 'master' of https://github.com/Sharogy/Group-3-Ecology-Forecast

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
}