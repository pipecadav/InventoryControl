/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorycontrol.view;

import inventorycontrol.InventoryControl;
import inventorycontrol.model.Inventory;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author cadav
 */
public class InventoryEditDialogueController {
    
    @FXML
    private TextField idField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField jobTitleField;
    @FXML
    private TextField departmentField;
    @FXML
    private TextField computerField;
    @FXML
    private TextField brandField;
    @FXML
    private TextField serialField;
    @FXML
    private TextField productNoField;
    @FXML
    private TextField keyboardNoField;
    @FXML
    private TextField mouseField;
    @FXML
    private TextField screenNoField;
    @FXML
    private TextField headsetNoField;
    @FXML
    private TextField telNoField;
    @FXML
    private TextField otherField;
    
    private Stage dialogStage;
    private Inventory inventory;
    private boolean okClicked = false;
    
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the FXML file has been loaded.
     */
    @FXML
    private void initialize(){
        
    }
    
    /**
     * Called when the user clicks OK.
     */
    @FXML
    private void handleOK(){
        if(isInputValid()){
            inventory.setIdNumber(idField.getText());
            inventory.setFirstName(firstNameField.getText());
            inventory.setLastName(lastNameField.getText());
            inventory.setJobTitle(jobTitleField.getText());
            inventory.setDepartment(departmentField.getText());
            inventory.setComputer(computerField.getText());
            inventory.setBrand(brandField.getText());
            inventory.setSerial(serialField.getText());
            inventory.setProductNo(productNoField.getText());
            inventory.setKeyboardNo(keyboardNoField.getText());
            inventory.setMouseNo(mouseField.getText());
            inventory.setScreenNo(screenNoField.getText());
            inventory.setHeadsetNo(headsetNoField.getText());
            inventory.setTelNo(telNoField.getText());
            inventory.setotherDevice(otherField.getText());
            
            okClicked = true;
            dialogStage.close();
        } 
    }
    
    /**
     * Called when the user click Cancel
     */
    @FXML
    private void handleCancel(){
        dialogStage.close();
    }
    
    /**
     * Validates the user input in the Text Fields.
     * @return 
     */    
    private boolean isInputValid(){
        String errorMessage = "";
        
        if(idField.getText() == null || idField.getText().length() == 0){
            errorMessage += "Invalid ID!\n"; 
        }
        if(firstNameField.getText() == null || firstNameField.getText().length() == 0){
            errorMessage += "Invalid First Name!\n"; 
        }
        if(lastNameField.getText() == null || lastNameField.getText().length() == 0){
            errorMessage += "Invalid Last Name!\n"; 
        }
        if(jobTitleField.getText() == null || jobTitleField.getText().length() == 0){
            errorMessage += "Invalid Job Title!\n"; 
        }
        if(departmentField.getText() == null || departmentField.getText().length() == 0){
            errorMessage += "Invalid Department!\n"; 
        }
        if(computerField.getText() == null || computerField.getText().length() == 0){
            errorMessage += "Invalid Computer Name!\n"; 
        }
        if(brandField.getText() == null || brandField.getText().length() == 0){
            errorMessage += "Invalid Brand!\n"; 
        }
        if(serialField.getText() == null || serialField.getText().length() == 0){
            errorMessage += "Invalid Serial Number!\n"; 
        }
        if(productNoField.getText() == null || productNoField.getText().length() == 0){
            errorMessage += "Invalid Product Number!\n"; 
        }
        if(keyboardNoField.getText() == null || keyboardNoField.getText().length() == 0){
            errorMessage += "Invali Keyboard Number!\n"; 
        }
        if(mouseField.getText() == null || mouseField.getText().length() == 0){
            errorMessage += "Invalid Mouse Number!\n"; 
        }
        if(screenNoField.getText() == null || screenNoField.getText().length() == 0){
            errorMessage += "Invalid Screen Number!\n"; 
        }
        if(headsetNoField.getText() == null || headsetNoField.getText().length() == 0){
            errorMessage += "Invalid Headset Number!\n"; 
        }
        if(telNoField.getText() == null || telNoField.getText().length() == 0){
            errorMessage += "Invalid Telephone Number!\n"; 
        }
        if(otherField.getText() == null || otherField.getText().length() == 0){
            errorMessage += "Invalid Device Number!\n"; 
        }
        
        if(errorMessage.length() == 0){
            return true;
        }else{
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
        
    /**
     * Sets the stage of this dialog.
     * @param dialogStage 
     */
    public void setDialogStage(Stage dialogStage){
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the Inventory Entry to be edited in the dialog
     * @param inventory 
     */
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
        
        idField.setText(inventory.getIdNumber());
        firstNameField.setText(inventory.getFirstName());
        lastNameField.setText(inventory.getLastName());
        jobTitleField.setText(inventory.getJobTitle());
        departmentField.setText(inventory.getDepartment());
        computerField.setText(inventory.getComputer());
        brandField.setText(inventory.getBrand());
        serialField.setText(inventory.getSerial());
        productNoField.setText(inventory.getProductNo());
        keyboardNoField.setText(inventory.getKeyboardNo());
        mouseField.setText(inventory.getMouseNo());
        screenNoField.setText(inventory.getScreenNo());
        headsetNoField.setText(inventory.getHeadsetNo());
        telNoField.setText(inventory.getTelNo());
        otherField.setText(inventory.getotherDevice());
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     * @return 
     */
    public boolean isOkClicked() {
        return okClicked;
    }
    
    
}
