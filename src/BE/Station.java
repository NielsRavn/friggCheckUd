/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BE;

/**
 *
 * @author Brobak
 */
public class Station implements IViewObjectBE{
    String iconPath;
    String name;
    public Station (String name, String iconPath) {
        this.iconPath = iconPath;
        this.name = name;
        
    }
    
    /**
     * @return the icon path
     */
    public String getIconPath() {
        return iconPath;
    }
    
    /**
     * @return a string representation of the object
     */
    public String getName() {
        return name;
    }
    
    
}
