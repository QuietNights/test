package enemies;

public class large_monster {
	int health = 300;
	int damage = 3;
	int sizeX = 1;
	int sizeY = 1;
	int[][] size = {{sizeX, sizeY}};
	
	monster large = new monster(size, health, damage);
	
}
