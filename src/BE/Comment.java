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
    Fireman fireman;
    int id;
    /**
     * Creates a new Comment
     * @param id the id of the comment
     * @param fireman the fireman who made the comment
     * @param comment the comment
     */
    public Comment(int id, Fireman fireman,String comment){
        this.id = id;
        this.fireman = fireman;
        this.comment = comment;
    }
    /**
     * reates a new Comment
     * @param fireman the fireman who made the comment
     * @param comment the comment
     */
    public Comment(Fireman fireman,String comment){
        this.fireman = fireman;
        this.comment = comment;
    }
    
    /**
     * Sets the id of the comment
     * @param id the id you want to give the comment
     */
    public void setId(int id){
        this.id = id;
    }
    /**
     * Gets the comment
     * @return returns the comment
     */
    public String getComment() {
        return comment;
    }

    public Fireman getFireman() {
        return fireman;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return "Kommentar: " + comment;
    }
    
    
    
}
