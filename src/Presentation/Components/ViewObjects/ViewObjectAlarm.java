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
import java.awt.Font;
import java.sql.Date;
import javax.swing.BorderFactory;
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
        JLabel lblDest = new JLabel(destination);
        lblDest.setFont(new Font("Comic Sans", Font.PLAIN, 42));
        JLabel lblTime = new JLabel(" - " + time.toString());
        lblTime.setFont(new Font("Comic Sans", Font.PLAIN, 42));
        topPanel.add(lblDest);
        topPanel.add(lblTime);
        add(topPanel, BorderLayout.NORTH);
        buttomPanel = new JPanel();
        buttomPanel.setLayout(new FlowLayout());
        JLabel lblType = new JLabel(type);
        lblType.setFont(new Font("Comic Sans", Font.PLAIN, 24));
        buttomPanel.add(lblType);
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
