/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Components.ViewObjects;

import BE.Alarm;
import BE.Car;
import BE.Position;

/**
 *
 * @author Brobak
 */
public abstract class ViewObjectFactory {
    
    public ViewObjectCar getViewObject(Car car){
        return new ViewObjectCar(car);
    }
    
    public ViewObjectAlarm getViewObject(Alarm alarm){
        return new ViewObjectAlarm(alarm);
    }
    
    public ViewObjectPosition getViewObject(Position position){
        return new ViewObjectPosition(position);
    }
    
}
