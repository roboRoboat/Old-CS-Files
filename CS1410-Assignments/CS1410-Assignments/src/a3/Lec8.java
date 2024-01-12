package a3;

import java.awt.Color;

public class Lec8 {

	public static void main(String[] args) {
		
		// Count 5 times
		for (int i = 0; i < 5; i++)
			System.out.println(i);
		
		// Look at safety of the condition
		for (int i = 0; i != 5; i++)
			System.out.println(i);
//		for (int i = 0; i != 5; i += 2) // Change increment
//			System.out.println(i);
		
		// How many iterations?
		for (int i = 4; i < 17; i += 3)
			System.out.println(i);
		
		// Nested loop
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.print(j + " ");
			}
			System.out.println();
		}

		// Nested loop using expressions in inner loop
		for (int i = 0; i < 5; i++) {
			for (int j = i; j < i+4; j++) {
				System.out.print(j + " ");
			}
			System.out.println();
		}

		Picture picture = new Picture("DelicateArch.jpg");
		picture.show();
		
		// Get the red part of the image
		
		// Work on a copy of the image
		Picture pictureCopy = new Picture(picture);
		// Loop over all the pixels
		for (int row = 0; row < pictureCopy.height(); row++)
        	for (int col = 0; col < pictureCopy.width(); col++) {
        		Color c = pictureCopy.get(col, row); // get the color
        		Color newColor = new Color(c.getRed(), 0, 0); // make a new color using the red value
        		pictureCopy.set(col, row, newColor); // Set the color in the new image
        	}
		pictureCopy.show();

		
	}

}
