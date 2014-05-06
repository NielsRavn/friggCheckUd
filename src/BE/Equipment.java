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
public class Equipment implements ViewObjectBE{
    int id;
    String name;
    String unitType;
    public Equipment(int id, String name, String unitType){
        this.id = id;
        this.name = name;
        this.unitType = unitType;
    }

    public int getId() {
        return id;
    }

    
    
    public String getName() {
        return name;
    }

    public String getUnitType() {
        return unitType;
    }

}
