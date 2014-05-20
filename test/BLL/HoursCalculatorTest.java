/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;

import BE.Hours;
import BE.Time_Sheet;
import DAL.TimeSheet_AccessTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Brobak
 */
public class HoursCalculatorTest {
    
    public HoursCalculatorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getHoursForTimeSheeet method, of class HoursCalculator.
     */
    @Test
    public void testGetHoursForTimeSheeet() {
        System.out.println("getHoursForTimeSheeet");
        TimeSheet_AccessTest tsa = new TimeSheet_AccessTest();
        
        
        tsa.addTimeSheet(new Time_Sheet);
        Time_Sheet timeSheet = null;
        HoursCalculator instance = new HoursCalculator(tsa);
        Hours expResult = null;
        Hours result = instance.getHoursForTimeSheeet(timeSheet);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
