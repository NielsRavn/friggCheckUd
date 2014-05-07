/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Components.ViewObjects;

import BE.Alarm;
import BE.Car;
import BE.Fireman;
import BE.Time_Sheet;
import Presentation.MyConstants;
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
public class ViewObjectTimeSheet extends ViewObject {
    
    ArrayList<Time_Sheet> timeSheet;
    Alarm alarm;
    Car car;
    ArrayList<Fireman> hl;
    ArrayList<Fireman> ch;
    ArrayList<Fireman> bm;
    ArrayList<Fireman> sd;
    public int getCarId;
    int FZ = 30;
    
    public ViewObjectTimeSheet(Alarm alarm, Car car) {
        this.alarm = alarm;
        this.car = car;
        setLayout(new BorderLayout());
        hl = new ArrayList<>();
        ch = new ArrayList<>();
        bm = new ArrayList<>();
        sd = new ArrayList<>();
        
        fillData();
    }
    
    private void fillData()
    {
        JPanel vest = new JPanel();
        JPanel center = new JPanel();
        vest.setLayout(new FlowLayout());
        center.setLayout(new GridLayout(0, 1));
                
                vest.add(new JLabel(new ImageIcon(car.getIconPath())));
                JLabel carInfo = new JLabel(""+car.getCarNr());
                carInfo.setFont(new Font("Verdana", Font.PLAIN, FZ));
                vest.add(carInfo);
       
                for(Fireman a : hl)
                {
                    JLabel fm = new JLabel("HL : "+ a.getFirstName()+" "+a.getLastName());
                    fm.setFont(new Font("Verdana", Font.PLAIN, FZ));
                    center.add(fm);
                }
                for(Fireman a : ch)
                {
                    JLabel fm = new JLabel("CH : "+ a.getFirstName()+" "+a.getLastName());
                    fm.setFont(new Font("Verdana", Font.PLAIN, FZ)); 
                    center.add(fm);
                }
                for(Fireman a : bm)
                {
                    JLabel fm = new JLabel("BM : "+ a.getFirstName()+" "+a.getLastName());
                    fm.setFont(new Font("Verdana", Font.PLAIN, FZ)); 
                    center.add(fm);
                }
                for(Fireman a : sd)
                {
                    JLabel fm = new JLabel("ST : "+ a.getFirstName()+" "+a.getLastName());
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
        hl.add(a.getFireman());
        fillData();
    }

    public void addDriver(Time_Sheet a) {
        ch.add(a.getFireman());
        fillData();
    }

    public void addFireman(Time_Sheet a) {
        bm.add(a.getFireman());
        fillData();
        
    }

    public void addStationDuty(Time_Sheet a) {
        sd.add(a.getFireman());
        fillData();
    }

    public int getCarId() {
        return car.getCarNr();
    }

    
    
    
}
