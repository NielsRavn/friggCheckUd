/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BE;

import java.sql.Timestamp;

/**
 *
 * @author Brobak
 */
public class TimeSheetTimeSlot {
    
    Timestamp startTime, endTime;

    /**
     * Creates a new TimeSheetTimeSlot
     * @param startTime the startTime of the timeslot
     * @param endTime the endtime of the timeslot
     */
    public TimeSheetTimeSlot(Timestamp startTime, Timestamp endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    /**
     * Gets the starttime
     * @return the starttime
     */
    public Timestamp getStartTime() {
        return startTime;
    }

    /**
     * Gets the endtime
     * @return the endtime
     */
    public Timestamp getEndTime() {
        return endTime;
    }
}
