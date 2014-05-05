/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Components;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Brobak
 */
public class IntervalButton extends JButton{
    int value;
    public IntervalButton(ImageIcon icon, int value){
        super(icon);
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    
    
}
