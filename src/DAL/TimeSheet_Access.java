/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import BE.Alarm;
import BE.ApprovalSheet;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;

/**
 *
 * @author Poul Nielsen
 */
public class TimeSheet_Access extends DatabaseConnection implements ITimeSheet_Access{
  
    final int oneHour = (60*60*1000);
    Position_Access pa;
    Comment_Access ca;
    /**
     * creates a new time sheet access
     * @throws IOException if an I/O exception of some sort has occurred.
     */
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
           ResultSet result = query.executeQuery("SELECT * FROM TimeSheet WHERE empoyeeId = "+id+" AND positionId = "+ MyConstants.TEAM_LEADER.getID() +" AND acceptedForSalary IS NULL;");
           while(result.next())
           {
               int tsId = result.getInt("id");
               int employeeId = result.getInt("empoyeeId");
               int alarmId = result.getInt("alarmId");
               int carNr = result.getInt("carNr"); 
               Position pos = pa.getPositionById(result.getInt("positionId"));
               Timestamp startTime = result.getTimestamp("startTime");
               Timestamp endtime = result.getTimestamp("endTime");
               int acceptedByTeamleader = result.getInt("acceptedByTeamLeader");
               int acceptedForSalary = result.getInt("acceptedForSalary");
               boolean addedToPayment = result.getBoolean("addedToPayment");
               String comment = result.getString("Comment");
               
               Time_Sheet c = new Time_Sheet(tsId, employeeId, alarmId, carNr, pos, startTime, endtime, acceptedByTeamleader, acceptedForSalary, addedToPayment, new Comment(comment));
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
    
    /**
     * adds a given time sheet to the database
     * @param ts the time sheet
     * @throws SQLServerException if the connection cant be made.
     * @throws SQLException if an error has occured executing the sql query
     */
    public void addTimeSheet(Time_Sheet ts) throws SQLServerException, SQLException{
        Connection con = null;
        try
        {
            con = getConnection();
            Statement query = con.createStatement();
            if(ts.getCarNr() == 0){
                
                
              
                
                query.executeUpdate("Insert into TimeSheet (empoyeeId,alarmId,carNr,positionId, startTime, endTime, acceptedByTeamleader, acceptedForSalary, addedToPayment, comment) Values ( "
                         + ts.getEmployeeID()+ ", "
                        + ts.getAlarmID()+ ", "
                        + "NULL"+","
                        + ts.getPositionID() + ",'"
                        + ts.getStartTime() + "','"
                        + ts.getEndTime() + "'," 
                        + "NULL , "
                        + "NULL ,"
                        + "'False' ,'"
                        + ts.getComment().getComment()+"'"+ ") ",Statement.RETURN_GENERATED_KEYS);
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
                        + "NULL , "
                        + "NULL ,"
                        + "'False' ,'"
                        + ts.getComment().getComment()+"'"+ ") ",Statement.RETURN_GENERATED_KEYS);
                ResultSet result = query.getGeneratedKeys();
                if(result.next()){
                    ts.setId(result.getInt(1));
                }
                else
                {
                    throw new SQLException ("Creating car model failed, id returned.");

                }
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
    
    /**
     * gets a list of unapproved timesheets where the position where not stationduty
     * @param alarmId the id of the alarm
     * @return a list of unapproved timesheets where the position where not stationduty
     * @throws SQLServerException if the connection cant be made.
     * @throws SQLException if an error has occured executing the sql query
     */
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
                                                                + "WHERE TimeSheet.alarmId = "+alarmId+" AND acceptedByTeamleader IS NULL;");
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
               boolean exercise = result.getBoolean("exercise");
               //getting data for the position
               
               int positionId = result.getInt("id");
               String positionName = result.getString("name");
               
               //getting the rest of data for timesheet
               int tsId = result.getInt("tsId");
               Timestamp startTime = result.getTimestamp("startTime");
               Timestamp endTime = result.getTimestamp("endTime");
               int firemenPositionId = result.getInt("positionId");
               int acceptedByTeamleader = result.getInt("acceptedByTeamLeader");
               int acceptedForSalary = result.getInt("acceptedForSalary");
               boolean addedToPayment = result.getBoolean("addedToPayment");
               String comment = result.getString("Comment");
               
               //Creating the arraylist with data from sql query
               
               Fireman a = new Fireman(firemanId, firemanFirsName, firemanLastName, isTeamLeader, isDriver);
               
               Alarm b = new Alarm(id, odinNr, destination, type, time, accepted, exercise);
               
               Car c = new Car(carNr, iconPath, carName, seats);
               
               Position d = new Position(positionId, positionName);
               
               Time_Sheet e = new Time_Sheet(tsId, a, b, c, d, startTime, endTime, firemenPositionId, acceptedByTeamleader, acceptedForSalary, addedToPayment, new Comment(comment));
               
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

    /**
     * Gets a list of firefigthers who where at staion duty at a given alarm
     * @param alarmId the id of the alarm
     * @return a list of firefigthers who where at staion duty at a given alarm
     * @throws SQLServerException if the connection cant be made.
     * @throws SQLException if an error has occured executing the sql query
     */
    public ArrayList<Time_Sheet> stationsVagt( int alarmId) throws SQLServerException, SQLException
{
        Connection con = null;
        ArrayList<Time_Sheet> timesheets = new ArrayList<Time_Sheet>();
       try
       {
           con = getConnection();
           
           Statement query = con.createStatement();
           ResultSet result = query.executeQuery("SELECT Timesheet.id as tsId,* FROM TimeSheet " +
                                                            "INNER JOIN Position ON TimeSheet.positionId = Position.id " +
                                                            "INNER JOIN Fireman ON TimeSheet.empoyeeId = Fireman.employeeId " +
                                                            "INNER JOIN Alarm ON TimeSheet.alarmId = Alarm.id " +
                                                            "WHERE TimeSheet.alarmId = "+alarmId+"" +
                                                            "AND TimeSheet.carNr IS NULL AND acceptedByTeamleader IS NULL;");
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
               boolean exercise = result.getBoolean("exercise");
               
               //getting data for the position
               
               int positionId = result.getInt("id");
               String positionName = result.getString("name");
               
               //getting the rest of data for timesheet
               int tsId = result.getInt("tsId");
               Timestamp startTime = result.getTimestamp("startTime");
               Timestamp endTime = result.getTimestamp("endTime");
               int firemenPositionId = result.getInt("positionId");
               int acceptedByTeamleader = result.getInt("acceptedByTeamLeader");
               int acceptedForSalary = result.getInt("acceptedForSalary");
               boolean addedToPayment = result.getBoolean("addedToPayment");
               String comment = result.getString("Comment");
               
               //Creating the arraylist with data from sql query
               
               Fireman a = new Fireman(firemanId, firemanFirsName, firemanLastName, isTeamLeader, isDriver);
               
               Alarm b = new Alarm(id, odinNr, destination, type, time, accepted, exercise);
               
               Station c = new Station(name, iconPath);
               
               Position d = new Position(positionId, positionName);
               
               Time_Sheet e = new Time_Sheet(tsId, a, b, c, d, startTime, endTime, firemenPositionId, acceptedByTeamleader, acceptedForSalary, addedToPayment, new Comment(comment));
               
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

    /**
     * Creates an approval sheet in the database and adds it to a given time sheet
     * @param t the time sheet
     * @param approvalSheet the approval sheet you want to add
     * @throws SQLServerException if the connection cant be made.
     * @throws SQLException if an error has occured executing the sql query
     */
        public void aproveTimesheetByTimesheetId(Time_Sheet t, ApprovalSheet approvalSheet)  throws SQLServerException, SQLException {
        Connection con = null;
        con = getConnection();
        con.setAutoCommit(false);
        con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        try
        {
            
            String sql = "INSERT INTO ApprovalSheet (firemanId, comment, approved, hours) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, approvalSheet.getFireman().getID());
            ps.setString(2, approvalSheet.getComment());
            ps.setBoolean(3, approvalSheet.isApproved());
            ps.setInt(4, approvalSheet.getHours());
            
            int affectedRows = ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            
            if (affectedRows == 0)
            {
                throw new SQLException("Problem with updating the ApprovalSheet");
            }

               if(rs.next()){
                approvalSheet.setId(rs.getInt(1));
                String updateSql = "UPDATE Timesheet SET acceptedByTeamleader=? WHERE id = ?;";
                PreparedStatement psUpdate = con.prepareStatement(updateSql);
                psUpdate.setInt(1, rs.getInt(1));
                psUpdate.setInt(2, t.getId());
                   affectedRows = psUpdate.executeUpdate();
                    if (affectedRows == 0)
                    {
                        throw new SQLException("Problem with updating the Timesheet ");
                    }
                    
            }else{
                throw new SQLException("No key returned when adding ApprovalSheet");
            }
               con.commit();
        }
        catch (SQLException e)
        {
            con.rollback();
            throw e;
        }
        finally
        {
            con.setAutoCommit(true);
            if(con != null)
            {
                con.close();
            }
        }
    }

    /**
     * Gets all time sheets that are conflicting with a given time sheet
     * @param timeSheet the time sheet you want all conflicts for
     * @return a list of time sheets that are conflicting with the given time sheet
     * @throws SQLServerException if the connection cant be made.
     * @throws SQLException if an error has occured executing the sql query
     */
    @Override
    public ArrayList<Time_Sheet> getConflictingTimeSheets(Time_Sheet timeSheet) throws SQLServerException, SQLException{
        Connection con = null;
        ArrayList<Time_Sheet> timesheets = new ArrayList<Time_Sheet>();
       try
       {
           con = getConnection();
           Statement query = con.createStatement();
           ResultSet result = null;
           if(timeSheet.getFireman() != null) result = query.executeQuery("SELECT * FROM TimeSheet WHERE startTime >= '" + new Timestamp(timeSheet.getStartTime().getTime()) +"' AND startTime <= '"+ new Timestamp(timeSheet.getEndTime().getTime())+"' AND empoyeeId = "+ timeSheet.getFireman().getID() + " AND id != " + timeSheet.getId()
                                                + " OR endTime >= '" + new Timestamp(timeSheet.getStartTime().getTime())+"' AND endTime <= '"+ new Timestamp(timeSheet.getEndTime().getTime()) +"' AND empoyeeId = "+ timeSheet.getFireman().getID()+ " AND id != " + timeSheet.getId() 
                                                + " OR startTime < '" + new Timestamp(timeSheet.getStartTime().getTime())+"' AND endTime > '"+ new Timestamp(timeSheet.getStartTime().getTime()) +"' AND empoyeeId = "+ timeSheet.getFireman().getID()+ " AND id != " + timeSheet.getId() + ";");
           else result = query.executeQuery("SELECT * FROM TimeSheet WHERE startTime >= '" + new Timestamp(timeSheet.getStartTime().getTime()) +"' AND startTime <= '"+ new Timestamp(timeSheet.getEndTime().getTime())+"' AND empoyeeId = "+ timeSheet.getEmployeeID() + " AND id != " + timeSheet.getId()
                                                + " OR endTime >= '" + new Timestamp(timeSheet.getStartTime().getTime())+"' AND endTime <= '"+ new Timestamp(timeSheet.getEndTime().getTime()) +"' AND empoyeeId = "+ timeSheet.getEmployeeID()+ " AND id != " + timeSheet.getId() 
                                                + " OR startTime < '" + new Timestamp(timeSheet.getStartTime().getTime())+"' AND endTime > '"+ new Timestamp(timeSheet.getStartTime().getTime()) +"' AND empoyeeId = "+ timeSheet.getEmployeeID()+ " AND id != " + timeSheet.getId() + ";");
            while(result.next())
           {
               int tsId = result.getInt("id");
               int employeeId = result.getInt("empoyeeId");
               int alarmId = result.getInt("alarmId");
               int carNr = result.getInt("carNr"); 
               Position pos = pa.getPositionById(result.getInt("positionId"));
               Timestamp startTime = result.getTimestamp("startTime");
               Timestamp endtime = result.getTimestamp("endTime");
               int acceptedByTeamleader = result.getInt("acceptedByTeamLeader");
               int acceptedForSalary = result.getInt("acceptedForSalary");
               boolean addedToPayment = result.getBoolean("addedToPayment");
               String comment = result.getString("Comment");
               
               Time_Sheet c = new Time_Sheet(tsId, employeeId, alarmId, carNr, pos, startTime, endtime, acceptedByTeamleader, acceptedForSalary, addedToPayment, new Comment(comment));
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
    
}
