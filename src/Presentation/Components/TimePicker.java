/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Components;

import BE.MyTime;
import BLL.ITimeObserver;
import BLL.MyUtil;
import Presentation.Frames.MainFrame;
import Presentation.MyConstants;
import datechooser.beans.DateChooserPanel;
import datechooser.model.exeptions.IncompatibleDataExeption;
import datechooser.model.multiple.MultyModelBehavior;
import datechooser.model.multiple.Period;
import datechooser.model.multiple.PeriodSet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Niels
 */
public class TimePicker extends javax.swing.JPanel {

    MyTime time;
    MainFrame parent;
    ArrayList<ITimeObserver> observers;
    MyDateChooserCombo choose;
    Calendar alarmStart;
    
    /**
     * creates a new timePicker
     * @param parent the object that initialized this, to be able to close it again
     */
    public TimePicker(MainFrame parent) {
        this.parent = parent;
        observers = new ArrayList<>();
        initComponents();
        myKeyAdapter ka = new myKeyAdapter();
        tfHour.addKeyListener(ka);
        tfMinute.addKeyListener(ka);
        tfHour1.addKeyListener(ka);
        tfMinute1.addKeyListener(ka);
        setBackground(MyConstants.COLOR_BLUE);
        setVisuals();
        choose = new MyDateChooserCombo();
        choose.setCalendarPreferredSize(new Dimension(500,500));
        choose.setBehavior(MultyModelBehavior.SELECT_SINGLE);
        choose.setPreferredSize(new Dimension(300,50));
        choose.setFieldFont(MyConstants.FONT_BUTTON_FONT);
        
        jPanel8.add(choose);
    }
    
    /**
     * sets the colors and borders of everything.
     */
    private void setVisuals(){
        btnAnnuller.setBackground(MyConstants.COLOR_RED);
        btnOkay.setBackground(MyConstants.COLOR_GREEN);
        btnAnnuller.setForeground(Color.WHITE);
        btnOkay.setForeground(Color.WHITE);
        jPanel1.setBackground(MyConstants.COLOR_BLUE);
        jPanel2.setBackground(MyConstants.COLOR_BLUE);
        jPanel8.setBackground(MyConstants.COLOR_BLUE);
        jPanel2.setBackground(MyConstants.COLOR_BLUE);
        jPanel6.setBackground(MyConstants.COLOR_BLUE);
        jPanel7.setBackground(MyConstants.COLOR_BLUE);
        jPanel10.setBackground(MyConstants.COLOR_BLUE);
        jPanel11.setBackground(MyConstants.COLOR_BLUE);
        jLabel3.setForeground(Color.WHITE);
        jLabel4.setForeground(Color.WHITE);
        jLabel1.setForeground(Color.WHITE);
        jLabel2.setForeground(Color.WHITE);
        jLabel1.setFont(MyConstants.FONT_HEADER_TEXT);
        jLabel2.setFont(MyConstants.FONT_HEADER_TEXT);
        jPanel4.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        jPanel5.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
    }

    /**
     * creates a new time picker with initialzed times.
     * @param parent the object that initialized this, to be able to close it again
     * @param hour the hour to set on the time picker
     * @param minute the minute to set on the timepicker
     */
    public TimePicker(MainFrame parent, MyTime time) {
        this(parent);
        this.time = time;
        tfHour1.setText(MyUtil.p0(time.getStartHour()));
        tfMinute1.setText(MyUtil.p0(time.getStartMinute()));
        tfHour.setText(MyUtil.p0(time.getEndHour()));
        tfMinute.setText(MyUtil.p0(time.getEndMinute()));
        Calendar date = Calendar.getInstance();
        date.setTimeInMillis(time.getStartDate().getTime());
        choose.setSelectedDate(date);
        alarmStart = Calendar.getInstance();
        alarmStart.setTimeInMillis(time.getAlarmStartDate().getTime());
        Calendar invalidStart = Calendar.getInstance();
        Calendar invalidEnd = Calendar.getInstance();
        invalidEnd.setTimeInMillis(alarmStart.getTimeInMillis());
        invalidEnd.set(Calendar.DAY_OF_YEAR, alarmStart.get(Calendar.DAY_OF_YEAR)-1);
        invalidStart.setTimeInMillis(0);
        PeriodSet p = new PeriodSet(new Period(invalidStart,invalidEnd));
        try {
            choose.setForbiddenPeriods(p);
        } catch (IncompatibleDataExeption ex) {}
    }
    
    /**
     * adds a time observer to be notified when okay i clicked
     * @param observer an object implementing ITimeObserver
     */
    public void addObserver(ITimeObserver observer){
        observers.add(observer);
    }
    
    /**
     * removes a given time observer from the time picker
     * @param observer the observer to remove
     */
    public void removeObserver(ITimeObserver observer){
        observers.remove(observer);
    }
    
    /**
     * notifies all observers, with the time currently sat on the time.
     */
    private void notifyObservers(){
        for(ITimeObserver observer: observers)
            observer.timeChanged(time);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        btnOkay = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btnAnnuller = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        tfHour1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tfMinute1 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        tfHour = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfMinute = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();

        setLayout(new java.awt.GridBagLayout());

        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel11.setLayout(new java.awt.BorderLayout());

        btnOkay.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        btnOkay.setText("OK");
        btnOkay.setMargin(new java.awt.Insets(20, 40, 20, 40));
        btnOkay.setMinimumSize(new java.awt.Dimension(100, 31));
        btnOkay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkayActionPerformed(evt);
            }
        });
        jPanel4.add(btnOkay);

        jPanel2.add(jPanel4);

        btnAnnuller.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        btnAnnuller.setText("Annuller");
        btnAnnuller.setMargin(new java.awt.Insets(20, 14, 20, 14));
        btnAnnuller.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnnullerActionPerformed(evt);
            }
        });
        jPanel5.add(btnAnnuller);

        jPanel2.add(jPanel5);

        jPanel11.add(jPanel2, java.awt.BorderLayout.SOUTH);

        jPanel6.setLayout(new java.awt.BorderLayout());

        jLabel2.setText("Sæt start tiden:");
        jPanel6.add(jLabel2, java.awt.BorderLayout.NORTH);

        tfHour1.setFont(new java.awt.Font("Tahoma", 1, 70)); // NOI18N
        tfHour1.setPreferredSize(new java.awt.Dimension(96, 91));
        jPanel10.add(tfHour1);

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 120)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText(":");
        jPanel10.add(jLabel4);

        tfMinute1.setFont(new java.awt.Font("Tahoma", 1, 70)); // NOI18N
        tfMinute1.setMinimumSize(new java.awt.Dimension(96, 91));
        tfMinute1.setPreferredSize(new java.awt.Dimension(96, 91));
        jPanel10.add(tfMinute1);

        jPanel6.add(jPanel10, java.awt.BorderLayout.CENTER);

        jPanel11.add(jPanel6, java.awt.BorderLayout.NORTH);

        jPanel7.setLayout(new java.awt.BorderLayout());

        jLabel1.setText("Sæt slut tiden:");
        jPanel7.add(jLabel1, java.awt.BorderLayout.NORTH);

        tfHour.setFont(new java.awt.Font("Tahoma", 1, 70)); // NOI18N
        tfHour.setPreferredSize(new java.awt.Dimension(96, 91));
        jPanel1.add(tfHour);

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 120)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText(":");
        jPanel1.add(jLabel3);

        tfMinute.setFont(new java.awt.Font("Tahoma", 1, 70)); // NOI18N
        tfMinute.setMinimumSize(new java.awt.Dimension(96, 91));
        tfMinute.setPreferredSize(new java.awt.Dimension(96, 91));
        jPanel1.add(tfMinute);

        jPanel7.add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel11.add(jPanel7, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel11, java.awt.BorderLayout.CENTER);
        jPanel3.add(jPanel8, java.awt.BorderLayout.NORTH);

        add(jPanel3, new java.awt.GridBagConstraints());
    }// </editor-fold>//GEN-END:initComponents

    private void btnOkayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkayActionPerformed
        checkInput();
        if(choose.getSelectedDate().get(Calendar.DAY_OF_YEAR) == alarmStart.get(Calendar.DAY_OF_YEAR)
                && choose.getSelectedDate().get(Calendar.YEAR) == alarmStart.get(Calendar.YEAR)){
            boolean changedSomthing = false;
            if(time.getStartHour() < alarmStart.get(Calendar.HOUR_OF_DAY)) {
                time.setStartHour(alarmStart.get(Calendar.HOUR_OF_DAY));
                changedSomthing = true;
            }
            if(time.getStartMinute()< alarmStart.get(Calendar.MINUTE)) {
                time.setStartMinute(alarmStart.get(Calendar.MINUTE));
                changedSomthing = true;
            }
            if(changedSomthing){
                JOptionPane.showMessageDialog(parent, "Start tiden er blevet ændret fordi den var sat til før alarm kaldet kom.");
            }
        }
        notifyObservers();
        parent.removeTimePicker();
    }//GEN-LAST:event_btnOkayActionPerformed

    private void btnAnnullerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnnullerActionPerformed
        parent.removeTimePicker();
    }//GEN-LAST:event_btnAnnullerActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnnuller;
    private javax.swing.JButton btnOkay;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JTextField tfHour;
    private javax.swing.JTextField tfHour1;
    private javax.swing.JTextField tfMinute;
    private javax.swing.JTextField tfMinute1;
    // End of variables declaration//GEN-END:variables

    /**
     * checks the inputed text in the textfields one after the other.
     */
    private void checkInput(){
        long selectedDate = choose.getSelectedDate().getTimeInMillis();
        if(time.getAlarmStartDate().getTime() < selectedDate){
            time.setStartDate(new Timestamp(selectedDate));
        }
        else {
            Calendar date = Calendar.getInstance();
            date.setTimeInMillis(time.getStartDate().getTime());
            choose.setSelectedDate(date);
        }
        try{
            time.setStartHour(checkInputForTextField(tfHour1, 23));
        }catch (IllegalStateException e){}
        try{
            time.setStartMinute(checkInputForTextField(tfMinute1, 59));
        }catch (IllegalStateException ex){}
        try{
            time.setEndHour(checkInputForTextField(tfHour, 23));
        }catch (IllegalStateException e){}
        try{
            time.setEndMinute(checkInputForTextField(tfMinute, 59));
        }catch (IllegalStateException ex){}
    }
    
    /**
     * checks the input in the given textfield is a 2 degit number under the max number.
     * Also fixes the string in the textfield so that nothing invalid is written.
     * @param toTest the textfield to be tested
     * @param maxNumber the max number that the field should hold
     * @return the correct number that is shown
     */
    private int checkInputForTextField(JTextField toTest, int maxNumber){
        if(!toTest.getText().equals("")){
            int result = 0;
        
            String text = toTest.getText();
            boolean takeAwayLast = false;
            if(text.length()> 2) text = text.substring(0, 2);
            
            try{
                result = Integer.parseInt(text);
                if(result > maxNumber) takeAwayLast = true;
            }catch (NumberFormatException e){
                takeAwayLast = true;
            }
            
            if(takeAwayLast){
                text = text.substring(0, text.length()-1);
            }
            toTest.setText(text);
            return result;
        }else throw new IllegalStateException("The field is empty");
    }
    
    /**
     * listens for any key event to trigger a check of the textfields.
     */
    private class myKeyAdapter extends KeyAdapter{

        @Override
        public void keyReleased(KeyEvent e) {
            checkInput();
        }

        @Override
        public void keyTyped(KeyEvent e) {
            checkInput();
            if(!Character.isDigit(e.getKeyChar()))
                e.consume();
        }
        
        
        
        
    }
    
}
