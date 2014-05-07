/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Components.ViewObjects;

import BE.Time_Sheet;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Poul Nielsen
 */
public class ViewObjectTimeSheet extends ViewObject {
    
    Time_Sheet timeSheet;
    
    public ViewObjectTimeSheet(Time_Sheet timeSheet) {
        this.timeSheet = timeSheet;
        setLayout(new FlowLayout());
        
        fillData();
    }
    
    private void fillData()
    {
        //setting the view for the car
        ArrayList<Integer> carId = new ArrayList<>();
        
        if(!carId.contains(timeSheet.getCarNr()   ))
        {
//            add(new JLabel(new ImageIcon(car.getIconPath())));
//            JLabel carInfo = new JLabel(""+car.getCarNr());
//            carInfo.setFont(new Font("Verdana", Font.PLAIN, 42));
//            add(carInfo);
        }
        
    }
    
    public Time_Sheet getTimeSheet()
    {
        return timeSheet;
    }
    
    
}
