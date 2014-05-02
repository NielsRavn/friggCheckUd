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
                ta.testForTimeSheet(firemanTotest.getID());
                
                    if(ta.testForTimeSheet(firemanTotest.getID()) != null)
                    {
                        timeSheetExist=true;
                    }
                                
                
            } catch (SQLException ex) {
                Logger.getLogger(TimeSheet_AccessLink.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(firemanTotest.getFirstName());
        }
    }
    
    public void aproveTimesheet(Fireman fireman)
    {
        isTeamleader(fireman);
    }
    
    public boolean showTimeAprove()
    {
        return timeSheetExist;
    }
    
    public void addTimeSheet(Time_Sheet ts) throws SQLException{
        ta.addTimeSheet(ts);
    }
}
