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
public class MyTime implements IViewObjectBE {
    
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

    /**
     * @return the startdate
     */
    public Timestamp getStartDate() {
        return startDate;
    }

    /**
     * Sets a new startdate
     * @param startDate the new startdate
     */
    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the alarm start date
     */
    public Timestamp getAlarmStartDate() {
        return alarmStartDate;
    }
    
    /**
     * @return the start hour
     */
    public int getStartHour() {
        return startHour;
    }
    
    /**
     * Sets a new start hour
     * @param startHour the new start hour
     */
    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    /**
     * @return the start minute
     */
    public int getStartMinute() {
        return startMinute;
    }

    /**
     * Sets a new start minute
     * @param startMinute the new start minute
     */
    public void setStartMinute(int startMinute) {
        this.startMinute = startMinute;
    }

    /**
     * @return the end hour
     */
    public int getEndHour() {
        return endHour;
    }
    
    /**
     * Sets a new end hour
     * @param endHour the new end hour
     */
    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }
    
    /**
     * @return the end minute
     */
    public int getEndMinute() {
        return endMinute;
    }

    /**
     * Sets a new end minute
     * @param endMinute the new end minute
     */
    public void setEndMinute(int endMinute) {
        this.endMinute = endMinute;
    }

    /**
     * @return a string representation of the object
     */
    @Override
    public String getName() {
        return "Starttid: ["+ getStartHour() + ":" + getStartMinute() + "] Sluttid: ["+ getEndHour() + ":" + getEndMinute() + "]";
    }
    
    
    
}
