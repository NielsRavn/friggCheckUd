/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Components;

import BLL.IObserver;
import Presentation.Components.ViewObjects.ViewObject;
import Presentation.MyColorConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import static java.util.Collections.list;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Brobak
 */
public class ListPanel extends javax.swing.JPanel{
    JPanel thisList;
    DefaultListModel model;
    ArrayList<IObserver> observers;
    
    /**
     * Creates new form ListPanel
     */
    public ListPanel() {
        initComponents();
        thisList = this;
        observers = new ArrayList();
        model = new DefaultListModel();
        lstData.setModel(model);
        lstData.setCellRenderer(getClientListRenderer());
        lstData.addListSelectionListener(new MyListSelectionListener());
        
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        lstData = new javax.swing.JList();

        jScrollPane1.setViewportView(lstData);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList lstData;
    // End of variables declaration//GEN-END:variables
    
    /**
     * Adds a new ViewObject to the lst
     * @param object the ViewObject you want to add to the list
     */
    public void addViewObject(ViewObject object){
        model.addElement(object);
    }
    
    /**
     * Gets the Cell renderer for the list
     * @return 
     */
    private ListCellRenderer getClientListRenderer(){
        ListCellRenderer r =
            new ListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList list,
                        Object value, int index, boolean isSelected,
                        boolean cellHasFocus)
                {
                    ViewObject myViewObject = (ViewObject) value;
                    if(isSelected)
                        myViewObject.setBackground(MyColorConstants.LIGHT_BLUE);
                    else
                        myViewObject.setBackground(Color.WHITE);
                    JPanel surroundPanel = new JPanel();
                    surroundPanel.setLayout(new BorderLayout());
                    surroundPanel.add(myViewObject, BorderLayout.CENTER);
                    surroundPanel.setBorder(new EmptyBorder(0, 0, 5, 0));
                    return surroundPanel;
                }
            };
        
        
        return r;
    }
    
    public ViewObject getSelectedViewObject(){
        return (ViewObject)lstData.getSelectedValue();
    }
    
    public void addSelectionObserver(IObserver observer){
        observers.add(observer);
    }
    
    public void removeSelectionObserver(IObserver observer){
        observers.remove(observer);
    }
    
    private void notifyObservers(){
        for(IObserver observer : observers){
            observer.notifyObserver();
        }
    }
    
    public void clearList(){
        model.removeAllElements();
    }
    
    private class MyListSelectionListener implements ListSelectionListener{

        @Override
        public void valueChanged(ListSelectionEvent e) {
            notifyObservers();
        }
        
    }
    
   
    

}
