/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import BE.Equipment;
import BE.Usage;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Niels
 */
public class Equipment_Access extends DatabaseConnection{
    /**
     * Creates a new Equipment access 
     * @throws IOException if an I/O exception of some sort has occurred.
     */
    public Equipment_Access() throws IOException{
        super();
    }
    /**
     * Gets all types of equipment
     * @return a list of equipments
     * @throws SQLServerException if the connection cant be made.
     * @throws SQLException if an error has occured executing the sql query
     */
    public ArrayList<Equipment> getAllEquipmentTypes() throws SQLServerException, SQLException{
        ArrayList<Equipment> result = new ArrayList<>(); // gets an arraylist with all equipment avalieble
        Connection con = null;
        
        try{
            con = getConnection();
            
            Statement stmnt = con.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT Equipment.id AS id, Equipment.name AS name, UnitType.name AS unitType FROM Equipment "
                    + "JOIN UnitType ON unitTypeId = UnitType.id;");
            
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String unitType = rs.getString("unitType");
                
                Equipment e = new Equipment(id, name, unitType); // information contained in the arraylist for all Equipment is the id, name and the unitType
                result.add(e);
            }
            
        }
        finally{
            if(con != null){
                con.close();
            }
        }
        return result;
    }
    /**
     * Gets all usages for a given car on a given alarm
     * @param alarmId the id of the alarm
     * @param carNr the nr of the car
     * @return a list with all usages for a given car on a given alarm
     * @throws SQLServerException if the connection cant be made.
     * @throws SQLException if an error has occured executing the sql query
     */
    public ArrayList<Usage> getUsagesFor(int alarmId, int carNr) throws SQLServerException, SQLException{ // gets equipment used by Alarm and Car
        Connection con = null;
        ArrayList<Usage> result = new ArrayList<>();
        
        try{
            con = getConnection();
            
            Statement stmnt = con.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT * FROM Usage WHERE alarmId = "+alarmId+" AND carNr = " + carNr +";");
            
            while(rs.next()){
                int equipmentId = rs.getInt("equipmentId");
                int amount = rs.getInt("amount");
                
                Usage u = new Usage(alarmId, carNr, equipmentId, amount, true);
                result.add(u);
            }
            
        }
        finally{
            if(con != null)
                con.close();
        }
        return result;
    }

    /**
     * Updates a list of usages to the database
     * @param usagesToUpdate the list of usages you want to update
     * @throws SQLServerException if the connection cant be made.
     * @throws SQLException if an error has occured executing the sql query
     */
    public void updateUsages(ArrayList<Usage> usagesToUpdate) throws SQLServerException, SQLException {
        Connection con = null;
        // updates how much equipment used on a car on a Alarm 
        try{
            con = getConnection();
            for(Usage u: usagesToUpdate){
                int affectedRows = 0;
                if(u.isLoadedFromDatabase()){
                    PreparedStatement ps = con.prepareStatement("UPDATE Usage SET amount = ? "
                            + "WHERE alarmId = ? AND carNr = ? AND equipmentId = ?;");
                    ps.setInt(1, u.getAmount());
                    ps.setInt(2, u.getAlarmId());
                    ps.setInt(3, u.getCarNr());
                    ps.setInt(4, u.getEquipmentId());
                    affectedRows = ps.executeUpdate();
                }else{
                    PreparedStatement ps = con.prepareStatement("INSERT INTO Usage VALUES "
                            + "(?, ?, ?, ?);");
                    ps.setInt(1, u.getAlarmId());
                    ps.setInt(2, u.getCarNr());
                    ps.setInt(3, u.getEquipmentId());
                    ps.setInt(4, u.getAmount());
                    affectedRows = ps.executeUpdate();
                }
                if(affectedRows == 0){
                    throw new SQLException("zero rows affected");
                }
            }
        }
        finally{
            if(con != null){
                con.close();
            }
        }
    }
    
}
