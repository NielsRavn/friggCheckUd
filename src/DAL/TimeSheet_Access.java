/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import BE.Alarm;
import BE.Car;
import BE.Fireman;
import BE.Position;
import BE.Time_Sheet;
import Presentation.MyConstants;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
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
    
    public ArrayList<Time_Sheet> getTimeSheetsbyFiremanId(int id) throws SQLException
    {
        Connection con = null;
        ArrayList<Time_Sheet> timesheets = new ArrayList<Time_Sheet>();
       try
       {
           con = getConnection();
           
           Statement query = con.createStatement();
           ResultSet result = query.executeQuery("SELECT * FROM TimeSheet WHERE empoyeeId = "+id+" AND positionId = "+ MyConstants.TEAM_LEADER.getID() +" AND accepted = 0;");
           while(result.next())
           {
               int employeeId = result.getInt("empoyeeId");
               int alarmId = result.getInt("alarmId");
               int carNr = result.getInt("carNr"); 
               Position pos = pa.getPositionById(result.getInt("positionId"));
               Time startTime = result.getTime("startTime");
               Time endtime = result.getTime("endTime");
               
               Time_Sheet c = new Time_Sheet(employeeId, alarmId, carNr, pos, startTime, endtime);
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
                        + ts.getStartTime() + "','"
                        + ts.getEndTime() + "','" 
                        + ts.isAccepted()+ "') ");
            }else{
            
                query.executeUpdate("Insert into TimeSheet Values ( "
                         + ts.getEmployeeID()+ ", "
                        + ts.getAlarmID()+ ", "
                        + ts.getCarNr()+","
                        + ts.getPositionID() + ",'"
                        + ts.getStartTime() + "','"
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
    
    
    public ArrayList<Time_Sheet> getTimeSheetForAproval(int alarmId) throws SQLServerException, SQLException
    {
        
        Connection con = null;
        ArrayList<Time_Sheet> timesheets = new ArrayList<Time_Sheet>();
       try
       {
           con = getConnection();
           
           Statement query = con.createStatement();
           ResultSet result = query.executeQuery("SELECT * FROM TimeSheet INNER JOIN Position ON TimeSheet.positionId = Position.id INNER JOIN Fireman ON TimeSheet.empoyeeId = Fireman.employeeId INNER JOIN Car ON TimeSheet.CarNr = Car.carNr INNER JOIN Alarm ON TimeSheet.alarmId = Alarm.id WHERE TimeSheet.alarmId = "+alarmId+";");
           while(result.next())
           {
              //getting data for fireman
               int firemanId = result.getInt("Fireman.employeeId");
               String firemanFirsName = result.getString("Fireman.firstName");
               String firemanLastName = result.getString("Fireman.lastName");
               boolean isTeamLeader = result.getBoolean("Fireman.teamLeader");
               boolean isDriver = result.getBoolean("Fireman.driver");
               
               //getting data for the car
               int carNr = result.getInt("Car.carNr");
               String iconPath = result.getString("Car.iconPath");
               String carName = result.getString("Car.name");
               int seats = result.getInt("Car.seats");
               
               //getting data for the alarm
               
               int id = result.getInt("Alarm.id");
               int odinNr = result.getInt("Alarm.odinNr");
               String destination = result.getString("Alarm.destination");
               String type = result.getString("Alarm.type");
               Timestamp time = result.getTimestamp("Alarm.time");
               boolean accepted = result.getBoolean("Alarm.accepted");
               
               //getting data for the position
               
               int positionId = result.getInt("Position.id");
               String positionName = result.getString("Position.name");
               
               //getting the rest of data for timesheet
               
               Time startTime = result.getTime("TimeSheet.startTime");
               Time endTime = result.getTime("TimeSheet.endTime");
               boolean timesheetAccepted = result.getBoolean("TimeSheet.accepted");
               
               //Creating the arraylist with data from sql query
               
               Fireman a = new Fireman(firemanId, firemanFirsName, firemanLastName, isTeamLeader, isDriver);
               
               Alarm b = new Alarm(id, odinNr, destination, type, time, accepted);
               
               Car c = new Car(carNr, iconPath, carName, seats);
               
               Position d = new Position(positionId, positionName);
               
               Time_Sheet e = new Time_Sheet(a, b, c, d, startTime, endTime);
               timesheets.add(e);
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

    public ArrayList<Time_Sheet> getDataForAproval(int alarmId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
