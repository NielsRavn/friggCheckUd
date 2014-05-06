/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;

import BE.Equipment;
import DAL.Equipment_Access;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Brobak
 */
public class Equipment_AccessLink {
    Equipment_Access ea;
    
    public Equipment_AccessLink() throws IOException{
        ea = new Equipment_Access();
    }
    
    public ArrayList<Equipment> getAllEquipmentTypes() throws SQLException{
        return ea.getAllEquipmentTypes();
    }
}
