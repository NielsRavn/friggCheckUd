/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Components.ViewObjects;

import BE.Story;
import java.awt.TextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Niels
 */
public class ViewObjectStory extends ViewObject {

    Story story;
    
    /**
     * Creates new form ViewObjectStory
     */
    public ViewObjectStory(Story story) {
        super(story);
        this.story = story;
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        TFEva = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        TFBrand = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TANavne = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TAAdresser = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        RBBlind = new javax.swing.JRadioButton();
        RBFalse = new javax.swing.JRadioButton();
        RBNoUse = new javax.swing.JRadioButton();
        RBNormal = new javax.swing.JRadioButton();
        jPanel14 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        TFGruppe = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        TFDetektor = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TAComent = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        jLabel1.setText("Eva nr:");
        jPanel1.add(jLabel1);

        TFEva.setPreferredSize(new java.awt.Dimension(75, 20));
        jPanel1.add(TFEva);

        jPanel2.add(jPanel1);

        jLabel2.setText("Brandrapport Nr:");
        jPanel3.add(jLabel2);

        TFBrand.setPreferredSize(new java.awt.Dimension(75, 20));
        jPanel3.add(TFBrand);

        jPanel2.add(jPanel3);

        add(jPanel2, java.awt.BorderLayout.NORTH);

        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel5.setLayout(new java.awt.BorderLayout());

        jLabel3.setText("Oplysninger om skadeslidte:");
        jPanel6.add(jLabel3);

        jPanel5.add(jPanel6, java.awt.BorderLayout.NORTH);

        jPanel9.setLayout(new java.awt.BorderLayout());

        TANavne.setColumns(20);
        TANavne.setRows(2);
        jScrollPane1.setViewportView(TANavne);

        jPanel9.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jLabel4.setText("Navne");
        jPanel9.add(jLabel4, java.awt.BorderLayout.NORTH);

        jPanel7.add(jPanel9);

        jPanel8.setLayout(new java.awt.BorderLayout());

        TAAdresser.setColumns(20);
        TAAdresser.setRows(2);
        jScrollPane2.setViewportView(TAAdresser);

        jPanel8.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jLabel5.setText("Adresser");
        jPanel8.add(jLabel5, java.awt.BorderLayout.NORTH);

        jPanel7.add(jPanel8);

        jPanel5.add(jPanel7, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel5, java.awt.BorderLayout.NORTH);

        jPanel10.setLayout(new java.awt.BorderLayout());

        jLabel6.setText("Beretning:");
        jPanel11.add(jLabel6);

        jPanel10.add(jPanel11, java.awt.BorderLayout.PAGE_START);

        jPanel12.setLayout(new java.awt.BorderLayout());

        buttonGroup1.add(RBBlind);
        RBBlind.setText("Blind alarm");
        jPanel13.add(RBBlind);

        buttonGroup1.add(RBFalse);
        RBFalse.setText("Falsk alarm");
        jPanel13.add(RBFalse);

        buttonGroup1.add(RBNoUse);
        RBNoUse.setText("Ikke i brug");
        jPanel13.add(RBNoUse);

        buttonGroup1.add(RBNormal);
        RBNormal.setSelected(true);
        RBNormal.setText("Normal");
        jPanel13.add(RBNormal);

        jPanel12.add(jPanel13, java.awt.BorderLayout.NORTH);

        jLabel8.setText("Ved ABA - alarm: ");
        jPanel14.add(jLabel8);

        jLabel9.setText("Gruppenummer: ");
        jPanel14.add(jLabel9);

        TFGruppe.setPreferredSize(new java.awt.Dimension(50, 20));
        jPanel14.add(TFGruppe);

        jLabel10.setText("Detektornummer");
        jPanel14.add(jLabel10);

        TFDetektor.setPreferredSize(new java.awt.Dimension(50, 20));
        jPanel14.add(TFDetektor);

        jPanel12.add(jPanel14, java.awt.BorderLayout.CENTER);

        jPanel16.setLayout(new java.awt.BorderLayout());

        jScrollPane3.setPreferredSize(new java.awt.Dimension(350, 96));

        TAComent.setColumns(20);
        TAComent.setRows(5);
        jScrollPane3.setViewportView(TAComent);

        jPanel16.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jLabel7.setText("Bemærkninger:");
        jPanel16.add(jLabel7, java.awt.BorderLayout.PAGE_START);

        jPanel15.add(jPanel16);

        jPanel12.add(jPanel15, java.awt.BorderLayout.SOUTH);

        jPanel10.add(jPanel12, java.awt.BorderLayout.CENTER);

        jPanel4.add(jPanel10, java.awt.BorderLayout.CENTER);

        add(jPanel4, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton RBBlind;
    private javax.swing.JRadioButton RBFalse;
    private javax.swing.JRadioButton RBNoUse;
    private javax.swing.JRadioButton RBNormal;
    private javax.swing.JTextArea TAAdresser;
    private javax.swing.JTextArea TAComent;
    private javax.swing.JTextArea TANavne;
    private javax.swing.JTextField TFBrand;
    private javax.swing.JTextField TFDetektor;
    private javax.swing.JTextField TFEva;
    private javax.swing.JTextField TFGruppe;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables

    public Story getStory(){
        int evaNr = Integer.parseInt(TFEva.getText());
        int brandNr = Integer.parseInt(TFBrand.getText());
        String navne = TANavne.getText();
        String addresser = TAAdresser.getText();
        int type = getSelectedRadioButton();
        int grup =  Integer.parseInt(TFGruppe.getText());
        int detek =  Integer.parseInt(TFDetektor.getText());
        String comment = TAComent.getText();
        return new Story(story.getAlarmId(), evaNr, brandNr, navne, addresser, type, grup, detek, comment);
    }

    private int getSelectedRadioButton() {
        if(RBBlind.isSelected()) return 0;
        else if(RBFalse.isSelected()) return 1;
        else if(RBNoUse.isSelected()) return 2;
        else return 3;
    }

    @Override
    protected void fillData() {
        initComponents();
        myKeyNumbersAdapter kna = new myKeyNumbersAdapter();
        TFBrand.addKeyListener(kna);
        TFEva.addKeyListener(kna);
        TFGruppe.addKeyListener(kna);
        TFDetektor.addKeyListener(kna);
    }

    @Override
    public void refreshViewObject() {
        
    }
    
    
    /**
     * listens for any key event to trigger a check of the textfields.
     */
    private class myKeyNumbersAdapter extends KeyAdapter{

        @Override
        public void keyTyped(KeyEvent e) {
            if(!Character.isDigit(e.getKeyChar()) || ((TextField)e.getComponent()).getText().length() >= 8)
                e.consume();
        }
    }
    
}
