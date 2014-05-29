/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Components.ViewObjects;

import BE.IViewObjectBE;
import java.awt.GridBagLayout;
import javax.swing.JLabel;

/**
 *
 * @author Brobak
 */
public class ViewObjectBasic extends ViewObject{
    IViewObjectBE vob;
    /**
     * Creates a new basic view object
     * @param vob the BE you want to be represented in the viewObject
     */
    protected ViewObjectBasic(IViewObjectBE vob){
        super(vob);
        this.vob = vob;
        
        fillData();
    }
    /**
     * fills the nessesary data in to the viewobject
     */
    protected void fillData(){
        setLayout(new GridBagLayout());
        add(new JLabel(vob.getName()));
    }

    /**
     * Does nothing
     */
    @Override
    public void refreshViewObject() {
        
    }
    
}
