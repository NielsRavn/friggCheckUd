/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BE;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author Susanne
 */
public class Alarm implements IViewObjectBE{
    private int ID;
    private int odinNr;
    private String distination;
    private String type;
    private Timestamp time;
    private boolean accepted;
    private boolean exercise;
    
    
    public Alarm (int ID, int odinNr, String distination, String type, Timestamp time, boolean accepted, boolean exercise) {
    this.ID = ID;
    this.odinNr = odinNr;
    this.distination = distination;
    this.type = type;
    this.time = time;
    this.accepted = accepted;
    this.exercise = exercise;
    }

    public Alarm(int odinNr, String destination, String type, Timestamp date, boolean exercise) {
        this.odinNr = odinNr;
        this.distination = destination;
        this.type = type;
        this.time = date;
        this.exercise = exercise;
    }
    public Alarm(String destination, String type, Timestamp date, boolean exercise) {
        this.distination = destination;
        this.type = type;
        this.time = date;
        this.exercise = exercise;
    }


    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * @return the odinNr
     */
    public int getOdinNr() {
        return odinNr;
    }

    /**
     * @param odinNr the odinNr to set
     */
    public void setOdinNr(int odinNr) {
        this.odinNr = odinNr;
    }

    /**
     * @return the distination
     */
    public String getDistination() {
        return distination;
    }

    /**
     * @param distination the distination to set
     */
    public void setDistination(String distination) {
        this.distination = distination;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the time
     */
    public Timestamp getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(Timestamp time) {
        this.time = time;
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
    
    public String getName(){
        return ""+ID;
    }
    
    public boolean isExercise(){
        return exercise;
    }
    
}
