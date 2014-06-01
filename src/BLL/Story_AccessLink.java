/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;

import BE.Story;
import DAL.Story_Access;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author Niels Kristian Ravn
 */
public class Story_AccessLink {
    
    Story_Access sa;
    /**
     * Creates a new story access link
     * @throws IOException 
     */
    public Story_AccessLink() throws IOException{
        sa = new Story_Access();
    }
    
    /**
     * Saves a new story
     * @param story the story you want to save
     * @throws SQLException 
     */
    public void saveStory(Story story) throws SQLException{
        sa.saveStory(story);
    }
}
