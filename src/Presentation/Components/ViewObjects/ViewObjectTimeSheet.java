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
    Alarm alarm;
    Car car;
    ArrayList<Time_Sheet> hl;
    ArrayList<Time_Sheet> ch;
    ArrayList<Time_Sheet> bm;
    public int getCarId;
    int FZ = 30;
    
    public ViewObjectTimeSheet( Car car) {
        this.car = car;
        setLayout(new BorderLayout());
        hl = new ArrayList<>();
        ch = new ArrayList<>();
        bm = new ArrayList<>();
        
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
               
        add(vest , BorderLayout.WEST);
        add(center , BorderLayout.CENTER);
    }
        

    public Object getCar() {
        return car;
    }

    public void addTeamLeader(Time_Sheet a) {
        hl.add(a);
        fillData();
    }

    public void addDriver(Time_Sheet a) {
        ch.add(a);
        fillData();
    }

    public void addFireman(Time_Sheet a) {
        bm.add(a);
        fillData();
        
    }


    public int getCarId() {
        return car.getCarNr();
    }

    
    
    
}
