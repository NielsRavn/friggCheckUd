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
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import static java.util.Collections.list;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author Brobak
 */
public class ListPanel extends javax.swing.JPanel {

    JPanel lstTheList;
    //DefaultListModel model;
    ArrayList<IObserver> observers;
    ArrayList<ViewObject> vos;
    int mySelectedIndex;
    TimePicker tp;
    boolean enabled;
    ViewObjectTableModel model;

    /**
     * Creates new form ListPanel
     * @param editable
     */
    public ListPanel( boolean editable) {
        enabled = true;
        mySelectedIndex = -1;
        initComponents();
        tblList.setTableHeader(null);
        vos = new ArrayList();
        lstTheList = this;
        observers = new ArrayList();
        model = new ViewObjectTableModel(editable);
        tblList.setModel(model);
        tblList.setDefaultRenderer(JPanel.class, new MyTableCellRenderer());
        if(editable)
            tblList.setDefaultEditor(JPanel.class, new MyTableCellEditor());
        //tblList.setCellRenderer(getClientListRenderer());
        MyMouseMotionAdapter mma = new MyMouseMotionAdapter();
        tblList.addMouseListener(mma);
        jScrollPane1.addMouseListener(mma);
        addMouseMotionListener(mma);
        addMouseListener(mma);
        tblList.addMouseMotionListener(mma);
        jScrollPane1.addMouseMotionListener(mma);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tblList = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        jScrollPane1.setViewportView(tblList);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblList;
    // End of variables declaration//GEN-END:variables
    private void populateTable() {
        model = (ViewObjectTableModel) tblList.getModel();
        model.setViewObjectList(vos);
    }

    public void updateTable(ArrayList<ViewObject> viewObjects) {
        ArrayList<ViewObject> vos = viewObjects;
        model.setViewObjectList(vos);
        model.fireTableDataChanged();
    }

//    /**
//     * Adds a cell renderer to the table coloums in tblResult
//     */
//    private void addCellRenderer() {
//        MyTableCellRenderer renderer = new MyTableCellRenderer();
//        
//        for(int col = 0; col < model.getColumnCount(); col ++){
//            TableColumn tc = tblList.getColumnModel().getColumn(col);
//            tc.setCellRenderer(renderer);
//        }
//    }
    /**
     * Adds a new ViewObject to the lst
     *
     * @param object the ViewObject you want to add to the list
     */
    public void addViewObject(ViewObject object) {
        vos.add(object);
        model.fireTableDataChanged();
        //model.addElement(object);
    }

    public void myRepaint() {
        JViewport viewPort = jScrollPane1.getViewport();
        Point scrollPosition = viewPort.getViewPosition();
        scrollPosition.y += 100;
        viewPort.setViewPosition(scrollPosition);
    }

    private class MyTableCellRenderer extends JPanel implements TableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return getCorectPanel(table, value, isSelected, row);
            
        }
    }

    private JPanel getCorectPanel(JTable table, Object value, boolean isSelected, int row) {
        ViewObject panel = (ViewObject) value;
        table.setRowHeight(row, (int) panel.getPreferredSize().getHeight());
        if (isSelected) {
            panel.setBackground(MyConstants.COLOR_LIGHT_BLUE);
        } else {
            panel.setBackground(Color.WHITE);
        }
        JPanel surroundPanel = new JPanel();
        surroundPanel.setLayout(new BorderLayout());
        surroundPanel.add(panel, BorderLayout.CENTER);
        surroundPanel.setBorder(new EmptyBorder(0, 0, 5, 0));
        return surroundPanel;
    }

    private class MyTableCellEditor extends AbstractCellEditor implements TableCellEditor {
        
        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
            return getCorectPanel(table, value, isSelected, row);
        }

        @Override
        public Object getCellEditorValue() {
            return null;
        }
        
    }
    
    public void fireTableDataChanged(){
        model.fireTableDataChanged();
    }
//    /**
//     * Gets the Cell renderer for the list
//     *
//     * @return
//     */
//    private ListCellRenderer getListRenderer(){
//        ListCellRenderer r =
//            new ListCellRenderer() {
//                @Override
//                public Component getListCellRendererComponent(JList list,
//                        Object value, int index, boolean isSelected,
//                        boolean cellHasFocus)
//                {
//                    ViewObject myViewObject = (ViewObject) value;
//                    if(isSelected)
//                        myViewObject.setBackground(MyConstants.COLOR_LIGHT_BLUE);
//                    else
//                        myViewObject.setBackground(Color.WHITE);
//                    JPanel surroundPanel = new JPanel();
//                    surroundPanel.setLayout(new BorderLayout());
//                    surroundPanel.add(myViewObject, BorderLayout.CENTER);
//                    surroundPanel.setBorder(new EmptyBorder(0, 0, 5, 0));
//                    return surroundPanel;
//                }
//            };
//        return r;
//    }

    public ViewObject getSelectedViewObject() {
        if (mySelectedIndex == -1) {
            return null;
        } else {
            return (ViewObject) model.getValueAt(mySelectedIndex, 0);
        }
    }

    public ArrayList<ViewObject> getAllViewObject() {
        ArrayList<ViewObject> viewObjects = new ArrayList();

        for (int i = 0; i < model.getRowCount(); i++) {
            viewObjects.add((ViewObject) model.getValueAt(i, 0));
        }
        return viewObjects;
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
        model.setViewObjectList(vos);
        model.fireTableDataChanged();
    }

    public void setElementsEnabled(boolean b) {
        tblList.setEnabled(b);
        enabled = b;
    }

    private class MyMouseMotionAdapter extends MouseAdapter {

        Point lastDragPoint = new Point();
        boolean beingDragged;
        
        @Override
        public void mouseClicked(MouseEvent e) {
            if (enabled) {
                mySelectedIndex = tblList.getSelectedRow();
                if(getSelectedViewObject() != null)
                    notifyObservers();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (beingDragged) beingDragged = false;
        }
        
        @Override
        public void mouseDragged(MouseEvent e) {
            if (mySelectedIndex == -1) tblList.clearSelection();
            else tblList.setRowSelectionInterval(mySelectedIndex, mySelectedIndex);

            if (!beingDragged) {
                beingDragged = true;
                setLastDragPoint(e.getX(), e.getY());
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

            int dy = e.getY() - lastDragPoint.y;

            int maxScroll = viewPort.getViewSize().height - getHeight();

            scrollPosition.y -= dy;
            if (scrollPosition.y > maxScroll) scrollPosition.y = maxScroll;
            if (scrollPosition.y < 0) scrollPosition.y = 0;

            viewPort.setViewPosition(scrollPosition);
        }
    }
}
