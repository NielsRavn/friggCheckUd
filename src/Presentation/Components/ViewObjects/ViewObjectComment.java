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
    protected ViewObjectComment(Comment comment){
        super(comment);

    }
    
    private void expandCommentField(){
        remove(btnExpandComment);
        add(lblText, BorderLayout.NORTH);
        add(taComment, BorderLayout.CENTER);
        setMaximumSize(new Dimension((int)getMaximumSize().getWidth(), (int) getPreferredSize().getHeight()));
        validate();
        repaint();
    }
    
    private void hideCommentField(){
        remove(taComment);
        remove(lblText);
        add(btnExpandComment, BorderLayout.CENTER);
        validate();
        repaint();
    }
    
    @Override
    protected void fillData(){
        setLayout(new BorderLayout());
        btnExpandComment = new JButton("<html><body marginwidth=30 marginheight=20>Add comment</body></html>");
        btnExpandComment.setBackground(MyConstants.COLOR_BLUE);
        btnExpandComment.setFont(MyConstants.FONT_BUTTON_FONT);
        btnExpandComment.setForeground(Color.WHITE);
        btnExpandComment.addActionListener(new MyActionListener());
        taComment = new JTextArea();
        taComment.setPreferredSize(new Dimension(0, 150));
        taComment.setFont(MyConstants.FONT_HEADER_TEXT);
        
        lblText = new JLabel("<html><body marginwidth=30 marginheight=20>Comment</body></html>");
        lblText.setHorizontalAlignment(JLabel.CENTER);
        lblText.setOpaque(true);
        lblText.setBackground(MyConstants.COLOR_BLUE);
        lblText.setFont(MyConstants.FONT_BUTTON_FONT);
        lblText.setForeground(Color.WHITE);
        
        add(btnExpandComment);
        validate();
        repaint();
    }
    
    public String getComment(){
        return taComment.getText();
    }

    @Override
    public void refreshViewObject() {
        
    }
    
    private class MyActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == btnExpandComment){
                expandCommentField();
            }
        }
        
    }
    
}
