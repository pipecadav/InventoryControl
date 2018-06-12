/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorycontrol.view;

import inventorycontrol.InventoryControl;
import java.io.File;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;

/**
 * The controller for the root layout. The root layout provides the basic
 * application layout containing a menu bar and space where other JavaFX
 * elements can be placed.
 * 
 * @author cadav
 */
public class RootLayoutController {
    
    //Reference to the Inventory Control Class
    private InventoryControl inventoryControl;
    
    /**
     * Is called by the main application to give a reference back to itself.
     * @param inventoryControl 
     */
    public void setInventoryControl (InventoryControl inventoryControl){
        this.inventoryControl = inventoryControl;
        
    }
    
    /**
     * Creates an empty Inventory Log.
     */
    @FXML
    private void handleNew(){
        inventoryControl.getInventoryData().clear();
        inventoryControl.setInventoryFilePath(null);
    }
    
    /**
     * Opens a FileChooser to let the user select an Inventory Database to load.
     */
    @FXML
    private void handleOpen(){
        FileChooser fileChooser = new FileChooser();

        //Sets extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        //Shows open file dialog
        File file = fileChooser.showOpenDialog(inventoryControl.getPrimaryStage());
        if (file != null) {
            inventoryControl.loadInventoryDataFromFile(file);
        }        
    }
    
    /**
     * Saves the file to the Inventory file that is currently open. If there is no
     * open file, the "save as" dialog is shown.
     */
    @FXML 
    private void handleSave(){
        File personFile = inventoryControl.getInventoryFilePath();
        if (personFile != null) {
            inventoryControl.saveInventoryDataToFile(personFile);
        } else {
            handleSaveAs();
        } 
    }
    
    /**
     * Opens a FileChooser to let the user select a file to save to
     */
    @FXML
    private void handleSaveAs(){
        FileChooser fileChooser = new FileChooser();
        
        //Sets extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        
        //Shows saved file dialog
        File file = fileChooser.showSaveDialog(inventoryControl.getPrimaryStage());
        if (file != null) {
            // Make sure it has the correct extension
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            inventoryControl.saveInventoryDataToFile(file);
        }
    }
    
    /**
     * Opens an about dialog
     */
    @FXML
    private void handleAbout(){
        inventoryControl.showAbout();
    }
    
    /**
     * Exits Application
     */
    @FXML
    private void handleExit(){
        System.exit(0);
    }
}
