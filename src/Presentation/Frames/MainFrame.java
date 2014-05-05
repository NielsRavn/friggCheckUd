 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Frames;

import BE.Alarm;
import BE.Car;
import BE.Position;
import BE.Time_Sheet;
import BLL.Alarm_AccessLink;
import BLL.Car_AccessLink;
import BLL.IObserver;
import BLL.MainFrameLogic;
import BLL.Position_AccessLink;
import BLL.TimeSheet_AccessLink;
import Presentation.Components.AproveTimeSheet;
import Presentation.Components.ListPanel;
import Presentation.Components.TabView;
import Presentation.Components.TimePicker;
import Presentation.Components.ViewObjects.ViewObject;
import Presentation.Components.ViewObjects.ViewObjectAlarm;
import Presentation.Components.ViewObjects.ViewObjectCar;
import Presentation.Components.ViewObjects.ViewObjectFactory;
import Presentation.Components.ViewObjects.ViewObjectPosition;
import Presentation.Components.ViewObjects.ViewObjectStationDuty;
import Presentation.Components.ViewObjects.ViewObjectTime;
import Presentation.MyConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
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
    AproveTimeSheet ats;
    MainFrameLogic mfl;
    myListPanelListener panelListener;
    TimeSheet_AccessLink tsa;
    int width;
    TabView tv;
    LogIn li;
    TimePicker tp;
    Header head;
    JButton btnApproveAccept, btnApproveCancel;
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
            
            head = new Header();
            add(head, BorderLayout.NORTH);
            
            //tv.setEnabledContent(p2, false);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, "Could not use the config file, error: " + ex);
        }

        
    }
    
    public void createPanels(){
        head.setUser(li.getFireman().getFirstName()+ " " + li.getFireman().getLastName());
        fot = new Footer(this);
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
            list.addViewObject(vof.getViewObject(MyConstants.STATION_DUTY_VIEW));
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
        btnApproveAccept = new JButton("<html><body marginwidth=30 marginheight=20>Accepter</body></html>");
        btnApproveAccept.setBackground(MyConstants.COLOR_GREEN);
        btnApproveAccept.setForeground(Color.WHITE);
        btnApproveAccept.setFont(MyConstants.FONT_BUTTON_FONT);
        btnApproveAccept.addActionListener(new MyActionListener());
        
        btnApproveCancel = new JButton("<html><body marginwidth=30 marginheight=20>Fortryd</body></html>");
        btnApproveCancel.setBackground(MyConstants.COLOR_RED);
        btnApproveCancel.setForeground(Color.WHITE);
        btnApproveCancel.setFont(MyConstants.FONT_BUTTON_FONT);
        btnApproveCancel.addActionListener(new MyActionListener());
        
        footer.add(btnApproveAccept);
        footer.add(btnApproveCancel);
        approvePanel.add(footer, BorderLayout.SOUTH);
        
        return approvePanel;
    }
    
    
    protected void fillApproveListPanel(){
        ViewObjectAlarm voa = (ViewObjectAlarm) alarmPanel.getSelectedViewObject();
        approveListPanel.addViewObject(voa);
        if(carPanel.getSelectedViewObject().getClass() == ViewObjectCar.class){
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
         createPanels();
        add(fot, BorderLayout.SOUTH);
       add(tv, BorderLayout.CENTER);
        validate();
        repaint();
    }
    
    public void logOut (){
        remove(tv);
        remove(fot);
        if(ats != null) remove(ats);
        head.loggedOut();
        if(tp != null) remove(tp);
        mfl.reset();
        add(li, BorderLayout.CENTER);
        li.setFocus();
        repaint();
    }
    /**
     * 
     */
    public void aproveTimesheet() {
        remove(tv);
        ats = new AproveTimeSheet(this, li.fireman.getID());
        add(ats, BorderLayout.CENTER);
        validate();
        repaint();
        
        
    }
    
    public int getWidth()
    {
        return width;
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
    
    private class MyActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == btnApproveAccept){
                int carNumber;
                ViewObjectAlarm alarm = (ViewObjectAlarm)alarmPanel.getSelectedViewObject();
                if(carPanel.getSelectedViewObject().getClass() == ViewObjectStationDuty.class)
                    carNumber = 0;
                else{
                    ViewObjectCar voc = (ViewObjectCar)carPanel.getSelectedViewObject();
                    carNumber = voc.getCar().getCarNr();
                }
                int positionId;
                if(carNumber == 0){
                    positionId = MyConstants.STATION_DUTY.getID();
                }else{
                    ViewObjectPosition vop = (ViewObjectPosition)positionPanel.getSelectedViewObject();
                    positionId = vop.getPosition().getID();
                }
                
                ArrayList<ViewObject> vos = approveListPanel.getAllViewObject();
                ViewObjectTime vot = null;
                for(ViewObject vo: vos){
                    if(vo.getClass() == ViewObjectTime.class)
                        vot = (ViewObjectTime)vo;
                }
                
                Time endTime = vot.getEndTime();
                
                Time_Sheet ts = new Time_Sheet(li.getFireman().getID(), alarm.getAlarm().getID() , carNumber, positionId, endTime, false);
                try {
                    tsa.addTimeSheet(ts);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(rootPane, "An sql error has accured " + ex);
                }
                logOut();
            }else if(e.getSource() == btnApproveCancel){
                logOut();
            }
        }
        
    }
    
    

}
