/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;

import BE.Fireman;
import BE.Time_Sheet;
import DAL.TimeSheet_Access;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Poul Nielsen
 */
public class TimeSheet_AccessLink {
    
    TimeSheet_Access ta;
    boolean timeSheetExist = false;
    
    public TimeSheet_AccessLink() throws IOException
    {
        ta = new TimeSheet_Access();
    }
    
    /**
     * 
     * @param fireman
     * @return 
     */
    public boolean timesheetExist(int firemanId)
    {
        try {
            if(getTimeSheet(firemanId) != null)
            {
                timeSheetExist=true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TimeSheet_AccessLink.class.getName()).log(Level.SEVERE, null, ex);
        }
        return timeSheetExist;
    }
    /**
     * 
     * @param id
     * @return
     * @throws SQLException 
     */
    public Time_Sheet getTimeSheet(int id) throws SQLException
    {
        return ta.testForTimeSheet(id);
    }
    /**
     * 
     * @param ts
     * @throws SQLException 
     */
    public void addTimeSheet(Time_Sheet ts) throws SQLException{
        ta.addTimeSheet(ts);
    }
}
