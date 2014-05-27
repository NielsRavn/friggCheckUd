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
    
    public Story_AccessLink() throws IOException{
        sa = new Story_Access();
    }
    
    public void saveStory(Story story) throws SQLException{
        sa.saveStory(story);
    }
}
