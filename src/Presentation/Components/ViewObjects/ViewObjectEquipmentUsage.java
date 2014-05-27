/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Components.ViewObjects;

import BE.EquipmentUsage;
import Presentation.MyConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Brobak
 */
public class ViewObjectEquipmentUsage extends ViewObject{
    EquipmentUsage equipmentUsage;
    JTextField equipmentAmount;
    protected ViewObjectEquipmentUsage(EquipmentUsage equipmentUsage){
        super(equipmentUsage);
        this.equipmentUsage = equipmentUsage;
        fillData();
    }
    
    /**
     * Fills the panel with data from the Equipment object
     */
    protected void fillData(){
        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        JLabel equipmentLbl = new JLabel(equipmentUsage.getName());
        equipmentLbl.setFont(MyConstants.FONT_HEADER_TEXT);
        topPanel.add(equipmentLbl);
        equipmentAmount = new JTextField(equipmentUsage.getUsage().getAmount());
        equipmentAmount.setFont(MyConstants.FONT_HEADER_TEXT);
        equipmentAmount.setPreferredSize(new Dimension(100, 40));
        equipmentAmount.addFocusListener(new MyFocusListener());
        equipmentAmount.addKeyListener(new myKeyAdapter());
        topPanel.add(equipmentAmount);
        JLabel unitType = new JLabel(equipmentUsage.getEquipment().getUnitType());
        unitType.setFont(MyConstants.FONT_HEADER_TEXT);
        topPanel.add(unitType);
        add(topPanel, BorderLayout.CENTER);
        
    }
    
    /**
     * checks the input in the given textfield is a valid number
     * Also fixes the string in the textfield so that nothing invalid is written.
     * @param toTest the textfield to be tested
     * @param maxNumber the max number that the field should hold
     * @return the correct number that is shown
     */
    private int checkInputForTextField(JTextField toTest){
        int result = -1;
        while(result == -1){
            if(!toTest.getText().equals("")){


                String text = toTest.getText();
                boolean takeAwayLast = false;

                try{
                    result = Integer.parseInt(text);
                }catch (NumberFormatException e){
                    takeAwayLast = true;
                }

                if(takeAwayLast){
                    text = text.substring(0, text.length()-1);
                }
                toTest.setText(text);
            }else result = 0;
        }
        return result;
    }
    
    private void checkInput() {
        try{
            equipmentUsage.getUsage().setAmount(checkInputForTextField(equipmentAmount));
        }catch (IllegalStateException e){}
    }

    @Override
    public void refreshViewObject() {
        equipmentAmount.setText(""+equipmentUsage.getUsage().getAmount());
    }
    
    /**
     * listens for any key event to trigger a check of the textfields.
     */
    private class myKeyAdapter extends KeyAdapter{

        @Override
        public void keyReleased(KeyEvent e) {
            checkInput();
        }
        
        @Override
        public void keyTyped(KeyEvent e) {
            if(!Character.isDigit(e.getKeyChar()) || equipmentAmount.getText().length() >= 8)
                e.consume();
        }
    }
    
    private class MyFocusListener implements FocusListener{

        @Override
        public void focusGained(FocusEvent e) {
            if(equipmentAmount.getText().equals("0")) equipmentAmount.setText("");
        }

        @Override
        public void focusLost(FocusEvent e) {
            if(equipmentAmount.getText().equals("")) equipmentAmount.setText("0");
            checkInput();
        }
        
    }
}
