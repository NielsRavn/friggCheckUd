/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BE;

import Presentation.MyConstants;
import java.util.ArrayList;

/**
 *
 * @author Brobak
 */
public class TimeSheetList implements IViewObjectBE {
    Alarm alarm;
    Car car;
    Station station;
    ArrayList<Time_Sheet> hl;
    ArrayList<Time_Sheet> ch;
    ArrayList<Time_Sheet> bm;
    ArrayList<Time_Sheet> sd;
    public TimeSheetList(Time_Sheet timeSheet){
        car = timeSheet.getCar();
        station = MyConstants.STATION_DUTY_VIEW;
        alarm = timeSheet.getAlarm();
        hl = new ArrayList<>();
        ch = new ArrayList<>();
        bm = new ArrayList<>();
        sd = new ArrayList<>();  
    }
    /**
     * @return the car
     */
    public Car getCar() {
        return car;
    }
    /**
     * @return the alarm
     */
    public Alarm getAlarm() {
        return alarm;
    }
    /**
     * @return the station
     */
    public Station getStation() {
        return station;
    }
    /**
     * @return a list of team leaders
     */
    public ArrayList<Time_Sheet> getTeamleaders() {
        return hl;
    }
    /**
     * @return a list of Chauffeurs
     */
    public ArrayList<Time_Sheet> getChauffeurs() {
        return ch;
    }
    /**
     * @return a list of firefighters
     */
    public ArrayList<Time_Sheet> getFiremen() {
        return bm;
    }
    /**
     * @return a list of firemen on station duty 
     */
    public ArrayList<Time_Sheet> getStationDutyFiremen() {
        return sd;
    }
    /**
     * adds a new teamleader
     * @param a the new teamleader
     */
    public void addTeamLeader(Time_Sheet a) {
        hl.add(a);
    }
    /**
     * adds a new driver
     * @param a the new driver
     */
    public void addDriver(Time_Sheet a) {
        ch.add(a);
    }
    /**
     * Adds a new firefighter
     * @param a the new firefighter
     */
    public void addFireman(Time_Sheet a) {
        bm.add(a);
        
    }
    /**
     * adds a new firefigther on station duty
     * @param a the firefigher
     */
    public void addStationDuty(Time_Sheet a) {
        sd.add(a);
    }
    /**
     * @return a string representation of the timesheet list
     */
    @Override
    public String getName() {
        
        return "";
    }
    
}
