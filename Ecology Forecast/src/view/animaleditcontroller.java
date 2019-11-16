package view;

import Util.AlertBox;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Animal;

/**
 * Dialog to edit details of a person.
 * 
 * @author Marco Jakob
 */
public class animaleditcontroller {

    @FXML
    private TextField nameField;
    @FXML
    private TextField totalField;
    @FXML
    private TextField growthField;
    @FXML
    private TextField deathField;
    @FXML
    private TextField consumptionField;

    private Stage dialogStage;
    private Animal animal;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the person to be edited in the dialog.
     * 
     * @param person
     */
    public void setAnimal(Animal animal) {
        this.animal = animal;
        
        nameField.setText(animal.getName());
        totalField.setText(String.valueOf(animal.getNumber()));
        growthField.setText(String.valueOf(animal.getGrowthrate()));
        deathField.setText(String.valueOf(animal.getDeathrate()));
        consumptionField.setText(String.valueOf(animal.getConsumptionrate()));
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleConfirm() {
        if (isInputValid()) {
        	if (!rootcontroller.addoredit)
        	{
        		Boolean answer = AlertBox.display("Editing Animals", "Are you sure you want to edit?");
        		if (answer)
        		{
        			animal.setName(nameField.getText());
        			animal.setNumber(Integer.valueOf(totalField.getText()));
        			animal.setGrowthrate(Double.valueOf(growthField.getText()));
        			animal.setDeathrate(Double.valueOf(deathField.getText()));
        			animal.setConsumptionrate(Double.valueOf(consumptionField.getText()));
        			
        			okClicked = true;
        			dialogStage.close();
        		}   
        	}
        	else if (rootcontroller.addoredit)
        	{
        		animal.setName(nameField.getText());
                animal.setNumber(Integer.valueOf(totalField.getText()));
                animal.setGrowthrate(Double.valueOf(growthField.getText()));
                animal.setDeathrate(Double.valueOf(deathField.getText()));
                animal.setConsumptionrate(Double.valueOf(consumptionField.getText()));
                    
                okClicked = true;
                dialogStage.close();
        	}
       	}
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (nameField.getText() == null || nameField.getText().length() == 0) {
            errorMessage += "No valid name!\n"; 
        }
        if (totalField.getText() == null || totalField.getText().length() == 0) {
            errorMessage += "No valid total!\n"; 
        } else {
            try {
                Integer.parseInt(totalField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid total (must be an Integer)!\n"; 
            }
        }
        if (growthField.getText() == null || growthField.getText().length() == 0) {
            errorMessage += "No valid growthrate!\n"; 
        } else {
            try {
                Double.parseDouble(growthField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid growthrate (must be a double)!\n"; 
            }
        }
        if (deathField.getText() == null || deathField.getText().length() == 0) {
            errorMessage += "No valid deathrate!\n"; 
        } else {
            try {
                Double.parseDouble(deathField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid deathrate (must be a double)!\n"; 
            }
        }
        if (consumptionField.getText() == null || consumptionField.getText().length() == 0) {
            errorMessage += "No valid consumptionrate!\n"; 
        } else {
            try {
                Double.parseDouble(consumptionField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid consumptionrate (must be a double)!\n"; 
            }
        }

        
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            
            alert.showAndWait();
            
            return false;
        }
    }
}