package enemies;

public class monster {
	
	//Integers
	
	int[][] size; //[x][y]
	int health;
	int damage;
	int movementSpeed;
	
	//Floats
	
	float attackSpeed;
	
	public monster(int[][] tempSize, int tempHealth, int tempDamage) {
		size = tempSize;
		health = tempHealth;
		damage = tempDamage;
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public int getmovementSpeed() {
		return movementSpeed;
	}
	
	public int[][] size() {
		return size;
		
	}
		
}
