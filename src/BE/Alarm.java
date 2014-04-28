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
public class Alarm {
    private int ID;
    private int odinNr;
    private String distination;
    private String type;
    private Date time;
    private boolean accepted;
    
    public Alarm (int ID, int odinNr, String distination, String type, Date time, boolean accepted) {
    this.ID = ID;
    this.odinNr = odinNr;
    this.distination = distination;
    this.type = type;
    this.time = time;
    this.accepted = accepted;
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
    public Date getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(Date time) {
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
    
}
