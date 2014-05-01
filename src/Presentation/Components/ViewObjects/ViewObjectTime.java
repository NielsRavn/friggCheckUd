/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Components.ViewObjects;

import BLL.IObserver;
import BLL.ITimeObserver;
import BLL.MyUtil;
import Presentation.Components.TimePicker;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Brobak
 */
public class ViewObjectTime extends ViewObject implements ITimeObserver{
    Calendar date;
    JPanel topPanel;
    JPanel buttomPanel;
    Calendar endDate;
    int endHour;
    int endMin;
    
    JLabel lblTimeEnd;
            
    public ViewObjectTime(Timestamp date){
        this.date = Calendar.getInstance();
        this.date.setTimeInMillis(date.getTime());
        endDate = Calendar.getInstance();
        setLayout(new BorderLayout());
        endHour = endDate.get(Calendar.HOUR_OF_DAY);
        endMin = endDate.get(Calendar.MINUTE);
        fillData();
    }
    
    
    private void fillData() {
        topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        Font topFont = new Font("Verdana", Font.PLAIN, 50);
        JLabel lblDateString = new JLabel("Dato: ");
        lblDateString.setFont(topFont);
        JLabel lblDate = new JLabel(date.get(Calendar.DAY_OF_MONTH)+"/"+(date.get(Calendar.MONTH)+1)+"-"+date.get(Calendar.YEAR));
        lblDate.setFont(topFont);
        topPanel.add(lblDateString);
        topPanel.add(lblDate);
        add(topPanel, BorderLayout.NORTH);
        
        buttomPanel = new JPanel();
        buttomPanel.setLayout(new FlowLayout());
        Font buttomFont = new Font("Verdana", Font.PLAIN, 30);
        
        JLabel lblStringInit = new JLabel("Fra kl ");
        lblStringInit.setFont(buttomFont);
        
        JLabel lblTimeStart = new JLabel(MyUtil.p0(date.get(Calendar.HOUR_OF_DAY)) + ":" + MyUtil.p0(date.get(Calendar.MINUTE)));
        lblTimeStart.setFont(buttomFont);
        
        JLabel lblStringDevider = new JLabel(" til ");
        lblStringDevider.setFont(buttomFont);
        
        lblTimeEnd = new JLabel(MyUtil.p0(endHour) + ":" + MyUtil.p0(endMin));
        lblTimeEnd.setFont(buttomFont);
        
        buttomPanel.add(lblStringInit);
        buttomPanel.add(lblTimeStart);
        buttomPanel.add(lblStringDevider);
        buttomPanel.add(lblTimeEnd);
        
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

    public int getEndHour() {
        return endHour;
    }

    public int getEndMin() {
        return endMin;
    }

    @Override
    public void timeChanged(int hour, int minute) {
        endHour = hour;
        endMin = minute;
        lblTimeEnd.setText(MyUtil.p0(endHour) + ":" + MyUtil.p0(endMin));
        repaint();
    }
}
