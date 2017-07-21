package code;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Bullet extends GameObject{
	
	BufferedImage img;
	private int x;
	private int y;
	private boolean right;
	private Handler handler;
	
	public Bullet(int x, int y, ID id, boolean right, Handler handler){
		super(x,y,id);
		this.x = x;
		this.y = y;		
		this.right = right;
		this.handler = handler;
		handler.addObject(this);
		try {
			img = ImageIO.read(getClass().getClassLoader().getResource("Resources/bullet.png"));
		}
		catch(Exception e){
			e.printStackTrace();
		}
		if(right) {velX = 20;} else velX = -20;
		
	}
	
	
	public void render(Graphics g) {
		
		if(right) {g.drawImage(img, x, y, 16, 8, null);}
		else {g.drawImage(img, x, y, -16, 8, null);}
		
	}

	public void tick() {
		
		x+=velX;
		
		
		if(x>=Application.WIDTH || x<0) { //if offscreen, destroy
			this.dead = true;
		}
		
		
	}
	
}
