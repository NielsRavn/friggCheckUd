/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Components.ViewObjects;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.Date;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Brobak
 */
public class ViewObjectTime extends ViewObject{
    Date date;
    JPanel topPanel;
    JPanel buttomPanel;
    Date endDate;
    int endHour;
    int endMin;
    public ViewObjectTime(Date date){
        this.date = date;
        endDate = new Date(System.currentTimeMillis());
        setLayout(new BorderLayout());
        endHour = endDate.getHours();
        endMin = endDate.getMinutes();
    }
    
    
    private void fillData() {
        topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        Font topFont = new Font("Verdana", Font.PLAIN, 42);
        JLabel lblDateString = new JLabel("Dato: ");
        lblDateString.setFont(topFont);
        JLabel lblDate = new JLabel(" - " + date.toString());
        lblDate.setFont(topFont);
        topPanel.add(lblDateString);
        topPanel.add(lblDate);
        add(topPanel, BorderLayout.NORTH);
        
        buttomPanel = new JPanel();
        buttomPanel.setLayout(new FlowLayout());
        Font buttomFont = new Font("Verdana", Font.PLAIN, 24);
        
        JLabel lblStringInit = new JLabel("Fra kl ");
        lblStringInit.setFont(buttomFont);
        
        JLabel lblTimeStart = new JLabel(date.getHours() + ":" + date.getMinutes());
        lblTimeStart.setFont(buttomFont);
        
        JLabel lblStringDevider = new JLabel(" til ");
        lblStringDevider.setFont(buttomFont);
        
        JLabel lblTimeEnd = new JLabel(endHour + ":" + endMin);
        
        
        buttomPanel.add(lblStringInit);
        buttomPanel.add(lblTimeStart);
        
        add(buttomPanel, BorderLayout.CENTER);
    }
    
    @Override
    public void setBackground(Color color){
        super.setBackground(color);
        if(topPanel != null)
            topPanel.setBackground(color);
        if(buttomPanel != null)
            buttomPanel.setBackground(color);
    }
    
    
}
