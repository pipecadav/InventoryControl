/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorycontrol;

import inventorycontrol.model.Inventory;
import inventorycontrol.model.InventoryListWrapper;
import inventorycontrol.view.InventoryEditDialogueController;
import inventorycontrol.view.InventoryOverviewController;
import inventorycontrol.view.RootLayoutController;
import java.awt.Desktop;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.prefs.Preferences;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author cadav
 */
public class InventoryControl extends Application{

    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Inventory> inventoryData = FXCollections.observableArrayList();
    
    /**
     * Constructor
     */
    public InventoryControl() {
        
    }

    public ObservableList<Inventory> getInventoryData() {
        return inventoryData;
    }
    
    @Override
    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("InventoryControl");
        this.primaryStage.getIcons().add(new Image("file:src/inventorycontrol/view/resources/img/App_icon.png"));
        initRootLayout();
        
        showInventoryOverview();
        
    }
    
    public void initRootLayout(){
        try{
            // Loads Root Layout
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(InventoryControl.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            //Shows scene with Root Layout
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            
            //Gives the controller Access to the Inventory Control
            RootLayoutController controller = loader.getController();
            controller.setInventoryControl(this);
            
            primaryStage.show();    
        }catch(IOException e){
            e.printStackTrace();
        }
        
        // Try to load last opened file
        File file = getInventoryFilePath();
        if (file != null){
            loadInventoryDataFromFile(file);
        }
    }
    
    public void showInventoryOverview(){
        try{
            //Loads Inventory overview
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(InventoryControl.class.getResource("view/InventoryOverview.fxml"));
            AnchorPane inventoryOverview = (AnchorPane) loader.load();
            
            //Sets Inventory overview into the center of Root Layout
            rootLayout.setCenter(inventoryOverview);
            
            // Gives the controller access to the Inventory Controller.
            InventoryOverviewController controller = loader.getController();
            controller.setInventoryControl(this);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public boolean showInventoryEditDialogue(Inventory inventory){
        try{
            //Load the fxml file and create a new stage for the popup dialog
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(InventoryControl.class.getResource("view/InventoryEditDialogue.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            
            //Creates Dialogue Stage
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add/Edit Entry");
            dialogStage.getIcons().add(new Image("file:src/inventorycontrol/view/resources/img/App_icon.png"));
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            
            //Sets the Entry into the controller.
            InventoryEditDialogueController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setInventory(inventory);
            
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            
            return controller.isOkClicked();
            
        }catch(IOException e){
            e.printStackTrace();
            return false;
        }
    }

    
    /**
     * Returns the Inventory file preference, i.e. the file that was last opened.
     * The preference is read from the OS specific registry. If no such
     * preference can be found, null is returned.
     * @return 
     */
    public File getInventoryFilePath(){
        Preferences prefs = Preferences.userNodeForPackage(InventoryControl.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null){
            return new File(filePath);
        }else{
            return null;
        }
        
    }
    
    /**
     * Sets the file path of the currently loaded file. The path is persisted in
     * the OS specific registry.
     * @param file the file or null to remove the path
     */
    public void setInventoryFilePath(File file){
        Preferences prefs = Preferences.userNodeForPackage(InventoryControl.class);
        if (file != null){
            prefs.put("filePath", file.getPath());
            
            // Update the stage title
             primaryStage.setTitle("InventoryControl - " + file.getName());
             
        }else{
            prefs.remove("filePath");
            
            // Update the stage title.
            primaryStage.setTitle("InventoryControl");
        }
    }
    
    /**
     * Loads inventory data from the specified file. The current inventory data will
     * be replaced.
     * @param file 
     */
    public void loadInventoryDataFromFile(File file){
        try{
            JAXBContext context = JAXBContext.newInstance(InventoryListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();
            
            // Reading XML from the file and unmarshalling.
            InventoryListWrapper wrapper = (InventoryListWrapper) um.unmarshal(file);
            
            inventoryData.clear();
            inventoryData.addAll(wrapper.getEntries());
            
            // Save the file path to the registry.
            setInventoryFilePath(file);            
            
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load data");
            alert.setContentText("Could not load data from file:\n" + file.getPath());
            
            alert.showAndWait();
        }
    }
    
    /**
     * Saves the current Inventory data to the specified file
     * @param file 
     */
    public void saveInventoryDataToFile(File file){
        try{
            JAXBContext context = JAXBContext.newInstance(InventoryListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            
             // Wrapping the inventory data
             InventoryListWrapper wrapper = new InventoryListWrapper();
             wrapper.setEntries(inventoryData);
             
             // Marshalling and saving XML to the file.
            m.marshal(wrapper, file);
            
            // Save the file path to the registry.
            setInventoryFilePath(file);
            
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save data");
            alert.setContentText("Could not save data to file:\n" + file.getPath());

            alert.showAndWait();
        }
        
    }
    
    /**
     * Loads the About page.
     */
    public void showAbout(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(InventoryControl.class.getResource("view/About.fxml"));
            AnchorPane localAnchorPane = (AnchorPane) loader.load();
            Stage localStage = new Stage();
            localStage.setTitle("About");
            localStage.getIcons().add(new Image("file:src/inventorycontrol/view/resources/img/App_icon.png"));
            localStage.initModality(Modality.WINDOW_MODAL);
            localStage.initOwner(this.primaryStage);
            Scene localScene = new Scene(localAnchorPane);
            localStage.setScene(localScene);
            localStage.show();
        }catch(IOException localIOException){
            localIOException.printStackTrace();
        }
    }
    
    public void showManual() throws URISyntaxException, IOException{
        Desktop.getDesktop().browse(new URI("https://drive.google.com/open?id=15NoZ3SUV4nUDEEd7Wf_QqeETFkwQGNSE"));
    }
    
    /**
     * Returns the main stage
     * @return 
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
