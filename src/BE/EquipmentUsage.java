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

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Usage getUsage() {
        return usage;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }
    
    
    
    @Override
    public String getName() {
        return equipment.getName();
    }
    
}
