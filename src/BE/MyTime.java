/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BE;

import java.sql.Timestamp;

/**
 *
 * @author Niels
 */
public class MyTime {
    
    Timestamp startDate, alarmStartDate;
    private int startHour, startMinute, endHour, endMinute;

    public MyTime(Timestamp startDate, Timestamp alarmStartDate, int startHour, int startMinute, int endHour, int endMinute) {
        this.startDate = (Timestamp) startDate.clone();
        this.alarmStartDate = (Timestamp) alarmStartDate.clone();
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.endHour = endHour;
        this.endMinute = endMinute;
    }
    
    public MyTime(int startHour, int startMinute, int endHour, int endMinute) {
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.endHour = endHour;
        this.endMinute = endMinute;
    }

    public MyTime(int startHour, int startMinute) {
        this.startHour = startHour;
        this.startMinute = startMinute;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getAlarmStartDate() {
        return alarmStartDate;
    }
    
    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getStartMinute() {
        return startMinute;
    }

    public void setStartMinute(int startMinute) {
        this.startMinute = startMinute;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public int getEndMinute() {
        return endMinute;
    }

    public void setEndMinute(int endMinute) {
        this.endMinute = endMinute;
    }
    
    
    
}
