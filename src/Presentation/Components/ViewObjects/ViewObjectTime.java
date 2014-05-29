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
            
    /**
     * Creates a new ViewObjectTime
     * @param time the time represented in the view object
     */
    protected ViewObjectTime(MyTime time){
        super(time);
        this.date = Calendar.getInstance();
        this.date.setTimeInMillis(time.getAlarmStartDate().getTime());
        endDate = Calendar.getInstance();
        
        this.time = time;
        fillData();
    }
    
    /**
     * fills the viewobject with nessesary data
     */
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
    
    /**
     * Sets the background of the view object
     * @param color the color you want the background to have
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
     * Gets the time represented in the view object
     * @return the time represented in the view object
     */
    public MyTime getTime() {
        return time;
    }

    /**
     * Refreshes the viewobject with new times
     * @param inTime the times you want the viewObject to show
     */
    @Override
    public void timeChanged(MyTime inTime) {
        time = inTime;
        date.setTimeInMillis(inTime.getStartDate().getTime());
        lblDate.setText(date.get(Calendar.DAY_OF_MONTH)+"/"+(date.get(Calendar.MONTH)+1)+"-"+date.get(Calendar.YEAR));
        lblTimeStart.setText(MyUtil.p0(time.getStartHour()) + ":" + MyUtil.p0(time.getStartMinute()));
        lblTimeEnd.setText(MyUtil.p0(time.getEndHour()) + ":" + MyUtil.p0(time.getEndMinute()));
        repaint();
    }
    /**
     * Gets the end time represented in the view object
     * @return the end time represented in the view object
     */
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
    
    /**
     * Gets the start time represented in the view object
     * @return the start time represented in the view object
     */
    public Timestamp getStartTime(){
        Calendar date = Calendar.getInstance();
        date.setTimeInMillis(time.getStartDate().getTime());
        date.set(Calendar.HOUR_OF_DAY, time.getStartHour());
        date.set(Calendar.MINUTE, time.getStartMinute());
        return new Timestamp(date.getTimeInMillis());
    }

    /**
     * Refreshes the textfields in the view object with current data
     */
    @Override
    public void refreshViewObject() {
        lblDate.setText(date.get(Calendar.DAY_OF_MONTH)+"/"+(date.get(Calendar.MONTH)+1)+"-"+date.get(Calendar.YEAR));

        lblTimeEnd.setText(MyUtil.p0(time.getEndHour()) + ":" + MyUtil.p0(time.getEndMinute()));
        lblTimeStart.setText(MyUtil.p0(time.getStartHour()) + ":" + MyUtil.p0(time.getStartMinute()));
    }
}
