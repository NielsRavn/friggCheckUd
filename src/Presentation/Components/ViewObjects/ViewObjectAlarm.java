/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Components.ViewObjects;

import BE.Alarm;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.sql.Date;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Brobak
 */
public class ViewObjectAlarm extends ViewObject{
    String destination;
    String type;
    Date time;
    JPanel topPanel;
    JPanel buttomPanel;
    public ViewObjectAlarm(Alarm alarm){
        setLayout(new BorderLayout());
        destination = alarm.getDistination();
        type = alarm.getType();
        time = alarm.getTime();
        
        
        fillData();
    }

    private void fillData() {
        topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        topPanel.add(new JLabel(destination));
        topPanel.add(new JLabel(time.toString()));
        add(topPanel, BorderLayout.NORTH);
        buttomPanel = new JPanel();
        buttomPanel.setLayout(new FlowLayout());
        buttomPanel.add(new JLabel(type));
        add(buttomPanel, BorderLayout.CENTER);
    }
    
    @Override
    public void setBackground(Color color){
        super.setBackground(color);
        if(topPanel != null)
            topPanel.setBackground(color);
        if(buttomPanel != null)
            buttomPanel.setBackground(color);
    }
    
}
