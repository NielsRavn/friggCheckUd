/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Components.ViewObjects;

import BE.Alarm;
import java.awt.BorderLayout;
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
    public ViewObjectAlarm(Alarm alarm){
        setLayout(new BorderLayout());
        destination = alarm.getDistination();
        type = alarm.getType();
        time = alarm.getTime();
        
        
        fillData();
    }

    private void fillData() {
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        topPanel.add(new JLabel(destination));
        topPanel.add(new JLabel(time.toString()));
        add(topPanel, BorderLayout.NORTH);
        add(new JLabel(type), BorderLayout.CENTER);
    }
    
}
