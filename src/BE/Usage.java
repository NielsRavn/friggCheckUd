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

    public boolean isLoadedFromDatabase() {
        return loadedFromDatabase;
    }
    
    public int getAlarmId() {
        return alarmId;
    }

    public int getCarNr() {
        return carNr;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    
    
}
