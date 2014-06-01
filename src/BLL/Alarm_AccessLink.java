/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;

import BE.Alarm;
import DAL.Alarm_Access;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Brobak
 */
public class Alarm_AccessLink {
    Alarm_Access aa;
    
    /**
     * Creates a new alarm acces link
     * @throws IOException 
     */
    public Alarm_AccessLink() throws IOException{
        aa = new Alarm_Access();
    }
    
    /**
     * Gets all unfinished alarms
     * @return a list containing all unfinished alarms
     * @throws SQLServerException if the connection cant be made.
     * @throws SQLException if an error has occured executing the sql query
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException 
     */
    public ArrayList<Alarm> getAllUnfinishedAlarms() throws SQLException, SQLServerException, ParserConfigurationException, SAXException, IOException{
        return aa.getAllUnfinishedAlarms();
    }
    
    /**
     * Gets a specific alarm by its Id
     * @param id the id of the alarm you want
     * @return the alarm that has the given id
     * @throws SQLException if an error has occured executing the sql query
     */
    public Alarm getAlarmById(int id) throws SQLException
    {
        return aa.getAlarmById(id);
    }
    
    /**
     * Adds an alarm to the database
     * @param alarm the alarm you want to add the the database
     * @throws SQLException if an error has occured executing the sql query
     */
    public void addAlarm(Alarm alarm) throws SQLException{
        aa.addAlarm(alarm);
    }
    
}
