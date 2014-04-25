/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Frames;

import java.awt.Color;

/**
 *
 * @author Poul Nielsen
 */
public class Footer extends javax.swing.JPanel {

    /**
     * Creates new form Footer
     */
    public Footer() {
        initComponents();
        jbLogOut.setBackground(Color.RED);
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

        jbLogOut.setText("Log ud");
        jbLogOut.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jbLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbLogOutActionPerformed(evt);
            }
        });

        jbErrorReporting.setText("Opreg Fejl Report");
        jbErrorReporting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbErrorReportingActionPerformed(evt);
            }
        });

        jbAprove.setText("Godkend køreseddel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbErrorReporting)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbAprove)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 332, Short.MAX_VALUE)
                .addComponent(jbLogOut)
                .addGap(26, 26, 26))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbLogOut)
                    .addComponent(jbErrorReporting)
                    .addComponent(jbAprove))
                .addGap(21, 21, 21))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbLogOutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbLogOutActionPerformed

    private void jbErrorReportingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbErrorReportingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbErrorReportingActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbAprove;
    private javax.swing.JButton jbErrorReporting;
    private javax.swing.JButton jbLogOut;
    // End of variables declaration//GEN-END:variables
}
