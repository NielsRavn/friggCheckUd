/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Frames;

import BE.Fireman;
import BLL.Fireman_AccessLink;
import Presentation.MyConstants;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Susanne
 */
public class LogIn extends javax.swing.JPanel {

    private Fireman_AccessLink firemanMgr;
    MainFrame parent;
    Fireman fireman;
    static LogIn login;
    
    public static LogIn getInstance(MainFrame parent)
    {
        if(login == null)
            login = new LogIn(parent);
        return login;
    }
    
    private LogIn(MainFrame parent) {
        initComponents();
        btnLogIn.setBackground(MyConstants.COLOR_GREEN);
        
        this.parent = parent;
        try {
            firemanMgr = new Fireman_AccessLink();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "fejl!");
        }

        txtLogIn.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    logIn();
                }
            }
            
            @Override
            public void keyTyped(KeyEvent e) {
                if(!Character.isDigit(e.getKeyChar()) || txtLogIn.getText().length() >=6)
                    e.consume();
            }

        });
        setLayout(new GridBagLayout());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtLogIn = new javax.swing.JTextField();
        btnLogIn = new javax.swing.JButton();

        txtLogIn.setFont(new java.awt.Font("Verdana", 0, 48)); // NOI18N
        txtLogIn.setPreferredSize(new java.awt.Dimension(275, 140));
        add(txtLogIn);

        btnLogIn.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        btnLogIn.setForeground(new java.awt.Color(255, 255, 255));
        btnLogIn.setLabel("<html><body marginwidth=90 marginheight=60>Log Ind</body></html>");
        btnLogIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogInActionPerformed(evt);
            }
        });
        add(btnLogIn);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogInActionPerformed
        logIn();
    }//GEN-LAST:event_btnLogInActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogIn;
    private javax.swing.JTextField txtLogIn;
    // End of variables declaration//GEN-END:variables
    /**
     * Login methode testing if worker exist in system
     */
    private void logIn() { // when LOG IND is pressed 
        if (!IsInteger(txtLogIn.getText()) || txtLogIn.getText().isEmpty()) { // checks if there are Stings or the textfield is empty
            JOptionPane.showMessageDialog(this, "Medarbejder nummer ikke godkendt");
            txtLogIn.setText(null);
            txtLogIn.requestFocus();
        } else { 
            int FiremanID = Integer.parseInt(txtLogIn.getText());
            try { // try to log in
                fireman = firemanMgr.getFiremanByID(FiremanID);
                if (fireman != null) {
                    parent.changeView();
                    txtLogIn.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Medarbejder nummer eksisterer ikke.");
                    txtLogIn.setText("");
                    setFocus();
                }
            } catch (SQLException ex) {
                Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    /**
     * 
     * @param s
     * @return 
     */
    private boolean IsInteger(String s) { // test if the text fiels contians numbers
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    
    public static Fireman getFiremanStatic() throws IllegalStateException{
        if(login == null) throw new IllegalStateException("Der er ikke blevet logget ind endnu");
        return login.getFireman();
    }
    
    /**
     * 
     * @return Fireman
     */
    public Fireman getFireman() {
        return fireman;
    }
    /**
     * Sets focus on txtfiled txtLogIn
     */
    public void setFocus() 
    {
        txtLogIn.requestFocus();
    }
    
}
