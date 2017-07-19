package code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends GameObject {

	File img = new File("Resources/lilgreenman.png");
	
	BufferedImage buffImg;
	
	public Player(int x, int y, ID id) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		try {
			buffImg = ImageIO.read(img);
		} catch (IOException e) {
			
		}
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(buffImg, 50, 50, null);
	}
	
}
