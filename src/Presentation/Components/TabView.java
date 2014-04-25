/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Components;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

/**
 *
 * @author Niels
 */
public class TabView extends JTabbedPane{
    
    private int width, height;
    ArrayList<Tab>  tabs;
    
    public TabView(int width, int height){
        this.width = width;
        this.height = height;
        setSize(width, height);
        tabs = new ArrayList<>();
        
    }
    
    public void addNewTab(String tabName, JPanel content){
        content.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        Tab t = new Tab(tabName, content);
        tabs.add(t);
        int margin = (width/100)*5;
        addTab("<html><body leftmargin=" + margin + " topmargin=" + margin + " marginwidth=25 marginheight=8>" + tabName + "</body></html>", content);
        
    }
    
    public void removeTab(JPanel content){
        for(int i = 0; i < tabs.size(); i++){
            if(tabs.get(i).getContent() == content) removeTabAt(i);
        }
    }
    
    
    private class Tab{
        
        String tabName;
        JPanel Content;
        
        
        public Tab( String tabName, JPanel Content) {
            this.Content = Content;
            this.tabName = tabName;
        }

        public JPanel getContent() {
            return Content;
        }
        
        
    }
}
