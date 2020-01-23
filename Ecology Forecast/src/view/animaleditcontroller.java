package view;

import Util.AlertBox;
import Util.Settings;
import interfaces.ianimal;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Animal;
import model.Predator;

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
    @FXML
    private TextField avgweightField;
    @FXML
    private TextField carryingField;
    @FXML
    private CheckBox typeBox; 
    @FXML
    private TextField likelihoodField;
    @FXML
    private TextField notesField;

    private Stage dialogStage;
    private boolean okClicked = false;
    private boolean selectedtype;
    private boolean predatorstatus;
    private Animal animal;
    private Predator pred;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	//typeBox.setSelected(true);
    	typeBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov,
                    Boolean old_val, Boolean new_val) {
                    //System.out.println(grassbox.isSelected());
                    selectedtype = typeBox.isSelected();
                 }
               });
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
    public void setAnimal(ianimal ianimal) {
    	if (ianimal.getName() == null ) {
    		predatorstatus = false;
    	}
    	else if (ianimal.getName().equalsIgnoreCase("Gray Wolves")) 
    	{
    		predatorstatus = true;
    	}
    	else
    	{
    		predatorstatus = false;
    	}
        
        if (predatorstatus)
        {
        	 Predator pred = (Predator) ianimal;
        	 nameField.setText(pred.getName());
        	 nameField.setEditable(false);
             totalField.setVisible(false);
             growthField.setText(String.valueOf(pred.getBirthrate()));
             deathField.setText(String.valueOf(pred.getDeathrate()));
             consumptionField.setText(String.valueOf(pred.getConsumptionrate()));
             avgweightField.setText(String.valueOf(pred.getAvgweight()));
             carryingField.setVisible(false);
             typeBox.setVisible(false);
             likelihoodField.setVisible(false);
             notesField.setVisible(false);
        }
        else
        {
	        animal = (Animal) ianimal;
	        nameField.setText(animal.getName());
	        totalField.setText(String.valueOf(animal.getNumber()));
	        growthField.setText(String.valueOf(animal.getBirthrate()));
	        deathField.setText(String.valueOf(animal.getDeathrate()));
	        consumptionField.setText(String.valueOf(animal.getConsumptionrate()));
	        avgweightField.setText(String.valueOf(animal.getAvgweight()));
	        carryingField.setText(String.valueOf(animal.getCarrying()));
	        if (animal.getType() == null)
	        {
	        	typeBox.setSelected(false);
	        }
	        else if (animal.getType().equalsIgnoreCase("Primary"))
	        {
	        	typeBox.setSelected(true);
	        }
	        else
	        {
	        	typeBox.setSelected(false);
	        }
	        likelihoodField.setText(String.valueOf(animal.getPreylikelihood()));
	        notesField.setText(animal.getNotes());
        }
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
        	if (!predatorstatus)
        	{
        		if (!rootcontroller.addoredit)
            	{
            		Boolean answer = AlertBox.display("Editing Animals", "Are you sure you want to edit?");
            		if (answer)
            		{
            			animal.setName(nameField.getText());
            			animal.setNumber(Integer.valueOf(totalField.getText()));
            			animal.setBirthrate(Double.valueOf(growthField.getText()));
            			animal.setDeathrate(Double.valueOf(deathField.getText()));
            			animal.setConsumptionrate(Double.valueOf(consumptionField.getText()));
            			animal.setAvgweight(Integer.valueOf(avgweightField.getText()));
            			animal.setCarrying(Integer.valueOf(carryingField.getText()));
            			if (selectedtype)
            			{
            				animal.setType("Primary");
            			}
            			else
            			{
            				animal.setType("");
            			}
            			animal.setPreylikelihood(Double.valueOf(likelihoodField.getText()));
            			animal.setNotes(notesField.getText());
            			
            			
            			okClicked = true;
            			dialogStage.close();
            		}   
            	}
            	else if (rootcontroller.addoredit)
            	{
            		animal.setName(nameField.getText());
        			animal.setNumber(Integer.valueOf(totalField.getText()));
        			animal.setBirthrate(Double.valueOf(growthField.getText()));
        			animal.setDeathrate(Double.valueOf(deathField.getText()));
        			animal.setConsumptionrate(Double.valueOf(consumptionField.getText()));
        			animal.setAvgweight(Integer.valueOf(avgweightField.getText()));
        			animal.setCarrying(Integer.valueOf(carryingField.getText()));
        			if (selectedtype)
        			{
        				animal.setType("Primary");
        			}
        			else
        			{
        				animal.setType("");
        			}
        			animal.setPreylikelihood(Double.valueOf(likelihoodField.getText()));
        			animal.setNotes(notesField.getText());
                        
                    okClicked = true;
                    dialogStage.close();
            	}
        	}
        	
        	else if (predatorstatus)
        	{
        		Boolean answer = AlertBox.display("Editing Animals", "Are you sure you want to edit?");
        		if (answer)
        		{
        			Settings.predatorbirth = Double.valueOf(growthField.getText());
            		Settings.predatordeath = Double.valueOf(deathField.getText());
            		Settings.predatorconsumption = Double.valueOf(consumptionField.getText());
            		Settings.predatoravgweight = Integer.valueOf(avgweightField.getText());
            		okClicked = true;
                    dialogStage.close();
        		}        		
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
        if (avgweightField.getText() == null || avgweightField.getText().length() == 0) {
            errorMessage += "No valid average weight!\n"; 
        } else {
            try {
                Integer.parseInt(avgweightField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid average weight (must be an integer)!\n"; 
            }
        }
        
        if (!predatorstatus)
        {
        	if (totalField.getText() == null || totalField.getText().length() == 0) {
                errorMessage += "No valid total!\n"; 
            } else {
                try {
                    Integer.parseInt(totalField.getText());
                } catch (NumberFormatException e) {
                    errorMessage += "No valid total (must be an Integer)!\n"; 
                }
            }
        	if (carryingField.getText() == null || carryingField.getText().length() == 0) {
                errorMessage += "No valid carrying capacity!\n"; 
            } else {
                try {
                    Double.parseDouble(carryingField.getText());
                } catch (NumberFormatException e) {
                    errorMessage += "No valid carrying capacity (must be a double)!\n"; 
                }
            }
            if (likelihoodField.getText() == null || likelihoodField.getText().length() == 0) {
                errorMessage += "No valid preylikelihood!\n"; 
            } else {
                try {
                    Double.parseDouble(likelihoodField.getText());
                } catch (NumberFormatException e) {
                    errorMessage += "No valid preylikelihood (must be a double)!\n"; 
                }
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