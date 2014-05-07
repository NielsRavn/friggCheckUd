/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Components.ViewObjects;

import BE.Alarm;
import BE.Car;
import BE.Equipment;
import BE.Position;
import BE.Station;
import BE.Time_Sheet;
import BE.ViewObjectBE;

/**
 *
 * @author Brobak
 */
public class ViewObjectFactory {
    
    public ViewObject getViewObject(ViewObjectBE viewObjectBE){
        if(viewObjectBE.getClass() == Car.class)
            return getViewObject((Car) viewObjectBE);
        else if(viewObjectBE.getClass() == Alarm.class)
            return getViewObject((Alarm) viewObjectBE);
        else if (viewObjectBE.getClass() == Position.class)
            return getViewObject((Position) viewObjectBE);
        else if(viewObjectBE.getClass() == Station.class)
            return getViewObject((Station) viewObjectBE);
//        else if(viewObjectBE.getClass() == Equipment.class)
//            return getViewObject((Equipment) viewObjectBE);
        else if(viewObjectBE.getClass() == Time_Sheet.class)
            return getViewObject((Time_Sheet) viewObjectBE);
        return null;
    }
    
    private ViewObjectCar getViewObject(Car car){
        return new ViewObjectCar(car);
    }
    
    private ViewObjectAlarm getViewObject(Alarm alarm){
        return new ViewObjectAlarm(alarm);
    }
    
    private ViewObjectPosition getViewObject(Position position){
        return new ViewObjectPosition(position);
    }
    
    private ViewObjectStationDuty getViewObject(Station station){
        return new ViewObjectStationDuty(station);
    }
    
    private ViewObjectEquipmentUsage getViewObject(Equipment equipment){
        return new ViewObjectEquipmentUsage(equipment);
    }
    
    private ViewObjectTimeSheet getViewObject(Time_Sheet timeSheet){
        return new ViewObjectTimeSheet(timeSheet);
    }
    
}
