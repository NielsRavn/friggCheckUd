/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;

import BE.Alarm;
import DAL.Alarm_Access;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Brobak
 */
public class Alarm_AccessLink {
    Alarm_Access aa;
    
    public Alarm_AccessLink() throws IOException{
        aa = new Alarm_Access();
    }
    
    public ArrayList<Alarm> getAllUnfinishedAlarms() throws SQLException{
        return aa.getAllUnfinishedAlarms();
    }
    
    public Alarm getAlarmById(int id) throws SQLException
    {
        return aa.getAlarmById(id);
    }
    
}
