package a4;

import java.awt.Color;
import java.util.Scanner;

/**
 * CS 1410 Spring 2019
 * Assignment 4
 * 
 * @author Christopher Drake, u1219632
 *
 */

public class SearchAndOptimizingLoops {

	public static void main(String[] args) {
		System.out.println("Begin findSmallestPositiveNumber() testing");
		System.out.println("Expected Result: 1 \t Actual Result: " + findSmallestPositiveNumber("1 2 3 4"));
		System.out.println("Expected Result: 44 \t Actual Result: " + findSmallestPositiveNumber("-2 44 -4 -40"));
		System.out.println("Expected Result: 3 \t Actual Result: " + findSmallestPositiveNumber("10 9 3 7"));
		System.out.println("End findSmallestPositiveNumber() testing\n");
		
		System.out.println("Begin lowestAlphabetically() testing");
		System.out.println("Expected Result: apple \t Actual Result: " + lowestAlphabetically("cat dog apple fish") );
		System.out.println("Expected Result: whisk \t Actual Result: " + lowestAlphabetically("zulu yankee xray whisk"));
		System.out.println("Expected Result: can \t Actual Result: " + lowestAlphabetically("car cat cap can"));
		System.out.println("End lowestAlphabetically() testing\n");
		
		System.out.println("Begin findSmallest() testing");
		System.out.println("Expected Result: 1 \t Actual Result: " + findSmallest("1 2 3 4"));
		System.out.println("Expected Result: -40 \t Actual Result: " + findSmallest("-2 44 -4 -40"));
		System.out.println("Expected Result: 3 \t Actual Result: " + findSmallest("10 9 3 7"));
		System.out.println("End findSmallest() testing\n");
		
		System.out.println("Begin findSmallestNumberInTwoStrings() testing");
		System.out.println("Expected Result: 1 \t Actual Result: " + findSmallestNumberInTwoStrings("1 2 3 4", "5 6 7 8"));
		System.out.println("Expected Result: -999 \t Actual Result: " + findSmallestNumberInTwoStrings("-2 44 -4 -40", "-999 32 87 46 -2"));
		System.out.println("Expected Result: 7 \t Actual Result: " + findSmallestNumberInTwoStrings("10 9 14 7", "13 27 9 55"));
		System.out.println("End findSmallestNumberInTwoStrings() testing\n");
		
		System.out.println("Begin findHighestScore() testing");
		System.out.println("Expected Result: 99 \t Actual Result: " + findHighestScore("89 78 99 67"));
		System.out.println("Expected Result: 78 \t Actual Result: " + findHighestScore("77 33 56 78"));
		System.out.println("Expected Result: 92 \t Actual Result: " + findHighestScore("84 83 89 92"));
		System.out.println("End findHighestScore() testing\n");
		
		System.out.println("Begin curveScores() testing");
		System.out.println("Expected Result: 100 72 89 95 20 \t Actual Result: " + curveScores("100 72 89 95 20"));
		System.out.println("Expected Result: 100 57 83 94 96  \t Actual Result: " + curveScores("95 52 78 89 91"));
		System.out.println("End curveScores() testing\n");

		Color white = new Color(255, 255, 255); 
		Color black = new Color(0, 0, 255); 
		Picture img = new Picture("Arches.jpg"); 
		
		System.out.println("Begin containsThisColor() testing");
		System.out.println("Expected Result: true \t Actual Result: " + containsThisColor(img, white ));
		System.out.println("Expected Result: false \t Actual Result: " + containsThisColor(img, black ));
		System.out.println("End containsThisColor() testing\n");
		
	}
	
	/**
	 * Finds smallest positive number when given a string of integers
	 * 
	 * @param nums String of numbers separated by spaces. Must be at least on positive number
	 * @return int that is smallest number greater than zero from input string
	 */
	public static int findSmallestPositiveNumber(String nums) {
		Scanner s = new Scanner(nums);
		int curSmallest = Integer.MAX_VALUE; 
		
		while (s.hasNextInt()) {
			int curNum = s.nextInt(); 
			if (curNum >= 0 && curNum < curSmallest) {
				curSmallest = curNum; 
			}
		}
		s.close();
		return curSmallest; 
		

	}
	
	/**
	 * Finds lowest word alphabetically given a String of words
	 * 
	 * @param words  String of lower-case words separated by spaces and made up of the letters a-z. The String will have at least one word.
	 * @return String containing the lowest alphabetical word
	 */
	public static String lowestAlphabetically(String words) {
		Scanner s = new Scanner(words); 
		String lowestWord = s.next(); 
		while (s.hasNext()) {
			String curWord = s.next(); 
			if (curWord.compareTo(lowestWord) < 0) {
				lowestWord = curWord; 
			}
		}
		s.close();
		return lowestWord; 
	}
	
	/**
	 * Finds smalles number in string, regardless of sign.  
	 * @param nums String of ints separated by spaces
	 * @return smallest int value contained in nums
	 */
	
	public static int findSmallest(String nums) {
		Scanner s = new Scanner(nums); 
		int curSmallest = s.nextInt(); 
		
		while (s.hasNextInt()) {
			int curNum = s.nextInt(); 
			if (curNum < curSmallest) {
				curSmallest = curNum; 
			}
		}
		s.close();
		return curSmallest; 
	}
	
	/**
	 * Returns value of smallest number found in two strings
	 * 
	 * @param nums1 String of numbers separated by spaces. Must contain at least one number. 
	 * @param nums2 String of numbers separated by spaces.
	 * @return int containing smallest number found in the two strings
	 */
	public static int findSmallestNumberInTwoStrings(String nums1, String nums2) {
		int smallest1 = findSmallest(nums1); 
		int smallest2 = findSmallest(nums2); 
		if (smallest1 < smallest2) {
			return smallest1; 
		}
		return smallest2; 
	}
	
	public static int findHighestScore(String scores) {
		Scanner s = new Scanner(scores); 
		int curHighest = 0; 
		
		while (s.hasNext()) {
			int curNum = s.nextInt(); 
			if (curNum > curHighest) {
				curHighest = curNum; 
			}
		}
		s.close();
		return curHighest; 
	}
	
	/**
	 * Returns new string of scores that have been curved up, where highest score has been changed to 100. 
	 * 
	 * @param scores  String containing numbers from 0 to 100. Each number is separated by a space. There must be at least one number in the String
	 * @return A String of numbers scaled so that the highest number in the parameter String becomes 100 and all the other numbers are moved up by the same amount.
	 */
	public static String curveScores(String scores) {
		Scanner s = new Scanner(scores);
		String curvedScores = ""; 
		
		if (s.findInLine("100") != null){
			s.close();
			return scores; 
		}
		else {
			int highestScore = findHighestScore(scores); 
			int curve = (100 - highestScore);
			
			while (s.hasNextInt()) {
				int curScore = s.nextInt(); 
				curvedScores += (curScore + curve + " "); 
			}
		}
		s.close();
		return curvedScores.trim(); 
	}
	
	/**
	 * Looks for Color object in image and returns true if color is contained in the image
	 * 
	 * @param img A Picture object that is at least 1x1 pixels
	 * @param color Color object searching for
	 * @return
	 */
	public static boolean containsThisColor(Picture img, Color color) {
		Picture pic = img; 
		Color testColor = color; 
		
		for (int row = 0; row < pic.height(); row++) {
			for (int col = 0; col < pic.width(); col++) {
				Color currColor = pic.get(col, row);
				if (currColor.equals(testColor)) {
					return true; 
				}
			}
		}
		return false;  
	}

}
