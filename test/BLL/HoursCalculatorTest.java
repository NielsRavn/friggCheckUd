/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;

import BE.Comment;
import BE.Position;
import BE.Time_Sheet;
import DAL.TimeSheet_AccessTest;
import Presentation.MyConstants;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Brobak
 */
public class HoursCalculatorTest {
    
    public HoursCalculatorTest() {
    }
    
    long baseTime = System.currentTimeMillis();
    final int oneHour = (60*60*1000);
    /**
     * Test of getHoursForTimeSheeet method, of class HoursCalculator.
     * 1 Hour with no conflicts = 2 hours
     */
    @Test
    public void testGetHoursForTimeSheeet() {
        System.out.println("getHoursForTimeSheeet : 1 timesheet 1 Hour");
        TimeSheet_AccessTest tsa = new TimeSheet_AccessTest();
        int id = 1;
        int employeeId = 1;
        int alarmId = 1;
        int carNumber = 2711;
        Position carPos = MyConstants.FIREMAN;
        Timestamp startTime = new Timestamp(baseTime-oneHour);
        Timestamp endTime = new Timestamp(baseTime);
        int acceptedByTeamleader = 0;
        int acceptedToSalary = 0;
        boolean addToPayment = false;
        Comment comment = new Comment("Some comment");
        Time_Sheet timeSheet1 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet1);
        
        HoursCalculator instance = new HoursCalculator(tsa);
        int expResult = 2;
        int result1 = 0;
        try {
            result1 = instance.getHoursForTimeSheeet(timeSheet1);
        } catch (SQLException ex) {
            Logger.getLogger(HoursCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        } 
        assertEquals(expResult, result1);
    }
    /**
     * Test of getHoursForTimeSheeet method, of class HoursCalculator.
     * 1 timesheet with 3 hours
     */
    @Test
    public void testGetHoursForTimeSheeet2() {
        System.out.println("getHoursForTimeSheeet2 : 1 timesheet with 3 hours");
        TimeSheet_AccessTest tsa = new TimeSheet_AccessTest();
        int id = 1;
        int employeeId = 1;
        int alarmId = 1;
        int carNumber = 2711;
        Position carPos = MyConstants.FIREMAN;
        Timestamp startTime = new Timestamp(baseTime-(oneHour*3));
        Timestamp endTime = new Timestamp(baseTime);
        int acceptedByTeamleader = 0;
        int acceptedToSalary = 0;
        boolean addToPayment = false;
        Comment comment = new Comment("Some comment");
        Time_Sheet timeSheet1 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet1);
        
        HoursCalculator instance = new HoursCalculator(tsa);
        int expResult = 3;
        int result1 = 0;
        try {
            result1 = instance.getHoursForTimeSheeet(timeSheet1);
        } catch (SQLException ex) {
            Logger.getLogger(HoursCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        } 
        assertEquals(expResult, result1);
    }
    
    /**
     * Test of getHoursForTimeSheeet method, of class HoursCalculator.
     * 2 timesheet with same startTime and endtime 1 hour total
     */
    @Test
    public void testGetHoursForTimeSheeet3() {
        
        System.out.println("getHoursForTimeSheeet3 : 2 timesheet with same startTime and endtime 1 hour total");
        TimeSheet_AccessTest tsa = new TimeSheet_AccessTest();
        int id = 3;
        int employeeId = 1;
        int alarmId = 1;
        int carNumber = 2711;
        Position carPos = MyConstants.FIREMAN;
        Timestamp startTime = new Timestamp(baseTime-(oneHour));
        Timestamp endTime = new Timestamp(baseTime);
        int acceptedByTeamleader = 0;
        int acceptedToSalary = 0;
        boolean addToPayment = false;
        Comment comment = new Comment("Some comment");
        Time_Sheet timeSheet1 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet1);
        
        id = 4;
        
        Time_Sheet timeSheet2 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet2);
        
        HoursCalculator instance = new HoursCalculator(tsa);
        int expResult1 = 1;
        int expResult2 = 1;
        int expResultTotal = 2;
        int result1 = 0;
        int result2 = 0;
        int resultTotal = 0;
        try {
            result1 = instance.getHoursForTimeSheeet(timeSheet1);
            result2 = instance.getHoursForTimeSheeet(timeSheet2);
        } catch (SQLException ex) {
            Logger.getLogger(HoursCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        } 
        resultTotal = result1 + result2;
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
        assertEquals(expResultTotal, resultTotal);
    }
    
    /**
     * Test of getHoursForTimeSheeet method, of class HoursCalculator.
     * 2 timesheet with same startTime and endtime 6 hour total
     */
    @Test
    public void testGetHoursForTimeSheeet4() {
        System.out.println("getHoursForTimeSheeet4 : 2 timesheet with same startTime and endtime 6 hour total");
        TimeSheet_AccessTest tsa = new TimeSheet_AccessTest();
        int id = 1;
        int employeeId = 1;
        int alarmId = 1;
        int carNumber = 2711;
        Position carPos = MyConstants.FIREMAN;
        Timestamp startTime = new Timestamp(baseTime-(oneHour*6));
        Timestamp endTime = new Timestamp(baseTime);
        int acceptedByTeamleader = 0;
        int acceptedToSalary = 0;
        boolean addToPayment = false;
        Comment comment = new Comment("Some comment");
        Time_Sheet timeSheet1 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet1);
        
        id = 2;
        
        Time_Sheet timeSheet2 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet2);
        
        HoursCalculator instance = new HoursCalculator(tsa);
        int expResult1 = 3;
        int expResult2 = 3;
        int expResultTotal = 6;
        int result1 = 0;
        int result2 = 0;
        int resultTotal = 0;
        try {
            result1 = instance.getHoursForTimeSheeet(timeSheet1);
            result2 = instance.getHoursForTimeSheeet(timeSheet2);
        } catch (SQLException ex) {
            Logger.getLogger(HoursCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        } 
        resultTotal = result1 + result2;
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
        assertEquals(expResultTotal, resultTotal);
    }
    /**
     * Test of getHoursForTimeSheeet method, of class HoursCalculator.
     * 2 timesheet with same startTime and endtime 4,25 hour total
     */
    @Test
    public void testGetHoursForTimeSheeet5() {
        System.out.println("getHoursForTimeSheeet5 : 2 timesheet with same startTime and endtime 4,25 hour total");
        TimeSheet_AccessTest tsa = new TimeSheet_AccessTest();
        int id = 5;
        int employeeId = 1;
        int alarmId = 1;
        int carNumber = 2711;
        Position carPos = MyConstants.FIREMAN;
        Timestamp startTime = new Timestamp(baseTime-(oneHour*4)-(oneHour/4));
        Timestamp endTime = new Timestamp(baseTime);
        int acceptedByTeamleader = 0;
        int acceptedToSalary = 0;
        boolean addToPayment = false;
        Comment comment = new Comment("Some comment");
        Time_Sheet timeSheet1 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet1);
        
        id = 6;
        
        Time_Sheet timeSheet2 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet2);
        
        HoursCalculator instance = new HoursCalculator(tsa);
        int expResult1 = 3;
        int expResult2 = 2;
        int expResultTotal = 5;
        int result1 = 0;
        int result2 = 0;
        int resultTotal = 0;
        try {
            result1 = instance.getHoursForTimeSheeet(timeSheet1);
            result2 = instance.getHoursForTimeSheeet(timeSheet2);
        } catch (SQLException ex) {
            Logger.getLogger(HoursCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        } 
        resultTotal = result1 + result2;
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
        assertEquals(expResultTotal, resultTotal);
    }
    
    /**
     * Test of getHoursForTimeSheeet method, of class HoursCalculator.
     * 2 timesheet with same startTime and different endTime 1 hour on the 1st timesheet and 1½ hours on 2nd timesheet
     */
    @Test
    public void testGetHoursForTimeSheeet6() {
        System.out.println("getHoursForTimeSheeet6 : 2 timesheet with same startTime and different endTime 1 hour on the 1st timesheet and 1½ hours on 2nd timesheet");
        TimeSheet_AccessTest tsa = new TimeSheet_AccessTest();
        int id = 5;
        int employeeId = 1;
        int alarmId = 1;
        int carNumber = 2711;
        Position carPos = MyConstants.FIREMAN;
        Timestamp startTime = new Timestamp(baseTime-(oneHour)-(oneHour/2));
        Timestamp endTime = new Timestamp(baseTime-(oneHour/2));
        int acceptedByTeamleader = 0;
        int acceptedToSalary = 0;
        boolean addToPayment = false;
        Comment comment = new Comment("Some comment");
        Time_Sheet timeSheet1 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet1);
        
        id = 6;
        endTime = new Timestamp(baseTime);
        Time_Sheet timeSheet2 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet2);
        
        HoursCalculator instance = new HoursCalculator(tsa);
        int expResult1 = 1;
        int expResult2 = 1;
        int expResultTotal = 2;
        int result1 = 0;
        int result2 = 0;
        int resultTotal = 0;
        try {
            result1 = instance.getHoursForTimeSheeet(timeSheet1);
            result2 = instance.getHoursForTimeSheeet(timeSheet2);
        } catch (SQLException ex) {
            Logger.getLogger(HoursCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        } 
        resultTotal = result1 + result2;
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
        assertEquals(expResultTotal, resultTotal);
    }
    
    /**
     * Test of getHoursForTimeSheeet method, of class HoursCalculator.
     * 2 timesheet with same startTime and different endTime 2 hour on the 1st timesheet and 6 hours on 2nd timesheet
     */
    @Test
    public void testGetHoursForTimeSheeet7() {
        System.out.println("getHoursForTimeSheeet7 : 2 timesheet with same startTime and different endTime 4 hour on the 1st timesheet and 6 hours on 2nd timesheet");
        TimeSheet_AccessTest tsa = new TimeSheet_AccessTest();
        int id = 5;
        int employeeId = 1;
        int alarmId = 1;
        int carNumber = 2711;
        Position carPos = MyConstants.FIREMAN;
        Timestamp startTime = new Timestamp(baseTime-(oneHour*6));
        Timestamp endTime = new Timestamp(baseTime-(oneHour*2));
        int acceptedByTeamleader = 0;
        int acceptedToSalary = 0;
        boolean addToPayment = false;
        Comment comment = new Comment("Some comment");
        Time_Sheet timeSheet1 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet1);
        
        id = 6;
        endTime = new Timestamp(baseTime);
        Time_Sheet timeSheet2 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet2);
        
        HoursCalculator instance = new HoursCalculator(tsa);
        int expResult1 = 2;
        int expResult2 = 4;
        int expResultTotal = 6;
        int result1 = 0;
        int result2 = 0;
        int resultTotal = 0;
        try {
            result1 = instance.getHoursForTimeSheeet(timeSheet1);
            result2 = instance.getHoursForTimeSheeet(timeSheet2);
        } catch (SQLException ex) {
            Logger.getLogger(HoursCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        resultTotal = result1 + result2;
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
        assertEquals(expResultTotal, resultTotal);
    }
    
    /**
     * Test of getHoursForTimeSheeet method, of class HoursCalculator.
     * 2 timesheet with different startTime and same endTime 1½ hour on the 1st timesheet and 1 hours on 2nd timesheet
     */
    @Test
    public void testGetHoursForTimeSheeet8() {
        System.out.println("getHoursForTimeSheeet8 : 2 timesheet with different startTime and same endTime 1½ hour on the 1st timesheet and 1 hours on 2nd timesheet");
        TimeSheet_AccessTest tsa = new TimeSheet_AccessTest();
        int id = 7;
        int employeeId = 1;
        int alarmId = 1;
        int carNumber = 2711;
        Position carPos = MyConstants.FIREMAN;
        Timestamp startTime = new Timestamp(baseTime-(oneHour)-(oneHour/2));
        Timestamp endTime = new Timestamp(baseTime);
        int acceptedByTeamleader = 0;
        int acceptedToSalary = 0;
        boolean addToPayment = false;
        Comment comment = new Comment("Some comment");
        Time_Sheet timeSheet1 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet1);
        
        id = 8;
        startTime = new Timestamp(baseTime - (oneHour));
        Time_Sheet timeSheet2 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet2);
        
        HoursCalculator instance = new HoursCalculator(tsa);
        int expResult1 = 1;
        int expResult2 = 1;
        int expResultTotal = 2;
        int result1 = 0;
        int result2 = 0;
        int resultTotal = 0;
        try {
            result1 = instance.getHoursForTimeSheeet(timeSheet1);
            result2 = instance.getHoursForTimeSheeet(timeSheet2);
        } catch (SQLException ex) {
            Logger.getLogger(HoursCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        } 
        resultTotal = result1 + result2;
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
        assertEquals(expResultTotal, resultTotal);
    }
    
    
    /**
     * Test of getHoursForTimeSheeet method, of class HoursCalculator.
     * 2 timesheet with different startTime and same endTime 5 hour on the 1st timesheet and 2 hours on 2nd timesheet
     */
    @Test
    public void testGetHoursForTimeSheeet9() {
        System.out.println("getHoursForTimeSheeet9 : 2 timesheet with different startTime and same endTime 5 hour on the 1st timesheet and 2 hours on 2nd timesheet");
        TimeSheet_AccessTest tsa = new TimeSheet_AccessTest();
        int id = 7;
        int employeeId = 1;
        int alarmId = 1;
        int carNumber = 2711;
        Position carPos = MyConstants.FIREMAN;
        Timestamp startTime = new Timestamp(baseTime-(oneHour*5));
        Timestamp endTime = new Timestamp(baseTime);
        int acceptedByTeamleader = 0;
        int acceptedToSalary = 0;
        boolean addToPayment = false;
        Comment comment = new Comment("Some comment");
        Time_Sheet timeSheet1 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet1);
        
        id = 8;
        startTime = new Timestamp(baseTime - (oneHour*2));
        Time_Sheet timeSheet2 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet2);
        
        HoursCalculator instance = new HoursCalculator(tsa);
        int expResult1 = 3;
        int expResult2 = 2;
        int expResultTotal = 5;
        int result1 = 0;
        int result2 = 0;
        int resultTotal = 0;
        try {
            result1 = instance.getHoursForTimeSheeet(timeSheet1);
            result2 = instance.getHoursForTimeSheeet(timeSheet2);
        } catch (SQLException ex) {
            Logger.getLogger(HoursCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        resultTotal = result1 + result2;
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
        assertEquals(expResultTotal, resultTotal);
    }
    
    /**
     * Test of getHoursForTimeSheeet method, of class HoursCalculator.
     * 2 timesheet with different startTime and different endTime total at 1½ hours
     */
    @Test
    public void testGetHoursForTimeSheeet10() {
        System.out.println("getHoursForTimeSheeet10 : 2 timesheet with different startTime and different endTime total at 1½ hours");
        TimeSheet_AccessTest tsa = new TimeSheet_AccessTest();
        int id = 7;
        int employeeId = 1;
        int alarmId = 1;
        int carNumber = 2711;
        Position carPos = MyConstants.FIREMAN;
        Timestamp startTime = new Timestamp(baseTime-(oneHour)-(oneHour/2));
        Timestamp endTime = new Timestamp(baseTime-(oneHour/2));
        int acceptedByTeamleader = 0;
        int acceptedToSalary = 0;
        boolean addToPayment = false;
        Comment comment = new Comment("Some comment");
        Time_Sheet timeSheet1 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet1);
        
        startTime = new Timestamp(baseTime-(oneHour));
        endTime = new Timestamp(baseTime);
        id = 8;
        Time_Sheet timeSheet2 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet2);
        
        HoursCalculator instance = new HoursCalculator(tsa);
        int expResult1 = 1;
        int expResult2 = 1;
        int expResultTotal = 2;
        int result1 = 0;
        int result2 = 0;
        int resultTotal = 0;
        try {
            result1 = instance.getHoursForTimeSheeet(timeSheet1);
            result2 = instance.getHoursForTimeSheeet(timeSheet2);
        } catch (SQLException ex) {
            Logger.getLogger(HoursCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        resultTotal = result1 + result2;
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
        assertEquals(expResultTotal, resultTotal);
    }
    
    /**
     * Test of getHoursForTimeSheeet method, of class HoursCalculator.
     * 2 timesheet with different startTime and different endTime total at 6 hours
     */
    @Test
    public void testGetHoursForTimeSheeet11() {
        System.out.println("getHoursForTimeSheeet11 : 2 timesheet with different startTime and different endTime total at 6 hours");
        TimeSheet_AccessTest tsa = new TimeSheet_AccessTest();
        int id = 7;
        int employeeId = 1;
        int alarmId = 1;
        int carNumber = 2711;
        Position carPos = MyConstants.FIREMAN;
        Timestamp startTime = new Timestamp(baseTime-(oneHour*6));
        Timestamp endTime = new Timestamp(baseTime-(oneHour*2));
        int acceptedByTeamleader = 0;
        int acceptedToSalary = 0;
        boolean addToPayment = false;
        Comment comment = new Comment("Some comment");
        Time_Sheet timeSheet1 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet1);
        
        startTime = new Timestamp(baseTime-(oneHour*4));
        endTime = new Timestamp(baseTime);
        id = 8;
        Time_Sheet timeSheet2 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet2);
        
        HoursCalculator instance = new HoursCalculator(tsa);
        int expResult1 = 2;
        int expResult2 = 4;
        int expResultTotal = 6;
        int result1 = 0;
        int result2 = 0;
        int resultTotal = 0;
        try {
            result1 = instance.getHoursForTimeSheeet(timeSheet1);
            result2 = instance.getHoursForTimeSheeet(timeSheet2);
        } catch (SQLException ex) {
            Logger.getLogger(HoursCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        resultTotal = result1 + result2;
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
        assertEquals(expResultTotal, resultTotal);
    }
    
    /**
     * Test of getHoursForTimeSheeet method, of class HoursCalculator.
     * 2 timesheet with different startTime and different endTime, 1 timesheet surrounding the other, total at 1,5 hours
     */
    @Test
    public void testGetHoursForTimeSheeet12() {
        System.out.println("getHoursForTimeSheeet12 : 2 timesheet with different startTime and different endTime, 1 timesheet surrounding the other, total at 1,5 hours");
        TimeSheet_AccessTest tsa = new TimeSheet_AccessTest();
        int id = 9;
        int employeeId = 1;
        int alarmId = 1;
        int carNumber = 2711;
        Position carPos = MyConstants.FIREMAN;
        Timestamp startTime = new Timestamp(baseTime-(oneHour)-(oneHour/2));
        Timestamp endTime = new Timestamp(baseTime);
        int acceptedByTeamleader = 0;
        int acceptedToSalary = 0;
        boolean addToPayment = false;
        Comment comment = new Comment("Some comment");
        Time_Sheet timeSheet1 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet1);
        
        startTime = new Timestamp(baseTime-(oneHour));
        endTime = new Timestamp(baseTime - (oneHour/2));
        id = 10;
        Time_Sheet timeSheet2 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet2);
        
        HoursCalculator instance = new HoursCalculator(tsa);
        int expResult1 = 1;
        int expResult2 = 1;
        int expResultTotal = 2;
        int result1 = 0;
        int result2 = 0;
        int resultTotal = 0;
        try {
            result1 = instance.getHoursForTimeSheeet(timeSheet1);
            result2 = instance.getHoursForTimeSheeet(timeSheet2);
        } catch (SQLException ex) {
            Logger.getLogger(HoursCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        resultTotal = result1 + result2;
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
        assertEquals(expResultTotal, resultTotal);
    }
    
    /**
     * Test of getHoursForTimeSheeet method, of class HoursCalculator.
     * 2 timesheet with different startTime and different endTime, 1 timesheet surrounding the other, total at 6 hours
     */
    @Test
    public void testGetHoursForTimeSheeet13() {
        System.out.println("getHoursForTimeSheeet13 : 2 timesheet with different startTime and different endTime, 1 timesheet surrounding the other, total at 6 hours");
        TimeSheet_AccessTest tsa = new TimeSheet_AccessTest();
        int id = 17;
        int employeeId = 1;
        int alarmId = 1;
        int carNumber = 2711;
        Position carPos = MyConstants.FIREMAN;
        Timestamp startTime = new Timestamp(baseTime-(oneHour*6));
        Timestamp endTime = new Timestamp(baseTime);
        int acceptedByTeamleader = 0;
        int acceptedToSalary = 0;
        boolean addToPayment = false;
        Comment comment = new Comment("Some comment");
        Time_Sheet timeSheet1 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet1);
        
        startTime = new Timestamp(baseTime-(oneHour*4));
        endTime = new Timestamp(baseTime - (oneHour*2));
        id = 18;
        Time_Sheet timeSheet2 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet2);
        
        HoursCalculator instance = new HoursCalculator(tsa);
        int expResult1 = 4;
        int expResult2 = 2;
        int expResultTotal = 6;
        int result1 = 0;
        int result2 = 0;
        int resultTotal = 0;
        try {
            result1 = instance.getHoursForTimeSheeet(timeSheet1);
            result2 = instance.getHoursForTimeSheeet(timeSheet2);
        } catch (SQLException ex) {
            Logger.getLogger(HoursCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        resultTotal = result1 + result2;
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
        assertEquals(expResultTotal, resultTotal);
    }
    
    /**
     * Test of getHoursForTimeSheeet method, of class HoursCalculator.
     * 2 timesheet, one following the other. Total of 1½ hours
     */
    @Test
    public void testGetHoursForTimeSheeet14() {
        System.out.println("getHoursForTimeSheeet14 : 2 timesheet, one following the other. Total of 1½ hours");
        TimeSheet_AccessTest tsa = new TimeSheet_AccessTest();
        int id = 7;
        int employeeId = 1;
        int alarmId = 1;
        int carNumber = 2711;
        Position carPos = MyConstants.FIREMAN;
        Timestamp startTime = new Timestamp(baseTime-(oneHour)-(oneHour/2));
        Timestamp endTime = new Timestamp(baseTime- oneHour);
        int acceptedByTeamleader = 0;
        int acceptedToSalary = 0;
        boolean addToPayment = false;
        Comment comment = new Comment("Some comment");
        Time_Sheet timeSheet1 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet1);
        
        startTime = new Timestamp(baseTime-(oneHour));
        endTime = new Timestamp(baseTime);
        id = 8;
        Time_Sheet timeSheet2 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet2);
        
        HoursCalculator instance = new HoursCalculator(tsa);
        int expResult1 = 1;
        int expResult2 = 1;
        int expResultTotal = 2;
        int result1 = 0;
        int result2 = 0;
        int resultTotal = 0;
        try {
            result1 = instance.getHoursForTimeSheeet(timeSheet1);
            result2 = instance.getHoursForTimeSheeet(timeSheet2);
        } catch (SQLException ex) {
            Logger.getLogger(HoursCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        resultTotal = result1 + result2;
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
        assertEquals(expResultTotal, resultTotal);
    }
    
    /**
     * Test of getHoursForTimeSheeet method, of class HoursCalculator.
     * 2 timesheet, one following the other. Total of 5 hours
     */
    @Test
    public void testGetHoursForTimeSheeet15() {
        System.out.println("getHoursForTimeSheeet15 : 2 timesheet, one following the other. Total of 5 hours");
        TimeSheet_AccessTest tsa = new TimeSheet_AccessTest();
        int id = 7;
        int employeeId = 1;
        int alarmId = 1;
        int carNumber = 2711;
        Position carPos = MyConstants.FIREMAN;
        Timestamp startTime = new Timestamp(baseTime-(oneHour*5));
        Timestamp endTime = new Timestamp(baseTime- (oneHour*3));
        int acceptedByTeamleader = 0;
        int acceptedToSalary = 0;
        boolean addToPayment = false;
        Comment comment = new Comment("Some comment");
        Time_Sheet timeSheet1 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet1);
        
        startTime = new Timestamp(baseTime-(oneHour*3));
        endTime = new Timestamp(baseTime);
        id = 8;
        Time_Sheet timeSheet2 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet2);
        
        HoursCalculator instance = new HoursCalculator(tsa);
        int expResult1 = 2;
        int expResult2 = 3;
        int expResultTotal = 5;
        int result1 = 0;
        int result2 = 0;
        int resultTotal = 0;
        try {
            result1 = instance.getHoursForTimeSheeet(timeSheet1);
            result2 = instance.getHoursForTimeSheeet(timeSheet2);
        } catch (SQLException ex) {
            Logger.getLogger(HoursCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        resultTotal = result1 + result2;
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
        assertEquals(expResultTotal, resultTotal);
    }
    
    /**
     * Test of getHoursForTimeSheeet method, of class HoursCalculator.
     * 2 timesheet, seperate periods not connected. both at 1 hour
     */
    @Test
    public void testGetHoursForTimeSheeet16() {
        System.out.println("getHoursForTimeSheeet16 : 2 timesheet, seperate periods not connected. both at 1 hour");
        TimeSheet_AccessTest tsa = new TimeSheet_AccessTest();
        int id = 7;
        int employeeId = 1;
        int alarmId = 1;
        int carNumber = 2711;
        Position carPos = MyConstants.FIREMAN;
        Timestamp startTime = new Timestamp(baseTime-(oneHour*5));
        Timestamp endTime = new Timestamp(baseTime- (oneHour*4));
        int acceptedByTeamleader = 0;
        int acceptedToSalary = 0;
        boolean addToPayment = false;
        Comment comment = new Comment("Some comment");
        Time_Sheet timeSheet1 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet1);
        
        startTime = new Timestamp(baseTime-(oneHour*1));
        endTime = new Timestamp(baseTime);
        id = 8;
        Time_Sheet timeSheet2 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet2);
        
        HoursCalculator instance = new HoursCalculator(tsa);
        int expResult1 = 2;
        int expResult2 = 2;
        int expResultTotal = 4;
        int result1 = 0;
        int result2 = 0;
        int resultTotal = 0;
        try {
            result1 = instance.getHoursForTimeSheeet(timeSheet1);
            result2 = instance.getHoursForTimeSheeet(timeSheet2);
        } catch (SQLException ex) {
            Logger.getLogger(HoursCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        } 
        resultTotal = result1 + result2;
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
        assertEquals(expResultTotal, resultTotal);
    }
    
    /**
     * Test of getHoursForTimeSheeet method, of class HoursCalculator.
     * 2 timesheet, seperate periods not connected. both at 1 hour
     */
    @Test
    public void testGetHoursForTimeSheeet17() {
        System.out.println("getHoursForTimeSheeet17 : 2 timesheet, seperate periods not connected. At 3 hours and 4 hours");
        TimeSheet_AccessTest tsa = new TimeSheet_AccessTest();
        int id = 7;
        int employeeId = 1;
        int alarmId = 1;
        int carNumber = 2711;
        Position carPos = MyConstants.FIREMAN;
        Timestamp startTime = new Timestamp(baseTime-(oneHour*8));
        Timestamp endTime = new Timestamp(baseTime- (oneHour*5));
        int acceptedByTeamleader = 0;
        int acceptedToSalary = 0;
        boolean addToPayment = false;
        Comment comment = new Comment("Some comment");
        Time_Sheet timeSheet1 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet1);
        
        startTime = new Timestamp(baseTime-(oneHour*4));
        endTime = new Timestamp(baseTime);
        id = 8;
        Time_Sheet timeSheet2 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet2);
        
        HoursCalculator instance = new HoursCalculator(tsa);
        int expResult1 = 3;
        int expResult2 = 4;
        int expResultTotal = 7;
        int result1 = 0;
        int result2 = 0;
        int resultTotal = 0;
        try {
            result1 = instance.getHoursForTimeSheeet(timeSheet1);
            result2 = instance.getHoursForTimeSheeet(timeSheet2);
        } catch (SQLException ex) {
            Logger.getLogger(HoursCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        resultTotal = result1 + result2;
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
        assertEquals(expResultTotal, resultTotal);
    }
    
    /**
     * Test of getHoursForTimeSheeet method, of class HoursCalculator.
     * 3 timesheet, different start and end times
     */
    @Test
    public void testGetHoursForTimeSheeet18() {
        System.out.println("getHoursForTimeSheeet18 : 3 timesheet, different start and end times");
        TimeSheet_AccessTest tsa = new TimeSheet_AccessTest();
        int id = 7;
        int employeeId = 1;
        int alarmId = 1;
        int carNumber = 2711;
        Position carPos = MyConstants.FIREMAN;
        Timestamp startTime = new Timestamp(baseTime-(oneHour*7));
        Timestamp endTime = new Timestamp(baseTime- (oneHour*3));
        int acceptedByTeamleader = 0;
        int acceptedToSalary = 0;
        boolean addToPayment = false;
        Comment comment = new Comment("Some comment");
        Time_Sheet timeSheet1 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet1);
        
        startTime = new Timestamp(baseTime-(oneHour*4));
        endTime = new Timestamp(baseTime- (oneHour));
        id = 8;
        Time_Sheet timeSheet2 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet2);
        
        startTime = new Timestamp(baseTime-(oneHour*2));
        endTime = new Timestamp(baseTime);
        id = 9;
        Time_Sheet timeSheet3 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet3);
        
        HoursCalculator instance = new HoursCalculator(tsa);
        int expResult1 = 3;
        int expResult2 = 2;
        int expResult3 = 2;
        int expResultTotal = 7;
        int result1 = 0;
        int result2 = 0;
        int result3 = 0;
        int resultTotal = 0;
        try {
            result1 = instance.getHoursForTimeSheeet(timeSheet1);
            result2 = instance.getHoursForTimeSheeet(timeSheet2);
            result3 = instance.getHoursForTimeSheeet(timeSheet3);
        } catch (SQLException ex) {
            Logger.getLogger(HoursCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        resultTotal = result1 + result2 + result3;
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
        assertEquals(expResult3, result3);
        assertEquals(expResultTotal, resultTotal);
    }
    
    /**
     * Test of getHoursForTimeSheeet method, of class HoursCalculator.
     * 3 timesheet, different start and end times
     */
    @Test
    public void testGetHoursForTimeSheeet19() {
        System.out.println("getHoursForTimeSheeet19 : 3 timesheet, different start and end times");
        TimeSheet_AccessTest tsa = new TimeSheet_AccessTest();
        int id = 1;
        int employeeId = 1;
        int alarmId = 1;
        int carNumber = 2711;
        Position carPos = MyConstants.FIREMAN;
        Timestamp startTime = new Timestamp(baseTime-(oneHour*5));
        Timestamp endTime = new Timestamp(baseTime);
        int acceptedByTeamleader = 0;
        int acceptedToSalary = 0;
        boolean addToPayment = false;
        Comment comment = new Comment("Some comment");
        Time_Sheet timeSheet1 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet1);
        
        startTime = new Timestamp(baseTime-(oneHour*4));
        endTime = new Timestamp(baseTime- (oneHour*3));
        id = 2;
        Time_Sheet timeSheet2 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet2);
        
        startTime = new Timestamp(baseTime-(oneHour*4));
        endTime = new Timestamp(baseTime - (oneHour*3));
        id = 3;
        Time_Sheet timeSheet3 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet3);
        
        HoursCalculator instance = new HoursCalculator(tsa);
        int expResult1 = 4;
        int expResult2 = 1;
        int expResult3 = 0;
        int expResultTotal = 5;
        int result1 = 0;
        int result2 = 0;
        int result3 = 0;
        int resultTotal = 0;
        try {
            result1 = instance.getHoursForTimeSheeet(timeSheet1);
            result2 = instance.getHoursForTimeSheeet(timeSheet2);
            result3 = instance.getHoursForTimeSheeet(timeSheet3);
        } catch (SQLException ex) {
            Logger.getLogger(HoursCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        resultTotal = result1 + result2 + result3;
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
        assertEquals(expResult3, result3);
        assertEquals(expResultTotal, resultTotal);
    }
    
    /**
     * Test of getHoursForTimeSheeet method, of class HoursCalculator.
     * 3 timesheet, different start and end times
     */
    @Test
    public void testGetHoursForTimeSheeet20() {
        System.out.println("getHoursForTimeSheeet20 : 3 timesheet, different start and end times");
        TimeSheet_AccessTest tsa = new TimeSheet_AccessTest();
        int id = 12;
        int employeeId = 1;
        int alarmId = 1;
        int carNumber = 2711;
        Position carPos = MyConstants.FIREMAN;
        Timestamp startTime = new Timestamp(baseTime-(oneHour*5));
        Timestamp endTime = new Timestamp(baseTime);
        int acceptedByTeamleader = 0;
        int acceptedToSalary = 0;
        boolean addToPayment = false;
        Comment comment = new Comment("Some comment");
        Time_Sheet timeSheet1 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet1);
        
        startTime = new Timestamp(baseTime-(oneHour*4));
        endTime = new Timestamp(baseTime- (oneHour*3));
        id = 13;
        Time_Sheet timeSheet2 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet2);
        
        startTime = new Timestamp(baseTime-(oneHour*3));
        endTime = new Timestamp(baseTime - (oneHour*2));
        id = 14;
        Time_Sheet timeSheet3 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet3);
        
        HoursCalculator instance = new HoursCalculator(tsa);
        int expResult1 = 3;
        int expResult2 = 1;
        int expResult3 = 1;
        int expResultTotal = 5;
        int result1 = 0;
        int result2 = 0;
        int result3 = 0;
        int resultTotal = 0;
        try {
            result1 = instance.getHoursForTimeSheeet(timeSheet1);
            result2 = instance.getHoursForTimeSheeet(timeSheet2);
            result3 = instance.getHoursForTimeSheeet(timeSheet3);
        } catch (SQLException ex) {
            Logger.getLogger(HoursCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        resultTotal = result1 + result2 + result3;
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
        assertEquals(expResult3, result3);
        assertEquals(expResultTotal, resultTotal);
    }
    
    /**
     * Test of getHoursForTimeSheeet method, of class HoursCalculator.
     * 3 timesheet, different start and end times
     */
    @Test
    public void testGetHoursForTimeSheeet21() {
        System.out.println("getHoursForTimeSheeet21 : 3 timesheet, different start and end times");
        TimeSheet_AccessTest tsa = new TimeSheet_AccessTest();
        int id = 12;
        int employeeId = 1;
        int alarmId = 1;
        int carNumber = 2711;
        Position carPos = MyConstants.FIREMAN;
        Timestamp startTime = new Timestamp(baseTime-(oneHour*5));
        Timestamp endTime = new Timestamp(baseTime - (oneHour*3));
        int acceptedByTeamleader = 0;
        int acceptedToSalary = 0;
        boolean addToPayment = false;
        Comment comment = new Comment("Some comment");
        Time_Sheet timeSheet1 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet1);
        
        startTime = new Timestamp(baseTime-(oneHour*4));
        endTime = new Timestamp(baseTime- (oneHour*3));
        id = 13;
        Time_Sheet timeSheet2 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet2);
        
        startTime = new Timestamp(baseTime-(oneHour*3));
        endTime = new Timestamp(baseTime );
        id = 14;
        Time_Sheet timeSheet3 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet3);
        
        HoursCalculator instance = new HoursCalculator(tsa);
        int expResult1 = 1;
        int expResult2 = 1;
        int expResult3 = 3;
        int expResultTotal = 5;
        int result1 = 0;
        int result2 = 0;
        int result3 = 0;
        int resultTotal = 0;
        try {
            result1 = instance.getHoursForTimeSheeet(timeSheet1);
            result2 = instance.getHoursForTimeSheeet(timeSheet2);
            result3 = instance.getHoursForTimeSheeet(timeSheet3);
        } catch (SQLException ex) {
            Logger.getLogger(HoursCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        } 
        resultTotal = result1 + result2 + result3;
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);
        assertEquals(expResult3, result3);
        assertEquals(expResultTotal, resultTotal);
    }
    
}
