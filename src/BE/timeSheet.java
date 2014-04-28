/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BE;

/**
 *
 * @author Susanne
 */
public class timeSheet {
    private int employeeID;
    private int alarmID;
    private int carNr;
    private int positionID;
    private String endTime;
    
    private timeSheet (int employeeID, int alarmID, int carNr, int positionID, String endTime) {
        this.employeeID = employeeID;
        this.alarmID = alarmID;
        this.carNr = carNr;
        this.positionID = positionID;
        this.endTime = endTime;
        
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
    public String getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
