package code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;



import java.io.File;
import java.io.IOException;

public class Player extends GameObject {
	
	private boolean left;
	private boolean right;
	private boolean down;
	private boolean up;
	private boolean falling;
	private boolean jumping;
	
	private int bullet;
	private int maxBullet;
	private boolean dead;
	private boolean invuln;
	private long invulnTime;
	
	private boolean firing;
	private int bulletDamage;
	
	KeyInput key;
	KeyEvent i;
	
	//private ArrayList<Bullet> bullets;
	

	File img = new File("Resources/lilgreenman.png");
	
	BufferedImage buffImg;
	
	public Player(int x, int y, ID id) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		
		width = 7;
		height = 12;
		
		moveSpeed = 0.5;
		fallSpeed = 0.1;
		maxFallSpeed = 4.0;
		jumpStart = -4.5;
		stopJumpSpeed = 0.3;
		
		facingRight = true;
		
		health = maxHealth = 5;
		bullet = maxBullet = 10;
		//bullets = new ArrayList<Bullet>();

		try {
			buffImg = ImageIO.read(img);
		} catch (IOException e) {
			
		}

	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		
		g.drawImage(buffImg, x, y, null);
	}
}
