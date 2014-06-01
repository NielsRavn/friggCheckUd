/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BE;

import java.util.ArrayList;

/**
 *
 * @author Brobak
 */
public class Equipment implements IViewObjectBE{
    int id;
    String name;
    String unitType;
    public Equipment(int id, String name, String unitType){
        this.id = id;
        this.name = name;
        this.unitType = unitType;
    }

    /**
     * @return the ID of the equipment
     */
    public int getId() {
        return id;
    }

    
    /**
     * @return the name of the equipment
     */
    public String getName() {
        return name;
    }

    /**
     * @return the unit type
     */
    public String getUnitType() {
        return unitType;
    }

}
