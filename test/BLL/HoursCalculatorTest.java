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
    

    /**
     * Test of getHoursForTimeSheeet method, of class HoursCalculator.
     * 1 Hour with no conflicts = 2 hours
     */
    @Test
    public void testGetHoursForTimeSheeet() {
        final int oneHour = (60*60*1000);
        System.out.println("getHoursForTimeSheeet : 1 Hour with no conflicts = 2 hours");
        TimeSheet_AccessTest tsa = new TimeSheet_AccessTest();
        int id = 1;
        int employeeId = 1;
        int alarmId = 1;
        int carNumber = 2711;
        Position carPos = MyConstants.FIREMAN;
        Timestamp startTime = new Timestamp(System.currentTimeMillis()-oneHour);
        Timestamp endTime = new Timestamp(System.currentTimeMillis());
        int acceptedByTeamleader = 0;
        int acceptedToSalary = 0;
        boolean addToPayment = false;
        Comment comment = new Comment("Some comment");
        Time_Sheet timeSheet1 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet1);
        
        HoursCalculator instance = new HoursCalculator(tsa);
        int expResult = 2;
        double result1 = 0;
        try {
            result1 = instance.getHoursForTimeSheeet(timeSheet1).getHours();
        } catch (SQLException ex) {
            Logger.getLogger(HoursCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(HoursCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        int result = (int)Math.ceil(result1);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getHoursForTimeSheeet method, of class HoursCalculator.
     * 3 Hours with no conflicts = 3 hours
     */
    @Test
    public void testGetHoursForTimeSheeet2() {
        final int oneHour = (60*60*1000);
        System.out.println("getHoursForTimeSheeet2 : 3 Hours with no conflicts = 3 hours");
        TimeSheet_AccessTest tsa = new TimeSheet_AccessTest();
        int id = 1;
        int employeeId = 1;
        int alarmId = 1;
        int carNumber = 2711;
        Position carPos = MyConstants.FIREMAN;
        Timestamp startTime = new Timestamp(System.currentTimeMillis()-(oneHour*3));
        Timestamp endTime = new Timestamp(System.currentTimeMillis());
        int acceptedByTeamleader = 0;
        int acceptedToSalary = 0;
        boolean addToPayment = false;
        Comment comment = new Comment("Some comment");
        Time_Sheet timeSheet1 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet1);
        
        HoursCalculator instance = new HoursCalculator(tsa);
        int expResult = 3;
        double result1 = 0;
        try {
            result1 = instance.getHoursForTimeSheeet(timeSheet1).getHours();
        } catch (SQLException ex) {
            Logger.getLogger(HoursCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(HoursCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        int result = (int)Math.round(result1);
        assertEquals(expResult, result);
    }
    
     /**
     * Test of getHoursForTimeSheeet method, of class HoursCalculator.
     * 2 different people working on same car at same time
     */
    @Test
    public void testGetHoursForTimeSheeet3() {
        final int oneHour = (60*60*1000);
        System.out.println("getHoursForTimeSheeet3 : 2 different people working on same car at same time");
        TimeSheet_AccessTest tsa = new TimeSheet_AccessTest();
        int id = 1;
        int employeeId = 1;
        int alarmId = 1;
        int carNumber = 2711;
        Position carPos = MyConstants.FIREMAN;
        Timestamp startTime = new Timestamp(System.currentTimeMillis()-(oneHour*3));
        Timestamp endTime = new Timestamp(System.currentTimeMillis());
        int acceptedByTeamleader = 0;
        int acceptedToSalary = 0;
        boolean addToPayment = false;
        Comment comment = new Comment("Some comment");
        Time_Sheet timeSheet1 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet1);
        id = 2;
        employeeId = 2;
        Time_Sheet timeSheet2 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet2);
        
        
        HoursCalculator instance = new HoursCalculator(tsa);
        
        double tempresult1 = 0;
        double tempresult2 = 0;
        try {
            tempresult1 = instance.getHoursForTimeSheeet(timeSheet1).getHours();
            tempresult2 = instance.getHoursForTimeSheeet(timeSheet2).getHours();
        } catch (SQLException ex) {
            Logger.getLogger(HoursCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(HoursCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int result = (int)Math.round(tempresult1);
        int result2 = (int)Math.round(tempresult2);
        int expResult = 3;
        
        assertEquals(expResult, result);
        assertEquals(expResult, result2);
    }
    
    /**
     * Test of getHoursForTimeSheeet method, of class HoursCalculator.
     * 1 person with 2 conflicting timeSheets
     */
    @Test
    public void testGetHoursForTimeSheeet4() {
        final int oneHour = (60*60*1000);
        System.out.println("getHoursForTimeSheeet4 : 1 person with 2 conflicting timeSheets");
        TimeSheet_AccessTest tsa = new TimeSheet_AccessTest();
        int id = 1;
        int employeeId = 1;
        int alarmId = 1;
        int carNumber = 2711;
        Position carPos = MyConstants.FIREMAN;
        Timestamp startTime = new Timestamp(System.currentTimeMillis()-(oneHour*4));
        Timestamp endTime = new Timestamp(System.currentTimeMillis());
        int acceptedByTeamleader = 0;
        int acceptedToSalary = 0;
        boolean addToPayment = false;
        Comment comment = new Comment("Some comment");
        Time_Sheet timeSheet1 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet1);
        
        startTime = new Timestamp(System.currentTimeMillis()-(oneHour*3));
        endTime = new Timestamp(System.currentTimeMillis());
        id = 2;
        Time_Sheet timeSheet2 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet2);
        
        
        HoursCalculator instance = new HoursCalculator(tsa);
        double result1 = 0;
        double result2 = 0;
        try {
            result1 = instance.getHoursForTimeSheeet(timeSheet1).getHours();
            result2 = instance.getHoursForTimeSheeet(timeSheet2).getHours();
        } catch (SQLException ex) {
            Logger.getLogger(HoursCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(HoursCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        int expResult = 1;
        assertEquals(expResult, (int)result1);
        expResult = 3;
        assertEquals(expResult, (int)result2);
        
        expResult = 4;
        int result = (int)(Math.round(result1 + result2));
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getHoursForTimeSheeet method, of class HoursCalculator.
     * 1 person with 3 conflicting timeSheets
     */
    @Test
    public void testGetHoursForTimeSheeet5() {
        final int oneHour = (60*60*1000);
        System.out.println("getHoursForTimeSheeet5 : 1 person with 3 conflicting timeSheets");
        TimeSheet_AccessTest tsa = new TimeSheet_AccessTest();
        int id = 1;
        int employeeId = 1;
        int alarmId = 1;
        int carNumber = 2711;
        Position carPos = MyConstants.FIREMAN;
        Timestamp startTime = new Timestamp(System.currentTimeMillis()-(oneHour*3)-(oneHour/4));
        Timestamp endTime = new Timestamp(System.currentTimeMillis()-(oneHour/4));
        int acceptedByTeamleader = 0;
        int acceptedToSalary = 0;
        boolean addToPayment = false;
        Comment comment = new Comment("Some comment");
        Time_Sheet timeSheet1 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet1);
        
        startTime = new Timestamp(System.currentTimeMillis()-(oneHour*2)-(oneHour/2));
        endTime = new Timestamp(System.currentTimeMillis());
        
        id = 2;
        Time_Sheet timeSheet2 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet2);
        
        id = 3;
        startTime = new Timestamp(System.currentTimeMillis()-(oneHour*2));
        endTime = new Timestamp(System.currentTimeMillis());
        
        Time_Sheet timeSheet3 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet3);
        
        
        HoursCalculator instance = new HoursCalculator(tsa);
        double result1 = 0;
        double result2 = 0;
        double result3 = 0;
        try {
            result1 = instance.getHoursForTimeSheeet(timeSheet1).getHours();
            result2 = instance.getHoursForTimeSheeet(timeSheet2).getHours();
            result3 = instance.getHoursForTimeSheeet(timeSheet3).getHours();
        } catch (SQLException ex) {
            Logger.getLogger(HoursCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(HoursCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        int expResult = 1;
        assertEquals(expResult, (int)result1);
        expResult = 1;
        assertEquals(expResult, (int)result2);
        expResult = 2;
        assertEquals(expResult, (int)result3);
        
        expResult = 4;
        int result = (int)(Math.round(result1 + result2 + result3));
        
        assertEquals(expResult, result);
    }
    
    
    /**
     * Test of getHoursForTimeSheeet method, of class HoursCalculator.
     * 1 Timesheet surrounding another
     */
    @Test
    public void testGetHoursForTimeSheeet6() {
        final int oneHour = (60*60*1000);
        System.out.println("getHoursForTimeSheeet6 : 1 Timesheet surrounding another");
        TimeSheet_AccessTest tsa = new TimeSheet_AccessTest();
        int id = 1;
        int employeeId = 1;
        int alarmId = 1;
        int carNumber = 2711;
        Position carPos = MyConstants.FIREMAN;
        Timestamp startTime = new Timestamp(System.currentTimeMillis()-(oneHour*4));
        Timestamp endTime = new Timestamp(System.currentTimeMillis());
        int acceptedByTeamleader = 0;
        int acceptedToSalary = 0;
        boolean addToPayment = false;
        Comment comment = new Comment("Some comment");
        Time_Sheet timeSheet1 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet1);
        
        id = 2;
        startTime = new Timestamp(System.currentTimeMillis()-(oneHour*3));
        endTime = new Timestamp(System.currentTimeMillis()-(oneHour));
        
        Time_Sheet timeSheet2 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet2);
        
        HoursCalculator instance = new HoursCalculator(tsa);
        
        double result1 = 0;
        double result2 = 0;
        
        try {
            result1 = instance.getHoursForTimeSheeet(timeSheet1).getHours();
            result2 = instance.getHoursForTimeSheeet(timeSheet2).getHours();
        } catch (SQLException ex) {
            Logger.getLogger(HoursCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(HoursCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        int expResult = 2;
        assertEquals(expResult, (int)result1);
        expResult = 2;
        assertEquals(expResult, (int)result2);
        
        expResult = 4;
        int result = (int)(Math.round(result1 + result2 ));
        
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getHoursForTimeSheeet method, of class HoursCalculator.
     * half hours
     */
    @Test
    public void testGetHoursForTimeSheeet7() {
        final int oneHour = (60*60*1000);
        System.out.println("getHoursForTimeSheeet7 : half hours");
        TimeSheet_AccessTest tsa = new TimeSheet_AccessTest();
        int id = 1;
        int employeeId = 1;
        int alarmId = 1;
        int carNumber = 2711;
        Position carPos = MyConstants.FIREMAN;
        Timestamp startTime = new Timestamp(System.currentTimeMillis()-(oneHour*9/2));
        Timestamp endTime = new Timestamp(System.currentTimeMillis());
        int acceptedByTeamleader = 0;
        int acceptedToSalary = 0;
        boolean addToPayment = false;
        Comment comment = new Comment("Some comment");
        Time_Sheet timeSheet1 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet1);
        
        
        HoursCalculator instance = new HoursCalculator(tsa);
        
        double result1 = 0;
        try {
            result1 = instance.getHoursForTimeSheeet(timeSheet1).getHours();
        } catch (SQLException ex) {
            Logger.getLogger(HoursCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(HoursCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int expResult = 5;
        assertEquals(expResult, (int)result1);
    }
    
    /**
     * Test of getHoursForTimeSheeet method, of class HoursCalculator.
     * quarter hours
     */
    @Test
    public void testGetHoursForTimeSheeet8() {
        final int oneHour = (60*60*1000);
        System.out.println("getHoursForTimeSheeet8 : quarter hours");
        TimeSheet_AccessTest tsa = new TimeSheet_AccessTest();
        int id = 1;
        int employeeId = 1;
        int alarmId = 1;
        int carNumber = 2711;
        Position carPos = MyConstants.FIREMAN;
        Timestamp startTime = new Timestamp(System.currentTimeMillis()-(oneHour*9/4));
        Timestamp endTime = new Timestamp(System.currentTimeMillis());
        int acceptedByTeamleader = 0;
        int acceptedToSalary = 0;
        boolean addToPayment = false;
        Comment comment = new Comment("Some comment");
        Time_Sheet timeSheet1 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet1);
        
        
        HoursCalculator instance = new HoursCalculator(tsa);
        
        double result1 = 0;
        try {
            result1 = instance.getHoursForTimeSheeet(timeSheet1).getHours();
        } catch (SQLException ex) {
            Logger.getLogger(HoursCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(HoursCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        int expResult = 3;
        assertEquals(expResult, (int)result1);
    }
    
    
    /**
     * Test of getHoursForTimeSheeet method, of class HoursCalculator.
     * 1 hour timesheet followed by a 1 hour gap and then by a 2 hour timeSheet
     */
    @Test
    public void testGetHoursForTimeSheeet9() {
        final int oneHour = (60*60*1000);
        System.out.println("getHoursForTimeSheeet9 : 1 hour timesheet followed by a 1 hour gap and then by a 2 hour timeSheet");
        TimeSheet_AccessTest tsa = new TimeSheet_AccessTest();
        int id = 1;
        int employeeId = 1;
        int alarmId = 1;
        int carNumber = 2711;
        Position carPos = MyConstants.FIREMAN;
        Timestamp startTime = new Timestamp(System.currentTimeMillis()-(oneHour*4));
        Timestamp endTime = new Timestamp(System.currentTimeMillis()-(oneHour*3));
        int acceptedByTeamleader = 0;
        int acceptedToSalary = 0;
        boolean addToPayment = false;
        Comment comment = new Comment("Some comment");
        Time_Sheet timeSheet1 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet1);
        
        id = 2;
        startTime = new Timestamp(System.currentTimeMillis()-(oneHour*2));
        endTime = new Timestamp(System.currentTimeMillis());
        
        Time_Sheet timeSheet2 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        
        HoursCalculator instance = new HoursCalculator(tsa);
        
        double result1 = 0;
        double result2 = 0;
        try {
            result1 = instance.getHoursForTimeSheeet(timeSheet1).getHours();
            result2 = instance.getHoursForTimeSheeet(timeSheet2).getHours();
        } catch (SQLException ex) {
            Logger.getLogger(HoursCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(HoursCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        int expResult = 2;
        assertEquals(expResult, (int)result1);
        expResult = 2;
        assertEquals(expResult, (int)result2);
        
        expResult = 4;
        int result = (int)(Math.round(result1 + result2));
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getHoursForTimeSheeet method, of class HoursCalculator.
     * 1 hour timesheet followed by a 1 hour gap and then by a 1 hour timeSheet
     */
    @Test
    public void testGetHoursForTimeSheeet10() {
        final int oneHour = (60*60*1000);
        System.out.println("getHoursForTimeSheeet10 : 1 hour timesheet followed by a 1 hour gap and then by a 1 hour timeSheet");
        TimeSheet_AccessTest tsa = new TimeSheet_AccessTest();
        int id = 1;
        int employeeId = 1;
        int alarmId = 1;
        int carNumber = 2711;
        Position carPos = MyConstants.FIREMAN;
        Timestamp startTime = new Timestamp(System.currentTimeMillis()-(oneHour*4));
        Timestamp endTime = new Timestamp(System.currentTimeMillis()-(oneHour*3));
        int acceptedByTeamleader = 0;
        int acceptedToSalary = 0;
        boolean addToPayment = false;
        Comment comment = new Comment("Some comment");
        Time_Sheet timeSheet1 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet1);
        
        id = 2;
        startTime = new Timestamp(System.currentTimeMillis()-(oneHour*2));
        endTime = new Timestamp(System.currentTimeMillis()-(oneHour));
        
        Time_Sheet timeSheet2 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        
        HoursCalculator instance = new HoursCalculator(tsa);
        
        double result1 = 0;
        double result2 = 0;
        try {
            result1 = instance.getHoursForTimeSheeet(timeSheet1).getHours();
            result2 = instance.getHoursForTimeSheeet(timeSheet2).getHours();
        } catch (SQLException ex) {
            Logger.getLogger(HoursCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(HoursCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        int expResult = 2;
        assertEquals(expResult, (int)result1);
        expResult = 2;
        assertEquals(expResult, (int)result2);
        
        expResult = 4;
        int result = (int)(Math.round(result1 + result2));
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getHoursForTimeSheeet method, of class HoursCalculator.
     * 1 hour timesheet followed by a 2 hour gap and then by a 1 hour timeSheet
     */
    @Test
    public void testGetHoursForTimeSheeet11() {
        final int oneHour = (60*60*1000);
        System.out.println("getHoursForTimeSheeet11 : 1 hour timesheet followed by a 2 hour gap and then by a 1 hour timeSheet");
        TimeSheet_AccessTest tsa = new TimeSheet_AccessTest();
        int id = 1;
        int employeeId = 1;
        int alarmId = 1;
        int carNumber = 2711;
        Position carPos = MyConstants.FIREMAN;
        Timestamp startTime = new Timestamp(System.currentTimeMillis()-(oneHour*4));
        Timestamp endTime = new Timestamp(System.currentTimeMillis()-(oneHour*3));
        int acceptedByTeamleader = 0;
        int acceptedToSalary = 0;
        boolean addToPayment = false;
        Comment comment = new Comment("Some comment");
        Time_Sheet timeSheet1 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet1);
        
        id = 2;
        startTime = new Timestamp(System.currentTimeMillis()-(oneHour*1));
        endTime = new Timestamp(System.currentTimeMillis());
        
        Time_Sheet timeSheet2 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        
        HoursCalculator instance = new HoursCalculator(tsa);
        
        double result1 = 0;
        double result2 = 0;
        try {
            result1 = instance.getHoursForTimeSheeet(timeSheet1).getHours();
            result2 = instance.getHoursForTimeSheeet(timeSheet2).getHours();
        } catch (SQLException ex) {
            Logger.getLogger(HoursCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(HoursCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        int expResult = 2;
        assertEquals(expResult, (int)result1);
        expResult = 2;
        assertEquals(expResult, (int)result2);
        
        expResult = 4;
        int result = (int)(Math.round(result1 + result2));
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getHoursForTimeSheeet method, of class HoursCalculator.
     * 1 hour timesheet followed by a 2 hour gap and then by a 1 hour timeSheet
     */
    @Test
    public void testGetHoursForTimeSheeet12() {
        final int oneHour = (60*60*1000);
        System.out.println("getHoursForTimeSheeet12 : Quater hour in conflicting timesheets");
        TimeSheet_AccessTest tsa = new TimeSheet_AccessTest();
        int id = 11;
        int employeeId = 1;
        int alarmId = 1;
        int carNumber = 2711;
        Position carPos = MyConstants.FIREMAN;
        Timestamp startTime = new Timestamp(System.currentTimeMillis()-(oneHour*4)-(oneHour/4));
        Timestamp endTime = new Timestamp(System.currentTimeMillis()-(oneHour*2));
        int acceptedByTeamleader = 0;
        int acceptedToSalary = 0;
        boolean addToPayment = false;
        Comment comment = new Comment("Some comment");
        Time_Sheet timeSheet1 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet1);
        
        id = 12;
        startTime = new Timestamp(System.currentTimeMillis()-(oneHour*3));
        endTime = new Timestamp(System.currentTimeMillis());
        
        Time_Sheet timeSheet2 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet2);
        HoursCalculator instance = new HoursCalculator(tsa);
        
        double result1 = 0;
        double result2 = 0;
        try {
            result1 = instance.getHoursForTimeSheeet(timeSheet1).getHours();
            result2 = instance.getHoursForTimeSheeet(timeSheet2).getHours();
        } catch (SQLException ex) {
            Logger.getLogger(HoursCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(HoursCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int expResult = 2;
        assertEquals(expResult, (int)result1);
        expResult = 3;
        assertEquals(expResult, (int)result2);
        
        expResult = 5;
        int result = (int)(Math.round(result1 + result2));
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getHoursForTimeSheeet method, of class HoursCalculator.
     * 1 hour timesheet followed by a 2 hour gap and then by a 1 hour timeSheet
     */
    @Test
    public void testGetHoursForTimeSheeet13() {
        final int oneHour = (60*60*1000);
        System.out.println("getHoursForTimeSheeet13 : 2 Timesheets at same time");
        TimeSheet_AccessTest tsa = new TimeSheet_AccessTest();
        int id = 1;
        int employeeId = 1;
        int alarmId = 1;
        int carNumber = 2711;
        Position carPos = MyConstants.FIREMAN;
        Timestamp startTime = new Timestamp(System.currentTimeMillis()-(oneHour*4));
        Timestamp endTime = new Timestamp(System.currentTimeMillis());
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
        
        double result1 = 0;
        double result2 = 0;
        try {
            result1 = instance.getHoursForTimeSheeet(timeSheet1).getHours();
            result2 = instance.getHoursForTimeSheeet(timeSheet2).getHours();
        } catch (SQLException ex) {
            Logger.getLogger(HoursCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(HoursCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int expResult = 4;
        assertEquals(expResult, (int)result1);
        expResult = 0;
        assertEquals(expResult, (int)result2);
        
        expResult = 4;
        int result = (int)(Math.round(result1 + result2));
        assertEquals(expResult, result);
    }
    /**
     * Test of getHoursForTimeSheeet method, of class HoursCalculator.
     * 1 hour timesheet followed by a 2 hour gap and then by a 1 hour timeSheet
     */
    @Test
    public void testGetHoursForTimeSheeet14() {
        final int oneHour = (60*60*1000);
        System.out.println("getHoursForTimeSheeet14 : 2 Timesheets wit same starttime but different endTimes");
        TimeSheet_AccessTest tsa = new TimeSheet_AccessTest();
        int id = 1;
        int employeeId = 1;
        int alarmId = 1;
        int carNumber = 2711;
        Position carPos = MyConstants.FIREMAN;
        Timestamp startTime = new Timestamp(System.currentTimeMillis()-(oneHour*4));
        Timestamp endTime = new Timestamp(System.currentTimeMillis()-(oneHour));
        int acceptedByTeamleader = 0;
        int acceptedToSalary = 0;
        boolean addToPayment = false;
        Comment comment = new Comment("Some comment");
        Time_Sheet timeSheet1 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet1);
        
        id = 2;
        endTime = new Timestamp(System.currentTimeMillis());
        Time_Sheet timeSheet2 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet2);
        HoursCalculator instance = new HoursCalculator(tsa);
        
        double result1 = 0;
        double result2 = 0;
        try {
            result1 = instance.getHoursForTimeSheeet(timeSheet1).getHours();
            result2 = instance.getHoursForTimeSheeet(timeSheet2).getHours();
            
        } catch (SQLException ex) {
            Logger.getLogger(HoursCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(HoursCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int expResult = 0;
        assertEquals(expResult, (int)result1);
        expResult = 4;
        assertEquals(expResult, (int)result2);
        
        expResult = 4;
        int result = (int)(Math.round(result1 + result2));
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getHoursForTimeSheeet method, of class HoursCalculator.
     * 1 hour timesheet followed by a 2 hour gap and then by a 1 hour timeSheet
     */
    @Test
    public void testGetHoursForTimeSheeet15() {
        final int oneHour = (60*60*1000);
        System.out.println("getHoursForTimeSheeet15 : 1 timesheet surrounding another");
        TimeSheet_AccessTest tsa = new TimeSheet_AccessTest();
        int id = 1;
        int employeeId = 1;
        int alarmId = 1;
        int carNumber = 2711;
        Position carPos = MyConstants.FIREMAN;
        Timestamp startTime = new Timestamp(System.currentTimeMillis()-(oneHour*5));
        Timestamp endTime = new Timestamp(System.currentTimeMillis());
        int acceptedByTeamleader = 0;
        int acceptedToSalary = 0;
        boolean addToPayment = false;
        Comment comment = new Comment("Some comment");
        Time_Sheet timeSheet1 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet1);
        
        id = 2;
        startTime = new Timestamp(System.currentTimeMillis()-(oneHour*4));
        endTime = new Timestamp(System.currentTimeMillis()-(oneHour*2));
        Time_Sheet timeSheet2 = new Time_Sheet(id, employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet2);
        HoursCalculator instance = new HoursCalculator(tsa);
        
        double result1 = 0;
        double result2 = 0;
        try {
            result1 = instance.getHoursForTimeSheeet(timeSheet1).getHours();
            result2 = instance.getHoursForTimeSheeet(timeSheet2).getHours();
        } catch (SQLException ex) {
            Logger.getLogger(HoursCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(HoursCalculatorTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        int expResult = 3;
        assertEquals(expResult, (int)result1);
        expResult = 2;
        assertEquals(expResult, (int)result2);
        
        expResult = 5;
        int result = (int)(Math.round(result1 + result2));
        assertEquals(expResult, result);
    }
}
