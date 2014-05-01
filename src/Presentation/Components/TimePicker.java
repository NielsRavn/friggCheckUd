/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Components;

import BLL.ITimeObserver;
import Presentation.Frames.MainFrame;
import Presentation.MyConstants;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JTextField;

/**
 *
 * @author Niels
 */
public class TimePicker extends javax.swing.JPanel {

    int hour, minute;
    MainFrame parent;
    ArrayList<ITimeObserver> observers;
    
    /**
     * Creates new form TimePicker
     */
    public TimePicker(MainFrame parent) {
        this.parent = parent;
        observers = new ArrayList<>();
        initComponents();
        btnAnnuller.setBackground(MyConstants.COLOR_RED);
        btnOkay.setBackground(MyConstants.COLOR_GREEN);
        btnAnnuller.setForeground(Color.WHITE);
        btnOkay.setForeground(Color.WHITE);
        myKeyAdapter ka = new myKeyAdapter();
        tfHour.addKeyListener(ka);
        tfMinute.addKeyListener(ka);
        
    }

    public TimePicker(MainFrame parent, int hour, int minute) {
        this(parent);
        this.hour = hour;
        this.minute = minute;
        tfHour.setText("" +hour);
        tfMinute.setText(""+minute);
    }
    
    public void addObserver(ITimeObserver observer){
        observers.add(observer);
    }
    
    public void removeObserver(ITimeObserver observer){
        observers.remove(observer);
    }
    
    private void notifyObservers(){
        for(ITimeObserver observer: observers)
            observer.timeChanged(hour, minute);
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
        jPanel2 = new javax.swing.JPanel();
        btnOkay = new javax.swing.JButton();
        btnAnnuller = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        tfHour = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfMinute = new javax.swing.JTextField();

        setLayout(new java.awt.GridBagLayout());

        jPanel3.setLayout(new java.awt.BorderLayout());

        btnOkay.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        btnOkay.setText("OK");
        btnOkay.setMargin(new java.awt.Insets(2, 40, 2, 40));
        btnOkay.setMinimumSize(new java.awt.Dimension(100, 31));
        btnOkay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkayActionPerformed(evt);
            }
        });
        jPanel2.add(btnOkay);

        btnAnnuller.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        btnAnnuller.setText("Annuller");
        btnAnnuller.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnnullerActionPerformed(evt);
            }
        });
        jPanel2.add(btnAnnuller);

        jPanel3.add(jPanel2, java.awt.BorderLayout.SOUTH);

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

        jPanel3.add(jPanel1, java.awt.BorderLayout.CENTER);

        add(jPanel3, new java.awt.GridBagConstraints());
    }// </editor-fold>//GEN-END:initComponents

    private void btnOkayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkayActionPerformed
        notifyObservers();
        parent.removeTimePicker();
    }//GEN-LAST:event_btnOkayActionPerformed

    private void btnAnnullerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnnullerActionPerformed
        parent.removeTimePicker();
    }//GEN-LAST:event_btnAnnullerActionPerformed

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnnuller;
    private javax.swing.JButton btnOkay;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField tfHour;
    private javax.swing.JTextField tfMinute;
    // End of variables declaration//GEN-END:variables

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
    }
    
    private void checkInput(){
        try{
            hour = checkInputForTextField(tfHour, 23);
        }catch (IllegalStateException e){}
        try{
            minute = checkInputForTextField(tfMinute, 59);
        }catch (IllegalStateException ex){}
        
        
    }
    
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
    
    private class myKeyAdapter extends KeyAdapter{

        @Override
        public void keyReleased(KeyEvent e) {
            checkInput();
        }
        
        
        
    }
}
