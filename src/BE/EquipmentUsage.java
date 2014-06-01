/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BE;

/**
 *
 * @author Niels Kristian Ravn
 */
public class EquipmentUsage implements IViewObjectBE{

    Equipment equipment;
    Usage usage;

    
    public EquipmentUsage(Equipment equipment, Usage usage) {
        this.equipment = equipment;
        this.usage = usage;
    }

    /**
     * @return gets the equipment
     */
    public Equipment getEquipment() {
        return equipment;
    }

    /**
     * Sets the equipment
     * @param equipment the new equipment you want to be set
     */
    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }
    /**
     * @return the usage
     */
    public Usage getUsage() {
        return usage;
    }
    
    /**
     * Sets a new usage
     * @param usage the new usage
     */
    public void setUsage(Usage usage) {
        this.usage = usage;
    }
    
    
    /**
     * @return the name of the usage
     */
    @Override
    public String getName() {
        return equipment.getName();
    }
    
}
