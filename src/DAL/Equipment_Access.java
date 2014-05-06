/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import BE.Equipment;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Niels
 */
public class Equipment_Access extends DatabaseConnection{
    
    public Equipment_Access() throws IOException{
        super();
    }
    
    public ArrayList<Equipment> getAllEquipmentTypes() throws SQLServerException, SQLException{
        ArrayList<Equipment> result = new ArrayList<>();
        Connection con = null;
        
        try{
            con = getConnection();
            
            Statement stmnt = con.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT Equipment.name AS name, UnitType.name AS unitType FROM Equipment "
                    + "JOIN UnitType ON unitTypeId = UnitType.id;");
            
            while(rs.next()){
                String name = rs.getString("name");
                String unitType = rs.getString("unitType");
                
                Equipment e = new Equipment(name, unitType);
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
    
}
