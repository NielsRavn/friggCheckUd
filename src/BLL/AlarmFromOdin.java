/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;

import BE.Alarm;
import DAL.XmlScanner;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Poul Nielsen
 */
public class AlarmFromOdin {
    
    XmlScanner scanner;
    
    public AlarmFromOdin()
    {
        scanner = new XmlScanner();
    }
    
    Alarm getAlarmFromOdin()
    {
        Alarm result = null;
        try {
            result = scanner.scanner();
        } catch (ParseException ex) {
          
        }
        return result;
    }
}
