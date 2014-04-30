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
import BLL.Car_AccessLink;
import BLL.IObserver;
import BLL.MainFrameLogic;
import BLL.Position_AccessLink;
import Presentation.Components.ListPanel;
import Presentation.Components.TabView;
import Presentation.Components.ViewObjects.ViewObjectAlarm;
import Presentation.Components.ViewObjects.ViewObjectCar;
import Presentation.Components.ViewObjects.ViewObjectFactory;
import Presentation.Components.ViewObjects.ViewObjectPosition;
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
    
    TabView tv;
    LogIn li;
    
    ListPanel alarmPanel;
    ListPanel carPanel;
    ListPanel positionPanel;
    ListPanel approveListPanel;
    
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
            panelListener = new myListPanelListener();
            
            initComponents();
            setResizable(false);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            this.setSize(dim.width, dim.height);
            this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
            setBackground(MyConstants.COLOR_BLUE);
            
            BorderLayout bl =  new BorderLayout();
            setLayout(bl);
            
            
            alarmPanel = getAlarmPanel();
            carPanel = getCarPanel();
            positionPanel = getPositionPanel();
            approvePanel = getApprovePanel();
            
            alarmPanel.addSelectionObserver(panelListener);
            carPanel.addSelectionObserver(panelListener);
            positionPanel.addSelectionObserver(panelListener);
            
            mfl.addDataList(alarmPanel);
            mfl.addDataList(carPanel);
            mfl.addDataList(positionPanel);
            
            tv = new TabView();
            tv.addNewTab("alarm", alarmPanel, dim.width);
            tv.addNewTab("car", carPanel, dim.width);
            tv.addNewTab("position", positionPanel, dim.width);
            tv.addNewTab("Godkend", approvePanel, dim.width);
            li = new LogIn(this);
            add(li, BorderLayout.CENTER);
            
            //tv.setEnabledContent(p2, false);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, "Could not use the config file, error: " + ex);
        }

        
    }
    
    protected ListPanel getAlarmPanel() {
        ListPanel list = new ListPanel();
        try{
            ArrayList<Alarm> alarms = aal.getAllUnfinishedAlarms();
            for(Alarm alarm : alarms){
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
        try{
            ArrayList<Position> positions = pal.getAllPositions();
            for(Position pos : positions){
                list.addViewObject(vof.getViewObject(pos));
            }
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database call error: " + ex);
        }
        return list;
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
        btnAccept.setFont(null);
        JButton btnCancel = new JButton("Fortryd");
        btnCancel.setBackground(MyConstants.COLOR_RED);
        btnCancel.setForeground(Color.WHITE);
        footer.add(btnAccept);
        footer.add(btnCancel);
        approvePanel.add(footer, BorderLayout.SOUTH);
        
        return approvePanel;
    }
    
    
    protected void fillApproveListPanel(){
        approveListPanel.addViewObject(alarmPanel.getSelectedViewObject());
        approveListPanel.addViewObject(carPanel.getSelectedViewObject());
        approveListPanel.addViewObject(positionPanel.getSelectedViewObject());
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
        add(tv, BorderLayout.CENTER);
        validate();
        repaint();
    }
    
    public void logOut (){
        remove(tv);
        remove(fot);
        add(li, BorderLayout.CENTER);
        li.setFocus();
        repaint();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    
    private class myListPanelListener implements IObserver{

        @Override
        public void notifyObserver() {
            if(mfl.getNextPanel() == null){
                approveListPanel.clearList();
                System.out.println("Test");
                fillApproveListPanel();
                tv.setSelectedComponent(approvePanel);
                
            }
            else
                tv.setSelectedComponent(mfl.getNextPanel());
            
        }
        
    }

}
