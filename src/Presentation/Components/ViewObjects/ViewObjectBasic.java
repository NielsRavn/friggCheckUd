/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Components.ViewObjects;

import BE.ViewObjectBE;
import java.awt.GridBagLayout;
import javax.swing.JLabel;

/**
 *
 * @author Brobak
 */
public class ViewObjectBasic extends ViewObject{
    ViewObjectBE vob;
    public ViewObjectBasic(ViewObjectBE vob){
        this.vob = vob;
        setLayout(new GridBagLayout());
        fillData();
    }
    
    private void fillData(){
        add(new JLabel(vob.getName()));
    }
    
}
