/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Frames;

import BE.Alarm;
import BE.Car;
import BE.Position;
import BLL.Alarm_AccessLink;
import BLL.TimeSheet_AccessLink;
import BLL.Car_AccessLink;
import BLL.IObserver;
import BLL.MainFrameLogic;
import BLL.Position_AccessLink;
import Presentation.Components.ListPanel;
import Presentation.Components.TabView;
import Presentation.Components.TimePicker;
import Presentation.Components.ViewObjects.ViewObjectAlarm;
import Presentation.Components.ViewObjects.ViewObjectCar;
import Presentation.Components.ViewObjects.ViewObjectFactory;
import Presentation.Components.ViewObjects.ViewObjectPosition;
import Presentation.Components.ViewObjects.ViewObjectTime;
import Presentation.MyConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

/**
 *
 * @author Niels
 */
public class MainFrame extends javax.swing.JFrame {
    Car_AccessLink cal;
    Alarm_AccessLink aal;
    Position_AccessLink pal;
    ViewObjectFactory vof;
    Footer fot;
    MainFrameLogic mfl;
    myListPanelListener panelListener;
    TimeSheet_AccessLink tsa;
    int width;
    TabView tv;
    LogIn li;
    TimePicker tp;
    
    ListPanel alarmPanel, carPanel, positionPanel, approveListPanel;
    
    JPanel approvePanel;
    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        super("FRIGG Check Ud");
        try {
            cal = new Car_AccessLink();
            aal = new Alarm_AccessLink();
            pal = new Position_AccessLink();
            vof = new ViewObjectFactory();
            fot = new Footer(this);
            mfl = new MainFrameLogic();
            tsa = new TimeSheet_AccessLink();
            
            panelListener = new myListPanelListener();
            
            initComponents();
            setResizable(false);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            this.setSize(dim.width, dim.height);
            this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
            setBackground(MyConstants.COLOR_BLUE);
            
            BorderLayout bl =  new BorderLayout();
            setLayout(bl);
            
            width = dim.width;
            
            li = new LogIn(this);
            add(li, BorderLayout.CENTER);
            
            //tv.setEnabledContent(p2, false);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, "Could not use the config file, error: " + ex);
        }

        
    }
    
    public void createPanels(){
        alarmPanel = getAlarmPanel();
        carPanel = getCarPanel();
        positionPanel = getPositionPanel();
        approvePanel = getApprovePanel();

        alarmPanel.addSelectionObserver(panelListener);
        carPanel.addSelectionObserver(panelListener);
        positionPanel.addSelectionObserver(panelListener);
        approveListPanel.addSelectionObserver(new myAcceptListPanelListener());

        mfl.addDataList(alarmPanel);
        mfl.addDataList(carPanel);
        mfl.addDataList(positionPanel);

        tv = new TabView();
        tv.addNewTab("alarm", alarmPanel, width);
        tv.addNewTab("car", carPanel, width);
        tv.addNewTab("position", positionPanel, width);
        tv.addNewTab("Godkend", approvePanel, width);
    }
    
    protected ListPanel getAlarmPanel() {
        ListPanel list = new ListPanel();
        try{
            ArrayList<Alarm> alarms = aal.getAllUnfinishedAlarms();
            for(Alarm alarm : alarms){
                System.out.println(alarm.getTime().getTime());
                list.addViewObject(vof.getViewObject(alarm));
            }
            
        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database call error: " + ex);
        }
        return list;
    }
        
    protected ListPanel getCarPanel(){
        ListPanel list = new ListPanel();
        try {
            
            ArrayList<Car> cars = cal.getAllCars();
            cars.add(MyConstants.STATION_DUTY_CAR);
            for(Car car : cars){
                list.addViewObject(vof.getViewObject(car));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database call error: " + ex);
        }
        return list;
    }
    
    protected ListPanel getPositionPanel(){
        ListPanel list = new ListPanel();
        
        ArrayList<Position> positions = createPositions();
        for(Position pos : positions){
            list.addViewObject(vof.getViewObject(pos));
        }

        return list;
    }
    
    
    private ArrayList<Position> createPositions(){
        
        ArrayList<Position> positions = new ArrayList();
        positions.add(MyConstants.FIREMAN);
        if(li.getFireman().isDriver())
            positions.add(MyConstants.DRIVER);
        if(li.getFireman().isTeamLeader())
            positions.add(MyConstants.TEAM_LEADER);
        
        return positions;
    }
    
    /**
     * Creates and returns a approve panel
     * @return a approve panel
     */
    protected JPanel getApprovePanel(){
        JPanel approvePanel = new JPanel();
        approvePanel.setLayout(new BorderLayout());
        
        approveListPanel = new ListPanel();
        approvePanel.add(approveListPanel, BorderLayout.CENTER);
        
        JPanel footer = new JPanel();
        footer.setLayout(new FlowLayout());
        JButton btnAccept = new JButton("Accepter");
        btnAccept.setBackground(MyConstants.COLOR_GREEN);
        btnAccept.setForeground(Color.WHITE);
        btnAccept.setFont(MyConstants.FONT_BUTTON_FONT);
        JButton btnCancel = new JButton("Fortryd");
        btnCancel.setBackground(MyConstants.COLOR_RED);
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setFont(MyConstants.FONT_BUTTON_FONT);
        footer.add(btnAccept);
        footer.add(btnCancel);
        approvePanel.add(footer, BorderLayout.SOUTH);
        
        return approvePanel;
    }
    
    
    protected void fillApproveListPanel(){
        ViewObjectAlarm voa = (ViewObjectAlarm) alarmPanel.getSelectedViewObject();
        approveListPanel.addViewObject(voa);
        ViewObjectCar carView = (ViewObjectCar)carPanel.getSelectedViewObject();
        if(carView.getCarNumber() != 0){
            approveListPanel.addViewObject(carPanel.getSelectedViewObject());
            approveListPanel.addViewObject(positionPanel.getSelectedViewObject());
        }else{
            approveListPanel.addViewObject(carPanel.getSelectedViewObject());
            approveListPanel.addViewObject(new ViewObjectPosition(MyConstants.STATION_DUTY));
        }
        approveListPanel.addViewObject(new ViewObjectTime(voa.getTime()));
        
    }
    
    
     

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        //</editor-fold>
        
        UIManager.put("TabbedPane.tabAreaBackground",
            MyConstants.COLOR_BLUE);
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
    
    public void changeView (){
        remove(li);
        add(fot, BorderLayout.SOUTH);
        createPanels();
        add(tv, BorderLayout.CENTER);
        validate();
        repaint();
    }
    
    public void logOut (){
        remove(tv);
        remove(fot);
        remove(tp);
        add(li, BorderLayout.CENTER);
        li.setFocus();
        repaint();
    }
    
    public void aproveTimesheet()
    {
        tsa.aproveTimesheet(li.getFireman());
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    public void addTimePicker(int hour, int minute){
        tp = new TimePicker(this, hour, minute);
        add(tp, BorderLayout.EAST);
        tv.setEnabled(false);
        approveListPanel.setElementsEnabled(false);
        fot.setElementsEnabled(false);
        validate();
        repaint();
    }
    
    public void removeTimePicker() {
        remove(tp);
        tv.setEnabled(true);
        approveListPanel.setElementsEnabled(true);
        fot.setElementsEnabled(true);
        validate();
        repaint();
    }
    
    private class myListPanelListener implements IObserver{

        @Override
        public void notifyObserver() {
            if(mfl.getNextPanel() == null){
                approveListPanel.clearList();
                fillApproveListPanel();
                tv.setSelectedComponent(approvePanel);
                
            }
            else
                tv.setSelectedComponent(mfl.getNextPanel());
            
        }
        
    }
    
    private class myAcceptListPanelListener implements IObserver{

        @Override
        public void notifyObserver() {
            if(approveListPanel.getSelectedViewObject().getClass() == ViewObjectAlarm.class){
                tv.setSelectedComponent(alarmPanel);
            }else if(approveListPanel.getSelectedViewObject().getClass() == ViewObjectCar.class){
                tv.setSelectedComponent(carPanel);
            }else if(approveListPanel.getSelectedViewObject().getClass() == ViewObjectPosition.class){
                tv.setSelectedComponent(positionPanel);
            }else if(approveListPanel.getSelectedViewObject().getClass() == ViewObjectTime.class){
                ViewObjectTime vot = (ViewObjectTime) approveListPanel.getSelectedViewObject();
                addTimePicker(vot.getEndHour(), vot.getEndMin());
                tp.addObserver(vot);
            }
        }
        
    }

}
