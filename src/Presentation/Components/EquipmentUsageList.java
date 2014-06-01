/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Components;

import BE.Equipment;
import BE.EquipmentStatus;
import BE.EquipmentUsage;
import BE.Usage;
import Presentation.Components.ViewObjects.ViewObject;
import Presentation.Components.ViewObjects.ViewObjectFactory;
import Presentation.Frames.MainFrame;
import Presentation.MyConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Niels Kristian Ravn
 */
public class EquipmentUsageList extends JPanel{
    
    ArrayList<Equipment> equipments;
    ListPanel panel;
    ArrayList<ViewObject> voeus;
    JButton btnAccept, btnDecline;
    ArrayList<Usage> usages;
    MainFrame parent;
    EquipmentStatus voes;

    /**
     * creates a new equipment usage panel, to manage the eqipment usage list.
     * @param equipments
     * @param parent 
     */
    public EquipmentUsageList(ArrayList<Equipment> equipments, MainFrame parent) {
        this.parent = parent;
        panel = new ListPanel(parent.getWidth());
        this.equipments = equipments;
        voeus = new ArrayList<>();
        for(Equipment e: equipments){
            ViewObject voeu = ViewObjectFactory.getViewObject(new EquipmentUsage(e, new Usage()));
            voeus.add(voeu);
            panel.addViewObject(voeu); 
        }
        createPanel();
    }
    
    private void createPanel(){
        setLayout(new BorderLayout());
        
        add(panel, BorderLayout.CENTER);

        JPanel footer = new JPanel();
        footer.setLayout(new FlowLayout());
        btnAccept = new JButton("<html><body marginwidth=30 marginheight=20>Gem ændringer</body></html>");
        btnAccept.setBackground(MyConstants.COLOR_GREEN);
        btnAccept.setForeground(Color.WHITE);
        btnAccept.setFont(MyConstants.FONT_BUTTON_FONT);
        btnAccept.addActionListener(new MyActionListener());

        btnDecline = new JButton("<html><body marginwidth=30 marginheight=20>Annuller</body></html>");
        btnDecline.setBackground(MyConstants.COLOR_RED);
        btnDecline.setForeground(Color.WHITE);
        btnDecline.setFont(MyConstants.FONT_BUTTON_FONT);
        btnDecline.addActionListener(new MyActionListener());

        footer.add(btnAccept);
        footer.add(btnDecline);
        add(footer, BorderLayout.SOUTH);
    }
    
    public void setAmountForUsages(ArrayList<Usage> usages){
        this.usages = usages;
        for(ViewObject e: voeus){
            EquipmentUsage eu = (EquipmentUsage) e.getViewObjectBE();
            boolean updated = false;
            for(Usage u: usages){
                if(eu.getEquipment().getId() == u.getEquipmentId()){
                    eu.setUsage(new Usage(u.getAlarmId(), u.getCarNr(), u.getEquipmentId(), u.getAmount()));
                    updated = true;
                }
            }
            if(!updated) eu.setUsage(new Usage());
        }
        panel.refreshAllViewobjects();
    }

    public void setStatusViewObject(EquipmentStatus voes) {
        this.voes = voes;
    }
    
    private class MyActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ev) {
            if (ev.getSource() == btnAccept) {
                int alarmId = parent.getSelectedAlarmId();
                int carNr = parent.getselectedCarNr();
                ArrayList<Usage> usagesToUpdate = new ArrayList<>();
                for(ViewObject e: voeus){
                    EquipmentUsage eu = (EquipmentUsage) e.getViewObjectBE();
                    Usage use = null;
                    for(Usage u: usages){
                        if(eu.getEquipment().getId() == u.getEquipmentId()) {
                            use = u;
                            if(u.getAmount() != eu.getUsage().getAmount()){
                                use.setAmount(eu.getUsage().getAmount());
                                usagesToUpdate.add(use);
                            }
                        }
                    }
                    if(use == null && eu.getUsage().getAmount() != 0) {
                        use = new Usage(alarmId, carNr, eu.getEquipment().getId(), eu.getUsage().getAmount());
                        usages.add(use);
                        usagesToUpdate.add(use);
                    }
                }
                voes.setEquipmentHasData(!usages.isEmpty());
                parent.completeEquipmentUsage(usagesToUpdate); 
            } else if (ev.getSource() == btnDecline) {
                setAmountForUsages(usages);
                parent.goToApprovePanel();
            }
        }

    }
}
