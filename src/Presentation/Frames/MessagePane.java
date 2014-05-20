/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Frames;

import BE.Message;
import BLL.Message_AccessLink;
import Presentation.MyConstants;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JViewport;

/**
 *
 * @author Niels Kristian Ravn
 */
public class MessagePane extends javax.swing.JPanel {

    private final int PREFERED_HEIGHT = 100;

    ArrayList<Message> messages;
    Message_AccessLink mal;
    int currentMessageIndex;
    boolean holding;

    /**
     * Creates new form MessagePane
     */
    public MessagePane() {
        currentMessageIndex = 0;
        holding = false;
        initComponents();
        setBackground(MyConstants.COLOR_BLUE);
        messages = new ArrayList<>();
        try {
            mal = new Message_AccessLink();
        } catch (IOException ex) {
        }
        setPreferredSize(new Dimension(0, PREFERED_HEIGHT));
        try {
            messages = mal.getAllMessageToBeShown();
        } catch (SQLException ex) {
        }
        fillData();
        scheduleUpdateOfMessageList();
        repaint();

    }

    private void fillData() {
        lblMessage.setFont(MyConstants.FONT_HEADER_TEXT);
        lblMessage.setForeground(Color.WHITE);
        lblMessage.setBackground(MyConstants.COLOR_BLUE);
        jPanel1.setBackground(MyConstants.COLOR_BLUE);
        MyMouseListener mml = new MyMouseListener();
        addMouseListener(mml);
        addMouseMotionListener(mml);
        jPanel1.addMouseListener(mml);
        jPanel1.addMouseMotionListener(mml);
        setText();
    }

    private void scheduleUpdateOfMessageList() {
        MyTimedUpdateTextTask updateTask = new MyTimedUpdateTextTask();
        Timer t = new Timer();
        t.schedule(updateTask, 5000, 5000);
        MyTimedUpdateArrayListTask updateArrayListTask = new MyTimedUpdateArrayListTask();
        Timer t2 = new Timer();
        t2.schedule(updateArrayListTask, 5*60000, 5*60000);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblMessage = new javax.swing.JLabel();

        setLayout(new java.awt.GridBagLayout());

        jPanel1.add(lblMessage);

        add(jPanel1, new java.awt.GridBagConstraints());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblMessage;
    // End of variables declaration//GEN-END:variables

    private void setText() {
        if (messages.size() > 0) {
            lblMessage.setText(messages.get(currentMessageIndex).getMessage());
        }
        repaint();
    }

    private class MyTimedUpdateTextTask extends TimerTask {

        @Override
        public void run() {
            if(!holding){
                currentMessageIndex++;
                if(currentMessageIndex > messages.size()-1) currentMessageIndex = 0;
            }
            setText();
        }
    }
    
    private class MyTimedUpdateArrayListTask extends TimerTask {

        @Override
        public void run() {
            try {
                messages = mal.getAllMessageToBeShown();
            } catch (SQLException ex) { ex.printStackTrace();
            }
        }
    }
    
    private class MyMouseListener extends MouseAdapter{

        Point lastDragPoint = new Point();
        boolean beingDragged, hasSwitched;
        
        @Override
        public void mouseReleased(MouseEvent e) {
            holding = false;
            hasSwitched = false;
            if (beingDragged) beingDragged = false;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            holding = true;
        }
        
        @Override
        public void mouseDragged(MouseEvent e) {

            if (!beingDragged) {
                beingDragged = true;
                setLastDragPoint(e.getX(), (e.getY()+ ((JComponent)e.getSource()).getY()));
            }
            handleDraging(e);
        }

        private void setLastDragPoint(int x, int y) {
            lastDragPoint.x = x;
            lastDragPoint.y = y;
        }

        private void handleDraging(MouseEvent e) {
            int dx = e.getX()+ - lastDragPoint.y;
            if(dx > 100 && !hasSwitched){
                currentMessageIndex++;
                if(currentMessageIndex > messages.size()-1) currentMessageIndex = 0;
                hasSwitched = true;
            }
            else if(dx < -100 && !hasSwitched){
                currentMessageIndex--;
                if(currentMessageIndex < 0) currentMessageIndex = messages.size()-1;
                hasSwitched = true;
            }
            setText();
            
        }
        
    }

}
