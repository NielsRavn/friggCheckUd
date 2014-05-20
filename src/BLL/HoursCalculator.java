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

/**
 *
 * @author Brobak
 */
public class HoursCalculator implements IHoursCalculator{
    ITimeSheet_Access timeSheetAccess;
    public HoursCalculator() throws IOException{
        timeSheetAccess = new TimeSheet_Access();
        
    }
    
    public HoursCalculator(ITimeSheet_Access tsa){
        timeSheetAccess = tsa;
    }
    
    

    @Override
    public Hours getHoursForTimeSheeet(Time_Sheet timeSheet) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
