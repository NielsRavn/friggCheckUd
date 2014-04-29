/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import BE.Fireman;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Susanne
 */
public class Fireman_Access extends DatabaseConnection {
    
    public Fireman_Access () throws IOException {
        super();
    }

    public Fireman getFiremanByID(int ID) throws SQLException {
        Connection con = null;
        Fireman fireman = null;
        
               try
       {
           con = getConnection();
           
           Statement query = con.createStatement();
           ResultSet result = query.executeQuery("SELECT * FROM Customer WHERE id = " + ID + ";");
           if(result.next())
           {
               int userId = result.getInt("id");
               String firstName = result.getString("firstName");
               String lastName = result.getString("lastName");
               boolean teamleader = result.getBoolean("teamleader");
               boolean driver = result.getBoolean("driver");
               
               
               fireman = new Fireman(userId, firstName, lastName, teamleader, driver);
               
           }
           
       }
       finally
       {
           if(con != null)
           {
               con.close();
           }
       }
        return fireman;
           
    }
       
    
}
