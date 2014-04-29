/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Components;

import Presentation.MyColorConstants;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

/**
 *
 * @author Niels
 */
public class TabView extends JTabbedPane{
    
    ArrayList<Tab>  tabs;
    
    public TabView(){
        tabs = new ArrayList<>();
        setOpaque(true);
    }
    
    public void addNewTab(String tabName, JPanel content){
        content.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        Tab t = new Tab(tabName, content);
        tabs.add(t);
        int margin = (getWidth()/100)*5;
        addTab("<html><body leftmargin=" + margin + " topmargin=" + margin + " marginwidth=25 marginheight=8>" + tabName + "</body></html>", content);
        setBackgroundAt(tabs.size() -1, Color.WHITE);
    }
    
    public void setEnabledContent(JPanel content, boolean state){
        for(int i = 0; i< tabs.size(); i++){
            if(tabs.get(i).getContent() == content){
                setEnabledAt(i, state);
                setBackgroundAt(i, state ? Color.GRAY : Color.WHITE );
            }
        }
    }
    
    public void removeTab(JPanel content){
        for(int i = 0; i < tabs.size(); i++){
            if(tabs.get(i).getContent() == content) removeTabAt(i);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(MyColorConstants.OUR_BLUE);
        g.drawRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
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
