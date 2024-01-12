package a10;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class TankPlant extends Actor {
	
	
	
	public TankPlant(Point2D.Double startingPosition, Point2D.Double initHitbox, BufferedImage img, int health, int coolDown, int attackDamage) {
		super(startingPosition, initHitbox, img, health, coolDown, 0, attackDamage);
		
		health = 200;
		coolDown = 10; 
		attackDamage = 20; 
		
	}
	
	@Override
	public void attack(Actor other) {
		if (other instanceof Zombie)
			super.attack(other);
	}

}
