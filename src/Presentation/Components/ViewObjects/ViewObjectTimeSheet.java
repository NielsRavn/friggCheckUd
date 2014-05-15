/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Components.ViewObjects;

import BE.Alarm;
import BE.Car;
import BE.Fireman;
import BE.Station;
import BE.Time_Sheet;
import Presentation.Components.ListPanel;
import Presentation.Components.ViewObjectTimeSheetTableModel;
import Presentation.MyConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
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
    Alarm alarm;
    Car car;
    Station station;
    ViewObjectTimeSheetTableModel model;
    ArrayList<Time_Sheet> hl;
    ArrayList<Time_Sheet> ch;
    ArrayList<Time_Sheet> bm;
    ArrayList<Time_Sheet> sd;
    public int getCarId;
    int FZ = 30;
    JTable firemen;
    JButton aproveCar;
    JPanel aproveCarPanel;
    
    public ViewObjectTimeSheet( Time_Sheet timesheet) {
        this.car = timesheet.getCar();
        this.station = timesheet.getStation();
        model = new ViewObjectTimeSheetTableModel();
        setLayout(new BorderLayout());
        hl = new ArrayList<>();
        ch = new ArrayList<>();
        bm = new ArrayList<>();
        sd = new ArrayList<>();
         
        getAproveCarPanel();
        
        fillData();
    }

    private JPanel getAproveCarPanel() {
        aproveCarPanel = new JPanel();
        aproveCar = new JButton();
        aproveCarPanel.setLayout(new BorderLayout());
        aproveCar.setFont(MyConstants.FONT_BUTTON_FONT); // NOI18N
        aproveCar.setBackground(MyConstants.COLOR_GREEN);
        aproveCar.setForeground(Color.WHITE);
        aproveCar.setText("<html><body marginwidth=30 marginheight=20>Godkend Bil</body></html>");
        aproveCar.setActionCommand("<html><body marginwidth=30 marginheight=20>Godkend Bil</body></html>");
        aproveCar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                for(int i = 0; model.getRowCount()> i; i++)
                {
                    model.setValueAt(true, i, 5);
                    
                }
            }
        });
        aproveCarPanel.add(aproveCar, BorderLayout.EAST);
        return aproveCarPanel;
    }
    
    
    private void fillData()
    {
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
        
                if(car == null)
                {
                    c.gridx = 0;
                    c.gridy = 1;
                    vest.add(new JLabel(new ImageIcon(station.getIconPath())), c);
                    
                    JLabel stationInfo = new JLabel(""+station.getName());
                    stationInfo.setFont(new Font("Verdana", Font.PLAIN, FZ));
                    c.gridx = 0;
                    c.gridy = 0;
                    vest.add(stationInfo, c);
                }
                else
                {
                    c.gridx = 0;
                    c.gridy = 1;
                    vest.add(new JLabel(new ImageIcon(car.getIconPath())), c);
                    
                    JLabel carInfo = new JLabel("Bil Nr.: "+car.getCarNr());
                    carInfo.setFont(new Font("Verdana", Font.PLAIN, FZ));
                    c.gridx = 0;
                    c.gridy = 0;
                    vest.add(carInfo, c);
                }
        
                for(Time_Sheet a : hl)
                {
                    model.addTimeSheet(a);
                }
                for(Time_Sheet a : ch)
                {
                     model.addTimeSheet(a);
                }
                for(Time_Sheet a : bm)
                {
                     model.addTimeSheet(a);
                }
                for(Time_Sheet a : sd)
                {
                     model.addTimeSheet(a);
                }
        center.add(scrl, BorderLayout.NORTH);
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
    

    private class TimeSheetTableRendere extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            
            JLabel lbl = new JLabel(value.toString());
            lbl.setFont(MyConstants.FONT_HEADER_TEXT);
            return lbl;
        }
        
    }
          

    public Object getCar() {
        return car;
    }

    public void addTeamLeader(Time_Sheet a) {
        hl.add(a);
        fillData();
    }

    public void addDriver(Time_Sheet a) {
        ch.add(a);
        fillData();
    }

    public void addFireman(Time_Sheet a) {
        bm.add(a);
        fillData();
        
    }
    
    public void addStationDuty(Time_Sheet a) {
        sd.add(a);
        fillData();
    }

    public int getCarId() {
        return car.getCarNr();
    }
    
    
}
