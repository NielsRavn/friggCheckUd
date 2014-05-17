/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Components.ViewObjects;

import BE.MyTime;
import BLL.IObserver;
import BLL.ITimeObserver;
import BLL.MyUtil;
import Presentation.Components.TimePicker;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Locale;
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
    MyTime time;
    
    JLabel lblTimeStart, lblTimeEnd, lblDate ;
            
    protected ViewObjectTime(MyTime time){
        super(time);
        this.date = Calendar.getInstance();
        this.date.setTimeInMillis(time.getAlarmStartDate().getTime());
        endDate = Calendar.getInstance();
        
//        int startHour = this.date.get(Calendar.HOUR_OF_DAY);
//        int startMin = this.date.get(Calendar.MINUTE);
//        int endHour = endDate.get(Calendar.HOUR_OF_DAY);
//        int endMin = endDate.get(Calendar.MINUTE);
        this.time = time;
    }
    
    
    @Override
    protected void fillData() {
        setLayout(new BorderLayout());
        topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        Font topFont = new Font("Verdana", Font.PLAIN, 50);
        JLabel lblDateString = new JLabel("Dato: ");
        lblDateString.setFont(topFont);
        lblDate = new JLabel(date.get(Calendar.DAY_OF_MONTH)+"/"+(date.get(Calendar.MONTH)+1)+"-"+date.get(Calendar.YEAR));
        lblDate.setFont(topFont);
        topPanel.add(lblDateString);
        topPanel.add(lblDate);
        add(topPanel, BorderLayout.NORTH);
        
        buttomPanel = new JPanel();
        buttomPanel.setLayout(new FlowLayout());
        Font buttomFont = new Font("Verdana", Font.PLAIN, 30);
        
        JLabel lblStringInit = new JLabel("Fra kl ");
        lblStringInit.setFont(buttomFont);
        
        lblTimeStart = new JLabel(MyUtil.p0(time.getStartHour()) + ":" + MyUtil.p0(time.getStartMinute()));
        lblTimeStart.setFont(buttomFont);
        
        JLabel lblStringDevider = new JLabel(" til ");
        lblStringDevider.setFont(buttomFont);
        
        lblTimeEnd = new JLabel(MyUtil.p0(time.getEndHour()) + ":" + MyUtil.p0(time.getEndMinute()));
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

    public MyTime getTime() {
        return time;
    }

    @Override
    public void timeChanged(MyTime inTime) {
        time = inTime;
        date.setTimeInMillis(inTime.getStartDate().getTime());
        lblDate.setText(date.get(Calendar.DAY_OF_MONTH)+"/"+(date.get(Calendar.MONTH)+1)+"-"+date.get(Calendar.YEAR));
        lblTimeStart.setText(MyUtil.p0(time.getStartHour()) + ":" + MyUtil.p0(time.getStartMinute()));
        lblTimeEnd.setText(MyUtil.p0(time.getEndHour()) + ":" + MyUtil.p0(time.getEndMinute()));
        repaint();
    }
    
    public Timestamp getEndTime(){
        Calendar date = Calendar.getInstance();
        date.setTimeInMillis(time.getStartDate().getTime());
        if(time.getEndHour() < time.getStartHour() || (time.getEndHour() == time.getStartHour() && time.getEndMinute() < time.getStartMinute())){
            date.set(Calendar.DAY_OF_YEAR, date.get(Calendar.DAY_OF_YEAR)+1);
        }
        date.set(Calendar.HOUR_OF_DAY, time.getEndHour());
        date.set(Calendar.MINUTE, time.getEndMinute());
        return new Timestamp(date.getTimeInMillis());
    }
    
    public Timestamp getStartTime(){
        Calendar date = Calendar.getInstance();
        date.setTimeInMillis(time.getStartDate().getTime());
        date.set(Calendar.HOUR_OF_DAY, time.getStartHour());
        date.set(Calendar.MINUTE, time.getStartMinute());
        return new Timestamp(date.getTimeInMillis());
    }

    @Override
    public void refreshViewObject() {
        lblDate.setText(date.get(Calendar.DAY_OF_MONTH)+"/"+(date.get(Calendar.MONTH)+1)+"-"+date.get(Calendar.YEAR));

        lblTimeEnd.setText(MyUtil.p0(time.getEndHour()) + ":" + MyUtil.p0(time.getEndMinute()));
        lblTimeStart.setText(MyUtil.p0(time.getStartHour()) + ":" + MyUtil.p0(time.getStartMinute()));
    }
}
