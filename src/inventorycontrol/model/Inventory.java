/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inventorycontrol.model;


import inventorycontrol.util.LocalDateAdapter;
import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Model class for a Inventory.
 *
 * @author Marco Jakob
 */
public class Inventory {

    private final StringProperty idNumber;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty jobTitle;
    private final StringProperty department;
    private final StringProperty computer;
    private final StringProperty brand;
    private final StringProperty serial;
    private final StringProperty productNo;
    private final StringProperty keyboardNo;
    private final StringProperty mouseNo;
    private final StringProperty screenNo;
    private final StringProperty headsetNo;
    private final StringProperty telNo;
    private final StringProperty otherDevice;
    

    /**
     * Default constructor.
     */
    public Inventory() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
    }

    public Inventory(String idNumber, String firstName, String lastName, String jobTitle, String department, String computer, String brand, String serial, String productNo, String keyboardNo, String mouseNo, String screenNo, String headsetNo, String telNo, String otherDevice) {
        this.idNumber = new SimpleStringProperty(idNumber);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.jobTitle = new SimpleStringProperty(jobTitle);
        this.department = new SimpleStringProperty(department);
        this.computer = new SimpleStringProperty(computer);
        this.brand = new SimpleStringProperty(brand);
        this.serial = new SimpleStringProperty(serial);
        this.productNo = new SimpleStringProperty(productNo);
        this.keyboardNo = new SimpleStringProperty(keyboardNo);
        this.mouseNo = new SimpleStringProperty(mouseNo);
        this.screenNo = new SimpleStringProperty(screenNo);
        this.headsetNo = new SimpleStringProperty(headsetNo);
        this.telNo = new SimpleStringProperty(telNo);
        this.otherDevice = new SimpleStringProperty(otherDevice);
    }
    
    public String getIdNumber() {
        return idNumber.get();
    }

    public void setIdNumber(String idNumber) {
        this.idNumber.set(idNumber);
    }

    public StringProperty idNumberProperty() {
        return idNumber;
    }
    
    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public String getJobTitle() {
        return jobTitle.get();
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle.set(jobTitle);
    }

    public StringProperty jobTitleProperty() {
        return jobTitle;
    }
    
    public String getDepartment() {
        return department.get();
    }

    public void setDepartment(String department) {
        this.department.set(department);
    }

    public StringProperty departmentProperty() {
        return department;
    }

    public String getComputer() {
        return computer.get();
    }

    public void setComputer(String computer) {
        this.computer.set(computer);
    }

    public StringProperty computerProperty() {
        return computer;
    }

    public String getBrand() {
        return brand.get();
    }

    public void setBrand(String brand) {
        this.brand.set(brand);
    }

    public StringProperty brandProperty() {
        return brand;
    }

    public String getSerial() {
        return serial.get();
    }

    public void setSerial(String serial) {
        this.serial.set(serial);
    }

    public StringProperty serialProperty() {
        return serial;
    }

    public String getProductNo() {
        return productNo.get();
    }

    public void setProductNo(String productNo) {
        this.productNo.set(productNo);
    }

    public StringProperty productNoProperty() {
        return productNo;
    }
    
    public String getKeyboardNo() {
        return keyboardNo.get();
    }

    public void setKeyboardNo(String keyboardNo) {
        this.keyboardNo.set(keyboardNo);
    }

    public StringProperty keyboardNoProperty() {
        return keyboardNo;
    }
    
    public String getMouseNo() {
        return mouseNo.get();
    }

    public void setMouseNo(String mouseNo) {
        this.mouseNo.set(mouseNo);
    }

    public StringProperty mouseNoProperty() {
        return mouseNo;
    }
    
    public String getScreenNo() {
        return screenNo.get();
    }

    public void setScreenNo(String screenNo) {
        this.screenNo.set(screenNo);
    }

    public StringProperty screenNoProperty() {
        return screenNo;
    }
    
    public String getHeadsetNo() {
        return headsetNo.get();
    }

    public void setHeadsetNo(String headsetNo) {
        this.headsetNo.set(headsetNo);
    }

    public StringProperty headsetNoProperty() {
        return headsetNo;
    }
    
    public String getTelNo() {
        return telNo.get();
    }

    public void setTelNo(String telNo) {
        this.telNo.set(telNo);
    }

    public StringProperty telNoProperty() {
        return telNo;
    }
    
    public String getotherDevice() {
        return otherDevice.get();
    }

    public void setotherDevice(String otherDevice) {
        this.otherDevice.set(otherDevice);
    }

    public StringProperty otherDeviceProperty() {
        return otherDevice;
    }
    
}