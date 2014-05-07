/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Components;

import BE.Equipment;
import BE.Usage;
import Presentation.Components.ViewObjects.ViewObjectEquipmentStatus;
import Presentation.Components.ViewObjects.ViewObjectEquipmentUsage;
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
    ArrayList<ViewObjectEquipmentUsage> voeus;
    JButton btnAccept, btnDecline;
    ArrayList<Usage> usages;
    MainFrame parent;
    ViewObjectEquipmentStatus voes;

    public EquipmentUsageList(boolean editable, ArrayList<Equipment> equipments, MainFrame parent) {
        this.parent = parent;
        panel = new ListPanel(editable);
        this.equipments = equipments;
        voeus = new ArrayList<>();
        for(Equipment e: equipments){
            ViewObjectEquipmentUsage voeu = new ViewObjectEquipmentUsage(e);
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
        for(ViewObjectEquipmentUsage e: voeus){
            boolean updated = false;
            for(Usage u: usages){
                if(e.getEquipment().getId() == u.getEquipmentId()){
                    e.setAmount(u.getAmount());
                    updated = true;
                }
            }
            if(!updated) e.setAmount(0);
        }
    }

    public void setStatusViewObject(ViewObjectEquipmentStatus voes) {
        this.voes = voes;
    }
    
    private class MyActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ev) {
            if (ev.getSource() == btnAccept) {
                int alarmId = parent.getSelectedAlarmId();
                int carNr = parent.getselectedCarNr();
                ArrayList<Usage> usagesToUpdate = new ArrayList<>();
                for(ViewObjectEquipmentUsage e: voeus){
                    Usage use = null;
                    for(Usage u: usages){
                        if(e.getEquipment().getId() == u.getEquipmentId()) {
                            use = u;
                            if(u.getAmount() != e.getAmount()){
                                use.setAmount(e.getAmount());
                                usagesToUpdate.add(use);
                            }
                        }
                    }
                    if(use == null && e.getAmount() != 0) {
                        use = new Usage(alarmId, carNr, e.getEquipment().getId(), e.getAmount());
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
