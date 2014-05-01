/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import BE.Position;
import BE.Time_Sheet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Poul Nielsen
 */
public class TimeSheet_Access extends DatabaseConnection{
  
    Position_Access pa;
    
    public TimeSheet_Access() throws IOException
    {
        super();
        pa = new Position_Access();
    }
    public boolean testForTimeSheet(int id) throws SQLException
    {
        Connection con = null;
        ArrayList<Time_Sheet> timesheets = new ArrayList<>();
       try
       {
           con = getConnection();
           
           Statement query = con.createStatement();
           ResultSet result = query.executeQuery("SELECT * FROM TimeSheet Where empoyeeId = "+id+" AND accepted = 0;");
           while(result.next())
           {
               int employeeId = result.getInt("empoyeeId");
               int alarmId = result.getInt("alarmId");
               int carNr = result.getInt("carNr"); 
               Position pos = pa.getPositionById(result.getInt("positionId"));
               Date endtime = result.getDate("endTime");
               
               Time_Sheet c = new Time_Sheet(employeeId, alarmId, carNr, pos, endtime);
               
               timesheets.add(c);
           }
           
       }
       finally
       {
           if(con != null)
           {
               con.close();
           }
       }
        
        
        return true;
    }
    
}
