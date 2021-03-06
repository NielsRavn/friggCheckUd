/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAL;

import BE.Story;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Niels Kristian Ravn
 */
public class Story_Access extends DatabaseConnection{
    /**
     * Creates a new story access
     * @throws FileNotFoundException if the config file could not be found
     * @throws IOException if an I/O exception of some sort has occurred.
     */
    public Story_Access() throws FileNotFoundException, IOException {
    }

    /**
     * Adds a story to the database
     * @param story the story you want to add to the database
     * @throws SQLServerException if the connection cant be made.
     * @throws SQLException if an error has occured executing the sql query
     */
    public void saveStory(Story story) throws SQLServerException, SQLException {
        Connection con = null;
        
        try{
            con = getConnection();
            Statement stmnt = con.createStatement();
            
            int affectedRows = stmnt.executeUpdate("INSERT INTO Story (alarmId, evaNr, "
                    + "brandrapportNr, navne, addresser, type, gruppeNr, detektorNr, comment) "
                    + "VALUES ("
                    + story.getAlarmId() + ", "
                    + story.getEvaNr() + ", "
                    + story.getBrandrapportNr()+ ", '"
                    + story.getNavne()+ "', '"
                    + story.getAddresser()+ "', "
                    + story.getType()+ ", "
                    + story.getGruppeNr()+ ", "
                    + story.getDetektorNr()+ ", '"
                    + story.getComment()+ "'); ");
            
            if(affectedRows == 0) throw new SQLException("input failed no affected rows");
            
        }finally{
            if(con != null) con.close();
        }
    }
    
    
    
}
