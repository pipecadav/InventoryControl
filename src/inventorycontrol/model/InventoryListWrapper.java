/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorycontrol.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Wraps list of entries. Saves a list of entries in XML format.
 * 
 * @author Felipe cadavid
 * 
 */
@XmlRootElement(name = "entries")
public class InventoryListWrapper {

    private List<Inventory> entries;

    @XmlElement(name = "entries")
    public List<Inventory> getEntries() {
        return entries;
    }

    public void setEntries(List<Inventory> entries) {
        this.entries = entries;
    }
}