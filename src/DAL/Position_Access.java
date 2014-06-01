/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import BE.Alarm;
import BE.Position;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Brobak
 */
public class Position_Access extends DatabaseConnection{
    /**
     * Creates a new position access
     * @throws IOException if an I/O exception of some sort has occurred.
     */
    public Position_Access() throws IOException{
        super();
    }
    /**
     * gets all positions in the database
     * @return a list of positions
     * @throws SQLServerException if the connection cant be made.
     * @throws SQLException if an error has occured executing the sql query
     */
    public ArrayList<Position> getAllPositions() throws SQLServerException, SQLException{
        Connection con = null;
        ArrayList<Position> positions = new ArrayList<>();
        
        try{
            con = getConnection();
            Statement stmnt = con.createStatement();
            
            ResultSet rs = stmnt.executeQuery("SELECT * FROM Position;");
            
            while(rs.next()){
                Position p = new Position(rs.getInt("id"), rs.getString("name"));
                
                positions.add(p);
            }
        }finally{
            if(con != null) con.close();
        }
        
        return positions;
    }
    
    /**
     * Gets a position by a given id
     * @param id the id of the position you want
     * @return the position with the given id
     * @throws SQLServerException if the connection cant be made.
     * @throws SQLException if an error has occured executing the sql query
     */
    public Position getPositionById(int id) throws SQLServerException, SQLException{
        Connection con = null;
        Position position = null;
        
        try{
            con = getConnection();
            Statement stmnt = con.createStatement();
            
            ResultSet rs = stmnt.executeQuery("SELECT * FROM Position WHERE id = "+id+";");
            
            if(rs.next()){
                position = new Position(rs.getInt("id"), rs.getString("name"));
                
            }
        }finally{
            if(con != null) con.close();
        }
        
        return position;
    }
    
}
