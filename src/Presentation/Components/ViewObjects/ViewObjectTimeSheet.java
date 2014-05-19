/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Components.ViewObjects;

import BE.ApprovalSheet;
import BE.TimeSheetList;
import BE.Time_Sheet;
import BLL.TimeSheet_AccessLink;
import Presentation.Components.ViewObjectTimeSheetTableModel;
import Presentation.Frames.LogIn;
import Presentation.MyConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author Poul Nielsen
 */
public class ViewObjectTimeSheet extends ViewObject {
    
    ViewObjectTimeSheetTableModel model;
    public int getCarId;
    Font myFont = new Font("Verdana", Font.PLAIN, 30);
    JTable firemen;
    TimeSheetList timesheets;
    JButton btnAproveTimesheet;
    JPanel aproveCarPanel;
    JPanel pnlaproveCarPanel;
    JPanel pnlaproveTimesheet;
    JCheckBox aproveAllOnCar;
    ApprovalSheet approvalSheet;
    TimeSheet_AccessLink tsa;
    protected ViewObjectTimeSheet( TimeSheetList timesheets) {
        super(timesheets);
        this.timesheets = timesheets;
        model = new ViewObjectTimeSheetTableModel();
        try {
            tsa = new TimeSheet_AccessLink();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Der er fejl i databasen, kontakt system administratoren " +  ex);
        }
        fillData();
        
    }

    private JPanel getAproveCarPanel() {
        pnlaproveCarPanel = new JPanel();
        aproveAllOnCar = new JCheckBox();
        
        aproveAllOnCar.setText(" Vælg alle på bilen");
        aproveAllOnCar.setFont(MyConstants.FONT_HEADER_TEXT);
        aproveAllOnCar.addActionListener( new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                for(int i = 0; model.getRowCount()> i; i++)
                {
                    model.setValueAt(true, i, 5);
                }
                model.fireTableDataChanged();
            }
        }
        );
        
        btnAproveTimesheet = new JButton();
        
        btnAproveTimesheet.setFont(MyConstants.FONT_BUTTON_FONT); // NOI18N
        btnAproveTimesheet.setBackground(MyConstants.COLOR_GREEN);
        btnAproveTimesheet.setForeground(Color.WHITE);
        btnAproveTimesheet.setText("<html><body marginwidth=30 marginheight=20>Godkend Timerne</body></html>");
        btnAproveTimesheet.setActionCommand("<html><body marginwidth=30 marginheight=20>Godkend Timerne</body></html>");

        final ArrayList<Time_Sheet> app = new ArrayList<>();
        btnAproveTimesheet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                for(int i = 0; model.getRowCount()> i; i++)
                {
                    String coment = null;
                    Time_Sheet t = model.getTimeSheet(i);
                    if(model.getValueAt(i, 5).equals(false))
                    {
                        coment = JOptionPane.showInternalInputDialog(ViewObjectTimeSheet.this, "Kometar til timesedlen på " + t.getFireman().getFirstName() + " " + t.getFireman().getLastName());
                    }
                    
                    approvalSheet = new ApprovalSheet(LogIn.getFiremanStatic(), coment, (boolean) model.getValueAt(i, 5) , (Integer)model.getValueAt(i, 4) );
                    
                    app.add(t);
                    
                }
                
                try {
                    tsa.aproveTimesheetByTimesheetId(app, approvalSheet);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(ViewObjectTimeSheet.this, "Der er sket en database fejl, kontakt system administrator " + ex);
                }
                
            }
        });
        
        pnlaproveCarPanel.setLayout(new FlowLayout());
        
        pnlaproveCarPanel.add(btnAproveTimesheet);
        pnlaproveCarPanel.add(aproveAllOnCar);
        return pnlaproveCarPanel;
    }
    
    
    protected void fillData()
    {
        setLayout(new BorderLayout());
        model.clearList();
        JPanel vest = new JPanel();
        vest.setLayout(new GridBagLayout());
        JPanel center = new JPanel();
        center.setLayout(new BorderLayout());
        GridBagConstraints c = new GridBagConstraints();
        firemen = new JTable();
        firemen.setRowHeight(28);
        JScrollPane scrl = new JScrollPane();
        scrl.setViewportView(firemen);
        scrl.setPreferredSize(new Dimension(0,200));
        firemen.setModel(model);
        c.fill = GridBagConstraints.CENTER;
        
                if(timesheets.getCar() == null)
                {
                    c.gridx = 0;
                    c.gridy = 1;
                    vest.add(new JLabel(new ImageIcon(timesheets.getStation().getIconPath())), c);
                    
                    JLabel stationInfo = new JLabel(""+timesheets.getStation().getName());
                    stationInfo.setFont(myFont);
                    c.gridx = 0;
                    c.gridy = 0;
                    vest.add(stationInfo, c);
                }
                else
                {
                    c.gridx = 0;
                    c.gridy = 1;
                    vest.add(new JLabel(new ImageIcon(timesheets.getCar().getIconPath())), c);
                    
                    JLabel carInfo = new JLabel("Bil Nr.: "+timesheets.getCar().getCarNr());
                    carInfo.setFont(myFont);
                    c.gridx = 0;
                    c.gridy = 0;
                    vest.add(carInfo, c);
                }
        
        for(Time_Sheet a : timesheets.getTeamleaders())
        {
            model.addTimeSheet(a);
        }
        for(Time_Sheet a : timesheets.getChauffeurs())
        {
             model.addTimeSheet(a);
        }
        for(Time_Sheet a : timesheets.getFiremen())
        {
             model.addTimeSheet(a);
        }
        for(Time_Sheet a : timesheets.getStationDutyFiremen())
        {
             model.addTimeSheet(a);
        }
        center.add(scrl, BorderLayout.CENTER);
        add(vest , BorderLayout.WEST);
        add(center , BorderLayout.CENTER);
        center.add(getAproveCarPanel() , BorderLayout.SOUTH);
        addCellRenderers();
    }

    private void addCellRenderers() {
        TableColumn titleCol = firemen.getColumnModel().getColumn(0);
        titleCol.setPreferredWidth(80);
        TableColumn nameCol = firemen.getColumnModel().getColumn(1);
        nameCol.setPreferredWidth(180);
        TableColumn startCol = firemen.getColumnModel().getColumn(2);
        startCol.setPreferredWidth(120);
        TableColumn endCol = firemen.getColumnModel().getColumn(3);
        endCol.setPreferredWidth(120);
        TableColumn timeCol = firemen.getColumnModel().getColumn(4);
        timeCol.setPreferredWidth(20);
        TableColumn checkbox = firemen.getColumnModel().getColumn(5);
        checkbox.setPreferredWidth(40);
        titleCol.setCellRenderer(new TimeSheetTableRendere());
        nameCol.setCellRenderer(new TimeSheetTableRendere());
        startCol.setCellRenderer(new TimeSheetTableRendere());
        endCol.setCellRenderer(new TimeSheetTableRendere());
        timeCol.setCellRenderer(new TimeSheetTableRendere());
    }

    @Override
    public void refreshViewObject() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    private class TimeSheetTableRendere extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            
            JLabel lbl = new JLabel(value.toString());
            lbl.setFont(MyConstants.FONT_HEADER_TEXT);
            return lbl;
        }
        
    }
          

}
