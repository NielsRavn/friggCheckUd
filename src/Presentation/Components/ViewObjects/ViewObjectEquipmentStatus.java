/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Components.ViewObjects;

import BE.Equipment;
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
    
    boolean equipmentHasData;
    JLabel equipmentLbl;
    JPanel topPanel;
    
    public ViewObjectEquipmentStatus(boolean equipmentHasData){
        setLayout(new BorderLayout());
        this.equipmentHasData = equipmentHasData;
        fillData();
        
    }

    public void setEquipmentHasData(boolean equipmentHasData) {
        this.equipmentHasData = equipmentHasData;
        setEquipmentlblLook();
    }
    
    private void setEquipmentlblLook(){
        equipmentLbl.setText("<html><body marginheight=15>"+(equipmentHasData ? "Forbrug er blevet udfyldt." : "Forbrug er ikke blevet udfyldt endnu.") +"</body></html>");
        equipmentLbl.setForeground(equipmentHasData ? MyConstants.COLOR_GREEN : Color.BLACK);
    }
    
    /**
     * Fills the panel with data from the Equipment object
     */
    private void fillData(){
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
