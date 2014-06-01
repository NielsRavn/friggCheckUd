/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BE;

/**
 *
 * @author Niels Kristian Ravn
 */
public class Message {
    
    private int id;
    private String message;
    private boolean shown;

    public Message(int id, String message, boolean shown) {
        this.id = id;
        this.message = message;
        this.shown = shown;
    }

    public Message(String message, boolean shown) {
        this.message = message;
        this.shown = shown;
    }

    /**
     * @return the id 
     */
    public int getId() {
        return id;
    }
    
    /**
     * Sets a new id
     * @param id the new Id
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * sets a new message
     * @param message the new message
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
    /**
     * @return if the message has been shown
     */
    public boolean isShown() {
        return shown;
    }

    /**
     * Sets if shown to a new status
     * @param shown the new shown status
     */
    public void setShown(boolean shown) {
        this.shown = shown;
    }
    
    
}
