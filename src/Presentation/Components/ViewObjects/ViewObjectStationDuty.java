/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Components.ViewObjects;

import BE.Station;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Brobak
 */
public class ViewObjectStationDuty extends ViewObject{
    ImageIcon icon;
    String name;
    Station station;
    /**
     * Creates a new ViewObjectStationDuty
     * @param station the Station you want represented in the view object
     */
    protected ViewObjectStationDuty(Station station){
        super(station);
        this.station = station;
        icon = new ImageIcon(station.getIconPath());
        name = station.getName();
        fillData();
    }

    /**
     * Gets the station represented in the view object
     * @return the station represented in the view object
     */
    public Station getStation() {
        return station;
    }

    /**
     * fills the view object with nessesary data
     */
    protected void fillData() {
        setLayout(new FlowLayout());
        
        add(new JLabel(icon));
        JLabel carInfo = new JLabel(name);
        carInfo.setFont(new Font("Verdana", Font.PLAIN, 42));
        add(carInfo);
    }

    /**
     * does nothing
     */
    @Override
    public void refreshViewObject() {
        
    }
}
