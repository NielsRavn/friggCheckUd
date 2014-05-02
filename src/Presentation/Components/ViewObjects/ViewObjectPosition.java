/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Components.ViewObjects;

import BE.Position;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.Date;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Brobak
 */
public class ViewObjectPosition extends ViewObject{
    Position position;
    public ViewObjectPosition(Position position){
        setLayout(new FlowLayout());
        this.position = position;
        fillData();
    }

    private void fillData() {
        JLabel lblPos = new JLabel(position.getName());
        lblPos.setFont(new Font("Comic Sans", Font.PLAIN, 80));
        add(lblPos);
    }
    
    public Position getPosition(){
        return position;
    }
}
