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
    public TimeSheet_AccessTest(){
        timeSheets = new ArrayList<>();
    }
    
    @Override
    public ArrayList<Time_Sheet> getConflictingTimeSheets(Time_Sheet timeSheet) {
        ArrayList<Time_Sheet> result = new ArrayList<>();
        for(Time_Sheet ts : timeSheets){
//            if(         ts.getEndTime().getTime() >=  timeSheet.getEndTime().getTime() && ts.getStartTime().getTime() <= (timeSheet.getEndTime().getTime()+(oneHour*2))
//                    ||  ts.getStartTime().getTime() <= timeSheet.getStartTime().getTime() && ts.getEndTime().getTime() >= timeSheet.getStartTime().getTime() - (oneHour*2)
//                    ||  ts.getStartTime().getTime() > (timeSheet.getStartTime().getTime()-(oneHour*2)) && ts.getEndTime().getTime() < (timeSheet.getEndTime().getTime()+(oneHour*2))){
            if(     ts.getStartTime().getTime() >= timeSheet.getStartTime().getTime() && ts.getStartTime().getTime() <= timeSheet.getEndTime().getTime()&& timeSheet.getId() != ts.getId() && ts.getEmployeeID() == timeSheet.getEmployeeID()||
                    ts.getEndTime().getTime() >= timeSheet.getStartTime().getTime() && ts.getEndTime().getTime() <= timeSheet.getEndTime().getTime() && timeSheet.getId() != ts.getId() && ts.getEmployeeID() == timeSheet.getEmployeeID()){
                result.add(ts);
            }
        }
        
        return result;
    }
    
    public void addTimeSheet(Time_Sheet ts){
        timeSheets.add(ts);
    }
    
    public void removeTimeSheet(Time_Sheet ts){
        timeSheets.remove(ts);
    }
    
}
