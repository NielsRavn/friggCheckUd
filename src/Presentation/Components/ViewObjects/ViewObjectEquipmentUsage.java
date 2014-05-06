/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Components.ViewObjects;

import BE.Equipment;
import Presentation.Components.IntervalButton;
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
        topPanel.add(equipmentLbl);
        equipmentAmount = new JTextField(amount);
        equipmentAmount.setPreferredSize(new Dimension(70, 30));
        topPanel.add(equipmentAmount);
        topPanel.add(new JLabel(equipment.getUnitType()));
        add(topPanel, BorderLayout.CENTER);
        
    }
}
