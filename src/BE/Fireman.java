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
public class Fireman {
    private int ID;
    private String firstName;
    private String lastName;
    private boolean teamLeader;
    private boolean driver;

    public Fireman (int ID, String firstName, String lastName, boolean teamLeader, boolean driver) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.teamLeader = teamLeader;
        this.driver = driver;
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
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the teamLeader
     */
    public boolean isTeamLeader() {
        return teamLeader;
    }

    /**
     * @param teamLeader the teamLeader to set
     */
    public void setTeamLeader(boolean teamLeader) {
        this.teamLeader = teamLeader;
    }

    /**
     * @return the driver
     */
    public boolean isDriver() {
        return driver;
    }

    /**
     * @param driver the driver to set
     */
    public void setDriver(boolean driver) {
        this.driver = driver;
    }
}
