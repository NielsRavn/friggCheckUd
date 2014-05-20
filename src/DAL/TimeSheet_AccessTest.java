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
    public TimeSheet_AccessTest(){
        timeSheets = new ArrayList<>();
    }
    
    @Override
    public ArrayList<Time_Sheet> getConflictingTimeSheets(Time_Sheet timeSheet) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void addTimeSheet(Time_Sheet ts){
        timeSheets.add(ts);
    }
    
    public void removeTimeSheet(Time_Sheet ts){
        timeSheets.remove(ts);
    }
    
}
