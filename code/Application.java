package code;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import java.awt.image.BufferStrategy;


public class Application extends Canvas implements Runnable {
    
	private static final long serialVersionUID = 1L;

	private Thread thread;
	private boolean running = false;
	
	private Player player;
	private Handler handler;
	
	public Application() {
		
		new Board(320, 240, "Game", this);
		handler = new Handler();
		this.addKeyListener(new KeyInput());
		player = new Player(50,50,ID.Player);
		handler.addObject(player);
				
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
		//s
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