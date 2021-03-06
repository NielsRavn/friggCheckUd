/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation.Components.ViewObjects;

import BE.Comment;
import Presentation.MyConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author Brobak
 */
public class ViewObjectComment extends ViewObject{
    JButton btnExpandComment;
    JTextArea taComment;
    JLabel lblText;
    /**
     * Creates a new viewObjectComment
     * @param comment the comment you want represented in the viewObject
     */
    protected ViewObjectComment(Comment comment){
        super(comment);
        fillData();
    }
    /**
     * expands the size of the viewObject to fit for a textarea for a comment
     */
    private void expandCommentField(){
        remove(btnExpandComment);
        add(lblText, BorderLayout.NORTH);
        add(taComment, BorderLayout.CENTER);
        setMaximumSize(new Dimension((int)getMaximumSize().getWidth(), (int) getPreferredSize().getHeight()));
        validate();
        repaint();
        super.notifyObservers();
    }
   
    /**
     * Fills the viewObject with nessesary data
     */
    protected void fillData(){
        setLayout(new BorderLayout());
        btnExpandComment = new JButton("<html><body marginwidth=30 marginheight=20>Tilføj kommentar</body></html>");
        btnExpandComment.setBackground(MyConstants.COLOR_BLUE);
        btnExpandComment.setFont(MyConstants.FONT_BUTTON_FONT);
        btnExpandComment.setForeground(Color.WHITE);
        btnExpandComment.addActionListener(new MyActionListener());
        taComment = new JTextArea();
        taComment.setPreferredSize(new Dimension(0, 150));
        taComment.setFont(MyConstants.FONT_HEADER_TEXT);
        
        lblText = new JLabel("<html><body marginwidth=30 marginheight=20>Kommentar</body></html>");
        lblText.setHorizontalAlignment(JLabel.CENTER);
        lblText.setOpaque(true);
        lblText.setBackground(MyConstants.COLOR_BLUE);
        lblText.setFont(MyConstants.FONT_BUTTON_FONT);
        lblText.setForeground(Color.WHITE);
        
        add(btnExpandComment);
        validate();
        repaint();
    }
    
    /**
     * Gets the comment represented in the view object
     * @return the comment represented in the view object
     */
    public String getComment(){
        return taComment.getText();
    }

    /**
     * Does nothing
     */
    @Override
    public void refreshViewObject() {
        
    }
    
    private class MyActionListener implements ActionListener{
        
        /**
         * expands the viewObject if the comment button is pushed
         * @param e the actionevent that occured
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == btnExpandComment){
                expandCommentField();
            }
        }
        
    }
    
}
