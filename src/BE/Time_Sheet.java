/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BE;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

/**
 *
 * @author Susanne
 */
public class Time_Sheet implements ViewObjectBE{
    private int employeeID;
    private int alarmID;
    private int carNr;
    private int positionID;
    private Position position;
    private Fireman fireman;
    private Alarm alarm;
    private Car car;
    private Time startTime, endTime;
    private boolean accepted;

    public Time_Sheet(int employeeID, int alarmID, int carNr, int positionID, Time startTime, Time endTime, boolean accepted) {
        this.employeeID = employeeID;
        this.alarmID = alarmID;
        this.carNr = carNr;
        this.positionID = positionID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.accepted = accepted;
    }

    public Time_Sheet(int employeeID, int alarmID, int carNr, int positionID, Time startTime, Time endTime) {
        this.employeeID = employeeID;
        this.alarmID = alarmID;
        this.carNr = carNr;
        this.positionID = positionID;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Time_Sheet(int employeeID, int alarmID, int carNr, Position position, Time startTime, Time endTime) {
        this.employeeID = employeeID;
        this.alarmID = alarmID;
        this.carNr = carNr;
        this.position = position;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Time_Sheet(Fireman a, Alarm b, Car c, Position d, Time startTime, Time endTime, int firemenPositionId) {
        this.fireman = a;
        this.alarm = b;
        this.car = c;
        this.position = d;
        this.startTime = startTime;
        this.endTime = endTime;
        this.positionID = firemenPositionId;
    }
    
    

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
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
    public Time getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(Time endTime) {
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

    /**
     * @return the position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * @return the fireman
     */
    public Fireman getFireman() {
        return fireman;
    }

    /**
     * @param fireman the fireman to set
     */
    public void setFireman(Fireman fireman) {
        this.fireman = fireman;
    }

    /**
     * @return the alarm
     */
    public Alarm getAlarm() {
        return alarm;
    }

    /**
     * @param alarm the alarm to set
     */
    public void setAlarm(Alarm alarm) {
        this.alarm = alarm;
    }

    /**
     * @return the car
     */
    public Car getCar() {
        return car;
    }

    /**
     * @param car the car to set
     */
    public void setCar(Car car) {
        this.car = car;
    }
}
