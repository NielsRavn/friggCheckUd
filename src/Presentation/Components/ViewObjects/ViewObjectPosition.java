/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Components.ViewObjects;

import BE.Position;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.sql.Date;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Brobak
 */
public class ViewObjectPosition extends ViewObject{
    String pos;
    public ViewObjectPosition(Position position){
        setLayout(new FlowLayout());
        pos = position.getName();
        fillData();
    }

    private void fillData() {
        add(new JLabel(pos));
    }
}
