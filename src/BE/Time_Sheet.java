/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BE;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;

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
    private int accepted;
    private ArrayList<Comment> comments;
    /**
     * 
     * @param employeeID
     * @param alarmID
     * @param carNr
     * @param positionID
     * @param startTime
     * @param endTime
     * @param accepted 
     */
    public Time_Sheet(int id, int employeeID, int alarmID, int carNr, int positionID, Timestamp startTime, Timestamp endTime, int accepted, ArrayList<Comment> comments) {
        this.id = id;
        this.employeeID = employeeID;
        this.alarmID = alarmID;
        this.carNr = carNr;
        this.positionID = positionID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.accepted = accepted;
        this.comments = comments;
    }
    public Time_Sheet(int employeeID, int alarmID, int carNr, int positionID, Timestamp startTime, Timestamp endTime, int accepted, ArrayList<Comment> comments) {
        this.employeeID = employeeID;
        this.alarmID = alarmID;
        this.carNr = carNr;
        this.positionID = positionID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.accepted = accepted;
        this.comments = comments;
    }
    /**
     * 
     * @param employeeID
     * @param alarmID
     * @param carNr
     * @param positionID
     * @param startTime
     * @param endTime 
     */
    public Time_Sheet(int id, int employeeID, int alarmID, int carNr, int positionID, Timestamp startTime, Timestamp endTime, ArrayList<Comment> comments) {
        this.id = id;
        this.employeeID = employeeID;
        this.alarmID = alarmID;
        this.carNr = carNr;
        this.positionID = positionID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.comments = comments;
    }
    /**
     * 
     * @param employeeID
     * @param alarmID
     * @param carNr
     * @param position
     * @param startTime
     * @param endTime 
     */
    public Time_Sheet(int id, int employeeID, int alarmID, int carNr, Position position, Timestamp startTime, Timestamp endTime, ArrayList<Comment> comments) {
        this.id = id;
        this.employeeID = employeeID;
        this.alarmID = alarmID;
        this.carNr = carNr;
        this.position = position;
        this.startTime = startTime;
        this.endTime = endTime;
        this.comments = comments;
    }
    /**
     * Is used with approve timesheeet with timesheets for firemen/teamleaders/drivers
     * @param a
     * @param b
     * @param c
     * @param d
     * @param startTime
     * @param endTime
     * @param firemenPositionId 
     */
    public Time_Sheet(int id, Fireman a, Alarm b, Car c, Position d, Timestamp startTime, Timestamp endTime, int firemenPositionId, ArrayList<Comment> comments, int accepted) {
        this.id = id;
        this.fireman = a;
        this.alarm = b;
        this.car = c;
        this.position = d;
        this.startTime = startTime;
        this.endTime = endTime;
        this.positionID = firemenPositionId;
        this.comments = comments;
        this.accepted = accepted;
    }
   /**
    * Is used with approve timesheeet with timesheets for stations duty
    * @param a
    * @param b
    * @param c
    * @param d
    * @param startTime
    * @param endTime
    * @param firemenPositionId 
    */
    public Time_Sheet(int id, Fireman a, Alarm b, Station c, Position d, Timestamp startTime, Timestamp endTime, int firemenPositionId, ArrayList<Comment> comments, int accepted) {
        this.id = id;
        this.fireman = a;
        this.alarm = b;
        this.station = c;
        this.position = d;
        this.startTime = startTime;
        this.endTime = endTime;
        this.positionID = firemenPositionId;
        this.comments = comments;
        this.accepted = accepted;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void addComments(Comment comment) {
        comments.add(comment);
    }
    
    public void removeComments(Comment comment) {
        comments.remove(comment);
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
    public int isAccepted() {
        return accepted;
    }

    /**
     * @param accepted the accepted to set
     */
    public void setAccepted(int accepted) {
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
