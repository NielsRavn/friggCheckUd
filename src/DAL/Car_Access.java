/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import BE.Car;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Brobak
 */
public class Car_Access extends DatabaseConnection{
    
    public Car_Access() throws IOException{
        super();
    }
    
    /**
     * Gets all the cars
     * @return an arraylist with all the cars
     * @throws SQLException 
     */
    public ArrayList<Car> getAllCars() throws SQLServerException, SQLException{
        Connection con = null;
        ArrayList<Car> cars = new ArrayList<>();
        
        try{
            con = getConnection();
            Statement stmnt = con.createStatement();
            
            ResultSet rs = stmnt.executeQuery("SELECT * FROM Car;");
            
            while(rs.next()){
                Car c = new Car(rs.getInt("carNr"), rs.getString("iconPath"), rs.getString("name"), rs.getInt("seats"));
                
                cars.add(c);
            }
        }finally{
            if(con != null) con.close();
        }
        
        return cars;
    }
    
}
