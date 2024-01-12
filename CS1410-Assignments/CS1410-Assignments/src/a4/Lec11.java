package a4;

import java.util.Scanner;

public class Lec11 {
	
	/**
	 * Search for a numeric digit in the string parameter. 
	 * Returns the first digit from the beginning or '_' if not found.
	 * @param s a String to search for numeric digits.
	 * @return the first numeric digit, or '_' if not found
	 */
	public static char findDigit(String s) {
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                return c;
            }
            i = i + 1;
        }
        return '_'; 
    }

	/**
	 * Search for a numeric digit in the string parameter.
	 * Written as a for loop. 
	 * Returns the first digit from the beginning or '_' if not found.
	 * @param s a String to search for numeric digits.
	 * @return the first numeric digit, or '_' if not found
	 */
	public static char findDigitForLoop(String s) {
        for (int i = 0; i < s.length(); i++) {
        	char c = s.charAt(i);
            if (Character.isDigit(c))
                return c;
        }
        return '_'; 
    }

	
	/**
	 * Find the index of the first numeric digit in the String
	 * @param s A String with possible letters and numbers
	 * @return the location of the first numeric character or -1 if none found.
	 */
	public static int findDigitIndex(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                return i;
            }
        }
        return -1; 
    }
	
	/**
	 * Search for a token in names that matches name.
	 * @param names is a string with words.
	 * @param name is the target to search for.
	 * @return true if name is found and false otherwise.
	 */
	public static boolean searchForName(String names, String name) {
		Scanner s = new Scanner(names);
		while (s.hasNext()) {
			String token = s.next();
			if (token.equals(name)) { 
				return true;
			}
		}
		return false;
	}

	
	/**
	 * Given a string of words, return the length of the longest token.
	 * @param sentence
	 * @return
	 */
	public static int longestToken(String sentence) {
    	int longestSoFar = 0;
    	Scanner s = new Scanner(sentence);
    	while ( s.hasNext() ) {
    		String token = s.next();
    		if (token.length() > longestSoFar)
    			longestSoFar = token.length();
    	}
		s.close();
    	return longestSoFar;
	}

	public static int findSmallestIncorrect(String sentence) {
		Scanner s = new Scanner(sentence);
		int smallestSoFar = 10000;
		while (s.hasNext()) {
			int num = s.nextInt();
			if ( num < smallestSoFar) {
				smallestSoFar = num;
			}
		}
		return smallestSoFar;
	}

	/**
	 * Find the smallest number in a String of numbers.
	 * There must be at least one number in the String.
	 * @param sentence: The string of numbers.
	 * @return the smallest number found.
	 */
	public static int findSmallest(String sentence) {
		Scanner s = new Scanner(sentence);
		int smallestSoFar = s.nextInt();
		while (s.hasNext()) {
			int num = s.nextInt();
			if ( num < smallestSoFar) {
				smallestSoFar = num;
			}
		}
		return smallestSoFar;
	}

	public static void main(String[] args) {
		
		System.out.println("The first digit is " + findDigit("aw13g"));
		System.out.println("Location of first digit is " + findDigitIndex("awthg"));
		
		// Test poor upper bound setting
		System.out.println("The smallest number is " + findSmallestIncorrect("10 2 5"));
		System.out.println("The smallest number is " + findSmallestIncorrect("100000 200000 500000"));

		System.out.println("The smallest number is " + findSmallest("10 2 5"));
		System.out.println("The smallest number is " + findSmallest("100000 200000 500000"));

	}

}
