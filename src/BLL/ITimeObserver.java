/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;

import BE.MyTime;

/**
 *
 * @author Niels
 */
public interface ITimeObserver {
    /**
     * notifies the observer with a changed time
     * @param time the new time
     */
    public void timeChanged(MyTime time);
    
}
