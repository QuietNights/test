package code;

import java.awt.Canvas;
import Audio.AudioPlayer;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;

import java.awt.event.KeyEvent;

import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;


public class Application extends Canvas implements Runnable { //Extends canvas for the drawing and implements Runnable which automatically calls start() when everything is loaded
    
	private static final long serialVersionUID = 1L; //its some random shit means nothing but throws up warning without it
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
	private Thread thread; //Unsure
	private boolean running = false; //Game state starts as off until start() is ran
	
	private Player player;
	private Handler handler;
	private AudioPlayer bgMusic;
	
	BufferedImage bgBuffImg;
	

	public Application () {
		
		try {
			bgBuffImg = ImageIO.read(new File("Resources/background.png"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		handler = new Handler();
		new Board(WIDTH, HEIGHT, "Game", this); // width, height, title, this - This is this game so that you can assign the board to it
		player = new Player(WIDTH / 2, 300, ID.Player);
		handler.addObject(player);
		bgMusic = new AudioPlayer("/Resources/music.wav");
		bgMusic.volume.setValue(-20.0f);
		bgMusic.play();
		//System.out.println("music = " + bgMusic);
		
		this.addKeyListener(new KeyInput(handler, player));
		// Allows creation of objects -> Refer to Handler.java for all function

	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
		//
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running)
				render();
				frames++;
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);;
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		handler.tick();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		/*
		 * Background & Color
		 */
		
		g.setColor(Color.black);
		g.drawImage(bgBuffImg, 0, 0, WIDTH, HEIGHT, null);;
		
		handler.render(g);
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String args[]) {
		new Application();
	}
	
	
}