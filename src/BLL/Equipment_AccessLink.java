/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;

import BE.Equipment;
import java.util.ArrayList;

/**
 *
 * @author Brobak
 */
public class Equipment_AccessLink {
    Equipment_Access ea;
    
    public Equipment_AccessLink(){
        ea = new Equipment_Access();
    }
    
    public ArrayList<Equipment> getAllEquipmentTypes(){
        return ea.getAllEquipmentTypes();
    }
}
