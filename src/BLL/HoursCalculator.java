/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;

import BE.Hours;
import BE.Time_Sheet;
import DAL.ITimeSheet_Access;
import DAL.TimeSheet_Access;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Brobak
 */
public class HoursCalculator{
    ITimeSheet_Access timeSheetAccess;
    final int oneHour = (60*60*1000);
    public HoursCalculator() throws IOException{
        timeSheetAccess = new TimeSheet_Access();
        
    }
    
    public HoursCalculator(ITimeSheet_Access tsa){
        timeSheetAccess = tsa;
    }
    
    

    public int  getHoursForTimeSheeet(Time_Sheet ts) throws SQLException, CloneNotSupportedException {
        int res = 0;
        long tsStart = ts.getStartTime().getTime();
        long tsEnd = ts.getEndTime().getTime();
        ArrayList<Time_Sheet> conflicts = new ArrayList();
        ArrayList<Time_Sheet> conflictingTS = timeSheetAccess.getConflictingTimeSheets(ts);
        long alternativeEndTime = 0;
        if(conflictingTS.size() > 0){
            
            for(Time_Sheet conflictingTs : conflictingTS){
                alternativeEndTime = 0;
                if(conflictingTs.getStartTime().getTime() > tsStart && tsEnd > conflictingTs.getStartTime().getTime() && tsEnd > conflictingTs.getEndTime().getTime()){
                    
                    alternativeEndTime = tsEnd - conflictingTs.getEndTime().getTime();
                    tsEnd = conflictingTs.getStartTime().getTime();
                    //System.out.println("TEST " + alternativeEndTime);
                }else if(conflictingTs.getStartTime().getTime() > tsStart && tsEnd > conflictingTs.getStartTime().getTime()){
                    if(tsEnd <= conflictingTs.getEndTime().getTime())
                        tsEnd = conflictingTs.getStartTime().getTime();
                }
                if(ts.getStartTime().getTime() == conflictingTs.getStartTime().getTime() && ts.getEndTime().getTime() == conflictingTs.getEndTime().getTime() && ts.getId() > conflictingTs.getId()){
                    tsStart = 0;
                    tsEnd = 0;
                }
                if(ts.getStartTime().getTime() == conflictingTs.getStartTime().getTime() && ts.getEndTime().getTime() < conflictingTs.getEndTime().getTime()){
                    tsStart = 0;
                    tsEnd = 0;
                }
            }
            double hours = tsEnd - tsStart + alternativeEndTime;
            
            hours = hours / (60*60*1000);
            res= (int)Math.ceil(hours);
            
            
        }else{
            if(ts.getEndTime().getTime() - ts.getStartTime().getTime() < (oneHour*2))
                res = 2;
            else {
                double hours = ts.getEndTime().getTime() - ts.getStartTime().getTime();
                hours = hours / (60*60*1000);
                res = (int)Math.ceil(hours);
            }
        }
        
        
        return res;
    }
    
    
    
}
