package code;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	
	private Handler handler;
	private Player player;
	
	public KeyInput(Handler handler, Player player) {
		this.handler = handler;
		this.player = player;
	}
	
	public void keyPressed(KeyEvent e){
		
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				
				switch(key) {
				
				case KeyEvent.VK_A: player.setLeft(true);
				break;
			
				case KeyEvent.VK_D: player.setRight(true);
				break;
			
				case KeyEvent.VK_W: player.setJumping(true);
				break;
				
				case KeyEvent.VK_P: player.takeDamage();
				break;
				
				case KeyEvent.VK_SPACE: player.shoot();
				break;
				
				case KeyEvent.VK_R: player.reloadBullets();
				break;
			
				default:
				
				}
				
			}
			
		}
		
		
		
	}
	
	public void keyReleased(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		switch(key) {
		
			case KeyEvent.VK_A: player.setLeft(false);
			break;
			
			case KeyEvent.VK_D: player.setRight(false);
			break;
			
			case KeyEvent.VK_W: player.setJumping(false);
			break;
			
			default:
		}
		
	}
	
}

