/**
 * Assignment 10
 * 
 * @author Christopher Drake CS1410 u1219632 4/15/19
 */

package a10;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Example extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Timer timer;
	private ArrayList<Actor> actors; // Plants and zombies all go in here
	BufferedImage plantImage; // Maybe these images should be in those classes, but easy to change here.
	BufferedImage TankPlantImage;
	BufferedImage SpeedPlantImage;

	BufferedImage zombieImage; 
	int numRows;
	int numCols;
	int cellSize;

	/**
	 * Setup the basic info for the example
	 */
	public Example() {
		super();

		// Define some quantities of the scene
		numRows = 5;
		numCols = 7;
		cellSize = 75;
		setPreferredSize(new Dimension(50 + numCols * cellSize, 50 + numRows * cellSize));

		// Store all the plants and zombies in here.
		actors = new ArrayList<>();

		// Load images
		try {
			plantImage = ImageIO.read(new File("src/a10/Animal-Icons/basic-cow.png"));
			TankPlantImage = ImageIO.read(new File("src/a10/Animal-Icons/black-cow.png"));
			SpeedPlantImage = ImageIO.read(new File("src/a10/Animal-Icons/brown-cow.png"));
			zombieImage = ImageIO.read(new File("src/a10/Animal-Icons/UFO.png"));
		} catch (IOException e) {
			System.out.println("A file was not found");
			System.exit(0);
		} 
		
		// Set random starting positions for Zombies and plants
		Random rand = new Random(); 
		int pXpos = rand.nextInt(500); 
		int pYpos = rand.nextInt(350);
		int zXpos = 500; //Zombies should always appear on the right side of the panel
		int zYpos = rand.nextInt(350);
		
		//Set default values for plant traits
			//Change actors inline or actually create a subclass?? 
			//Won't collide properly if implemented as a subclass...
		int health = 100; 
		int coolDown = 5; 
		int attackDamage = 1; 
		
		int plantGen = rand.nextInt(100); 
		
		if (plantGen < 25 ) { 
			plantImage = TankPlantImage; 
			health = 200; 
			coolDown = 120; 
			attackDamage = 20; 
		}
		
		if (plantGen < 50) {
			plantImage = SpeedPlantImage; 
			health = 75; 
			coolDown = 2; 
			attackDamage = 1; 
		}
	
		// Make a plant
		Plant plant = new Plant(new Point2D.Double(pXpos, pYpos), 
				new Point2D.Double(plantImage.getWidth(), plantImage.getHeight()), plantImage, health, coolDown, attackDamage);
		actors.add(plant);
	
		// Make a zombie
		Zombie zombie = new Zombie(new Point2D.Double(zXpos, zYpos),
				new Point2D.Double(plantImage.getWidth(), plantImage.getHeight()), zombieImage, 100, 50, -2, 10); 
		// Add them to the list of actors
		actors.add(zombie);

		// The timer updates the game each time it goes.
		// Get the javax.swing Timer, not from util.
		timer = new Timer(30, this);
		timer.start();

	}

	/***
	 * Implement the paint method to draw the plants
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Actor actor : actors) {
			actor.draw(g, 0);
			actor.drawHealthBar(g);
		}
	}

	/**
	 * 
	 * This is triggered by the timer. It is the game loop of this test.
	 * 
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		// This method is getting a little long, but it is mostly loop code
		// Increment their cooldowns and reset collision status
		for (Actor actor : actors) {
			actor.update();
		}

		// Try to attack 
		for (Actor zombie : actors) {
			for (Actor other : actors) {
					zombie.attack(other);
			}
		}

		// Remove plants and zombies with low health
		ArrayList<Actor> nextTurnActors = new ArrayList<>();
		for (Actor actor : actors) {
			if (actor.isAlive())
				nextTurnActors.add(actor);
			else
				actor.removeAction(actors); // any special effect or whatever on removal
		}
		actors = nextTurnActors;

		// Check for collisions between zombies and plants and set collision status
		for (Actor zombie : actors) {
			for (Actor other : actors) {
				zombie.setCollisionStatus(other);
			}
		}

		// Move the actors.
		for (Actor actor : actors) {
			actor.move(); // for Zombie, only moves if not colliding.
		}

		// Redraw the new scene
		repaint();
	}

	/**
	 * Make the game and run it
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame app = new JFrame("Plant and Zombie Test");
				app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Example panel = new Example();

				app.setContentPane(panel);
				app.pack();
				app.setVisible(true);
			}
		});
	}

}