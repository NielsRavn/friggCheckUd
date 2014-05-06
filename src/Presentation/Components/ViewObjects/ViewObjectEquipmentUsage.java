/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Components.ViewObjects;

import BE.Equipment;
import Presentation.Components.IntervalButton;
import Presentation.MyConstants;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Brobak
 */
public class ViewObjectEquipmentUsage extends ViewObject{
    Equipment equipment;
    JTextField equipmentAmount;
    int amount;
    public ViewObjectEquipmentUsage(Equipment equipment){
        setLayout(new BorderLayout());
        this.equipment = equipment;
        amount = 0;
        fillData();
        
    }
    
    /**
     * Fills the panel with data from the Equipment object
     */
    private void fillData(){
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        JLabel equipmentLbl = new JLabel(equipment.getName());
        equipmentLbl.setFont(MyConstants.FONT_HEADER_TEXT);
        topPanel.add(equipmentLbl);
        equipmentAmount = new JTextField(amount);
        equipmentAmount.setFont(MyConstants.FONT_HEADER_TEXT);
        equipmentAmount.setPreferredSize(new Dimension(100, 40));
        topPanel.add(equipmentAmount);
        JLabel unitType = new JLabel(equipment.getUnitType());
        unitType.setFont(MyConstants.FONT_HEADER_TEXT);
        topPanel.add(unitType);
        add(topPanel, BorderLayout.CENTER);
        
    }
}
