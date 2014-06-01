/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;

import BE.Fireman;
import DAL.Fireman_Access;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author Susanne
 */
public class Fireman_AccessLink {
    
    Fireman_Access fa;
    
    /**
     * Creates a new fireman access link
     * @throws IOException if an I/O exception of some sort has occurred. 
     */
    public Fireman_AccessLink() throws IOException{
        fa = new Fireman_Access();
    }

    /**
     * Gets a fireman with a given id
     * @param ID the id of the fireman you want to get
     * @return the fireman who has the given id
     * @throws SQLException if an error has occured executing the sql query
     */
    public Fireman getFiremanByID (int ID) throws SQLException {
        return fa.getFiremanByID(ID);
    }
}
