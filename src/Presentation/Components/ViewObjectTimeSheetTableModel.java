/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Components;

import BE.Time_Sheet;
import Presentation.Components.ViewObjects.ViewObject;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Poul Nielsen
 */
public class ViewObjectTimeSheetTableModel extends AbstractTableModel{
private ArrayList<Time_Sheet> vos;
    // The names of columns
    private String[] colNames = {"Pos" ,"Navn", "Start", "Slut", "Timer", "Godkend"};
    // The type of columns
    private Class[] classes = {
        String.class, String.class , String.class, String.class, int.class, boolean.class};
   
    /**
     * Creates a new ViewObjectTableModel
     * @param allViewObjects a list of view objects that should be shown in the model
     */
    public ViewObjectTimeSheetTableModel() {
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
        Time_Sheet vo = vos.get(row);
        switch (col) {
            case 0:
                return vo.getPosition().getName();
            case 1:
                return vo.getFireman().getFirstName() + " " + vo.getFireman().getLastName();
            case 2:
                return vo.getStartTime();
            case 3:
                return vo.getEndTime();
            case 4:
                return 1;
            case 5:
                return false;
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
     * Sets the content of the table model to the given list of firemen.
     *
     * @param procList the list of Projects to show in the JTable.
     */
    public void setViewObjectList(ArrayList<Time_Sheet> voList) {
        vos = voList;
    }

    /**
     * Return the firemen instance from the table model with the given row index.
     *
     * @param row the index for the song in the cars list.
     * @return the song at the given row index.
     */
    public Time_Sheet getViewObjectByRow(int row) {
        return vos.get(row);
    }
    
    public void addTimeSheet(Time_Sheet timesheet)
    {
        vos.add(timesheet);
        fireTableDataChanged();
    }
    
    public void clearList(){
        vos.clear();
    }
     
}