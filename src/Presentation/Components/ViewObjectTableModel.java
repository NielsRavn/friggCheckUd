/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Components;


import Presentation.Components.ViewObjects.ViewObject;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Brobak
 */
public class ViewObjectTableModel extends AbstractTableModel{
    private ArrayList<ViewObject> vos;
    // The names of columns
    private String[] colNames = {"Data"};
    // The type of columns
    private Class[] classes = {
        JPanel.class};

   
    
    /**
     * Creates a new ViewObjectTableModel
     * @param allViewObjects a list of view objects that should be shown in the model
     */
    public ViewObjectTableModel(ArrayList<ViewObject> allViewObjects) {
        vos = allViewObjects;
        fireTableDataChanged();
    }
    /**
     * Creates a new ViewObjectTableModel
     * @param allViewObjects a list of view objects that should be shown in the model
     */
    public ViewObjectTableModel() {
        vos = new ArrayList();
        fireTableDataChanged();
    }

    /**
     * Gets the number of rows in the model
     * @return the number of rows in the model
     */
    @Override
    public int getRowCount() {
            return vos.size();
    }

    /**
     * Gets the number of coloums in the model
     * @return the number of coloums in the model
     */
    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    /**
     * Gets the value at a given cell in the model
     * @param row the row of the cell
     * @param col the col of the cell
     * @return the value in the given cell
     */
    @Override
    public Object getValueAt(int row, int col) {
        ViewObject vo = vos.get(row);
        switch (col) {
            case 0:
                return vo;

        }

        return null;
    }

    @Override
    public String getColumnName(int col) {

        return colNames[col];
    }

    /**
     * Gets the class of the given coloum
     * @param col the coloum
     * @return the class of the given coloum
     */
    @Override
    public Class<?> getColumnClass(int col) {
        return classes[col];
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return true;
    }

    /**
     * Sets the content of the table model to the given list of cars.
     *
     * @param procList the list of Projects to show in the JTable.
     */
    public void setViewObjectList(ArrayList<ViewObject> voList) {
        vos = voList;
    }

    /**
     * Return the song instance from the table model with the given row index.
     *
     * @param row the index for the song in the cars list.
     * @return the song at the given row index.
     */
    public ViewObject getViewObjectByRow(int row) {
        return vos.get(row);
    }
    
     
}
