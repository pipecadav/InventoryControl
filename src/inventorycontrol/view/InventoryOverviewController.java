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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author cadav
 */
public class InventoryOverviewController {
    
    @FXML
    private TableView<Inventory> inventoryTable;
    @FXML
    private TableColumn<Inventory, String> idColumn;
    @FXML
    private TableColumn<Inventory, String> firstNameColumn;
    @FXML
    private TableColumn<Inventory, String> lastNameColumn;
    @FXML
    private Label idLabel;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label jobTitleLabel;
    @FXML
    private Label departmentLabel;
    @FXML
    private Label computerLabel;
    @FXML
    private Label brandLabel;
    @FXML
    private Label serialLabel;
    @FXML
    private Label productNoLabel;
    @FXML
    private Label keyboardNoLabel;
    @FXML
    private Label mouseNoLabel;
    @FXML
    private Label screenNoLabel;
    @FXML
    private Label headsetNoLabel;
    @FXML
    private Label telNoLabel;
    @FXML
    private Label otherLabel;
    //Reference to the Invontory Controll Main App
    private InventoryControl inventoryControl;

    /**
     * Constructor
     */
    public InventoryOverviewController() {
    }
    
    /**
     * Initializes the Controller Class. This method is automatically called
     * when the FXML file is loaded
     */
    @FXML
    private void initialize (){
        // Initialize the Inventory table with the three columns
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idNumberProperty());
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        
        //Clears Details
        showInventoryDetails(null);
        
        // Listens to selection changes and shows the Inventory details when changed.
        inventoryTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showInventoryDetails(newValue));
    }
    
    /**
     * Fills all text fields to show details about the person. 
     * If the specified Entry is null, all text fields are cleared.
     * @param inventory 
     */
    private void showInventoryDetails(Inventory inventory){
        if(inventory != null){
            //Fill the labels with info from the Inventory object.
            idLabel.setText(inventory.getIdNumber());
            firstNameLabel.setText(inventory.getFirstName());
            lastNameLabel.setText(inventory.getLastName());
            jobTitleLabel.setText(inventory.getJobTitle());
            departmentLabel.setText(inventory.getDepartment());
            computerLabel.setText(inventory.getComputer());
            brandLabel.setText(inventory.getBrand());
            serialLabel.setText(inventory.getSerial());
            productNoLabel.setText(inventory.getProductNo());
            keyboardNoLabel.setText(inventory.getKeyboardNo());
            mouseNoLabel.setText(inventory.getMouseNo());
            screenNoLabel.setText(inventory.getScreenNo());
            headsetNoLabel.setText(inventory.getHeadsetNo());
            telNoLabel.setText(inventory.getTelNo());
            otherLabel.setText(inventory.getotherDevice());
        }else{
            //If Inventory data is Null, removes all the text
            idLabel.setText("");
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            jobTitleLabel.setText("");
            departmentLabel.setText("");
            computerLabel.setText("");
            brandLabel.setText("");
            serialLabel.setText("");
            productNoLabel.setText("");
            keyboardNoLabel.setText("");
            mouseNoLabel.setText("");
            screenNoLabel.setText("");
            headsetNoLabel.setText("");
            telNoLabel.setText("");
            otherLabel.setText("");
        }   
    }

    /**
     * Deletes a selected entry from the table View
     */
    @FXML
    private void deleteEntry(){
        int selectedIndex = inventoryTable.getSelectionModel().getSelectedIndex();
        if(selectedIndex >= 0){
            inventoryTable.getItems().remove(selectedIndex);
        }else{
            //Nothing Selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(inventoryControl.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Entry Selected");
            alert.setContentText("Please select an entry in the table.");

            alert.showAndWait();
        }
    }
    
    /**
     * Add a new Entry to the inventory
     */
    @FXML
    private void addNewEntry(){
        Inventory newEntry = new Inventory();
        boolean okClicked = inventoryControl.showInventoryEditDialogue(newEntry);
        if(okClicked){
            inventoryControl.getInventoryData().add(newEntry);
        }
    }
    
    @FXML
    private void editEntry(){
        Inventory selectedEntry = inventoryTable.getSelectionModel().getSelectedItem();
        if(selectedEntry != null){
            boolean okClicked = inventoryControl.showInventoryEditDialogue(selectedEntry);
            if(okClicked){
                showInventoryDetails(selectedEntry);
            }
        }else{
            // Nothing selected.
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.initOwner(inventoryControl.getPrimaryStage());
           alert.setTitle("No Selection");
           alert.setHeaderText("No Entry Selected");
           alert.setContentText("Please select an Entry in the table.");

           alert.showAndWait();
        }
    }
    
    
    public void setInventoryControl(InventoryControl inventoryControl) {
        this.inventoryControl = inventoryControl;
        
        // Add observable list data to the table
        inventoryTable.setItems(inventoryControl.getInventoryData());
    }
    
    
    
    
}
