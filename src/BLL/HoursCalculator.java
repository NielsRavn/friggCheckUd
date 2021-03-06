/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;

import BE.Time_Sheet;
import DAL.ITimeSheet_Access;
import DAL.TimeSheet_Access;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Brobak
 */
public class HoursCalculator{
    ITimeSheet_Access timeSheetAccess;
    final int oneHour = (60*60*1000);
    public HoursCalculator() throws IOException{
        timeSheetAccess = new TimeSheet_Access();
        
    }
    
    public HoursCalculator(ITimeSheet_Access tsa){
        timeSheetAccess = tsa;
    }
    
    
    /**
     * Calculates how many hours a given timesheet should get
     * @param ts the timesheet of which you want the hours
     * @return the hours
     * @throws SQLException if an error has occured executing the sql query
     */
    public int  getHoursForTimeSheeet(Time_Sheet ts) throws SQLException {
        ArrayList<Time_Sheet> conflicts = getAllConflicts(ts);

        Collections.sort(conflicts);
        ArrayList<Time_Sheet> currentTimesheets = new ArrayList(); //A list of timesheets that are still active
        ArrayList<Time_Sheet> primaryTimesheets = new ArrayList(); //A list of timesheets that are currently getting the hours
        for(int i = 0; i < conflicts.size(); i++){
            Time_Sheet currentTs = conflicts.get(i);
            
            /**
             * If the current timesheet is the first timesheet in conflicts, or if the current timesheet has the same startime, 
             * as the current active timesheets the add it to the currently active timesheets
            **/
            if(primaryTimesheets.isEmpty() || primaryTimesheets.get(0).getStartTime().getTime() == currentTs.getStartTime().getTime()){
                primaryTimesheets.add(currentTs);
                
            }else{
                
                removeOldTimeSheets(currentTimesheets, currentTs.getStartTime());     
                addTimeToPrimaryTimeSheets(primaryTimesheets, currentTimesheets, currentTs.getStartTime());
                primaryTimesheets.add(currentTs);
            }
        }
        cleanUpRemainingTimeSheets(primaryTimesheets, currentTimesheets);
        
        //calculate the total amount of minutes
        int minutes = 0;
        for(Time_Sheet myTS : conflicts){
            minutes += myTS.getMinutes();
        }
        //calculate the total amount of hours
        int totalHours;
        if(minutes % 60 == 0)
            totalHours = (int)(minutes / 60);
        else
            totalHours = (int)(minutes / 60)+1;
        
        if(totalHours < 2)
            totalHours = 2;
        for(Time_Sheet myTS : conflicts){
            while(myTS.getMinutes() >= 60){
                myTS.addHours(1);
                myTS.removeMinute(60);
                totalHours--;
            }
        }
        
        
        sortTimeSheetListByHours(conflicts, 0, conflicts.size()-1);
        
        while(totalHours > 0){
            conflicts.get(conflicts.size()-1).addHours(1);
            totalHours --;
            conflicts.get(conflicts.size()-1).removeMinute(60);
            sortTimeSheetListByHours(conflicts, 0, conflicts.size()-1);
        }
        int res = ts.getHours();
        for(Time_Sheet myTS : conflicts){
            myTS.clearHoursAndMinutes();
        }
        
        return res;
    }   
    
    
    
    
    /**
     * Adds time to timesheets from two given lists and removes them from the lists
     * @param primaryTimesheets //A list of timesheets that are currently getting the hours
     * @param currentTimesheets //A list of timesheets that are still active
     */
    private void cleanUpRemainingTimeSheets(ArrayList<Time_Sheet> primaryTimesheets, ArrayList<Time_Sheet> currentTimesheets){
        Timestamp latestTimePayedFor = primaryTimesheets.get(primaryTimesheets.size()-1).getEndTime();
        
        //removes all the timesheets from current time sheets, that ended before the last primary time sheet
        removeOldTimeSheets(currentTimesheets, latestTimePayedFor);
     
        //adds time to the remaining primary time sheets
        addTimeToPrimaryTimeSheets(primaryTimesheets, currentTimesheets, primaryTimesheets.get(primaryTimesheets.size()-1).getEndTime());

        //Adds time to the remaining current time sheets
        for(int j = currentTimesheets.size()-1; j >= 0; j--){
            
            Time_Sheet currenTempTS = currentTimesheets.get(j);
            long millis = currenTempTS.getEndTime().getTime() - latestTimePayedFor.getTime();
            int minutes = (int) (millis / (60*1000));
            currenTempTS.addMinute(minutes);
            latestTimePayedFor = currenTempTS.getEndTime();
        }
        currentTimesheets.clear();
    }
    
    /**
     * Removes timesheets that ended before a given time, from a list 
     * @param currentTimesheets the list you want to clean up in
     * @param currentTime the time you which you want to remove all timesheets that ended before
     */
    private void removeOldTimeSheets(ArrayList<Time_Sheet> currentTimesheets, Timestamp currentTime){
        for(int j = 0; j < currentTimesheets.size(); j++){
            if(currentTimesheets.get(j).getEndTime().getTime() <= currentTime.getTime()){
                currentTimesheets.remove(j);
                j--;
            }
        }
    }
    
    /**
     * Add time to primary timesheets and move them to current timesheets if they're still active
     * @param primaryTimesheets A list of timesheets that are currently getting the hours
     * @param currentTimesheets A list of timesheets that are still active
     * @param currentTime The current time to calculate hours and such from;
     */
    private void addTimeToPrimaryTimeSheets(ArrayList<Time_Sheet> primaryTimesheets, ArrayList<Time_Sheet> currentTimesheets, Timestamp currentTime){
        //Do for all time sheets in primary time sheet
        for(int j = 0; j < primaryTimesheets.size(); j++){

            Timestamp tempEnd = currentTime;
            Time_Sheet currentPrimaryTS = primaryTimesheets.get(j);
            //If the primary timesheet ended before the new current time, then remove it, add minutes and check if any current timesheets are still active.
            if(currentPrimaryTS.getEndTime().getTime() <= currentTime.getTime()){
                tempEnd = currentPrimaryTS.getEndTime();
                long millis = currentPrimaryTS.getEndTime().getTime() - currentPrimaryTS.getStartTimeForCurrentTimeAtAlarm().getTime();
                int minutes = (int) (millis / (60*1000));
                //split the time between all the primary time sheets
                for(Time_Sheet myTS: primaryTimesheets){
                    myTS.addMinute((minutes/primaryTimesheets.size()));
                    myTS.setStartTimeForCurrentTimeAtAlarm(currentPrimaryTS.getEndTime());
                }
                boolean hasAddedNewTimeSheet = false;
                //If it's the last primary time sheet. Then move the last added current time sheet to primary time sheets.
                if(primaryTimesheets.size() == 1 && !currentTimesheets.isEmpty()){
                    
                    removeOldTimeSheets(currentTimesheets, currentPrimaryTS.getEndTime());
                    
                    primaryTimesheets.add(currentTimesheets.get(currentTimesheets.size()-1));
                    j++;
                    primaryTimesheets.get(j).setStartTimeForCurrentTimeAtAlarm(currentPrimaryTS.getEndTime());
                    currentTimesheets.remove(currentTimesheets.size()-1);
                    hasAddedNewTimeSheet = true;
                    
                }
                //Remove the primary time sheet we have calculated time for
                if(hasAddedNewTimeSheet){
                    primaryTimesheets.remove(j-1);
                    j--;
                }else{
                    primaryTimesheets.remove(j);
                    
                }
                j--;
                
            }else{//if the primary time sheet ended after the current time, then add minutes and move it to current time sheets
                
                long millis = tempEnd.getTime() - currentPrimaryTS.getStartTimeForCurrentTimeAtAlarm().getTime();
                int minutes = (int) (millis / (60*1000));
               
                currentPrimaryTS.addMinute(minutes);
                currentPrimaryTS.setStartTimeForCurrentTimeAtAlarm(currentTime);
                currentTimesheets.add(currentPrimaryTS);
                
                primaryTimesheets.remove(j);
                j--;
            }
            
        }
        
    }
    /**
     * Gets all timesheets that are conflicting with a given timesheet or conflicts with the conflicting timesheets and so on
     * @param ts the timesheet
     * @return a the conflicting timesheets
     * @throws SQLException if an error has occured executing the sql query
     */
    private ArrayList<Time_Sheet> getAllConflicts(Time_Sheet ts) throws SQLException{
        ArrayList<Time_Sheet> conflicts = new ArrayList();
        int oldConflictsSize = 0;
        conflicts.add(ts);
        for(int i = 0; i < conflicts.size(); i++){

            for(Time_Sheet tempTs : timeSheetAccess.getConflictingTimeSheets(conflicts.get(i))){
                boolean alreadyIncluded = false;
                
                for(Time_Sheet temporaryTs : conflicts){
                    if(tempTs.getId() == temporaryTs.getId())
                        alreadyIncluded = true;
                }
                if(!alreadyIncluded)
                    conflicts.add(tempTs);
            }
            
        }
        return conflicts;

    }
    
    
    /**
     * Sorts an area of a list of timesheets by their hours, according to the quick sort method
     * @param timesheets the list of timesheets
     * @param i from where in the list to begin sorting
     * @param j from where in the list to stop sorting
     */
    private void sortTimeSheetListByHours(ArrayList<Time_Sheet> timesheets, int i, int j){

         int idx = partition(timesheets, i, j);

         if(i<idx-1) {
             sortTimeSheetListByHours(timesheets, i, idx-1);
         }

         if(j>idx) {
             sortTimeSheetListByHours(timesheets, idx, j);
         }
    }
    
    /**
    * Partition logic
    *
    * @param timesheets list to be modified based on pivot
    * @param left lower bound of the arrayList
    * @param right upper bound of the arrayList
    * @return the partition index where timesheet hours to its left are less than it and
    * timesheet hours to its right are more than it
    */
    public int partition(ArrayList<Time_Sheet> timesheets, int left, int right) {
        Time_Sheet pivot = timesheets.get((left+right)/2);

        while(left <= right) {
            
            while(myCompare(timesheets.get(left), pivot) > 0)
                
                left++;
            
            while(myCompare(timesheets.get(right), pivot) < 0)
                right--;

            if(left <= right) {
                swap(timesheets, left, right);
                left++;
                right--;
            }
        }  
        return left;
    }
    
    /**
     * Compares 2 time sheets
     * @param ts1 the 1st timesheet
     * @param ts2 the 2nd timesheet
     * @return the difference in minutes between the 2 timesheets
     */
    private int myCompare(Time_Sheet ts1, Time_Sheet ts2){
        if(ts1.getMinutes() == ts2.getMinutes()){
            return ts1.getId() -ts2.getId();
        }else{
            return ts2.getMinutes()-ts1.getMinutes();
        }
        
    }
    
    /**
     * Swaps two timesheets in an ArrayList
     * @param timesheets the List
     * @param i index in the list of one of the elements
     * @param j index in the list of the other element
     */
    private void swap(ArrayList<Time_Sheet> timesheets, int i, int j){
        Time_Sheet saved = timesheets.get(i);
        timesheets.set(i, timesheets.get(j));
        timesheets.set(j, saved);
    }
    
}
