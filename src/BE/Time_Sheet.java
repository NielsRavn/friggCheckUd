/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BE;

import java.sql.Date;

/**
 *
 * @author Susanne
 */
public class Time_Sheet {
    private int employeeID;
    private int alarmID;
    private int carNr;
    private int positionID;
    private Date endTime;
    private boolean accepted;
    
    private Time_Sheet (int employeeID, int alarmID, int carNr, int positionID, Date endTime, boolean accepted) {
        this.employeeID = employeeID;
        this.alarmID = alarmID;
        this.carNr = carNr;
        this.positionID = positionID;
        this.endTime = endTime;
        this.accepted = accepted;
        
                }

    /**
     * @return the employeeID
     */
    public int getEmployeeID() {
        return employeeID;
    }

    /**
     * @param employeeID the employeeID to set
     */
    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    /**
     * @return the alarmID
     */
    public int getAlarmID() {
        return alarmID;
    }

    /**
     * @param alarmID the alarmID to set
     */
    public void setAlarmID(int alarmID) {
        this.alarmID = alarmID;
    }

    /**
     * @return the carNr
     */
    public int getCarNr() {
        return carNr;
    }

    /**
     * @param carNr the carNr to set
     */
    public void setCarNr(int carNr) {
        this.carNr = carNr;
    }

    /**
     * @return the positionID
     */
    public int getPositionID() {
        return positionID;
    }

    /**
     * @param positionID the positionID to set
     */
    public void setPositionID(int positionID) {
        this.positionID = positionID;
    }

    /**
     * @return the endTime
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the accepted
     */
    public boolean isAccepted() {
        return accepted;
    }

    /**
     * @param accepted the accepted to set
     */
    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
