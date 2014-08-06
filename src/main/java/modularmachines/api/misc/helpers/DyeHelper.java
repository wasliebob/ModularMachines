/**
* This file is created by wasliebob.
* Created on: 3 aug. 2014, at 14:56:22
* This file is part of Modular Machines (created by wasliebob)
*/
package modularmachines.api.misc.helpers;

import java.awt.Color;

public class DyeHelper {
	static int black = new Color(50, 50, 50).getRGB();
	static int red = new Color(255, 0, 0).getRGB();
	static int green = new Color(0, 255, 20).getRGB();
    static int brown = new Color(139, 69, 19).getRGB();
    static int blue = new Color(0, 0, 255).getRGB();
    static int purple = new Color(148, 0, 211).getRGB();
    static int cyan = Color.cyan.getRGB();
    static int lgray = Color.lightGray.getRGB();
    static int gray = Color.gray.getRGB();
    static int pink = new Color(255, 105, 180).getRGB();
    static int lime = new Color(124, 252, 0).getRGB();
    static int yellow = Color.yellow.getRGB();
    static int lblue = new Color(127, 255, 212).getRGB();
    static int magenta = Color.magenta.getRGB();
    static int orange = Color.orange.getRGB();
    static int white = Color.white.getRGB();
	    
    public static String BLACK = "Black";
    public static String RED = "Red";
    public static String GREEN = "Green";
    public static String BROWN = "Brown";
    public static String BLUE = "Blue";
    public static String PURPLE = "Purple";
    public static String CYAN = "Cyan";
    public static String LGRAY = "Light Gray";
    public static String GRAY = "Gray";
    public static String PINK = "Pink";
    public static String LIME = "Lime";
    public static String YELLOW = "Yellow";
    public static String LBLUE = "Light Blue";
    public static String MAGENTA = "Magenta";
    public static String ORANGE = "Orange";
    public static String WHITE = "White";
	    
    public static int getColorCode(int value){
    	switch(value){
    	case 0: return black;
    	case 1: return red;
    	case 2: return green;
    	case 3: return brown;
    	case 4: return blue;
    	case 5: return purple;
    	case 6: return cyan;
    	case 7: return lgray;
    	case 8: return gray;
    	case 9: return pink;
    	case 10: return lime;
    	case 11: return yellow;
    	case 12: return lblue;
    	case 13: return magenta;
    	case 14: return orange;
    	case 15: return white;
    	default: return black;
    	}
	}
    
    public static String getColorName(int value){
    	switch(value){
    	case 0: return BLACK;
    	case 1: return RED;
    	case 2: return GREEN;
    	case 3: return BROWN;
    	case 4: return BLUE;
    	case 5: return PURPLE;
    	case 6: return CYAN;
    	case 7: return LGRAY;
    	case 8: return GRAY;
    	case 9: return PINK;
    	case 10: return LIME;
    	case 11: return YELLOW;
    	case 12: return LBLUE;
    	case 13: return MAGENTA;
    	case 14: return ORANGE;
    	case 15: return WHITE;
    	default: return BLACK;
    	}
	}
    
    public static int getID(String name){
    	if(name == BLACK)
    		return 0;
    	else if(name == RED)
    		return 1;
    	else if(name == GREEN)
    		return 2;
    	else if(name == BROWN)
    		return 3;
    	else if(name == BLUE)
    		return 4;
    	else if(name == PURPLE)
    		return 5;
    	else if(name == CYAN)
    		return 6;
    	else if(name == LGRAY)
    		return 7;
    	else if(name == GRAY)
    		return 8;
    	else if(name == PINK)
    		return 9;
    	else if(name == LIME)
    		return 10;
    	else if(name == YELLOW)
    		return 11;
    	else if(name == LBLUE)
    		return 12;
    	else if(name == MAGENTA)
    		return 13;
    	else if(name == ORANGE)
    		return 14;
    	else if(name == WHITE)
    		return 15;
    	else
    		return 0;
    }
}