/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Components.ViewObjects;

import BE.Position;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JLabel;

/**
 *
 * @author Brobak
 */
public class ViewObjectPosition extends ViewObject{
    Position position;
    protected ViewObjectPosition(Position position){
        super(position);
        
        this.position = position;
    }

    @Override
    protected void fillData() {
        setLayout(new FlowLayout());
        JLabel lblPos = new JLabel(position.getName());
        lblPos.setFont(new Font("Comic Sans", Font.PLAIN, 80));
        add(lblPos);
    }
    
    public Position getPosition(){
        return position;
    }

    @Override
    public void refreshViewObject() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
