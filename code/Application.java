package code;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D; 
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;


public class Application extends Canvas implements Runnable { //Extends canvas for the drawing and implements Runnable which automatically calls start() when everything is loaded
    
	private static final long serialVersionUID = 1L; //Unsure what this means

	private Thread thread; //Unsure
	private boolean running = false; //Game state starts as off until start() is ran
	
	private Handler handler;
	
	public Application() {
		new Board(320, 240, "Game", this); // width, height, title, this - This is this game so that you can assign the board to it
		
		handler = new Handler();
		// Allows creation of objects -> Refer to Handler.java for all functions
		
		handler.addObject(new Player(50,50, ID.Player));
		// Adds an instance of an player class with a width, height and ID
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
		double ns = 1000000000;
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
		g.fillRect(0, 0, 320, 240);
		
		handler.render(g);
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String args[]) {
		new Application();
	}
}