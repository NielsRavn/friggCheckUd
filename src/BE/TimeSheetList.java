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
    
    public Car getCar() {
        return car;
    }

    public Alarm getAlarm() {
        return alarm;
    }

    public Station getStation() {
        return station;
    }

    public ArrayList<Time_Sheet> getTeamleaders() {
        return hl;
    }

    public ArrayList<Time_Sheet> getChauffeurs() {
        return ch;
    }

    public ArrayList<Time_Sheet> getFiremen() {
        return bm;
    }

    public ArrayList<Time_Sheet> getStationDutyFiremen() {
        return sd;
    }
    

    public void addTeamLeader(Time_Sheet a) {
        hl.add(a);
    }

    public void addDriver(Time_Sheet a) {
        ch.add(a);
    }

    public void addFireman(Time_Sheet a) {
        bm.add(a);
        
    }
    
    public void addStationDuty(Time_Sheet a) {
        sd.add(a);
    }

    @Override
    public String getName() {
        
        return "";
    }
    
}
