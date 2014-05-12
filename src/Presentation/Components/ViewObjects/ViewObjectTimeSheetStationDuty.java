/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Components.ViewObjects;

import BE.Fireman;
import BE.Station;
import BE.Time_Sheet;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Poul Nielsen
 */
public class ViewObjectTimeSheetStationDuty  extends ViewObject {
    Station station;
    ArrayList<Time_Sheet> sd;
    int FZ = 30;
    
    public ViewObjectTimeSheetStationDuty(Station station)
    {
        this.station = station;
        sd = new ArrayList<>();
        setLayout(new BorderLayout());
        fillData();
    }
    
    
    private void fillData()
    {
        JPanel vest = new JPanel();
        JPanel center = new JPanel();
        vest.setLayout(new FlowLayout());
        center.setLayout(new GridLayout(0, 1));
        
                vest.add(new JLabel(new ImageIcon(station.getIconPath())));
                JLabel stationInfo = new JLabel(""+station.getName());
                stationInfo.setFont(new Font("Verdana", Font.PLAIN, FZ));
                vest.add(stationInfo);
        
                for(Time_Sheet a : sd)
                {
                    JLabel fm = new JLabel("ST : "+ a.getFireman().getFirstName()+" "+a.getFireman().getLastName());
                    fm.setFont(new Font("Verdana", Font.PLAIN, FZ)); 
                    center.add(fm);
                }
        add(center, BorderLayout.CENTER);
        add(vest, BorderLayout.WEST);
    }
    
    public void addStationDuty(Time_Sheet a) {
        sd.add(a);
        fillData();
    }
}

