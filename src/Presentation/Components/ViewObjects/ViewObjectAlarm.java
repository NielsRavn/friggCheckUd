/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Components.ViewObjects;

import BE.Alarm;
import BLL.MyUtil;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Brobak
 */
public class ViewObjectAlarm extends ViewObject{
   
    JPanel topPanel;
    JPanel buttomPanel;
    Calendar date;
    Alarm alarm;
    /**
     * Creates a new ViewObjectAlarm
     * @param alarm the alarm you want the viewobject to represent
     */
    protected ViewObjectAlarm(Alarm alarm){
        super(alarm);
        this.alarm = alarm;
        fillData();
    }
    
    /**
     * fills the nessesary data in to the viewobject
     */
    protected void fillData() {
        setLayout(new BorderLayout());
        date = Calendar.getInstance();
        date.setTimeInMillis(alarm.getTime().getTime());

        topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        JLabel lblDest = new JLabel(alarm.getDistination());
        lblDest.setFont(new Font("Comic Sans", Font.PLAIN, 42));
        JLabel lblTime = new JLabel(" - " + date.get(Calendar.DAY_OF_MONTH)+"/"+(date.get(Calendar.MONTH)+1)+"-"+date.get(Calendar.YEAR)+" " + MyUtil.p0(date.get(Calendar.HOUR_OF_DAY)) + ":" + MyUtil.p0(date.get(Calendar.MINUTE)));
        lblTime.setFont(new Font("Comic Sans", Font.PLAIN, 42));
        topPanel.add(lblDest);
        topPanel.add(lblTime);
        add(topPanel, BorderLayout.NORTH);
        buttomPanel = new JPanel();
        buttomPanel.setLayout(new FlowLayout());
        JLabel lblType = new JLabel(alarm.getType());
        lblType.setFont(new Font("Comic Sans", Font.PLAIN, 24));
        buttomPanel.add(lblType);
        add(buttomPanel, BorderLayout.CENTER);
    }
    
    /**
     * Sets the background color on the viewObject
     * @param color 
     */
    @Override
    public void setBackground(Color color){
        super.setBackground(color);
        if(topPanel != null)
            topPanel.setBackground(color);
        if(buttomPanel != null)
            buttomPanel.setBackground(color);
    }

    /**
     * Gets the time for the alarm shown in the viewObject
     * @return the time of the alarm
     */
    public Timestamp getTime() {
        return alarm.getTime();
    }
    
    /**
     * Gets the alarm show in the viewObject
     * @return the alarm
     */
    public Alarm getAlarm(){
        return alarm;
    }

    /**
     * Does nothing
     */
    @Override
    public void refreshViewObject() {
        
    }
    
    
}
