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
     * @param firemanTotest 
     */
    private void isTeamleader(Fireman firemanTotest)
    {
        if(firemanTotest.isTeamLeader())
        {
            try {
                //If fireman is allowed to bee a teamlead check the database for timesheets not approved
                    if(getTimeSheet(firemanTotest.getID()) != null)
                    {
                        timeSheetExist=true;
                    }
            } catch (SQLException ex) {
                
            }
        }
    }
    
    public boolean showTimeAprove(Fireman fireman)
    {
        isTeamleader(fireman);
        return timeSheetExist;
    }
    
    public Time_Sheet getTimeSheet(int id) throws SQLException
    {
        return ta.testForTimeSheet(id);
    }
    
    public void addTimeSheet(Time_Sheet ts) throws SQLException{
        ta.addTimeSheet(ts);
    }
}
