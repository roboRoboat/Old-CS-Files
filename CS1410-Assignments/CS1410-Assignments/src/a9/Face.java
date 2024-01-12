package a9;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;

/**
 * A Face has a 
 *  - name (from the filename) 
 *  - img (a drawable BufferedImage)
 *  - normalized_mouth_width and height: a number from 0.0 to 1.0 saying how much of the face width the mouth covers.
 *  - distance: the distance to the unknown.
 *  - classification: the string saying what expression the face has.
 * @author dejohnso
 *
 */
public class Face implements Comparable<Face>{
	
	private double normalized_mouth_width;
	private double normalized_mouth_height;
	private String name;
	private BufferedImage image;

	double distance;
	String classification;

	public Face(double initWidth, double initHeight, String initClassification, String initName, BufferedImage img) {
		normalized_mouth_width = initWidth;
		normalized_mouth_height = initHeight;
		classification = initClassification;
		name = initName;
		image = img;
	}
	
	public double getX() {
		return normalized_mouth_width;
	}

	public double getY() {
		return normalized_mouth_height;
	}
	
	public double getDistance() {
		return distance;
	}
	
	public void setDistance(double dist) {
		distance = dist;
	}
	
	public BufferedImage getImage() {
		return image;
	}

	public double calculateDistance(Face other) {
		double diffWeight = normalized_mouth_width - other.normalized_mouth_width;
		double diffCircumference = normalized_mouth_height - other.normalized_mouth_height;
		return Math.sqrt(diffWeight*diffWeight + diffCircumference*diffCircumference);
	}

	public String getClassification() {
		return classification;
	}
	
	// Needs to be implemented
	@Override
	public int compareTo(Face other) {
		 Double dist = this.distance;
		 return dist.compareTo(other.distance);
	}
	
	
	/**
	 * Reads files in a folder. The files are .gif files, and the name of the file encodes
	 * the expression and the face width in pixels, the mouth width in pixels, and mouth height in 
	 * pixels.
	 * @param folder
	 * @return
	 */
	public static ArrayList<Face> readSamples(File folder) {
		ArrayList<Face> faces = new ArrayList<Face>();
		File[] files = folder.listFiles();
		System.out.println(Arrays.deepToString(files));
		//If this pathname does not denote a directory, then listFiles() returns null. 
		for (File file : files) {
		    if (file.isFile()) {
		        String origFilename = file.getName();
		        System.out.println(origFilename);
		        String filename = origFilename.replaceAll("\\.\\w+", "");
		        System.out.println("file:" + origFilename);
		        
				String[] items = filename.split("_");
				String name = items[0];
				String classification = items[1];
				int faceWidth = Integer.parseInt(items[2]);
				int mouthWidth = Integer.parseInt(items[3]);
				int mouthHeight = Integer.parseInt(items[4]);
				
				BufferedImage img = null;
				try {
					System.out.println("open " + file.getAbsolutePath() );
					img = ImageIO.read(new File(file.getAbsolutePath()));
				} catch (IOException e) {
					System.out.println("Face file " + origFilename + " not found.");
					System.exit(-1);
				}
				
				Face face = new Face(mouthWidth/(double)faceWidth,
									 mouthHeight/(double)faceWidth,
									 classification,
									 name, img);
				faces.add(face);
		    }
		}
		return faces;
	}

}
