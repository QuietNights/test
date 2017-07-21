package code;

import java.awt.Color;
import java.awt.Graphics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import Audio.AudioPlayer;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Player extends GameObject {
	
	private boolean left = false;
	private boolean right = false;
	private boolean jumping;
	private boolean onGround = true;
	private boolean idle;
	private boolean facingRight;
	private byte deathFontSize;
	
	private int bullets;
	private int maxBullets;
	private boolean invuln;
	private int invulnTimer;
	private Bullet bullet;
	private int shootTimer;
	private boolean shooting = false;
	
	private HashMap<String, AudioPlayer> sfx;
	
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
		
		sfx = new HashMap<String, AudioPlayer>();
		sfx.put("hurt", new AudioPlayer("/Resources/hurt.wav"));
		sfx.put("shoot", new AudioPlayer("/Resources/shoot.wav"));
		sfx.get("shoot").volume.setValue(-10.0f);
		
		shootTimer = 30;
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

		try {
			buffImg = ImageIO.read(getClass().getClassLoader().getResource("Resources/lilgreenman.png"));
			heartImg = ImageIO.read(getClass().getClassLoader().getResource("Resources/heart.png"));
			bulletImg = ImageIO.read(getClass().getClassLoader().getResource("Resources/bulletupright.png"));
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
		}
		else {
			y = 335;
			onGround = true;
			if(!jumping) {velY = 0;}
		}
		if(!onGround) { //if in the air, apply gravity
			if(velY < maxFallSpeed) {
				velY += fallSpeed;
			}
			else {
				velY = maxFallSpeed;
			}
		}
		if((!right && !left && !jumping && onGround) || onGround && !jumping && (right && left)) {idle=true;} else {idle = false;} //no input, idle
		
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
		
		if(shooting) {
			if(shootTimer > 0) {
				shootTimer--;
			} else {
				shootTimer = 30;
				shooting = false;
			}
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
			
			Font f = new Font("Times New Roman", Font.BOLD, 18);
			g.setFont(f);
			g.setColor(Color.red);
			g.drawString("ur ded kiddo", (int) ((System.currentTimeMillis() / 5) % 640),(int)(220 + 10 * Math.sin(System.currentTimeMillis() / 100)));
			System.out.println((int)(220 + 20 * Math.sin((System.currentTimeMillis() % 1000) / 30)));
			
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
			invuln = true;
			health--;
			sfx.get("hurt").playSFX();
		}
		if(health == 0) {
			dead = true;
			System.out.println("play dead music");
			Application.music.get("bgMusic").close();
			Application.music.get("deadMusic").playMusic();
		}
		
	}

	public void shoot() {
		if(!shooting && bullets >= 1) {
			shooting = true;
			bullet = new Bullet(x + width / 2, y + height / 2, ID.Bullet, facingRight, handler);
			bullets--;
			sfx.get("shoot").playSFX();
		}
		
	}
	
	public void reloadBullets() {
		bullets = 0;
	}
	

}
