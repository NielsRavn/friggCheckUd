/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Components.ViewObjects;

import BE.Car;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
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
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
    }

    private void fillData() {
        add(new JLabel(carIcon));
        JLabel carInfo = new JLabel(""+carNr);
        carInfo.setFont(new Font("Comic Sans", Font.PLAIN, 42));
        add(carInfo);
    }
    
}
