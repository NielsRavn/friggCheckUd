/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;

import BE.Hours;
import BE.Time_Sheet;

/**
 *
 * @author Brobak
 */
public interface IHoursCalculator {
    
    
    public Hours getHoursForTimeSheeet(Time_Sheet timeSheet);
    
}
