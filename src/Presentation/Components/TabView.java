/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Components;

import Presentation.MyConstants;
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
    
    //mostly for geting a number for a specific jpanel.
    ArrayList<Tab>  tabs;
    
    /**
     * creates a new tabview
     */
    public TabView(){
        tabs = new ArrayList<>();
        setOpaque(true);
    }
    
    /**
     * adds a tab to the tabview, and gives the content a lowered bevelborder to show that it is in the tab.
     * @param tabName the name to stand on the tab
     * @param content the content that should be shown under the tab
     * @param width the width of the screen to calculate tab size and make them clickable with touch
     */
    public void addNewTab(String tabName, JPanel content, int width){
        content.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        Tab t = new Tab(tabName, content);
        tabs.add(t);
        int margin = (width/100);
        addTab("<html><body marginwidth="+(margin*7)+" marginheight="+(margin*2)+">" + tabName + "</body></html>", content);
        setBackgroundAt(tabs.size() -1, Color.WHITE);
    }
    
    /**
     * sets a given tab enabled status
     * @param content the content of the tab to chage the state of
     * @param state the state to set the tab in, false for not being able to pick the tab.
     */
    public void setEnabledContent(JPanel content, boolean state){
        for(int i = 0; i< tabs.size(); i++){
            if(tabs.get(i).getContent() == content){
                setEnabledAt(i, state);
                setBackgroundAt(i, state ? Color.GRAY : Color.WHITE );
            }
        }
    }
    
    /**
     * removes a tab form the tabs list
     * @param content the content of the tab to be removed.
     */
    public void removeTab(JPanel content){
        for(int i = 0; i < tabs.size(); i++){
            if(tabs.get(i).getContent() == content) removeTabAt(i);
        }
        tabs.remove(content);
    }

    /**
     * is overwritten to change the background behind the tabs
     * @param g the grafics object to draw on.
     */
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(MyConstants.COLOR_BLUE);
        g.drawRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    /**
     * to keep track of the inputted tabs
     */
    private class Tab{
        
        String tabName;
        JPanel Content;
        
        /**
         * creates a new tab
         * @param tabName the name of the tab
         * @param Content the content of the tab
         */
        public Tab( String tabName, JPanel Content) {
            this.Content = Content;
            this.tabName = tabName;
        }

        /**
         * 
         * @return the content of the tab.
         */
        public JPanel getContent() {
            return Content;
        }
        
        
    }
}
