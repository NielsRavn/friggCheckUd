/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import BE.Alarm;
import BE.Car;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Brobak
 */
public class Alarm_Access extends DatabaseConnection{
    
    public Alarm_Access() throws IOException{
        super();
    }
    
    
    public ArrayList<Alarm> getAllUnfinishedAlarms() throws SQLServerException, SQLException{
        Connection con = null;
        ArrayList<Alarm> alarms = new ArrayList<>();
        
        try{
            con = getConnection();
            Statement stmnt = con.createStatement();
            
            ResultSet rs = stmnt.executeQuery("SELECT * FROM Alarm WHERE accepted = 0;");
            
            while(rs.next()){
                Alarm a = new Alarm(rs.getInt("id"), rs.getInt("odinNr"), rs.getString("destination"), rs.getString("type"), rs.getDate("time"), rs.getBoolean("accepted"));
                
                alarms.add(a);
            }
        }finally{
            if(con != null) con.close();
        }
        
        return alarms;
    }
    
}
