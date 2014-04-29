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
import Presentation.Components.ListPanel;
import Presentation.Components.TabView;
import Presentation.Components.ViewObjects.ViewObjectAlarm;
import Presentation.Components.ViewObjects.ViewObjectCar;
import Presentation.Components.ViewObjects.ViewObjectFactory;
import Presentation.Components.ViewObjects.ViewObjectPosition;
import Presentation.MyColorConstants;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    ViewObjectFactory vof;
    
    TabView tv;
    LogIn li;
    
    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        super("FRIGG Check Ud");
        try {
            cal = new Car_AccessLink();
            aal = new Alarm_AccessLink();
            vof = new ViewObjectFactory();
            initComponents();
            setResizable(false);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            this.setSize(dim.width, dim.height);
            this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
            setBackground(MyColorConstants.OUR_BLUE);
            
            BorderLayout bl =  new BorderLayout();
            setLayout(bl);
            Footer f = new Footer();
            add(f, BorderLayout.SOUTH);
            
            JPanel p1 = getAlarmPanel();
            JPanel p2 = getCarPanel();
            JPanel p3 = makePositionPanel();
            ApprovePanel ap = new ApprovePanel();
            
            tv = new TabView();
            tv.addNewTab("alarm", p1);
            tv.addNewTab("car", p2);
            tv.addNewTab("position", p3);
            tv.addNewTab("Godkend", ap);
            li = new LogIn(this);
            add(li, BorderLayout.CENTER);
            
            //tv.setEnabledContent(p2, false);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, "Could not use the config file, error: " + ex);
        }

        
    }
    
    protected JPanel getAlarmPanel() {
        ListPanel list = new ListPanel();
        try{
            ArrayList<Alarm> alarms = aal.getAllUnfinishedAlarms();
            for(Alarm alarm : alarms){
                list.addViewObject(vof.getViewObject(alarm));
            }
            
        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database call error: " + ex);
        }
//        ViewObjectAlarm viewObject1 = new ViewObjectAlarm(new Alarm(25, 25565, "Hansensvej 22", "Brand og redning", new Date(2014-1900, 3, 29), false));
//        ViewObjectAlarm viewObject2 = new ViewObjectAlarm(new Alarm(25, 25567, "Strandbygade 42", "Kat i træ", new Date(2014-1900, 3, 29), false));
//        ViewObjectAlarm viewObject3 = new ViewObjectAlarm(new Alarm(25, 25568, "Hjertingvej 13", "Brand og redning", new Date(2014-1900, 3, 30), false));
//        ViewObjectAlarm viewObject4 = new ViewObjectAlarm(new Alarm(25, 25570, "Skt. Petersplads 0", "Brand og redning", new Date(2014-1900, 3, 31), false));
//        list.addViewObject(viewObject1);
//        list.addViewObject(viewObject2);
//        list.addViewObject(viewObject3);
//        list.addViewObject(viewObject4);
        return list;
    }
        
    protected JPanel getCarPanel(){
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
    
    protected JPanel makePositionPanel(){
        ListPanel list = new ListPanel();
        ViewObjectPosition viewObject1 = new ViewObjectPosition(new Position(0, "Chauffør"));
        ViewObjectPosition viewObject2 = new ViewObjectPosition(new Position(1, "Holdleder"));
        ViewObjectPosition viewObject3 = new ViewObjectPosition(new Position(2, "Brandmand"));
        list.addViewObject(viewObject1);
        list.addViewObject(viewObject2);
        list.addViewObject(viewObject3);
        return list;
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
            MyColorConstants.OUR_BLUE);
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
    
    public void changeView (){
        remove(li);
        add(tv, BorderLayout.CENTER);
        validate();
        repaint();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
