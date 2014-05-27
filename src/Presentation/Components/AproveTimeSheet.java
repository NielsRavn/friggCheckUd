/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Components;

import BE.Alarm;
import BE.Story;
import BE.TimeSheetList;
import BE.Time_Sheet;
import BLL.Alarm_AccessLink;
import BLL.IObserver;
import BLL.TimeSheet_AccessLink;
import Presentation.Components.ViewObjects.ViewObject;
import Presentation.Components.ViewObjects.ViewObjectAlarm;
import Presentation.Components.ViewObjects.ViewObjectFactory;
import Presentation.Components.ViewObjects.ViewObjectTimeSheet;
import Presentation.Frames.MainFrame;
import Presentation.MyConstants;
import java.awt.BorderLayout;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Poul Nielsen
 */
public class AproveTimeSheet extends javax.swing.JPanel {
    TimeSheet_AccessLink tsa;
    Alarm_AccessLink aal;
    ViewObjectFactory vof;
    TabView tv;
    MainFrame parent;
    ListPanel alarmList, timeSheetList;
    myObserverListener myObserver;
    ArrayList<Alarm> openAlarms; 
    /**
     * Creates new form AproveTimeSheet
     * @param parent
     */
    public AproveTimeSheet(MainFrame parent, int firemanId) {
        initComponents();
        setLayout(new BorderLayout());
        this.parent = parent;
        myObserver = new myObserverListener();
        openAlarms = new ArrayList<>();
        try {
            tsa = new TimeSheet_AccessLink();
            aal = new Alarm_AccessLink();
            vof = new ViewObjectFactory();
        } catch (IOException ex) {
          
        }
        createTabs(firemanId);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 755, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 593, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    private void createTabs(int firemanId)
    {
        alarmList = getAlarmByFiremanId(firemanId);
        alarmList.addSelectionObserver(myObserver);
        
        tv = new TabView();
        tv.addNewTab("alarm", alarmList, parent.getWidth());
        
        add(tv, BorderLayout.CENTER);
        validate();
        repaint();
    }
    
    protected ListPanel getAlarmByFiremanId(int firemanId)
    {
        ArrayList<Time_Sheet> timeSheet = new ArrayList<Time_Sheet>();
        
        ListPanel list = new ListPanel(true, parent.getWidth());
        ArrayList<Alarm> alarms = new ArrayList<Alarm>();
        try{
            timeSheet = tsa.getTimeSheetsbyFiremanId(firemanId);
            
            for(Time_Sheet c : timeSheet)
            {
                if(!arrayContainsAlarmId(alarms, c.getAlarmID())){
                    alarms.add(aal.getAlarmById(c.getAlarmID()));
                }
            }
       
        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database call error: " + ex);
            ex.printStackTrace();
        }
        for(Alarm a : alarms){
                list.addViewObject(vof.getViewObject(a));
            }
        
        return list;
    }
    
    private ListPanel getTimeSheetByAlarmId(int alarmId)
    {
        ArrayList<Time_Sheet> timeSheet = new ArrayList<Time_Sheet>();
        ArrayList<Time_Sheet> timeSheetStatinsduty = new ArrayList<Time_Sheet>();
        ListPanel list = new ListPanel(true, parent.getWidth());
        try {
            timeSheet = tsa.getDataForAproval(alarmId);
            timeSheetStatinsduty = tsa.stationsVagt(alarmId);
            //here lays the logic for showing the timesheets order by cars and station duty
            ArrayList<TimeSheetList> timeSheetLists = new ArrayList<>();
            for(Time_Sheet a : timeSheet)
            {
                boolean carFound = false;
                for(TimeSheetList ts : timeSheetLists){
                    if(ts.getCar() != null && ts.getCar().getCarNr() == a.getCar().getCarNr()){
                        carFound = true;
                        populateFiremenViewObject(a, ts);
                        
                    }
                }
                if(!carFound){
                    TimeSheetList tsl = new TimeSheetList(a);
                    populateFiremenViewObject(a, tsl);
                    timeSheetLists.add(tsl);
                    //list.addViewObject(ViewObjectFactory.getViewObject(tsl));
                }
            }
            TimeSheetList v;
            if(timeSheetStatinsduty.size() > 0){
                v = new TimeSheetList(timeSheetStatinsduty.get(0));
                for(Time_Sheet b : timeSheetStatinsduty)
                {
                    v.addStationDuty(b);
                    
                }
                list.addViewObject(ViewObjectFactory.getViewObject(v));
            }
            
            for(TimeSheetList tsl: timeSheetLists){
                list.addViewObject(ViewObjectFactory.getViewObject(tsl));
            }
            Story s = new Story(alarmId);
            list.addViewObject(ViewObjectFactory.getViewObject(s));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database call error: " + ex);
        }
        
        
        return list;
    }

    private void populateFiremenViewObject(Time_Sheet a, TimeSheetList v) {
        if(a.getPositionID() == MyConstants.TEAM_LEADER.getID())
        {
            v.addTeamLeader(a);
        }
        if(a.getPositionID() == MyConstants.DRIVER.getID())
        {
            v.addDriver(a);
        }
        if(a.getPositionID() == MyConstants.FIREMAN.getID())
        {
            v.addFireman(a);
        }
        
    }

    private boolean arrayContainsAlarmId(ArrayList<Alarm> alarms, int alarmID) {
        for(Alarm a: alarms){
            if(a.getID() == alarmID) return true;
        }
        
        return false;
    }
    
    
  
    
    private class myObserverListener implements IObserver{

        @Override
        public void notifyObserver() {
            
        if(alarmList.getSelectedViewObject().getClass() == ViewObjectAlarm.class)
        {
            ViewObjectAlarm voa = (ViewObjectAlarm)alarmList.getSelectedViewObject();
            
            Alarm alarm = voa.getAlarm();
            
            String alarmTitle = voa.getAlarm().getDistination();
            if(!openAlarms.contains(alarm))
            {
                timeSheetList = getTimeSheetByAlarmId(alarm.getID());
                tv.addNewTab(alarmTitle, timeSheetList, parent.getWidth());
                tv.setSelectedComponent(timeSheetList);
                openAlarms.add(alarm);
            }
            
        }
        }
        
    }
    
}

