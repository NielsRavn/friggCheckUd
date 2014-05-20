/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;

import BE.Comment;
import BE.Time_Sheet;
import DAL.TimeSheet_AccessTest;
import Presentation.MyConstants;
import java.sql.Timestamp;
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
        int employeeId = 1;
        int alarmId = 1;
        int carNumber = 2711;
        int carPos = MyConstants.FIREMAN.getID();
        Timestamp startTime = new Timestamp(System.currentTimeMillis()-oneHour);
        Timestamp endTime = new Timestamp(System.currentTimeMillis());
        int acceptedByTeamleader = 0;
        int acceptedToSalary = 0;
        boolean addToPayment = false;
        Comment comment = new Comment("Some comment");
        Time_Sheet timeSheet1 = new Time_Sheet(employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet1);
        
        HoursCalculator instance = new HoursCalculator(tsa);
        int expResult = 2;
        double result1 = instance.getHoursForTimeSheeet(timeSheet1).getHours();
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
        int employeeId = 1;
        int alarmId = 1;
        int carNumber = 2711;
        int carPos = MyConstants.FIREMAN.getID();
        Timestamp startTime = new Timestamp(System.currentTimeMillis()-(oneHour*3));
        Timestamp endTime = new Timestamp(System.currentTimeMillis());
        int acceptedByTeamleader = 0;
        int acceptedToSalary = 0;
        boolean addToPayment = false;
        Comment comment = new Comment("Some comment");
        Time_Sheet timeSheet1 = new Time_Sheet(employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet1);
        
        HoursCalculator instance = new HoursCalculator(tsa);
        int expResult = 3;
        double result1 = instance.getHoursForTimeSheeet(timeSheet1).getHours();
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
        System.out.println("getHoursForTimeSheeet2 : 2 different people working on same car at same time");
        TimeSheet_AccessTest tsa = new TimeSheet_AccessTest();
        int employeeId = 1;
        int alarmId = 1;
        int carNumber = 2711;
        int carPos = MyConstants.FIREMAN.getID();
        Timestamp startTime = new Timestamp(System.currentTimeMillis()-(oneHour*3));
        Timestamp endTime = new Timestamp(System.currentTimeMillis());
        int acceptedByTeamleader = 0;
        int acceptedToSalary = 0;
        boolean addToPayment = false;
        Comment comment = new Comment("Some comment");
        Time_Sheet timeSheet1 = new Time_Sheet(employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet1);
        employeeId = 2;
        Time_Sheet timeSheet2 = new Time_Sheet(employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet2);
        
        
        HoursCalculator instance = new HoursCalculator(tsa);
        int expResult = 3;
        double result1 = instance.getHoursForTimeSheeet(timeSheet1).getHours();
        int result = (int)Math.round(result1);
        assertEquals(expResult, result);
        
        result1 = instance.getHoursForTimeSheeet(timeSheet2).getHours();
        result = (int)Math.round(result1);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getHoursForTimeSheeet method, of class HoursCalculator.
     * 1 person with 2 conflicting timeSheets
     */
    @Test
    public void testGetHoursForTimeSheeet4() {
        final int oneHour = (60*60*1000);
        System.out.println("getHoursForTimeSheeet2 : 1 person with 2 conflicting timeSheets");
        TimeSheet_AccessTest tsa = new TimeSheet_AccessTest();
        int employeeId = 1;
        int alarmId = 1;
        int carNumber = 2711;
        int carPos = MyConstants.FIREMAN.getID();
        Timestamp startTime = new Timestamp(System.currentTimeMillis()-(oneHour*3));
        Timestamp endTime = new Timestamp(System.currentTimeMillis());
        int acceptedByTeamleader = 0;
        int acceptedToSalary = 0;
        boolean addToPayment = false;
        Comment comment = new Comment("Some comment");
        Time_Sheet timeSheet1 = new Time_Sheet(employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet1);
        
        Time_Sheet timeSheet2 = new Time_Sheet(employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet2);
        
        
        HoursCalculator instance = new HoursCalculator(tsa);
        double result1 = instance.getHoursForTimeSheeet(timeSheet1).getHours();
        
        int expResult = 3;
        double result2 = instance.getHoursForTimeSheeet(timeSheet2).getHours();
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
        System.out.println("getHoursForTimeSheeet2 : 1 person with 3 conflicting timeSheets");
        TimeSheet_AccessTest tsa = new TimeSheet_AccessTest();
        int employeeId = 1;
        int alarmId = 1;
        int carNumber = 2711;
        int carPos = MyConstants.FIREMAN.getID();
        Timestamp startTime = new Timestamp(System.currentTimeMillis()-(oneHour*3));
        Timestamp endTime = new Timestamp(System.currentTimeMillis());
        int acceptedByTeamleader = 0;
        int acceptedToSalary = 0;
        boolean addToPayment = false;
        Comment comment = new Comment("Some comment");
        Time_Sheet timeSheet1 = new Time_Sheet(employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet1);
        
        Time_Sheet timeSheet2 = new Time_Sheet(employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet2);
        
        startTime = new Timestamp(System.currentTimeMillis()-(oneHour*2));
        
        Time_Sheet timeSheet3 = new Time_Sheet(employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet3);
        
        
        HoursCalculator instance = new HoursCalculator(tsa);
        
        double result1 = instance.getHoursForTimeSheeet(timeSheet1).getHours();
        double result2 = instance.getHoursForTimeSheeet(timeSheet2).getHours();
        double result3 = instance.getHoursForTimeSheeet(timeSheet3).getHours();
        
        int expResult = 3;
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
        System.out.println("getHoursForTimeSheeet2 : 1 Timesheet surrounding another");
        TimeSheet_AccessTest tsa = new TimeSheet_AccessTest();
        int employeeId = 1;
        int alarmId = 1;
        int carNumber = 2711;
        int carPos = MyConstants.FIREMAN.getID();
        Timestamp startTime = new Timestamp(System.currentTimeMillis()-(oneHour*4));
        Timestamp endTime = new Timestamp(System.currentTimeMillis());
        int acceptedByTeamleader = 0;
        int acceptedToSalary = 0;
        boolean addToPayment = false;
        Comment comment = new Comment("Some comment");
        Time_Sheet timeSheet1 = new Time_Sheet(employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet1);
        
        startTime = new Timestamp(System.currentTimeMillis()-(oneHour*3));
        endTime = new Timestamp(System.currentTimeMillis()-(oneHour));
        
        Time_Sheet timeSheet2 = new Time_Sheet(employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet2);
        
        HoursCalculator instance = new HoursCalculator(tsa);
        
        double result1 = instance.getHoursForTimeSheeet(timeSheet1).getHours();
        double result2 = instance.getHoursForTimeSheeet(timeSheet2).getHours();
        
        int expResult = 4;
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
        System.out.println("getHoursForTimeSheeet2 : half hours");
        TimeSheet_AccessTest tsa = new TimeSheet_AccessTest();
        int employeeId = 1;
        int alarmId = 1;
        int carNumber = 2711;
        int carPos = MyConstants.FIREMAN.getID();
        Timestamp startTime = new Timestamp(System.currentTimeMillis()-(oneHour*9/2));
        Timestamp endTime = new Timestamp(System.currentTimeMillis());
        int acceptedByTeamleader = 0;
        int acceptedToSalary = 0;
        boolean addToPayment = false;
        Comment comment = new Comment("Some comment");
        Time_Sheet timeSheet1 = new Time_Sheet(employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet1);
        
        
        HoursCalculator instance = new HoursCalculator(tsa);
        
        double result1 = instance.getHoursForTimeSheeet(timeSheet1).getHours();
        
        int expResult = 5;
        int result = (int)(Math.round(result1));
        
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getHoursForTimeSheeet method, of class HoursCalculator.
     * quarter hours
     */
    @Test
    public void testGetHoursForTimeSheeet8() {
        final int oneHour = (60*60*1000);
        System.out.println("getHoursForTimeSheeet2 : quarter hours");
        TimeSheet_AccessTest tsa = new TimeSheet_AccessTest();
        int employeeId = 1;
        int alarmId = 1;
        int carNumber = 2711;
        int carPos = MyConstants.FIREMAN.getID();
        Timestamp startTime = new Timestamp(System.currentTimeMillis()-(oneHour*9/4));
        Timestamp endTime = new Timestamp(System.currentTimeMillis());
        int acceptedByTeamleader = 0;
        int acceptedToSalary = 0;
        boolean addToPayment = false;
        Comment comment = new Comment("Some comment");
        Time_Sheet timeSheet1 = new Time_Sheet(employeeId, alarmId, carNumber, carPos, startTime, endTime, acceptedByTeamleader, acceptedToSalary, addToPayment, comment);
        tsa.addTimeSheet(timeSheet1);
        
        
        HoursCalculator instance = new HoursCalculator(tsa);
        
        double result1 = instance.getHoursForTimeSheeet(timeSheet1).getHours();
        
        int expResult = 3;
        int result = (int)(Math.round(result1));
        
        assertEquals(expResult, result);
    }
    
}
