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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Brobak
 */
public class Alarm_Access extends DatabaseConnection{
    XmlScanner xml;
    /**
     * Creates a new Alarm access
     * @throws IOException 
     */
    public Alarm_Access() throws IOException{
        super();
        xml = new XmlScanner();
    }
    
    /**
     * Gets a list of all unfinished alarms
     * @return a list of all unfinished alarms
     * @throws SQLServerException
     * @throws SQLException
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException 
     */
    public ArrayList<Alarm> getAllUnfinishedAlarms() throws SQLServerException, SQLException, ParserConfigurationException, SAXException, IOException{
        Connection con = null;
        ArrayList<Alarm> alarms = new ArrayList<>();
        getAlarmFromXml();
        try{
            con = getConnection();
            Statement stmnt = con.createStatement();
            
            ResultSet rs = stmnt.executeQuery("SELECT * FROM Alarm WHERE accepted = 0;");
            
            while(rs.next()){
                Alarm a = new Alarm(rs.getInt("id"), rs.getInt("odinNr"), rs.getString("destination"), rs.getString("type"), rs.getTimestamp("time"), rs.getBoolean("accepted"), rs.getBoolean("exercise"));
                
                alarms.add(a);
            }
        }finally{
            if(con != null) con.close();
        }
        
        return alarms;
    }
    
    /**
     * Gets all new alarms from the XML file and adds them to the database
     * @throws SQLException
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException 
     */
    private void getAlarmFromXml() throws SQLException, ParserConfigurationException, SAXException, IOException
    {
        ArrayList<Integer> alarmsFromSql = new ArrayList<Integer>();
        Connection con = null;
        try{
            con = getConnection();
            Statement stmnt = con.createStatement();
            
            ResultSet rs = stmnt.executeQuery("SELECT * FROM Alarm WHERE accepted = 0;");
            
            while(rs.next()){
               alarmsFromSql.add(rs.getInt("odinNr"));
            }
        
        try {
            ArrayList<Alarm> newAlarm =  xml.scanner();
        
        for(Alarm a : newAlarm)
        {
            if(!alarmsFromSql.contains(a.getOdinNr()))
            {
                String sql = "INSERT INTO Alarm (odinNr, destination, type, time, accepted, exercise) VALUES (?,?,?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, a.getOdinNr());
                ps.setString(2, a.getDistination());
                ps.setString(3, a.getType());
                ps.setTimestamp(4, a.getTime());
                ps.setBoolean(5, false);
                ps.setBoolean(6, false);
                
                ps.executeUpdate();
            }
            
        }
        
        } catch (ParseException ex) {
            
        }
        }finally{
            if(con != null) con.close();
        }
    }

    /**
     * Gets an alarm from the database with a given id
     * @param id the id of the alarm you want
     * @return the alarm from the database with a given id
     * @throws SQLServerException
     * @throws SQLException 
     */
    public Alarm getAlarmById(int id) throws SQLServerException, SQLException {
        Connection con = null;
        Alarm alarm = null;
        try{
            con = getConnection();
            Statement stmnt = con.createStatement();
            
            ResultSet rs = stmnt.executeQuery("SELECT * FROM Alarm WHERE id="+id+" AND accepted = 0;");
            
            while(rs.next()){
                alarm = new Alarm(rs.getInt("id"), rs.getInt("odinNr"), rs.getString("destination"), rs.getString("type"), rs.getTimestamp("time"), rs.getBoolean("accepted"), rs.getBoolean("exercise"));
                
            }
        }finally{
            if(con != null) con.close();
        }
        
        return alarm;
    }
    
    /**
     * adds a given alarm to the database
     * @param alarm the alarm you want to add to the database
     * @throws SQLServerException
     * @throws SQLException 
     */
    public void addAlarm(Alarm alarm) throws SQLServerException, SQLException{
        Connection con = null;
        try
        {
            con = getConnection();
            Statement query = con.createStatement();
                int key = query.executeUpdate("Insert into Alarm (destination, type, time, exercise, accepted ) VALUES("
                        + "'"+alarm.getDistination()+ "', "
                        + "'"+alarm.getType()+ "', "
                        + "'"+alarm.getTime()+"',"
                        + "'"+alarm.isExercise()+"',"
                        + "'False') ", Statement.RETURN_GENERATED_KEYS);
                alarm.setID(key);
                
                
           
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
