/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;

import BE.Car;
import DAL.Car_Access;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Brobak
 */
public class Car_AccessLink {
    Car_Access ca;
    
    public Car_AccessLink() throws IOException{
        ca = new Car_Access();
    }
    
    /**
     * Gets all the cars
     * @return an arraylist with all the cars
     * @throws SQLException 
     */
    public ArrayList<Car> getAllCars() throws SQLException{
        return ca.getAllCars();
    }
}
