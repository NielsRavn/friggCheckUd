/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BE;

import java.sql.Timestamp;

/**
 *
 * @author Susanne
 */
public class Time_Sheet implements IViewObjectBE{
    private int id;
    private int employeeID;
    private int alarmID;
    private int carNr;
    private int positionID;
    private Position position;
    private Fireman fireman;
    private Alarm alarm;
    private Car car;
    private Station station;
    private Timestamp startTime, endTime;
    private int accedtedByTeamLeader;
    private int acceptedForSallary;
    private boolean addedToPayment;
    private Comment comment;
   
    public Time_Sheet(int id, int employeeID, int alarmID, int carNr, int positionID, Timestamp startTime, Timestamp endTime, int accedtedByTeamLeader, int acceptedForSallary, boolean addedToPayment, Comment comment) {
        this.id = id;
        this.employeeID = employeeID;
        this.alarmID = alarmID;
        this.carNr = carNr;
        this.positionID = positionID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.accedtedByTeamLeader = accedtedByTeamLeader;
        this.acceptedForSallary = acceptedForSallary;
        this.addedToPayment = addedToPayment;
        this.comment = comment;
    }
    public Time_Sheet(int employeeID, int alarmID, int carNr, int positionID, Timestamp startTime, Timestamp endTime, int accedtedByTeamLeader, int acceptedForSallary, boolean addedToPayment, Comment comment) {
        this.employeeID = employeeID;
        this.alarmID = alarmID;
        this.carNr = carNr;
        this.positionID = positionID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.accedtedByTeamLeader = accedtedByTeamLeader;
        this.acceptedForSallary = acceptedForSallary;
        this.addedToPayment = addedToPayment;
        this.comment = comment;
    }
    
    public Time_Sheet(int id, int employeeID, int alarmID, int carNr, Position position, Timestamp startTime, Timestamp endTime,int accedtedByTeamLeader, int acceptedForSallary, boolean addedToPayment, Comment comment) {
        this.id = id;
        this.employeeID = employeeID;
        this.alarmID = alarmID;
        this.carNr = carNr;
        this.position = position;
        this.startTime = startTime;
        this.endTime = endTime;
        this.accedtedByTeamLeader = accedtedByTeamLeader;
        this.acceptedForSallary = acceptedForSallary;
        this.addedToPayment = addedToPayment;
        this.comment = comment;
    }
    
    public Time_Sheet(int id, Fireman a, Alarm b, Car c, Position d, Timestamp startTime, Timestamp endTime, int firemenPositionId, int accedtedByTeamLeader, int acceptedForSallary, boolean addedToPayment, Comment comment) {
        this.id = id;
        this.fireman = a;
        this.alarm = b;
        this.car = c;
        this.position = d;
        this.startTime = startTime;
        this.endTime = endTime;
        this.positionID = firemenPositionId;
        this.accedtedByTeamLeader = accedtedByTeamLeader;
        this.acceptedForSallary = acceptedForSallary;
        this.addedToPayment = addedToPayment;
        this.comment = comment;
    }
   
    public Time_Sheet(int id, Fireman a, Alarm b, Station c, Position d, Timestamp startTime, Timestamp endTime, int firemenPositionId, int accedtedByTeamLeader, int acceptedForSallary, boolean addedToPayment, Comment comment) {
        this.id = id;
        this.fireman = a;
        this.alarm = b;
        this.station = c;
        this.position = d;
        this.startTime = startTime;
        this.endTime = endTime;
        this.positionID = firemenPositionId;this.accedtedByTeamLeader = accedtedByTeamLeader;
        this.acceptedForSallary = acceptedForSallary;
        this.addedToPayment = addedToPayment;
        this.comment = comment;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
    
    
    
    
    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }
    
    
    /**
     * Gets the ID for a time sheet
     * @return the id of the time sheet 
     */
    public int getId() {
        return id;
    }
    
    /**
     * Sets the ID for a time sheet 
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
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
    public Timestamp getEndTime() {
        return endTime;
    }

    /**
     * @param endTime the endTime to set
     */
    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the accepted
     */
    public int getAcceptedByTeamleaderId() {
        return accedtedByTeamLeader;
    }

    /**
     * @param accepted the accepted to set
     */
    public void setAcceptedByTeamleaderId(int accepted) {
        this.accedtedByTeamLeader = accepted;
    }
    
    /**
     * @return the accepted
     */
    public int getAcceptedForSalaryId() {
        return acceptedForSallary;
    }

    /**
     * @param accepted the accepted to set
     */
    public void setAcceptedForSalaryId(int accepted) {
        this.acceptedForSallary = accepted;
    }

    public boolean isAddedToPayment() {
        return addedToPayment;
    }

    public void setAddedToPayment(boolean addedToPayment) {
        this.addedToPayment = addedToPayment;
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

    /**
     * @return the station
     */
    public Station getStation() {
        return station;
    }

    /**
     * @param station the station to set
     */
    public void setStation(Station station) {
        this.station = station;
    }
    
    public String getName(){
        return "Alarm: "+ alarmID + " - Brændmand: " + fireman.getID();
    }
}