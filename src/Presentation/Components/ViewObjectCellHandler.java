/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Components;

import Presentation.Components.ViewObjects.ViewObject;
import Presentation.MyConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Niels
 */
public class ViewObjectCellHandler extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {

    JPanel surroundPanel;

    ViewObject feed;

    private void updateData(ViewObject feed, boolean isSelected, JTable table) {
        this.feed = feed;
        if (isSelected) {
            feed.setBackground(MyConstants.COLOR_LIGHT_BLUE);
        } else {
            feed.setBackground(Color.WHITE);
        }
        surroundPanel = new JPanel();
        surroundPanel.setLayout(new BorderLayout());
        surroundPanel.add(feed, BorderLayout.CENTER);
        surroundPanel.setBorder(new EmptyBorder(0, 0, 5, 0));
        
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        ViewObject feed = (ViewObject) value;
        updateData(feed, true, table);
        return surroundPanel;
    }

    public Object getCellEditorValue() {
        return null;
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        ViewObject feed = (ViewObject) value;
        table.setRowHeight(row, (int)feed.getPreferredSize().getHeight());
        updateData(feed, isSelected, table);
        return surroundPanel;
    }

}
