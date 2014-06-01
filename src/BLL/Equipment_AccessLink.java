/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;

import BE.Equipment;
import BE.Usage;
import DAL.Equipment_Access;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Brobak
 */
public class Equipment_AccessLink {
    Equipment_Access ea;
    /**
     * Creates a new equipment acces link
     * @throws IOException 
     */
    public Equipment_AccessLink() throws IOException{
        ea = new Equipment_Access();
    }
    
    /**
     * Gets a list of all equipment types
     * @return a list of all equipment types
     * @throws SQLException 
     */
    public ArrayList<Equipment> getAllEquipmentTypes() throws SQLException{
        return ea.getAllEquipmentTypes();
    }
    
    /**
     * Gets a list of usages for a given car on a given alarm
     * @param alarmId the id of the alarm which you want usages for
     * @param carNr the id of the car which you want usages for
     * @return a list of usages for the given car on the given alarm
     * @throws SQLServerException
     * @throws SQLException 
     */
    public ArrayList<Usage> getUsagesFor(int alarmId, int carNr) throws SQLServerException, SQLException{
        return ea.getUsagesFor(alarmId, carNr);
    }

    /**
     * updates a list of usages
     * @param usagesToUpdate the list of usages you want to update
     * @throws SQLException 
     */
    public void updateUsages(ArrayList<Usage> usagesToUpdate) throws SQLException {
        ea.updateUsages(usagesToUpdate);
    }
}
