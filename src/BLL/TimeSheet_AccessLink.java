/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;

import BE.ApprovalSheet;
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
     * @param id
     * @return
     * @throws SQLException 
     */
    public ArrayList<Time_Sheet> getTimeSheet(int id) throws SQLException
    {
        return ta.getUnaccedtedTimeSheetsbyFiremanIdInPositionTeamleader(id);
    }
    /**
     * 
     * @param ts
     * @throws SQLException 
     */
    public void addTimeSheet(Time_Sheet ts) throws SQLException{
        ta.addTimeSheet(ts);
    }

    public ArrayList<Time_Sheet> getTimeSheetsbyFiremanId(int firemanId) throws SQLException {
        return ta.getUnaccedtedTimeSheetsbyFiremanIdInPositionTeamleader(firemanId);
    }

    public ArrayList<Time_Sheet> getDataForAproval(int alarmId) throws SQLException {
        return ta.getDataForAproval(alarmId);
    }
    
    public ArrayList<Time_Sheet> stationsVagt(int alarmId) throws SQLException {
        return ta.stationsVagt(alarmId);
    }


    public void aproveTimesheetByTimesheetId(Time_Sheet t, ApprovalSheet approvalSheet) throws SQLException {
        ta.aproveTimesheetByTimesheetId(t, approvalSheet);
    }
}
