/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Components;

import BLL.IObserver;
import Presentation.Components.ViewObjects.ViewObject;
import Presentation.MyConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
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
import javax.swing.JViewport;
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
    int mySelectedIndex;
    
    /**
     * Creates new form ListPanel
     */
    public ListPanel() {
        mySelectedIndex  = -1;
        initComponents();
        thisList = this;
        observers = new ArrayList();
        model = new DefaultListModel();
        lstData.setModel(model);
        lstData.setCellRenderer(getClientListRenderer());
        MyMouseMotionAdapter mma = new MyMouseMotionAdapter();
        lstData.addMouseListener(mma);
        jScrollPane1.addMouseListener(mma);
        addMouseMotionListener(mma);
        addMouseListener(mma);
        lstData.addMouseMotionListener(mma);
        jScrollPane1.addMouseMotionListener(mma);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
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
     *
     * @param object the ViewObject you want to add to the list
     */
    public void addViewObject(ViewObject object) {
        model.addElement(object);
    }

    /**
     * Gets the Cell renderer for the list
     *
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
                        myViewObject.setBackground(MyConstants.COLOR_LIGHT_BLUE);
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
        if(mySelectedIndex == -1)
            return null;
        else 
            return (ViewObject)model.get(mySelectedIndex);
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
    
   
    
    private class MyMouseMotionAdapter extends MouseAdapter {

        Point lastDragPoint = new Point();
        boolean beingDragged;

        @Override
        public void mouseDragged(MouseEvent e) {
            if(mySelectedIndex == -1)
                lstData.clearSelection();
            else
                lstData.setSelectedIndex(mySelectedIndex);
            
            if (!beingDragged) {
                beingDragged = true;
                setLastDragPoint(e.getX(), e.getY());
            }
            
            handleDraging(e);
            
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            mySelectedIndex = lstData.getSelectedIndex();
            notifyObservers();
            
        }
        
        

        @Override
        public void mouseReleased(MouseEvent e) {
            if (beingDragged) {
                beingDragged = false;
            }
        }

        private void setLastDragPoint(int x, int y) {
            lastDragPoint.x = x;
            lastDragPoint.y = y;
        }

        private void handleDraging(MouseEvent e) {
            JViewport viewPort = jScrollPane1.getViewport();
            Point scrollPosition = viewPort.getViewPosition();

            int dy = e.getY() - lastDragPoint.y;

            int maxScroll = viewPort.getViewSize().height - getHeight();
            
            scrollPosition.y -= dy;
            if(scrollPosition.y < 0) scrollPosition.y = 0;
            if(scrollPosition.y > maxScroll) scrollPosition.y = maxScroll;

            viewPort.setViewPosition(scrollPosition);
            
        }

    }

}
