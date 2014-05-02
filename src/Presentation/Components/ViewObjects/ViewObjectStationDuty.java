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
    public ViewObjectStationDuty(Station station){
        setLayout(new FlowLayout());
        icon = new ImageIcon(station.getIconPath());
        name = station.getName();
        
        fillData();
    }

    private void fillData() {
        add(new JLabel(icon));
        JLabel carInfo = new JLabel(name);
        carInfo.setFont(new Font("Verdana", Font.PLAIN, 42));
        add(carInfo);
    }
}
