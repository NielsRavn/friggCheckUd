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

    public boolean isEquipmentHasData() {
        return equipmentHasData;
    }

    public void setEquipmentHasData(boolean equipmentHasData) {
        this.equipmentHasData = equipmentHasData;
    }
    
    @Override
    public String getName() {
        return "" + equipmentHasData;
    }
    
}
