/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;

import BE.Comment;
import BE.Hours;
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
        int result = instance.getHoursForTimeSheeet(timeSheet1).getHours();
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
        int result = instance.getHoursForTimeSheeet(timeSheet1).getHours();
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
        int result = instance.getHoursForTimeSheeet(timeSheet1).getHours();
        assertEquals(expResult, result);
        
        result = instance.getHoursForTimeSheeet(timeSheet2).getHours();
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
        int expResult = 1;
        int result = instance.getHoursForTimeSheeet(timeSheet1).getHours();
        assertEquals(expResult, result);
        
        expResult = 2;
        result = instance.getHoursForTimeSheeet(timeSheet2).getHours();
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
        int expResult = 1;
        int result = instance.getHoursForTimeSheeet(timeSheet1).getHours();
        assertEquals(expResult, result);
        
        expResult = 2;
        result = instance.getHoursForTimeSheeet(timeSheet2).getHours();
        assertEquals(expResult, result);
    }
    
}
