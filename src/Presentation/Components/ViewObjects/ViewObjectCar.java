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
    Car car;
    public ViewObjectCar(Car car){
        super(car);
        this.car = car;
        
        
    }

    @Override
    protected void fillData() {
        setLayout(new FlowLayout());
        add(new JLabel(new ImageIcon(car.getIconPath())));
        JLabel carInfo = new JLabel(""+car.getCarNr());
        carInfo.setFont(new Font("Verdana", Font.PLAIN, 42));
        add(carInfo);
    }
    
    public Car getCar(){
        return car;
    }

    @Override
    public void refreshViewObject() {
        
    }
    
}
