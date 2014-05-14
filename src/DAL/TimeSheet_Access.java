/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import BE.Alarm;
import BE.Car;
import BE.Comment;
import BE.Fireman;
import BE.Position;
import BE.Station;
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
import javax.swing.ImageIcon;

/**
 *
 * @author Poul Nielsen
 */
public class TimeSheet_Access extends DatabaseConnection{
  
    Position_Access pa;
    Comment_Access ca;
    
    public TimeSheet_Access() throws IOException
    {
        super();
        pa = new Position_Access();
        ca = new Comment_Access();
    }
    
    /**
     * Gets all unaccpedted timesheets for a fireman with a given ID where the given fireman is positioned as team leader
     * @param id the id of the fireman
     * @return all uaccepted timesheets where the given fireman was teamleader 
     * @throws SQLException 
     */
    public ArrayList<Time_Sheet> getUnaccedtedTimeSheetsbyFiremanIdInPositionTeamleader(int id) throws SQLException
    {
        Connection con = null;
        ArrayList<Time_Sheet> timesheets = new ArrayList<Time_Sheet>();
       try
       {
           con = getConnection();
           
           Statement query = con.createStatement();
           ResultSet result = query.executeQuery("SELECT * FROM TimeSheet WHERE empoyeeId = "+id+" AND positionId = "+ MyConstants.TEAM_LEADER.getID() +" AND acceptedBy IS NULL;");
           while(result.next())
           {
               int tsId = result.getInt("id");
               int employeeId = result.getInt("empoyeeId");
               int alarmId = result.getInt("alarmId");
               int carNr = result.getInt("carNr"); 
               Position pos = pa.getPositionById(result.getInt("positionId"));
               Timestamp startTime = result.getTimestamp("startTime");
               Timestamp endtime = result.getTimestamp("endTime");
               
               Time_Sheet c = new Time_Sheet(tsId, employeeId, alarmId, carNr, pos, startTime, endtime, ca.getCommentsByTimeSheetId(tsId));
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
                System.out.println("Insert into TimeSheet Values ( "
                         + ts.getEmployeeID()+ ", "
                        + ts.getAlarmID()+ ", "
                        + "NULL"+","
                        + ts.getPositionID() + ",'"
                        + ts.getStartTime() + "','"
                        + ts.getEndTime() + "'," 
                        + "NULL"+ ") ");
                query.executeUpdate("Insert into TimeSheet Values ( "
                         + ts.getEmployeeID()+ ", "
                        + ts.getAlarmID()+ ", "
                        + "NULL"+","
                        + ts.getPositionID() + ",'"
                        + ts.getStartTime() + "','"
                        + ts.getEndTime() + "'," 
                        + "NULL"+ ") ",Statement.RETURN_GENERATED_KEYS);
                ResultSet result = query.getGeneratedKeys();
                if(result.next()){
                    ts.setId(result.getInt(1));
                }
                else
                {
                    throw new SQLException ("Creating car model failed, id returned.");

                }
                
            }else{
                query.executeUpdate("Insert into TimeSheet Values ( "
                         + ts.getEmployeeID()+ ", "
                        + ts.getAlarmID()+ ", "
                        + ts.getCarNr()+","
                        + ts.getPositionID() + ",'"
                        + ts.getStartTime() + "','"
                        + ts.getEndTime() + "'," 
                        + "NULL"+ ") ",Statement.RETURN_GENERATED_KEYS);
                ResultSet result = query.getGeneratedKeys();
                if(result.next()){
                    ts.setId(result.getInt(1));
                }
                else
                {
                    throw new SQLException ("Creating car model failed, id returned.");

                }
            }
            
           
            for(Comment c : ts.getComments()){
                ca.addCommentToTimesheet(c, ts.getId());
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
    
    
    public ArrayList<Time_Sheet> getDataForAproval(int alarmId) throws SQLServerException, SQLException
    {
        
        Connection con = null;
        ArrayList<Time_Sheet> timesheets = new ArrayList<Time_Sheet>();
       try
       {
           con = getConnection();
           
           Statement query = con.createStatement();
           ResultSet result = query.executeQuery("SELECT Timesheet.id as tsId, * FROM TimeSheet "
                                                                + "INNER JOIN Position ON TimeSheet.positionId = Position.id "
                                                                + "INNER JOIN Fireman ON TimeSheet.empoyeeId = Fireman.employeeId "
                                                                + "INNER JOIN Car ON TimeSheet.CarNr = Car.carNr "
                                                                + "INNER JOIN Alarm ON TimeSheet.alarmId = Alarm.id "
                                                                + "WHERE TimeSheet.alarmId = "+alarmId+";");
           while(result.next())
           {
              //getting data for fireman
               int firemanId = result.getInt("employeeId");
               String firemanFirsName = result.getString("firstName");
               String firemanLastName = result.getString("lastName");
               boolean isTeamLeader = result.getBoolean("teamLeader");
               boolean isDriver = result.getBoolean("driver");
               
               //getting data for the car
               int carNr = result.getInt("carNr");
               String iconPath = result.getString("iconPath");
               String carName = result.getString("name");
               int seats = result.getInt("seats");
               
               //getting data for the alarm
               
               int id = result.getInt("id");
               int odinNr = result.getInt("odinNr");
               String destination = result.getString("destination");
               String type = result.getString("type");
               Timestamp time = result.getTimestamp("time");
               boolean accepted = result.getBoolean("accepted");
               //getting data for the position
               
               int positionId = result.getInt("id");
               String positionName = result.getString("name");
               
               //getting the rest of data for timesheet
               int tsId = result.getInt("tsId");
               Timestamp startTime = result.getTimestamp("startTime");
               Timestamp endTime = result.getTimestamp("endTime");
               int firemenPositionId = result.getInt("positionId");
               
               //Creating the arraylist with data from sql query
               
               Fireman a = new Fireman(firemanId, firemanFirsName, firemanLastName, isTeamLeader, isDriver);
               
               Alarm b = new Alarm(id, odinNr, destination, type, time, accepted);
               
               Car c = new Car(carNr, iconPath, carName, seats);
               
               Position d = new Position(positionId, positionName);
               
               Time_Sheet e = new Time_Sheet(tsId, a, b, c, d, startTime, endTime, firemenPositionId, ca.getCommentsByTimeSheetId(tsId));
               
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

    public ArrayList<Time_Sheet> stationsVagt( int alarmId) throws SQLServerException, SQLException
{
    
           
           Connection con = null;
        ArrayList<Time_Sheet> timesheets = new ArrayList<Time_Sheet>();
       try
       {
           con = getConnection();
           
           Statement query = con.createStatement();
           ResultSet result = query.executeQuery("SELECT Timesheet.id as tsId,* FROM TimeSheet \n" +
                                                            "INNER JOIN Position ON TimeSheet.positionId = Position.id \n" +
                                                            "INNER JOIN Fireman ON TimeSheet.empoyeeId = Fireman.employeeId \n" +
                                                            "INNER JOIN Alarm ON TimeSheet.alarmId = Alarm.id \n" +
                                                            "WHERE TimeSheet.alarmId = "+alarmId+"\n" +
                                                            "and ISNULL(TimeSheet.carNr, 0) = 0;");
           while(result.next())
           {
              //getting data for fireman
               int firemanId = result.getInt("employeeId");
               String firemanFirsName = result.getString("firstName");
               String firemanLastName = result.getString("lastName");
               boolean isTeamLeader = result.getBoolean("teamLeader");
               boolean isDriver = result.getBoolean("driver");
               
               //getting data for the Station
               String name = MyConstants.STATION_DUTY_VIEW.getName();
               String iconPath = MyConstants.STATION_DUTY_VIEW.getIconPath();
               //getting data for the alarm
               
               int id = result.getInt("id");
               int odinNr = result.getInt("odinNr");
               String destination = result.getString("destination");
               String type = result.getString("type");
               Timestamp time = result.getTimestamp("time");
               boolean accepted = result.getBoolean("accepted");
               
               //getting data for the position
               
               int positionId = result.getInt("id");
               String positionName = result.getString("name");
               
               //getting the rest of data for timesheet
               int tsId = result.getInt("tsId");
               Timestamp startTime = result.getTimestamp("startTime");
               Timestamp endTime = result.getTimestamp("endTime");
               int firemenPositionId = result.getInt("positionId");
               
               //Creating the arraylist with data from sql query
               
               Fireman a = new Fireman(firemanId, firemanFirsName, firemanLastName, isTeamLeader, isDriver);
               
               Alarm b = new Alarm(id, odinNr, destination, type, time, accepted);
               
               Station c = new Station(name, iconPath);
               
               Position d = new Position(positionId, positionName);
               
               Time_Sheet e = new Time_Sheet(tsId, a, b, c, d, startTime, endTime, firemenPositionId, ca.getCommentsByTimeSheetId(tsId));
               
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
    
}
