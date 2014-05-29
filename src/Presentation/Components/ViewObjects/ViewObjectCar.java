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
    /**
     * Creates a new viewObjectCar
     * @param car the car you want represented in the view object
     */
    protected ViewObjectCar(Car car){
        super(car);
        this.car = car;
        
        fillData();
    }

    /**
     * Fills the viewObject with the nessesary data
     */
    protected void fillData() {
        setLayout(new FlowLayout());
        add(new JLabel(new ImageIcon(car.getIconPath())));
        JLabel carInfo = new JLabel(""+car.getCarNr());
        carInfo.setFont(new Font("Verdana", Font.PLAIN, 42));
        add(carInfo);
    }
    
    /**
     * Gets the car represented in the viewObject
     * @return the car represented in the viewObject
     */
    public Car getCar(){
        return car;
    }
    /**
     * does nothing
     */
    @Override
    public void refreshViewObject() {
        
    }
    
}
