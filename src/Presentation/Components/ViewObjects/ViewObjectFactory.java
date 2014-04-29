/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Components.ViewObjects;

import BE.Alarm;
import BE.Car;
import BE.Position;
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
    
}
