 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Frames;

import BE.Alarm;
import BE.Car;
import BE.Comment;
import BE.Equipment;
import BE.EquipmentStatus;
import BE.MyTime;
import BE.Position;
import BE.Time_Sheet;
import BE.Usage;
import BLL.Alarm_AccessLink;
import BLL.Car_AccessLink;
import BLL.Equipment_AccessLink;
import BLL.IObserver;
import BLL.MainFrameLogic;
import BLL.Position_AccessLink;
import BLL.TimeSheet_AccessLink;
import Presentation.Components.AlarmCreater;
import Presentation.Components.AproveTimeSheet;
import Presentation.Components.EquipmentUsageList;
import Presentation.Components.ListPanel;
import Presentation.Components.TabView;
import Presentation.Components.TimePicker;
import Presentation.Components.ViewObjects.ViewObject;
import Presentation.Components.ViewObjects.ViewObjectAlarm;
import Presentation.Components.ViewObjects.ViewObjectCar;
import Presentation.Components.ViewObjects.ViewObjectComment;
import Presentation.Components.ViewObjects.ViewObjectEquipmentStatus;
import Presentation.Components.ViewObjects.ViewObjectFactory;
import Presentation.Components.ViewObjects.ViewObjectPosition;
import Presentation.Components.ViewObjects.ViewObjectStationDuty;
import Presentation.Components.ViewObjects.ViewObjectTime;
import Presentation.MyConstants;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.text.ViewFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Niels
 */
public class MainFrame extends JFrame {

    Car_AccessLink cal;
    Alarm_AccessLink aal;
    Position_AccessLink pal;
    Equipment_AccessLink eal;
    Footer fot;
    AproveTimeSheet ats;
    MainFrameLogic mfl;
    myListPanelListener panelListener;
    TimeSheet_AccessLink tsa;
    int width;
    TabView tv;
    LogIn li;
    MessagePane messagePane;
    TimePicker tp;
    AlarmCreater ac;
    Header head;
    JButton btnApproveAccept, btnApproveCancel, btnCreateAlarm;
    ListPanel alarmPanel, carPanel, positionPanel, approveListPanel;
    EquipmentUsageList equipmentPanel;
    JPanel approvePanel, primaryAlarmPanel, mainAlarmPanel;

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        super("FRIGG Check Ud");
        
        try {
            cal = new Car_AccessLink();
            aal = new Alarm_AccessLink();
            pal = new Position_AccessLink();
            eal = new Equipment_AccessLink();

            mfl = new MainFrameLogic();
            tsa = new TimeSheet_AccessLink();
            panelListener = new myListPanelListener();

            initComponents();
            setResizable(false);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            this.setSize(dim.width, dim.height);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBackground(MyConstants.COLOR_BLUE);

            BorderLayout bl = new BorderLayout();
            setLayout(bl);
            width = dim.width;

            li =  LogIn.getInstance(this);
            add(li, BorderLayout.CENTER);
            
            messagePane = new MessagePane();
            add(messagePane, BorderLayout.SOUTH);

            head = new Header();
            add(head, BorderLayout.NORTH);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(rootPane, "Could not use the config file, error: " + ex);
        }

    }

    /**
     * creates the tabs to be shown and sets alot of other things, connot be run without a logged in user.
     */
    public void createPanels() {
        head.setUser(li.getFireman().getFirstName() + " " + li.getFireman().getLastName());
        fot = new Footer(this);
        primaryAlarmPanel = getAlarmPanel();
        carPanel = getCarPanel();
        positionPanel = getPositionPanel();
        approvePanel = getApprovePanel();
        equipmentPanel = getEquipmentUsageList();
        alarmPanel.addSelectionObserver(panelListener);
        carPanel.addSelectionObserver(panelListener);
        positionPanel.addSelectionObserver(panelListener);
        approveListPanel.addSelectionObserver(new myAcceptListPanelListener());

        mfl.addDataList(alarmPanel);
        mfl.addDataList(carPanel);
        mfl.addDataList(positionPanel);

        tv = new TabView();
        tv.addNewTab("alarm", primaryAlarmPanel, width);
        tv.addNewTab("car", carPanel, width);
        tv.addNewTab("position", positionPanel, width);
        tv.addNewTab("Godkend", approvePanel, width);
        tv.addNewTab("Forbrug", equipmentPanel, width);
        tv.setEnabledContent(equipmentPanel, false);
        
    }
    
    /**
     * creates a new equipmentusage list
     * @return  the equipment usage list to use.
     */
    protected EquipmentUsageList getEquipmentUsageList() {
        EquipmentUsageList list = null;

        try {
            ArrayList<Equipment> equipments = eal.getAllEquipmentTypes();
            list = new EquipmentUsageList(equipments, this);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database call error: " + ex);
        }

        return list;
    }
    
    /**
     * adds a new alarm to the alarm panel
     * @param alarm the new alarm
     */
    public void addAlarm(Alarm alarm){
        alarmPanel.addViewObject(ViewObjectFactory.getViewObject(alarm));
        validate();
        repaint();
    }
    
    /**
     * creates the alarm panel to be show alarms and be able to create new alarm
     * it contains a list of alarms
     * @return the panel to show in the tab.
     */
    protected JPanel getAlarmPanel(){
        mainAlarmPanel = new JPanel();
        mainAlarmPanel.setLayout(new BorderLayout());
        alarmPanel = new ListPanel(width);
        try{
            ArrayList<Alarm> alarms = aal.getAllUnfinishedAlarms();
            for(Alarm alarm : alarms){
                alarmPanel.addViewObject(ViewObjectFactory.getViewObject(alarm));
            }
        } catch (SQLException | ParserConfigurationException| SAXException| IOException ex) {
            JOptionPane.showMessageDialog(this, "Database call error: " + ex);
            ex.printStackTrace();
        }
        
        JPanel footer = new JPanel();
        footer.setLayout(new FlowLayout());
        btnCreateAlarm = new JButton("<html><body marginwidth=30 marginheight=20>Opret ny alarm</body></html>");
        btnCreateAlarm.setBackground(MyConstants.COLOR_BLUE);
        btnCreateAlarm.setForeground(Color.WHITE);
        btnCreateAlarm.setFont(MyConstants.FONT_BUTTON_FONT);
        btnCreateAlarm.addActionListener(new MyActionListener());
        footer.add(btnCreateAlarm);
        
        btnCreateAlarm.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.this.addAlarmCreater(new AlarmCreater(MainFrame.this));
                MainFrame.this.validate();
                MainFrame.this.repaint();
            }
        });
        
        
        
        mainAlarmPanel.add(alarmPanel, BorderLayout.CENTER);
        mainAlarmPanel.add(footer, BorderLayout.SOUTH);
        return mainAlarmPanel;
    }
    
    /**
     * adds the alarm creator to the frame
     * @param aCreater 
     */
    public void addAlarmCreater(AlarmCreater aCreater){
        if(ac != null) remove(ac);
        ac = aCreater;
        add(ac, BorderLayout.EAST);
    }
    
    /**
     * removes the alarmcreator from the frame
     */
    public void removeAlarmCreater(){
        if(ac != null){
            remove(ac);
            validate();
            repaint();
        }
    }

    /**
     * creates a listpanel with the cars from the database.
     * @return a listpanel with cars.
     */
    protected ListPanel getCarPanel() {
        ListPanel list = new ListPanel(width);
        try {

            ArrayList<Car> cars = cal.getAllCars();
            list.addViewObject(ViewObjectFactory.getViewObject(MyConstants.STATION_DUTY_VIEW));
            for (Car car : cars) {
                list.addViewObject(ViewObjectFactory.getViewObject(car));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database call error: " + ex);
        }
        return list;
    }

    /**
     * gets a listpanel with all the positions, it only showa what the logged in firman can be.
     * @return the listpanel with positions
     */
    protected ListPanel getPositionPanel() {
        ListPanel list = new ListPanel(width);

        ArrayList<Position> positions = createPositions();
        for (Position pos : positions) {
            list.addViewObject(ViewObjectFactory.getViewObject(pos));
        }

        return list;
    }

    /**
     * checks what the logged in firman can be and creates the positions from that
     * @return 
     */
    private ArrayList<Position> createPositions() {

        ArrayList<Position> positions = new ArrayList();
        positions.add(MyConstants.FIREMAN);
        if (li.getFireman().isDriver()) {
            positions.add(MyConstants.DRIVER);
        }
        if (li.getFireman().isTeamLeader()) {
            positions.add(MyConstants.TEAM_LEADER);
        }

        return positions;
    }
    
    

    /**
     * Creates and returns an approve panel
     *
     * @return a approve panel
     */
    protected JPanel getApprovePanel() {
        JPanel approvePanel = new JPanel();
        approvePanel.setLayout(new BorderLayout());

        approveListPanel = new ListPanel(width);
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

    /**
     * fills the approve panel list with the selected items from each list
     * and the timepicker and comment field.
     */
    protected void fillApproveListPanel() {
        ViewObjectAlarm voa = (ViewObjectAlarm) alarmPanel.getSelectedViewObject();
        approveListPanel.addViewObject(ViewObjectFactory.getViewObject(voa.getViewObjectBE()));
        if (carPanel.getSelectedViewObject().getClass() == ViewObjectCar.class) {
            ViewObjectCar voc = (ViewObjectCar) carPanel.getSelectedViewObject();
            approveListPanel.addViewObject(ViewObjectFactory.getViewObject(voc.getViewObjectBE()));
            approveListPanel.addViewObject(ViewObjectFactory.getViewObject(positionPanel.getSelectedViewObject().getViewObjectBE()));
            try {
                ArrayList<Usage> usages = eal.getUsagesFor(voa.getAlarm().getID(), voc.getCar().getCarNr());
                equipmentPanel.setAmountForUsages(usages);
                EquipmentStatus es = new EquipmentStatus(!usages.isEmpty());
                equipmentPanel.setStatusViewObject(es);
                approveListPanel.addViewObject(ViewObjectFactory.getViewObject(es));
                tv.setEnabledContent(equipmentPanel, true);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database call error: " + ex);
            }
        } else {
            ViewObjectStationDuty station = (ViewObjectStationDuty) carPanel.getSelectedViewObject();
            approveListPanel.addViewObject(ViewObjectFactory.getViewObject(station.getStation()));
            approveListPanel.addViewObject(ViewObjectFactory.getViewObject(MyConstants.STATION_DUTY));
            tv.setEnabledContent(equipmentPanel, false);
        }
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(voa.getTime().getTime());
        Calendar endDate = Calendar.getInstance();
        int startHour = cal.get(Calendar.HOUR_OF_DAY);
        int startMin = cal.get(Calendar.MINUTE);
        int endHour = endDate.get(Calendar.HOUR_OF_DAY);
        int endMin = endDate.get(Calendar.MINUTE);
        approveListPanel.addViewObject(ViewObjectFactory.getViewObject(
                new MyTime(new Timestamp(voa.getTime().getTime()), new Timestamp(voa.getTime().getTime()),startHour, startMin, endHour, endMin)
        ));
        approveListPanel.addViewObject(ViewObjectFactory.getViewObject(new Comment("")));

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
            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    /**
     * change view to the tabs when a user has logged in
     */
    public void changeView() {
        remove(li);
        remove(messagePane);
        createPanels();
        add(fot, BorderLayout.SOUTH);
        add(tv, BorderLayout.CENTER);
        validate();
        repaint();
    }

    /**
     * chenge view to the login page removing every thing not needed
     */
    public void logOut() {
        remove(tv);
        remove(fot);
        if (ats != null) {
            remove(ats);
        }
        head.loggedOut();
        if (tp != null) {
            remove(tp);
        }
        removeAlarmCreater();
        mfl.reset();
        add(li, BorderLayout.CENTER);
        add(messagePane, BorderLayout.SOUTH);
        li.setFocus();
        repaint();
    }

    /**
     *shifts to approving timesheets
     */
    public void aproveTimesheet() {
        remove(tv);
        ats = new AproveTimeSheet(this, li.fireman.getID());
        add(ats, BorderLayout.CENTER);
        validate();
        repaint();

    }

    /**
     * gets the width of this object
     * @return 
     */
    @Override
    public int getWidth() {
        return width;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    /**
     * ads the time picker with the given time input, also setting most things disabled
     * @param time the time to set into the time picker
     */
    public void addTimePicker(MyTime time) {
        tp = new TimePicker(this, time);
        add(tp, BorderLayout.EAST);
        tv.setEnabled(false);
        approveListPanel.setElementsEnabled(false);
        fot.setElementsEnabled(false);
        validate();
        repaint();
    }

    /**
     * removes the timepicker, setting things enabled again
     */
    public void removeTimePicker() {
        remove(tp);
        tv.setEnabled(true);
        approveListPanel.setElementsEnabled(true);
        fot.setElementsEnabled(true);
        validate();
        repaint();
    }
    
    /**
     * @return the id of the selected alarm
     */
    public int getSelectedAlarmId() {
       return ((ViewObjectAlarm)alarmPanel.getSelectedViewObject()).getAlarm().getID();
    }

    /**
     * @return the carnr of the selected car.
     */
    public int getselectedCarNr() {
        return ((ViewObjectCar)carPanel.getSelectedViewObject()).getCar().getCarNr();
    }

    /**
     * updates the usages from the equipment usage to the database and goes to approve panel
     * @param usagesToUpdate 
     */
    public void completeEquipmentUsage(ArrayList<Usage> usagesToUpdate) {
        try {
            eal.updateUsages(usagesToUpdate);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database call error: " + ex);
        }
        goToApprovePanel();
    }
    
    /**
     * goes to the approve panel and refreshes the objects.
     */
    public void goToApprovePanel(){
        tv.setSelectedComponent(approvePanel);
        approveListPanel.refreshAllViewobjects();
    }

    /**
     * added to the three first lists to change tabs when something is selected.
     */
    private class myListPanelListener implements IObserver {

        @Override
        public void notifyObserver() {
            JPanel next = mfl.getNextPanel();
            if ( next == null) {
                approveListPanel.clearList();
                fillApproveListPanel();
                tv.setSelectedComponent(approvePanel);
 
            } else { 
                if(next == alarmPanel)
                    tv.setSelectedIndex(0);
                else
                    tv.setSelectedComponent(next);
            }

        }

    }

    /**
     * added to the approve panel to handle when somthing is clicked and the tab should shift to the apropriate tab.
     */
    private class myAcceptListPanelListener implements IObserver {

        @Override
        public void notifyObserver() {
            Class test = approveListPanel.getSelectedViewObject().getClass();
            if (test == ViewObjectAlarm.class) {
                tv.setSelectedComponent(primaryAlarmPanel);
            } else if (test == ViewObjectCar.class
                    || test == ViewObjectStationDuty.class) {
                tv.setSelectedComponent(carPanel);
            } else if (test == ViewObjectPosition.class) {
                tv.setSelectedComponent(positionPanel);
            }else if (test == ViewObjectEquipmentStatus.class) {
                tv.setSelectedComponent(equipmentPanel);
            } else if (test == ViewObjectTime.class) {
                ViewObjectTime vot = (ViewObjectTime) approveListPanel.getSelectedViewObject();
                addTimePicker(vot.getTime());
                tp.addObserver(vot);
            }
        }

    }

    /**
     * action listener for the buttons on the approve page, approve send all the data to the database
     * decline just logs you out.
     */
    private class MyActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnApproveAccept) {
                ArrayList<ViewObject> vos = approveListPanel.getAllViewObject();
                ViewObjectTime vot = null;
                    for (ViewObject vo : vos) {
                        if (vo.getClass() == ViewObjectTime.class) {
                            vot = (ViewObjectTime) vo;
                        }
                    }
                if (vot != null) {
                    int carNumber;
                    ViewObjectAlarm alarm = (ViewObjectAlarm) alarmPanel.getSelectedViewObject();
                    if (carPanel.getSelectedViewObject().getClass() == ViewObjectStationDuty.class) {
                        carNumber = 0;
                    } else {
                        ViewObjectCar voc = (ViewObjectCar) carPanel.getSelectedViewObject();
                        carNumber = voc.getCar().getCarNr();
                    }
                    int positionId;
                    if (carNumber == 0) {
                        positionId = MyConstants.STATION_DUTY.getID();
                    } else {
                        ViewObjectPosition vop = (ViewObjectPosition) positionPanel.getSelectedViewObject();
                        positionId = vop.getPosition().getID();
                    }
                    
                    Timestamp endTime = vot.getEndTime();
                    Timestamp startTime = vot.getStartTime();
                    
                    Time_Sheet ts = new Time_Sheet(li.getFireman().getID(), alarm.getAlarm().getID(), carNumber, positionId, startTime, endTime, 0, 0,false, new Comment(""));
                    String comment = null;
                    for(ViewObject vo: approveListPanel.getAllViewObject()){
                        if(vo.getClass() == ViewObjectComment.class){
                            ViewObjectComment voc = (ViewObjectComment)vo;
                            comment = voc.getComment();
                        }
                    }
                    if(!comment.equals("") && comment != null){
                        ts.setComment(new Comment(comment));
                    }
                    
                    try {
                        tsa.addTimeSheet(ts);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        
                        JOptionPane.showMessageDialog(rootPane, "Du kan ikke melde dig på samme alarm 2 gange.");
                    }
                    logOut();
                }
            } else if (e.getSource() == btnApproveCancel) {
                    logOut();
                logOut();
            }
        }

    }

}
