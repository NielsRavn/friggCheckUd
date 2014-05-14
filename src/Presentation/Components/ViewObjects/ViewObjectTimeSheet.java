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
import javax.swing.JTable;
import Presentation.Components.ViewObjectTimeSheetTableModel;
import javax.swing.JScrollPane;

/**
 *
 * @author Poul Nielsen
 */
public class ViewObjectTimeSheet extends ViewObject {
    Alarm alarm;
    Car car;
    Station station;
    ViewObjectTimeSheetTableModel model;
    ArrayList<Time_Sheet> hl;
    ArrayList<Time_Sheet> ch;
    ArrayList<Time_Sheet> bm;
    ArrayList<Time_Sheet> sd;
    public int getCarId;
    int FZ = 30;
    
    public ViewObjectTimeSheet( Time_Sheet timesheet) {
        this.car = timesheet.getCar();
        this.station = timesheet.getStation();
        model = new ViewObjectTimeSheetTableModel();
        setLayout(new BorderLayout());
        hl = new ArrayList<>();
        ch = new ArrayList<>();
        bm = new ArrayList<>();
        sd = new ArrayList<>();
        
        fillData();
    }
    
    
    private void fillData()
    {
        model.clearList();
        JPanel vest = new JPanel();
        vest.setLayout(new GridBagLayout());
        JPanel center = new JPanel();
        center.setLayout(new BorderLayout());
        GridBagConstraints c = new GridBagConstraints();
        JTable firemen = new JTable();
        JScrollPane scrl = new JScrollPane();
        scrl.setViewportView(firemen);
        firemen.setModel(model);
        c.fill = GridBagConstraints.CENTER;
        
                if(car == null)
                {
                    c.gridx = 0;
                    c.gridy = 1;
                    vest.add(new JLabel(new ImageIcon(station.getIconPath())), c);
                    
                    JLabel stationInfo = new JLabel(""+station.getName());
                    stationInfo.setFont(new Font("Verdana", Font.PLAIN, FZ));
                    c.gridx = 0;
                    c.gridy = 0;
                    vest.add(stationInfo, c);
                }
                else
                {
                    c.gridx = 0;
                    c.gridy = 1;
                    vest.add(new JLabel(new ImageIcon(car.getIconPath())), c);
                    
                    JLabel carInfo = new JLabel("Bil Nr.: "+car.getCarNr());
                    carInfo.setFont(new Font("Verdana", Font.PLAIN, FZ));
                    c.gridx = 0;
                    c.gridy = 0;
                    vest.add(carInfo, c);
                }
        
                for(Time_Sheet a : hl)
                {
                    model.addTimeSheet(a);
                }
                for(Time_Sheet a : ch)
                {
                     model.addTimeSheet(a);
                }
                for(Time_Sheet a : bm)
                {
                     model.addTimeSheet(a);
                }
                for(Time_Sheet a : sd)
                {
                     model.addTimeSheet(a);
                }
        center.add(scrl, BorderLayout.CENTER);
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
    
    public void addStationDuty(Time_Sheet a) {
        sd.add(a);
        fillData();
    }

    public int getCarId() {
        return car.getCarNr();
    }

    
    
    
}
