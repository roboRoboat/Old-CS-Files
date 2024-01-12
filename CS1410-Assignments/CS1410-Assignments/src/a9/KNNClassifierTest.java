package a9;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

public class KNNClassifierTest {

	public static void addFaces(KNNClassifier kNN) {
		Face f1 = new Face(0,1,"surprised",null, null);
		f1.setDistance(2);
		kNN.addSample(f1);
		Face f2 = new Face(0,2,"surprised",null, null);
		f2.setDistance(1);
		kNN.addSample(f2);
		Face f3 = new Face(0,3,"surprised",null, null);
		f3.setDistance(5);
		kNN.addSample(f3);
		Face f4 = new Face(0,4,"neutral",null, null);
		f4.setDistance(4);
		kNN.addSample(f4);
		Face f5 = new Face(0,5,"new expression",null, null);
		f5.setDistance(3);
		kNN.addSample(f5);
	}
	
	@Test
	public void testCalculateDistances() {
		KNNClassifier kNN = new KNNClassifier(3);
		addFaces(kNN);
		Face unknown = new Face(0,10,"surprised",null, null);
		kNN.computeDistanceFromUnknownToSamples(unknown);
		ArrayList<Face> samples = kNN.getSamples();
		assertEquals("Checking samples[0]", 9.0, samples.get(0).getDistance(), 1e-10);
		assertEquals("Checking samples[1]", 8.0, samples.get(1).getDistance(), 1e-10);
		assertEquals("Checking samples[2]", 7.0, samples.get(2).getDistance(), 1e-10);
		assertEquals("Checking samples[3]", 6.0, samples.get(3).getDistance(), 1e-10);
		assertEquals("Checking samples[4]", 5.0, samples.get(4).getDistance(), 1e-10);
	}

	@Test
	public void testfindKClosest() {
		KNNClassifier kNN = new KNNClassifier(3);
		addFaces(kNN);
		kNN.findKClosest();
		ArrayList<Face> closest = kNN.getkClosestSet();
		assertEquals("Testing kClosest size", 3, closest.size());
		assertEquals("Checking samples[0]", 1.0, closest.get(0).getDistance(), 1e-10);
		assertEquals("Checking samples[1]", 2.0, closest.get(1).getDistance(), 1e-10);
		assertEquals("Checking samples[2]", 3.0, closest.get(2).getDistance(), 1e-10);
	}

	@Test
	public void testfindKClosestSmallK() {
		KNNClassifier kNN = new KNNClassifier(1);
		addFaces(kNN);
		kNN.findKClosest();
		ArrayList<Face> closest = kNN.getkClosestSet();
		assertEquals("Testing kClosest size", 1, closest.size());
		assertEquals("Checking samples[0]", 1.0, closest.get(0).getDistance(), 1e-10);
	}

	@Test
	public void testHighestVoted() {
		HashMap<String, Integer> votes = new HashMap<>();
		votes.put("Joe",  2);
		votes.put("David", 3);
		votes.put("Sally",  1);
		KNNClassifier kNN = new KNNClassifier(1);
		String winner = kNN.getHighestVotedClassification(votes);
		assertEquals("Testing vote winner", "David", winner);
	}

	@Test
	public void testVoteOnClassification() {
		KNNClassifier kNN = new KNNClassifier(3);
		addFaces(kNN);
		kNN.findKClosest();
		String winner = kNN.voteOnClassification();
		assertEquals("Testing voting", "surprised", winner);
	}
	
}
