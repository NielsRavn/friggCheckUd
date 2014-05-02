/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Presentation;

import BE.Car;
import BE.Position;
import BE.Station;
import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author Niels
 */
public class MyConstants {
    
    //all these constants are made so that they are the same through out the program
    // and if we want a different shade or text size we can change it in one place
    // although it is not fully implemented.
    
    public static final Color COLOR_BLUE = new Color(0, 51, 66),
            COLOR_RED = new Color(106, 0, 0),
            COLOR_GREEN = new Color(0, 85, 0),
            COLOR_LIGHT_BLUE = new Color(180, 230, 255);
    public static final Font FONT_BUTTON_FONT = new Font("Verdana", Font.PLAIN, 18);
    
    public static final Position DRIVER = new Position(2, "Chauffør"), TEAM_LEADER = new Position(1, "Holdleder"), FIREMAN = new Position(3, "Brandmand"), STATION_DUTY = new Position(4,"Stationsvagt");
    public static final Station STATION_DUTY_VIEW = new Station("Stations vagt", "res/Esbjerg_st.jpg");
}
