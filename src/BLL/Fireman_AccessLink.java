/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;

import BE.Fireman;
import DAL.Fireman_Access;
import java.sql.SQLException;

/**
 *
 * @author Susanne
 */
public class Fireman_AccessLink {
    
    Fireman_Access fa;

    
    public Fireman getFiremanByID (int ID) throws SQLException {
        return fa.getFiremanByID(ID);
    }
}
