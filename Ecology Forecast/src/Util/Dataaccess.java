package Util;

import java.io.File;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Animal;
import model.AnimalFactory;


public class Dataaccess {
	
	private ObservableList<Animal> animallist;
	private AnimalFactory af = AnimalFactory.getInstance();
	
	public Dataaccess() {
	}
	
	public ObservableList<Animal> loadAnimalPreset(String path)
    {   	
    	Dataminer.setpath(path); 
    	setAnimalFilePath(null);
    	return(af.getAnimals());
    }
	
	public ObservableList<Animal> loadAnimalDataFromFile(File file) {
        try {
            Dataminer.setpath(file.getAbsolutePath());        
            //setAnimalFilePath(file);
            
            
        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load data");
            alert.setContentText("Could not load data from file:\n" + file.getPath());

            alert.showAndWait();
        	e.printStackTrace();
        }
		return af.getAnimals();
    }
	
	public void saveAnimalDataToFile(File file, ObservableList<Animal> animallist) {
    	
    	try {
    		
    		Datawriter.setpath(file.getAbsolutePath());    	
    		Datawriter.writeData(animallist);
        
    		// Save the file path to the registry.
    		//setAnimalFilePath(file);
                     
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
            return new File("C:");
        }
    }
    
    // sets the file path of the current file.
    public void setAnimalFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());
        } else {
            prefs.remove("filePath");
        }
    }
	
}
