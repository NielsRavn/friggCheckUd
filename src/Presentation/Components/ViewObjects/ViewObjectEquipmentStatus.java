/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Components.ViewObjects;

import BE.Equipment;
import BE.EquipmentStatus;
import Presentation.MyConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Niels Kristian Ravn
 */
public class ViewObjectEquipmentStatus extends ViewObject{
    
    EquipmentStatus equipmentStatus;
    JLabel equipmentLbl;
    JPanel topPanel;
    
    public ViewObjectEquipmentStatus(EquipmentStatus equipmentStatus){
        super(equipmentStatus);
        this.equipmentStatus = equipmentStatus;
        
    }

    public void setEquipmentHasData(boolean equipmentHasData) {
        equipmentStatus.setEquipmentHasData(equipmentHasData);
        setEquipmentlblLook();
    }
    
    private void setEquipmentlblLook(){
        equipmentLbl.setText("<html><body marginheight=15>"+(equipmentStatus.isEquipmentHasData() ? "Forbrug er blevet udfyldt." : "Forbrug er ikke blevet udfyldt endnu.") +"</body></html>");
        equipmentLbl.setForeground(equipmentStatus.isEquipmentHasData() ? MyConstants.COLOR_GREEN : Color.BLACK);
    }
    
    /**
     * Fills the panel with data from the Equipment object
     */
    @Override
    protected void fillData(){
        setLayout(new BorderLayout());
        topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout()); 
        equipmentLbl = new JLabel();
        setEquipmentlblLook();
        equipmentLbl.setFont(MyConstants.FONT_HEADER_TEXT);
        topPanel.add(equipmentLbl);
        
        add(topPanel, BorderLayout.CENTER);
        
    }
    
    @Override
    public void setBackground(Color color){
        super.setBackground(color);
        if(topPanel != null)
            topPanel.setBackground(color);
    }
    
}
