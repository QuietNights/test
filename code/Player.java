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
	private boolean facingRight;
	
	private int bullets;
	private int maxBullets;
	private boolean invuln;
	private int invulnTimer;
	private Bullet bullet;
	
	private int reloadTime;
	private int bulletDamage;
	KeyInput key;
	//KeyEvent i;
	
	//private ArrayList<Bullet> bullets;
	
	private Handler handler;
	BufferedImage heartImg;	
	BufferedImage buffImg;
	BufferedImage bulletImg;
	
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		// TODO Auto-generated constructor stub
		
		width = 14;
		height = 24;
		
		this.handler = handler;
		
		moveSpeed = 5;
		fallSpeed = 1;
		maxFallSpeed = 10;
		jumpStart = -12;
		invuln = false;
		invulnTimer = 60;
		reloadTime = 90;
		
		facingRight = true;
		
		health = maxHealth = 5;
		bullets = maxBullets = 10;
		//bullets = new ArrayList<Bullet>();

		try {
			buffImg = ImageIO.read(new File("Resources/lilgreenman.png"));
			heartImg = ImageIO.read(new File("Resources/heart.png"));
			bulletImg = ImageIO.read(new File("Resources/bulletupright.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void tick() {
		
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
		
		if(y < 335) { //check if in air, make falling true
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
		if((!right && !left && !jumping && !falling) || !falling && !jumping && (right && left)) {idle=true;} else {idle = false;} //no input, idle
		
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
		
		if(bullets == 0) {
			if(reloadTime > 0) {
				reloadTime--;
			}
			else {
				bullets = maxBullets;
				reloadTime = 90;
			}
		}
		
		if(health <= 0) {
			dead = true;
		}

	}
	
	public void render(Graphics g) {
		
		for(int i = 0; i < health; i++) { 
			g.drawImage(heartImg, (i+1)*32, 32, 32, 32, null);//draws hearts according to hp
		}
		
		if(!dead) {
			for(int i = 0; i < bullets; i++) {
			g.drawImage(bulletImg, (i+2)*16 + 4, 64, 8, 16, null);//draws bullets according to ammo
			}
		}		
		
		if(dead) {
			g.setColor(Color.red);
			g.drawString("ur ded kiddo", Application.WIDTH / 2, Application.HEIGHT / 2);
			return;
		}
		if(invuln) {
			if(invulnTimer / 2 % 2 == 0) { //if player recently hit, dont draw the player half the time - flickers player
				return;
			}
		}
		
		g.drawImage(buffImg, x, y, 14, 24, null); //draws player
		
		
		
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

	public void shoot() {
		if(bullets>0) {
			bullet = new Bullet(x + width / 2, y + height / 2, ID.Bullet, facingRight, handler);
			bullets--;
		}	
	}
	
	public void reloadBullets() {
		bullets = 0;
	}
	

}
