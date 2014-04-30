/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;

import Presentation.Components.ListPanel;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Brobak
 */
public class MainFrameLogic {
    ArrayList<ListPanel> dataLists;
    public MainFrameLogic(){
        dataLists = new ArrayList();
    }
    
    
    public void addDataList(ListPanel list){
        dataLists.add(list);
    }
    
    /**
     * returns the index value of the first unselected list
     * @return the index value of the first unselected list
     */
    public JPanel getNextPanel(){
        ListPanel res = null;
        for(int i = 0; i< dataLists.size(); i++){
            
            if(dataLists.get(i).getSelectedViewObject() == null){
                res = dataLists.get(i);
                break;
            }
        }
        
        return res;
    }
    
}
