/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BE;

/**
 *
 * @author Niels Kristian Ravn
 */
public class Usage {
    
    int alarmId, carNr, equipmentId, amount;
    boolean loadedFromDatabase = false;

    public Usage(int alarmId, int carNr, int equipmentId, int amount) {
        this.alarmId = alarmId;
        this.carNr = carNr;
        this.equipmentId = equipmentId;
        this.amount = amount;
    }
    
    public Usage(int alarmId, int carNr, int equipmentId, int amount, boolean loadedFromDatabase) {
        this.alarmId = alarmId;
        this.carNr = carNr;
        this.equipmentId = equipmentId;
        this.amount = amount;
        this.loadedFromDatabase = loadedFromDatabase;
    }
    public Usage() {
        amount = 0;
    }
    /**
     * @return if the usage is loaded from the database
     */
    public boolean isLoadedFromDatabase() {
        return loadedFromDatabase;
    }
    /**
     * @return the alarmid
     */
    public int getAlarmId() {
        return alarmId;
    }
    /**
     * @return the car number
     */
    public int getCarNr() {
        return carNr;
    }
    /**
     * @return the equipment id
     */
    public int getEquipmentId() {
        return equipmentId;
    }
    /**
     * @return the amount
     */
    public int getAmount() {
        return amount;
    }
    /**
     * Sets a new amount
     * @param amount the new amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    
    
}
