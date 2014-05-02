/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import BE.Position;
import BE.Time_Sheet;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
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
    
    public Time_Sheet testForTimeSheet(int id) throws SQLException
    {
        Connection con = null;
        Time_Sheet timesheets = null;
       try
       {
           con = getConnection();
           
           Statement query = con.createStatement();
           ResultSet result = query.executeQuery("SELECT * FROM TimeSheet WHERE empoyeeId = "+id+" AND positionId = 1 AND accepted = 0;");
           if(result.next())
           {
               int employeeId = result.getInt("empoyeeId");
               int alarmId = result.getInt("alarmId");
               int carNr = result.getInt("carNr"); 
               Position pos = pa.getPositionById(result.getInt("positionId"));
               Time endtime = result.getTime("endTime");
               
               //booking = new CarBooking(Id, car, cust, startDate, endDate, emp,startKm, endKm, cmp, "");
               timesheets = new Time_Sheet(employeeId, alarmId, carNr, pos, endtime);
               
           }
       }
       finally
       {
           if(con != null)
           {
               con.close();
           }
       }
        
        
        return timesheets;
    }
    
    public void addTimeSheet(Time_Sheet ts) throws SQLServerException, SQLException{
        Connection con = null;
        try
        {
            con = getConnection();
            Statement query = con.createStatement();
            if(ts.getCarNr() == 0){
                query.executeUpdate("Insert into TimeSheet Values ( "
                         + ts.getEmployeeID()+ ", "
                        + ts.getAlarmID()+ ", "
                        + "NULL"+","
                        + ts.getPositionID() + ",'"
                        + ts.getEndTime() + "','" 
                        + ts.isAccepted()+ "') ");
            }else{
            
                query.executeUpdate("Insert into TimeSheet Values ( "
                         + ts.getEmployeeID()+ ", "
                        + ts.getAlarmID()+ ", "
                        + ts.getCarNr()+","
                        + ts.getPositionID() + ",'"
                        + ts.getEndTime() + "','" 
                        + ts.isAccepted()+ "') ");
            }

        }
        finally
        {
            if(con != null)
            {
                con.close();
            }
        }
    }
    
}
