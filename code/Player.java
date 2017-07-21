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
	private boolean invuln;
	private int invulnTimer;
	
	private boolean firing;
	private int bulletDamage;
	
	KeyInput key;
	//KeyEvent i;
	
	//private ArrayList<Bullet> bullets;
	

	BufferedImage heartImg;	
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
		invuln = false;
		invulnTimer = 60;
		
		facingRight = true;
		
		health = maxHealth = 5;
		bullet = maxBullet = 10;
		//bullets = new ArrayList<Bullet>();

		try {
			buffImg = ImageIO.read(new File("Resources/lilgreenman.png"));
			heartImg = ImageIO.read(new File("Resources/heart.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void tick() {
		
		//System.out.println(invuln);
		x += velX;
		y += velY;
		
		if(right && left) {velX = 0;} //if pressing left and right, don't move
		
		else if(right && x<=(Application.WIDTH-22)) { //if pressing right and not at right edge, go right
			facingRight = true;
			velX = moveSpeed;
		} 
		else if(left && x>=4) { //if pressing left and not at left edge, go left
			facingRight = false;
			velX = (-1) * moveSpeed;
		} 
		else {velX = 0;} //not pressing left or right, stand still
		
		if(jumping && onGround) { //if jump button pressed and character is on the ground, jump
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
		if(falling && !onGround) { //if in the air, apply gravity
			if(velY < maxFallSpeed) {
				velY += fallSpeed;
			}
			else {
				velY = maxFallSpeed;
			}
		}
		if(onGround) {falling = false;}
		if(!right && !left && !jumping && !falling) {idle=true;} else {idle = false;} //no input, idle
		
		if(invuln) {
			if (invulnTimer > 0) {
				invulnTimer--;
				//System.out.println(invulnTimer);
			}
			else {
				invuln = false;
				invulnTimer = 60;
			}
		}
		if(health <= 0) {
			dead = true;
		}

	}
	
	public void render(Graphics g) {
		
		for(int i = 0; i < health; i++) {
			g.drawImage(heartImg, (i+1)*48, 48, 32, 32, null);
		}
		if(dead) {
			g.setColor(Color.red);
			g.drawString("ur ded kiddo", Application.WIDTH / 2, Application.HEIGHT / 2);
			return;
		}
		if(invuln) {
			if(invulnTimer / 2 % 2 == 0) {
				return;
			}
		}
		g.drawImage(buffImg, x, y, 14, 24, null);
		
		
	}

	
	public void setLeft(boolean b) {left = b;}
	public void setRight(boolean b) {right = b;}
	public void setJumping(boolean b) {jumping = b;}
	
	public void takeDamage() {
		if(!invuln && health >= 1) {
			health--;
			invuln = true;
		}		
	}

	
	

}
