package code;

import java.awt.Color;
import java.awt.Graphics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;



import java.io.File;
import java.io.IOException;

public class Player extends GameObject {
	
	private boolean left = false;
	private boolean right = false;
	private boolean falling;
	private boolean jumping;
	private boolean onGround = true;
	private boolean idle;
	
	private int bullet;
	private int maxBullet;
	private boolean dead;
	private boolean invuln;
	private long invulnTime;
	
	private boolean firing;
	private int bulletDamage;
	
	KeyInput key;
	//KeyEvent i;
	
	//private ArrayList<Bullet> bullets;
	

	File img = new File("Resources/lilgreenman.png");
	
	BufferedImage buffImg;
	
	public Player(int x, int y, ID id) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		
		width = 14;
		height = 24;
		
		moveSpeed = 5;
		fallSpeed = 1;
		maxFallSpeed = 10;
		jumpStart = -12;
		
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
		
		x += velX;
		y += velY;
		System.out.println(Application.WIDTH);
		//System.out.println(idle);
		//System.out.println(falling);
		//System.out.println(jumping && onGround);
		if(right && left) {velX = 0;}
		
		else if(right && x<=(Application.WIDTH-22)) {
			velX = moveSpeed;
		} 
		else if(left && x>=4) {
			velX = (-1) * moveSpeed;
		} 
		else {velX = 0;}
		
		if(jumping && onGround) {
			velY = jumpStart;
		}
		
		if(y < 335) {
			onGround = false;
			falling = true;
		}
		else {
			y = 335;
			onGround = true;
			if(!jumping) {velY = 0;}
		}
		if(falling && !onGround) {
			if(velY < maxFallSpeed) {
				velY += fallSpeed;
			}
			else {
				velY = maxFallSpeed;
			}
		}
		if(onGround) {falling = false;}
		if(!right && !left && !jumping && !falling) {idle=true;} else {idle = false;}

	}
	
	public void render(Graphics g) {
		g.drawImage(buffImg, x, y, 14, 24, null);
	}

	
	public void setLeft(boolean b) {left = b;}
	public void setRight(boolean b) {right = b;}
	public void setJumping(boolean b) {jumping = b;}
	
	

	
	

}
