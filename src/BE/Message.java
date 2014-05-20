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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isShown() {
        return shown;
    }

    public void setShown(boolean shown) {
        this.shown = shown;
    }
    
    
}
