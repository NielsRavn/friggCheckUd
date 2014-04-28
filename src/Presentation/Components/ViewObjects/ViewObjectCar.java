/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Components.ViewObjects;

import BE.Car;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Brobak
 */
public class ViewObjectCar extends ViewObject{
    ImageIcon carIcon;
    int carNr;
    public ViewObjectCar(Car car){
        setLayout(new FlowLayout());
        carIcon = new ImageIcon(car.getIconPath());
        carNr = car.getCarNr();
        
        fillData();
    }

    private void fillData() {
        add(new JLabel(carIcon));
        add(new JLabel(""+carNr));
    }
    
}
