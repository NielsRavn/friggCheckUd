/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Frames;

import Presentation.MyConstants;
import java.awt.Color;
import javax.swing.ImageIcon;

/**
 *
 * @author Niels
 */
public class Header extends javax.swing.JPanel {

    /**
     * Creates new form Header
     */
    public Header() {
        initComponents();
        lblLogo.setIcon(new ImageIcon("res/brandogredninglogo.png"));
        lblUser.setFont(MyConstants.FONT_HEADER_TEXT);
        lblUser.setForeground(Color.WHITE);
        setBackground(MyConstants.COLOR_BLUE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblLogo = new javax.swing.JLabel();
        lblUser = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        add(lblLogo, java.awt.BorderLayout.WEST);
        add(lblUser, java.awt.BorderLayout.LINE_END);
    }// </editor-fold>//GEN-END:initComponents

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblUser;
    // End of variables declaration//GEN-END:variables

    public void setUser(String fullName){
        lblUser.setText("<html><body>Logget ind som: &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "
                + "<br>" + fullName + "  </body></html>");
    }
    
    public void loggedOut(){
        lblUser.setText("");
    }

}
