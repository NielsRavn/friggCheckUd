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
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Brobak
 */
public class ListPanel extends javax.swing.JPanel{

    JPanel lstTheList;
    ArrayList<IObserver> observers;
    ArrayList<ViewObject> vos;
    int mySelectedIndex;
    boolean enabled;
    JScrollPane jScrollPane1;
    JPanel panel;
    MyMouseMotionAdapter mma;
    int width;
    boolean editAble;
    /**
     * Creates new form ListPanel
     * @param editable
     */
    public ListPanel( boolean editable, int width) {
        this.width = width;
        panel = new JPanel();
        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        this.editAble = editable;
        panel.setLayout(layout);
        jScrollPane1 = new JScrollPane(panel);
        enabled = true;
        mySelectedIndex = -1;
        initComponents();
        vos = new ArrayList();
        observers = new ArrayList();
        add(jScrollPane1, BorderLayout.CENTER);
        mma = new MyMouseMotionAdapter();
        populateTable();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    private void populateTable() {
        
    }

    public void updateTable(ArrayList<ViewObject> viewObjects) {
        ArrayList<ViewObject> vos = viewObjects;
    }

    
    /**
     * Adds a new ViewObject to the lst
     *
     * @param object the ViewObject you want to add to the list
     */
    public void addViewObject(ViewObject object) {
        object.addMouseMotionListener(mma);
        object.addMouseListener(mma);
        object.setMaximumSize(new Dimension(width, (int) object.getPreferredSize().getHeight()));
        object.addObserver(new ViiewObjectObserver());
        vos.add(object);
        panel.add(object); 
    }
    
//    public void addViewObject(ViewObject object) {
//        object.addMouseMotionListener(mma);
//        object.addMouseListener(mma);
//        object.setMaximumSize(new Dimension(width, (int) object.getPreferredSize().getHeight()));
//        object.addObserver(new ViiewObjectObserver());
//        vos.add(object);
//        panel.add(object); 
//    }
    
    public void refreshAllViewobjects(){
        for(ViewObject vo: vos){
            vo.refreshViewObject();
        }
    }

    private JPanel getCorectPanel(ViewObject value) {
        JPanel surroundPanel = new JPanel();
        surroundPanel.addMouseMotionListener(mma);
        surroundPanel.addMouseListener(mma);
        surroundPanel.setLayout(new BorderLayout());
        surroundPanel.add(value, BorderLayout.CENTER);
        surroundPanel.setBorder(new EmptyBorder(0, 0, 5, 0));
        return surroundPanel;
    }

    public ViewObject getSelectedViewObject() {
        if (mySelectedIndex == -1) {
            return null;
        } else {
            return vos.get(mySelectedIndex);
        }
    }

    public ArrayList<ViewObject> getAllViewObject() {
        return vos;
    }

    public void addSelectionObserver(IObserver observer) {
        observers.add(observer);
    }

    public void removeSelectionObserver(IObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (IObserver observer : observers) {
            observer.notifyObserver();
        }
    }

    public void clearList() {
        vos = new ArrayList();
        panel.removeAll();
    }

    public void setElementsEnabled(boolean b) {
        enabled = b;
    }
    
    private void setBackgroundColorOfAllElements(Color c) {
            for(ViewObject vo: vos){
                vo.setBackground(c);
            }
    }
    
    private int getIndexOfObject(ViewObject viewObject) {
        for(int i = 0; i <vos.size(); i++){
            if(vos.get(i) == viewObject) return i;
        }
        return -1;
    }

    public int getMyWidth() {
        return width;
    }
    
    private class ViiewObjectObserver implements IObserver{

        @Override
        public void notifyObserver() {
            ListPanel.this.validate();
            ListPanel.this.repaint();
        }
        
    }
    
    private class MyMouseMotionAdapter extends MouseAdapter {

        Point lastDragPoint = new Point();
        boolean beingDragged;
        
        @Override
        public void mouseClicked(MouseEvent e) {
            if (enabled) {
                mySelectedIndex = getIndexOfObject((ViewObject) e.getSource());
                setBackgroundColorOfAllElements(Color.WHITE);
                ViewObject selected = getSelectedViewObject();
                if(selected != null)
                    selected.setBackground(MyConstants.COLOR_LIGHT_BLUE);
                    notifyObservers();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (beingDragged) beingDragged = false;
        }
        
        @Override
        public void mouseDragged(MouseEvent e) {

            if (!beingDragged) {
                beingDragged = true;
                setLastDragPoint(e.getX(), (e.getY()+ ((JComponent)e.getSource()).getY()));
            }
            handleDraging(e);
        }

        private void setLastDragPoint(int x, int y) {
            lastDragPoint.x = x;
            lastDragPoint.y = y;
        }

        private void handleDraging(MouseEvent e) {
            JViewport viewPort = jScrollPane1.getViewport();
            Point scrollPosition = viewPort.getViewPosition();

            int dy = (e.getY()+ ((JComponent)e.getSource()).getY()) - lastDragPoint.y;
            
            int maxScroll = viewPort.getViewSize().height - getHeight();
            scrollPosition.y -= dy;
            if (scrollPosition.y > maxScroll) scrollPosition.y = maxScroll;
            if (scrollPosition.y < 0) scrollPosition.y = 0;
            
            viewPort.setViewPosition(scrollPosition);
        }

    }
}
