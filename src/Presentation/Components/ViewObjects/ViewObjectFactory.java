/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Components.ViewObjects;

import BE.Alarm;
import BE.Car;
import BE.Comment;
import BE.Equipment;
import BE.EquipmentStatus;
import BE.EquipmentUsage;
import BE.IViewObjectBE;
import BE.MyTime;
import BE.Position;
import BE.Station;
import BE.Story;
import BE.TimeSheetList;
import BE.Time_Sheet;

/**
 *
 * @author Brobak
 */
public class ViewObjectFactory {
    
    /**
     * Gets an appropriate view object for a given view object BE
     * @param viewObjectBE the BE you want a view object for
     * @return an appropriate view object for a given view object BE
     */
    public static ViewObject getViewObject(IViewObjectBE viewObjectBE){
        if(viewObjectBE.getClass() == Car.class)
            return new ViewObjectCar((Car)viewObjectBE);
        else if(viewObjectBE.getClass() == Alarm.class)
            return new ViewObjectAlarm((Alarm)viewObjectBE);
        else if (viewObjectBE.getClass() == Position.class)
            return new ViewObjectPosition((Position)viewObjectBE);
        else if(viewObjectBE.getClass() == Station.class)
           return new ViewObjectStationDuty((Station)viewObjectBE);
        else if(viewObjectBE.getClass() == EquipmentUsage.class)
            return new ViewObjectEquipmentUsage((EquipmentUsage)viewObjectBE);
        else if(viewObjectBE.getClass() == EquipmentStatus.class)
            return new ViewObjectEquipmentStatus((EquipmentStatus)viewObjectBE);
        else if(viewObjectBE.getClass() == Comment.class)
            return new ViewObjectComment((Comment)viewObjectBE);
        else if(viewObjectBE.getClass() == Story.class)
            return new ViewObjectStory((Story)viewObjectBE);
        else if(viewObjectBE.getClass() == MyTime.class)
            return new ViewObjectTime((MyTime)viewObjectBE);
        else if(viewObjectBE.getClass() == TimeSheetList.class)
            return new ViewObjectTimeSheet((TimeSheetList)viewObjectBE);
        else
            return new ViewObjectBasic(viewObjectBE); 
    }  
}
