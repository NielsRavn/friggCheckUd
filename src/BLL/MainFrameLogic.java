/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;

import Presentation.Components.ListPanel;
import Presentation.Components.ViewObjects.ViewObjectCar;
import Presentation.Components.ViewObjects.ViewObjectStationDuty;
import java.util.ArrayList;
import javax.swing.JPanel;
/**
 *
 * @author Brobak
 */
public class MainFrameLogic {
    ArrayList<ListPanel> dataLists;
    /**
     * Creates a new main frame logic
     */
    public MainFrameLogic(){
        dataLists = new ArrayList();
    }
    
    /**
     * Adds a listpanel to the logic
     * @param list the listpanel you want to add
     */
    public void addDataList(ListPanel list){
        dataLists.add(list);
    }
    
    /**
     * Clears the list of listpanels
     */
    public void reset(){
        dataLists.clear();
    }
    
    /**
     * returns the index value of the first unselected list
     * @return the index value of the first unselected list
     */
    public JPanel getNextPanel(){
        ListPanel res = null;
        for(int i = 0; i< dataLists.size(); i++){
            if(dataLists.get(i).getSelectedViewObject() != null){
                if(dataLists.get(i).getSelectedViewObject().getClass() == ViewObjectStationDuty.class)
                    return null;
            }
            if(dataLists.get(i).getSelectedViewObject() == null){
                res = dataLists.get(i);
                break;
            }
        }
        
        return res;
    }
    
}
