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
    /**
     * Creates a new time sheet acces link
     * @throws IOException 
     */
    public TimeSheet_AccessLink() throws IOException
    {
        ta = new TimeSheet_Access();
    }
    
    
    /**
     * adds a timesheet to the database
     * @param ts the time sheet you want to add to the database
     * @throws SQLException 
     */
    public void addTimeSheet(Time_Sheet ts) throws SQLException{
        ta.addTimeSheet(ts);
    }

    /**
     * Gets all unaccepted time sheets for a fireman with a given ID where the given fireman is positioned as team leader
     * @param firemanId the id of the fireman
     * @return a list of unaccepted time sheets
     * @throws SQLException 
     */
    public ArrayList<Time_Sheet> getTimeSheetsbyFiremanId(int firemanId) throws SQLException {
        return ta.getUnaccedtedTimeSheetsbyFiremanIdInPositionTeamleader(firemanId);
    }

    /**
     * Gets a list of unaproved timesheets on a given alarm, where the fireman was not on station duty
     * @param alarmId the id of the alarm
     * @return a list of unaproved timesheets
     * @throws SQLException 
     */
    public ArrayList<Time_Sheet> getDataForAproval(int alarmId) throws SQLException {
        return ta.getDataForAproval(alarmId);
    }
    /**
     * Gets all unaccepted time sheets on a given alarm, where the firefigther was on station duty
     * @param alarmId
     * @return
     * @throws SQLException 
     */
    public ArrayList<Time_Sheet> stationsVagt(int alarmId) throws SQLException {
        return ta.stationsVagt(alarmId);
    }

    /**
     * adds a given approval sheet to a given time sheet in the database
     * @param t the timesheets
     * @param approvalSheet the approval sheet
     * @throws SQLException 
     */
    public void aproveTimesheetByTimesheetId(Time_Sheet t, ApprovalSheet approvalSheet) throws SQLException {
        ta.aproveTimesheetByTimesheetId(t, approvalSheet);
    }
}
