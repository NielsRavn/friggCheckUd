/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BE;

/**
 *
 * @author Brobak
 */
public class Comment implements IViewObjectBE{
    String comment;
    /**
     * Creates a new Comment
     * @param comment the comment
     */
    public Comment(String comment){
        this.comment = comment;
    }
    
    
    /**
     * Gets the comment
     * @return returns the comment
     */
    public String getComment() {
        return comment;
    }

   
    @Override
    public String getName() {
        return "Kommentar: " + comment;
    }
    
    
    
}
