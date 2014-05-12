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
    
    public static ViewObject getViewObject(ViewObjectBE viewObjectBE){
        if(viewObjectBE.getClass() == Car.class)
            return new ViewObjectCar((Car)viewObjectBE);
        else if(viewObjectBE.getClass() == Alarm.class)
            return new ViewObjectAlarm((Alarm)viewObjectBE);
        else if (viewObjectBE.getClass() == Position.class)
            return new ViewObjectPosition((Position)viewObjectBE);
        else if(viewObjectBE.getClass() == Station.class)
           return new ViewObjectStationDuty((Station)viewObjectBE);
        else if(viewObjectBE.getClass() == Equipment.class)
            return new ViewObjectEquipmentUsage((Equipment)viewObjectBE);
        else
            return new ViewObjectBasic(viewObjectBE); 
    }
    
    
}
