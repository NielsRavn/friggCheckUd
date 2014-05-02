/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Frames;

import BLL.TimeSheet_AccessLink;
import Presentation.MyConstants;
import java.awt.Color;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Poul Nielsen
 */
public class Footer extends javax.swing.JPanel {
    MainFrame parent;
    TimeSheet_AccessLink tsl;
    /**
     * 
     * @param parent 
     */
    public Footer(MainFrame parent) {
        initComponents();
        jbLogOut.setBackground(MyConstants.COLOR_RED);
        jbAprove.setBackground(MyConstants.COLOR_GREEN);
        jbErrorReporting.setBackground(MyConstants.COLOR_LIGHT_BLUE);
        this.setBackground(Color.white);
        this.parent = parent;
        try {
            tsl = new TimeSheet_AccessLink();
        } catch (IOException ex) {
            Logger.getLogger(Footer.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(tsl.showTimeAprove(parent.li.getFireman())== true)
        {
           jbAprove.setVisible(true);
        }
        else
        {
            jbAprove.setVisible(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbLogOut = new javax.swing.JButton();
        jbErrorReporting = new javax.swing.JButton();
        jbAprove = new javax.swing.JButton();

        jbLogOut.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jbLogOut.setForeground(new java.awt.Color(255, 255, 255));
        jbLogOut.setText("<html><body marginwidth=30 marginheight=20>Log ud</body></html>");
        jbLogOut.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jbLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbLogOutActionPerformed(evt);
            }
        });

        jbErrorReporting.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jbErrorReporting.setText("<html><body marginwidth=30 marginheight=20>Opret Fejl Report</body></html>");
        jbErrorReporting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbErrorReportingActionPerformed(evt);
            }
        });

        jbAprove.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jbAprove.setForeground(new java.awt.Color(255, 255, 255));
        jbAprove.setText("<html><body marginwidth=30 marginheight=20>Godkend køreseddel</body></html>");
        jbAprove.setActionCommand("<html><body marginwidth=30 marginheight=20>Godkend køreseddel</body></html>");
        jbAprove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAproveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbErrorReporting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbAprove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addComponent(jbLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbErrorReporting, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbAprove, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbLogOutActionPerformed
        
        parent.logOut();
        
    }//GEN-LAST:event_jbLogOutActionPerformed

    private void jbErrorReportingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbErrorReportingActionPerformed
       JOptionPane.showMessageDialog(parent, "Denne funktion er ikke implementeret endnu");
    }//GEN-LAST:event_jbErrorReportingActionPerformed

    private void jbAproveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAproveActionPerformed
        parent.aproveTimesheet();
    }//GEN-LAST:event_jbAproveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbAprove;
    private javax.swing.JButton jbErrorReporting;
    private javax.swing.JButton jbLogOut;
    // End of variables declaration//GEN-END:variables

    void setElementsEnabled(boolean b) {
        jbAprove.setEnabled(b);
        jbErrorReporting.setEnabled(b);
    }
}
