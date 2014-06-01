/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Components;

import BE.Alarm;
import BLL.Alarm_AccessLink;
import Presentation.Frames.MainFrame;
import Presentation.MyConstants;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Brobak
 */
public class AlarmCreater extends javax.swing.JPanel {
    MainFrame parent;
    Alarm_AccessLink aal;
    /**
     * Creates new form AlarmCreater
     * @param parent
     */
    public AlarmCreater(MainFrame parent){
        this.parent = parent;
        try {
            aal = new Alarm_AccessLink();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(parent, "An sql error has occured: " + ex.getMessage());
        }
        initComponents();
        txtTime.setValue(System.currentTimeMillis()-3600000);
        Date currentDate = new Date(System.currentTimeMillis()-3600000);
        txtDate.setValue(System.currentTimeMillis()-3600000);
        setSize(300, 100);
        setBackground(MyConstants.COLOR_BLUE);
        setForeground(Color.WHITE);
        setButtonColors();
        btnCancel.addActionListener(new MyActionListener());
        btnAccept.addActionListener(new MyActionListener());
        btnAccept.setEnabled(false);
        txtDestination.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                if(!txtDestination.getText().equals("") && !txtType.getText().equals(""))
                    btnAccept.setEnabled(true);
                else
                    btnAccept.setEnabled(false);
            }
            
        });
        txtType.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(!txtDestination.getText().equals("") && !txtType.getText().equals(""))
                    btnAccept.setEnabled(true);
                else
                    btnAccept.setEnabled(false);
            }
        });
    }
    
    /**
     * sets the colors of the buttons.
     */
    public void setButtonColors(){
        btnAccept.setBackground(MyConstants.COLOR_GREEN);
        btnAccept.setForeground(Color.WHITE);
        btnCancel.setBackground(MyConstants.COLOR_RED);
        btnCancel.setForeground(Color.WHITE);
    }

    /**
     * sets the background of the obect
     * @param bg the color to set the background to.
     */
    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg); 
        if(pnlInner != null)
            pnlInner.setBackground(bg);
        if(pnlMain != null)
            pnlMain.setBackground(bg);
        if(pnlMainSouth != null)
            pnlMainSouth.setBackground(bg);
        
    }

    /**
     * sets the foreground color of the object
     * @param fg the color to set the forground to.
     */
    @Override
    public void setForeground(Color fg) {
        super.setForeground(fg); //To change body of generated methods, choose Tools | Templates.
        if(jLabel1 != null)
            jLabel1.setForeground(fg);
        if(jLabel2 != null)
            jLabel2.setForeground(fg);
        if(jLabel3 != null)
            jLabel3.setForeground(fg);
        if(jLabel4 != null)
            jLabel4.setForeground(fg);
    }
    
    /**
     * @return the string in the destination field
     */
    public String getDestination(){
        return txtDestination.getText();
    }
    
    /**
     * @return the alarm type text.
     */
    public String getType(){
        return txtType.getText();
    }
    
    /**
     * @return  the date this new alarm stated on.
     */
    public Timestamp getDateTime(){
        Date returnDate = new Date();
        if(txtDate.getValue().getClass() == Long.class && txtTime.getValue().getClass() == Long.class){
            returnDate = new Date((long)txtTime.getValue());
        }else if(txtTime.getValue().getClass() == Long.class){
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis((long)txtTime.getValue());
            Date dateDate = (Date)txtDate.getValue();
            dateDate = new Date(dateDate.getTime()+(cal.get(Calendar.HOUR_OF_DAY)*60*60*1000)+(cal.get(Calendar.MINUTE)*60*1000));
            returnDate = dateDate;
        }else if(txtDate.getValue().getClass() == Long.class){
            Date dateTime = (Date)txtTime.getValue();
            returnDate = new Date((long)txtDate.getValue());
        }
        else{
            Date dateDate = (Date)txtDate.getValue();
            Date dateTime = (Date)txtTime.getValue();
            returnDate = new Date(dateDate.getTime() + dateTime.getTime() + 3600000);
        }
        return new Timestamp(returnDate.getTime());
    }
    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        pnlInner = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtDestination = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtType = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtDate = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTime = new javax.swing.JFormattedTextField();
        pnlMainSouth = new javax.swing.JPanel();
        btnAccept = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        pnlMain.setLayout(new java.awt.BorderLayout(10, 10));

        pnlInner.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        pnlInner.setMinimumSize(new java.awt.Dimension(300, 80));
        pnlInner.setLayout(new java.awt.GridLayout(4, 2, 10, 10));

        jLabel1.setFont(MyConstants.FONT_HEADER_TEXT);
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Destination");
        jLabel1.setMaximumSize(new java.awt.Dimension(100, 14));
        jLabel1.setMinimumSize(new java.awt.Dimension(100, 14));
        jLabel1.setPreferredSize(null);
        pnlInner.add(jLabel1);

        txtDestination.setFont(MyConstants.FONT_HEADER_TEXT);
        txtDestination.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtDestination.setPreferredSize(new java.awt.Dimension(160, 30));
        pnlInner.add(txtDestination);

        jLabel2.setFont(MyConstants.FONT_HEADER_TEXT);
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Type");
        jLabel2.setPreferredSize(null);
        pnlInner.add(jLabel2);

        txtType.setFont(MyConstants.FONT_HEADER_TEXT);
        txtType.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtType.setPreferredSize(new java.awt.Dimension(160, 30));
        pnlInner.add(txtType);

        jLabel4.setFont(MyConstants.FONT_HEADER_TEXT);
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Dato");
        jLabel4.setPreferredSize(null);
        pnlInner.add(jLabel4);

        txtDate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd-MM-yyyy"))));
        txtDate.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtDate.setFont(MyConstants.FONT_HEADER_TEXT);
        txtDate.setPreferredSize(new java.awt.Dimension(160, 30));
        pnlInner.add(txtDate);

        jLabel3.setFont(MyConstants.FONT_HEADER_TEXT);
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Tid");
        jLabel3.setPreferredSize(null);
        pnlInner.add(jLabel3);

        txtTime.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getTimeInstance(java.text.DateFormat.SHORT))));
        txtTime.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtTime.setFont(MyConstants.FONT_HEADER_TEXT);
        txtTime.setPreferredSize(new java.awt.Dimension(160, 30));
        pnlInner.add(txtTime);

        pnlMain.add(pnlInner, java.awt.BorderLayout.CENTER);

        btnAccept.setFont(MyConstants.FONT_BUTTON_FONT);
        btnAccept.setText("Opret alarm");
        btnAccept.setMaximumSize(null);
        btnAccept.setMinimumSize(null);
        btnAccept.setName(""); // NOI18N
        btnAccept.setPreferredSize(new java.awt.Dimension(150, 60));
        pnlMainSouth.add(btnAccept);

        btnCancel.setFont(MyConstants.FONT_BUTTON_FONT);
        btnCancel.setText("Fortryd");
        btnCancel.setPreferredSize(new java.awt.Dimension(150, 60));
        pnlMainSouth.add(btnCancel);

        pnlMain.add(pnlMainSouth, java.awt.BorderLayout.SOUTH);

        add(pnlMain, new java.awt.GridBagConstraints());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAccept;
    private javax.swing.JButton btnCancel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel pnlInner;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlMainSouth;
    private javax.swing.JFormattedTextField txtDate;
    private javax.swing.JTextField txtDestination;
    private javax.swing.JFormattedTextField txtTime;
    private javax.swing.JTextField txtType;
    // End of variables declaration//GEN-END:variables

    /**
     * actionlistener for the buttons to cancel, or create a new alarm in the database when
     * approve button is clicked, the fields are used to get the data for the alarm.
     */
    private class MyActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == btnAccept){
                try {
                    Alarm alarm = new Alarm(txtDestination.getText(), txtType.getText(), AlarmCreater.this.getDateTime(), false);
                    aal.addAlarm(alarm);
                    if(alarm.getID() != 0)
                        parent.addAlarm(alarm);
                    parent.removeAlarmCreater();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(parent, "SQL error: " + ex.getMessage());
                }
            }
            if(e.getSource() == btnCancel){
                parent.removeAlarmCreater();
            }
        }
        
    }
}
