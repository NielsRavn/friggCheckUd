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
public class Car implements ViewObjectBE{
    private int carNr;
    private String iconPath;
    private String name;
    private int seats;
    
    public Car (int carNr, String iconPath, String name, int seats) {
        this.carNr = carNr;
        this.iconPath = iconPath;
        this.name = name;
        this.seats = seats;
        
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
     * @return the iconPath
     */
    public String getIconPath() {
        return iconPath;
    }

    /**
     * @param iconPath the iconPath to set
     */
    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the seats
     */
    public int getSeats() {
        return seats;
    }

    /**
     * @param seats the seats to set
     */
    public void setSeats(int seats) {
        this.seats = seats;
    }
}
