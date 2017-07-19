package code;

import java.awt.Graphics;

public abstract class GameObject {

		protected int x, y;
		protected int width, height;
		protected ID id;
		protected int velX, velY;
		
		protected boolean left;
		protected boolean right;
		protected boolean down;
		protected boolean up;
		protected boolean falling;
		protected boolean jumping;
		
		protected double moveSpeed;
		protected double fallSpeed;
		protected double maxFallSpeed;
		protected double jumpStart;
		protected double stopJumpSpeed;
		
		protected boolean facingRight;
		
		public GameObject(int x, int y, ID id) {
			this.x = x;
			this.y = y;
			this.id = id;
		}
		
		public abstract void tick();
		public abstract void render(Graphics g);
		
		public void setX(int x) {
			this.x=x;
		}
		
		public void setY(int y) {
			this.y = y;
		}
		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}
		
		public void setId(ID id) {
			this.id = id;
		}
		
		public ID getId() {
			return id;
		}
		
		public void setVelX(int velX) {
			this.velX = velX;
		}
		
		public void setVelY(int velY) {
			this.velY = velY;
		}
		
		public int getVelX() {
			return velX;
		}
		
		public int getVelY() {
			return velY;
		}
}
