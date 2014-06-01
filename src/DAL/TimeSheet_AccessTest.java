/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import BE.Time_Sheet;
import java.util.ArrayList;

/**
 *
 * @author Brobak
 */
public class TimeSheet_AccessTest implements ITimeSheet_Access{
    ArrayList<Time_Sheet> timeSheets;
    final int oneHour = (60*60*1000);
    /**
     * Creates a new time sheet acces test
     */
    public TimeSheet_AccessTest(){
        timeSheets = new ArrayList<>();
    }
    
    /**
     * Gets all time sheets that are conflicting with the given time sheet
     * @param timeSheet the time sheet you want all conflicts for
     * @return a list of all time sheets conflicting with the given time sheet
     */
    @Override
    public ArrayList<Time_Sheet> getConflictingTimeSheets(Time_Sheet timeSheet) {
        ArrayList<Time_Sheet> result = new ArrayList<>();
        for(Time_Sheet ts : timeSheets){
            if(     ts.getStartTime().getTime() >= timeSheet.getStartTime().getTime() && ts.getStartTime().getTime() <= timeSheet.getEndTime().getTime()&& timeSheet.getId() != ts.getId() && ts.getEmployeeID() == timeSheet.getEmployeeID()||
                    ts.getEndTime().getTime() >= timeSheet.getStartTime().getTime() && ts.getEndTime().getTime() <= timeSheet.getEndTime().getTime() && timeSheet.getId() != ts.getId() && ts.getEmployeeID() == timeSheet.getEmployeeID() ||
                    ts.getStartTime().getTime() < timeSheet.getStartTime().getTime() && ts.getEndTime().getTime() > timeSheet.getStartTime().getTime() && timeSheet.getId() != ts.getId() && ts.getEmployeeID() == timeSheet.getEmployeeID() ){
                result.add(ts);
            }
        }
        
        return result;
    }
    /**
     * Adds a new time sheet to the access object
     * @param ts the time sheet you want to add
     */
    public void addTimeSheet(Time_Sheet ts){
        timeSheets.add(ts);
    }
    /**
     * Removes a given time sheet from the access object
     * @param ts the time sheet you want to remove
     */
    public void removeTimeSheet(Time_Sheet ts){
        timeSheets.remove(ts);
    }
    
}
