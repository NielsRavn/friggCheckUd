/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;

import BE.Position;
import DAL.Position_Access;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Brobak
 */
public class Position_AccessLink {
    Position_Access pa;
    /**
     * Creates a new position access link
     * @throws IOException 
     */
    public Position_AccessLink() throws IOException {
        pa = new Position_Access();
    }
    
    /**
     * Gets all positions from the database
     * @return a list of all positions
     * @throws SQLException 
     */
    public ArrayList<Position> getAllPositions() throws SQLException{
        return pa.getAllPositions();
    }
    
    
    
}
