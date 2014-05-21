/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import BE.Time_Sheet;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Brobak
 */
public interface ITimeSheet_Access {
    
    /**
     * Gets all timeSheets for the same fireman  the given timeSheet
     * @param timeSheet the timesheet to which you want to find conflicts
     * @return a list containing all timeSheets conflicting with 
     */
    public ArrayList<Time_Sheet> getConflictingTimeSheets(Time_Sheet timeSheet)throws SQLServerException, SQLException;
    
}
