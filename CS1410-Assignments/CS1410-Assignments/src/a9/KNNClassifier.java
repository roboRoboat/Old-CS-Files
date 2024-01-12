package a9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Implements a kNN classifier on Face samples.
 * 
 * @author dejohnso
 * @author Christopher Drake, 01219632
 */
public class KNNClassifier {

	private int k; // The number of nearest neighbors used
	private ArrayList<Face> samples; // The List of known samples
	private ArrayList<Face> kClosestSet; // The sublist of the nearest k samples
	private Face unknown; // The face that is being tested as an unknown sample.

	// Some getters to allow the GUI to draw these elements
	public ArrayList<Face> getSamples() {
		return samples;
	}

	public ArrayList<Face> getkClosestSet() {
		return kClosestSet;
	}

	public Face getUnknown() {
		return unknown;
	}

	/**
	 * Swap out the old unknown for the new one.
	 * 
	 * @param newUnknown
	 */
	public void setUnknown(Face newUnknown) {
		// To test the classifier, pull one sample out of the known samples.
		// Pretend we do not know its classification and classify it.
		// Compare the kNN classification with what it really is.
		// If there already was an unknown, add it back into samples
		if (unknown != null)
			samples.add(unknown);

		// Now find the unknown, save it, and remove it from the samples
		int unknownIndex = samples.indexOf(newUnknown);
		if (unknownIndex != -1) {
			unknown = samples.get(unknownIndex);
			samples.remove(unknownIndex);
		}
		kClosestSet = null;
	}

	/**
	 * Construct the classifier object with a k value set.
	 * 
	 * @param kInit
	 */
	public KNNClassifier(int kInit) {
		k = kInit;
		samples = new ArrayList<Face>(); // Make the List to store samples in
		kClosestSet = null; // The nearest set is not yet known
		unknown = null; // no unknown face yet.
	}

	/**
	 * Add a known sample to the classifier.
	 * 
	 * @param sample
	 */
	public void addSample(Face sample) {
		samples.add(sample);
	}

	/**
	 * Get rid of the existing samples in preparation for adding new ones.
	 */
	public void clearSamples() {
		samples.clear();
	}

	// Implementation note: I originally made some of the the following private but
	// to help
	// with testing I changed them to public. There are some other ways around this
	// but I stuck with the simplest.
	
	/**
	 * Find the distance from every known sample to the unknown and store each
	 * distance in each known sample. At the end of this method, each Face in
	 * samples should have a correct distance to the unknown sample assigned to its
	 * distance instance variable. Call the methods in Face to accomplish this.
	 * 
	 * @param unknown
	 */
	public void computeDistanceFromUnknownToSamples(Face unknown) {
		// Compute distance using method in face for each Face in samples. 
		// Store that distance value in each known sample. 	
		for (Face sample : samples) {
			sample.setDistance(sample.calculateDistance(unknown));
		}
	}

	/**
	 * Set the internal kClosestSet instance variable to the nearest k Faces in
	 * samples. Assumes Face distances have been calculated. At the end of this
	 * method the kClosestSet should contain the k smallest Faces (as defined by
	 * Face.compareTo). You can assume there are at least k items in samples.
	 */
	public void findKClosest() {
		// Sort works on Faces because it has compareTo implemented to compare distances
		ArrayList<Face> sortedList = samples;
		Collections.sort(sortedList);
		kClosestSet = new ArrayList<Face>(); 

		for (int i = 0; i < k; i++) {
			kClosestSet.add(sortedList.get(i)); 
		}
	}

	/**
	 * Given a HashMap of classifications and their votes, pick the classification
	 * with the most votes. For a tie, one of the tied classifications can be picked
	 * but it is not specified which.
	 * 
	 * @param map with entries of classifications as keys and number of votes as
	 *            values.
	 * @return the classification with the most votes.
	 */
	public String getHighestVotedClassification(HashMap<String, Integer> map) {
		Integer highestScore = 0;
		String highestVoted = ""; 
		// implement
		for (Map.Entry<String, Integer> v : map.entrySet()) {
			if (v.getValue() > highestScore) {
				//TODO Why does the next line cause a bug? Seems like it would be necessary 
				//highestScore = v.getValue(); 
				highestVoted = v.getKey(); 
			}
		}
		return highestVoted; // change this
	}

	/**
	 * Use the stored kClosestSet instance variable to vote on the classification.
	 * Each Face in kClosestSet has a classification. This method uses a Map to
	 * store classifications and their vote count. It returns the classification
	 * with the highest vote. Use the getHighestVotedClassification to pick the
	 * winner.
	 * 
	 * @return the String classification.
	 */
	public String voteOnClassification() {
		// implement		
		HashMap<String, Integer> classScores = new HashMap<String, Integer>(); 
		
		for (Face f : kClosestSet) {
			String currClass = f.getClassification(); 
			if(classScores.containsKey(currClass)){
				Integer vote = classScores.get(currClass);
				//TODO Unable to use classScores.get(currClass) as a parameter below, why? 
				classScores.replace(currClass, vote++); 
			}
			else {
				classScores.put(currClass, 1); 
			}
		}
		
		return getHighestVotedClassification(classScores); // change this
	}

	/**
	 * The main method to find a classification. The code calls methods to enact
	 * each of the needed steps.
	 * 
	 * @param unknown sample
	 * @return the classification
	 */
	public String classifyUnknownSample(Face unknown) {
		computeDistanceFromUnknownToSamples(unknown);
		findKClosest();
		return voteOnClassification();
	}
}
