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
public class EquipmentStatus implements IViewObjectBE{

    boolean equipmentHasData;

    public EquipmentStatus(boolean equipmentHasData) {
        this.equipmentHasData = equipmentHasData;
    }
    
    /**
     * @return the equipment status
     */
    public boolean isEquipmentHasData() {
        return equipmentHasData;
    }

    /**
     * sets the equipment status
     * @param equipmentHasData the new status
     */
    public void setEquipmentHasData(boolean equipmentHasData) {
        this.equipmentHasData = equipmentHasData;
    }
    
    /**
     * @return a representation of the equipment status
     */
    @Override
    public String getName() {
        return "" + equipmentHasData;
    }
    
}
