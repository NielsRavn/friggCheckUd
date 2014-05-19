/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BE;

/**
 *
 * @author Poul Nielsen
 */
public class ApprovalSheet {
    private int id;
    private Fireman fireman;
    private String comment;
    private boolean approved;
    private int hours;
    
    public ApprovalSheet(int id, Fireman fireman, String comment, boolean approved, int hours)
    {
        this.id = id;
        this.fireman = fireman;
        this.comment = comment;
        this.approved = approved;
        this.hours = hours;
    }
    
    public ApprovalSheet(Fireman fireman, String comment, boolean approved, int hours)
    {
        this.fireman = fireman;
        this.comment = comment;
        this.approved = approved;
        this.hours = hours;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
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
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return the approved
     */
    public boolean isApproved() {
        return approved;
    }

    /**
     * @param approved the approved to set
     */
    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    /**
     * @return the hours
     */
    public int getHours() {
        return hours;
    }

    /**
     * @param hours the hours to set
     */
    public void setHours(int hours) {
        this.hours = hours;
    }
    
}
