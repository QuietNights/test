package code;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Board extends Canvas {
    
    public Board (int width, int height, String title, Application game) {
    	
    	JFrame frame = new JFrame(title);
    	
    	frame.setPreferredSize(new Dimension(width, height));
    	frame.setMaximumSize(new Dimension(width, height));
    	frame.setMinimumSize(new Dimension(width, height));
    	frame.requestFocus();
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setResizable(false);
    	frame.setLocationRelativeTo(null);
    	frame.add(game);
    	frame.setVisible(true);
    	game.start();
    	
	}
}