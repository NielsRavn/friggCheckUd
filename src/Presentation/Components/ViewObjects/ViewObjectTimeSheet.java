/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Components.ViewObjects;

import BE.Alarm;
import BE.Car;
import BE.Fireman;
import BE.Station;
import BE.Time_Sheet;
import Presentation.MyConstants;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Poul Nielsen
 */
public class ViewObjectTimeSheet extends ViewObject {
    Alarm alarm;
    Car car;
    Station station;
    ArrayList<Time_Sheet> hl;
    ArrayList<Time_Sheet> ch;
    ArrayList<Time_Sheet> bm;
    ArrayList<Time_Sheet> sd;
    public int getCarId;
    int FZ = 30;
    
    public ViewObjectTimeSheet( Time_Sheet timesheet) {
        this.car = timesheet.getCar();
        this.station = timesheet.getStation();
        setLayout(new GridBagLayout());
        hl = new ArrayList<>();
        ch = new ArrayList<>();
        bm = new ArrayList<>();
        sd = new ArrayList<>();
        
        fillDatanew();
    }
    
    
    private void fillDatanew()
    {
        JPanel left = new JPanel();
        left.setLayout(new GridBagLayout());
        JPanel right = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.CENTER;
        
                if(car == null)
                {
                    c.gridx = 0;
                    c.gridy = 1;
                    left.add(new JLabel(new ImageIcon(station.getIconPath())), c);
                    
                    JLabel stationInfo = new JLabel(""+station.getName());
                    stationInfo.setFont(new Font("Verdana", Font.PLAIN, FZ));
                    c.gridx = 0;
                    c.gridy = 0;
                    left.add(stationInfo, c);
                }
                else
                {
                    c.gridx = 0;
                    c.gridy = 1;
                    left.add(new JLabel(new ImageIcon(car.getIconPath())), c);
                    
                    JLabel carInfo = new JLabel(""+car.getCarNr());
                    carInfo.setFont(new Font("Verdana", Font.PLAIN, FZ));
                    c.gridx = 0;
                    c.gridy = 0;
                    left.add(carInfo, c);
                }
        
                for(Time_Sheet a : hl)
                {
                    JLabel fm = new JLabel("HL : "+ a.getFireman().getFirstName()+" "+a.getFireman().getLastName());
                    fm.setFont(new Font("Verdana", Font.PLAIN, FZ));
                    right.add(fm);
                }
                for(Time_Sheet a : ch)
                {
                    JLabel fm = new JLabel("CH : "+ a.getFireman().getFirstName()+" "+a.getFireman().getLastName());
                    fm.setFont(new Font("Verdana", Font.PLAIN, FZ)); 
                    right.add(fm);
                }
                for(Time_Sheet a : bm)
                {
                    JLabel fm = new JLabel("BM : "+ a.getFireman().getFirstName()+" "+a.getFireman().getLastName());
                    fm.setFont(new Font("Verdana", Font.PLAIN, FZ)); 
                    right.add(fm);
                }
                for(Time_Sheet a : sd)
                {
                    JLabel fm = new JLabel("BM : "+ a.getFireman().getFirstName()+" "+a.getFireman().getLastName());
                    fm.setFont(new Font("Verdana", Font.PLAIN, FZ)); 
                    right.add(fm);
                }
                
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.LINE_START;
        add(left, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.LINE_END;
        add(right, gbc);
        
        
    }
    
    
    
    private void fillData()
    {
        JPanel vest = new JPanel();
        JPanel center = new JPanel();
        vest.setLayout(new FlowLayout());
        center.setLayout(new GridLayout(0, 1));
                
                if(car == null)
                {
                    vest.add(new JLabel(new ImageIcon(station.getIconPath())));
                    JLabel stationInfo = new JLabel(""+station.getName());
                    stationInfo.setFont(new Font("Verdana", Font.PLAIN, FZ));
                    vest.add(stationInfo);
                }
                else
                {
                    vest.add(new JLabel(new ImageIcon(car.getIconPath())));
                    JLabel carInfo = new JLabel(""+car.getCarNr());
                    carInfo.setFont(new Font("Verdana", Font.PLAIN, FZ));
                    vest.add(carInfo);
                }
        
                
       
                for(Time_Sheet a : hl)
                {
                    JLabel fm = new JLabel("HL : "+ a.getFireman().getFirstName()+" "+a.getFireman().getLastName());
                    fm.setFont(new Font("Verdana", Font.PLAIN, FZ));
                    center.add(fm);
                }
                for(Time_Sheet a : ch)
                {
                    JLabel fm = new JLabel("CH : "+ a.getFireman().getFirstName()+" "+a.getFireman().getLastName());
                    fm.setFont(new Font("Verdana", Font.PLAIN, FZ)); 
                    center.add(fm);
                }
                for(Time_Sheet a : bm)
                {
                    JLabel fm = new JLabel("BM : "+ a.getFireman().getFirstName()+" "+a.getFireman().getLastName());
                    fm.setFont(new Font("Verdana", Font.PLAIN, FZ)); 
                    center.add(fm);
                }
                for(Time_Sheet a : sd)
                {
                    JLabel fm = new JLabel("BM : "+ a.getFireman().getFirstName()+" "+a.getFireman().getLastName());
                    fm.setFont(new Font("Verdana", Font.PLAIN, FZ)); 
                    center.add(fm);
                }
               
        add(vest , BorderLayout.WEST);
        add(center , BorderLayout.CENTER);
    }
        

    public Object getCar() {
        return car;
    }

    public void addTeamLeader(Time_Sheet a) {
        hl.add(a);
        fillDatanew();
    }

    public void addDriver(Time_Sheet a) {
        ch.add(a);
        fillDatanew();
    }

    public void addFireman(Time_Sheet a) {
        bm.add(a);
        fillDatanew();
        
    }
    
    public void addStationDuty(Time_Sheet a) {
        sd.add(a);
        fillDatanew();
    }

    public int getCarId() {
        return car.getCarNr();
    }

    
    
    
}
