package a2;

import java.util.Scanner;

/**
 * A collection of methods for the second assignment of CS 1410.
 * 
 * @author Christopher Drake
 */

public class MethodCollection {
	
	/**
	 * Runs the test cases for the methods that are implemented. 
	 * @param args
	 */

	public static void main(String[] args) {
		/*
		//Test cases for countTokens(); 
		System.out.println("Start countTokens(); tests"); 
		System.out.println(countTokens("This is a test")); // Should print 4
		System.out.println(countTokens("")); // Should print 0
		System.out.println("End countTokens(); testing \n");
		
		//Test cases for countTokensThatAreNotInt(); 
		System.out.println("Start countTokensThatAreNotInt(); tests");
		System.out.println(countTokensThatAreNotInt("1 1.0 1.3 test 2 words")); // Should print 4
		System.out.println(countTokensThatAreNotInt("1")); // Should print 0
		System.out.println("End countTokensThatAreNotInt(); tests \n"); 
		
		//Test cases for describeSign(); 
		System.out.println("Start describeSign(); tests"); 
		System.out.println(describeSign(-1));
		System.out.println(describeSign(1));
		System.out.println(describeSign(0));
		System.out.println("End describeSigns(); tests \n");
		
		//Test cases for isEvenlyDivisibleBySeven(); 
		System.out.println("Start isEvenlyDivisible(); tests");
		System.out.println(isEvenlyDivisibleBySeven(49));
		System.out.println(isEvenlyDivisibleBySeven(16));
		System.out.println(isEvenlyDivisibleBySeven(3));
		System.out.println("End isEvenlyDivisible(); tests \n");
		
		//Test cases for makeLine(); 
		System.out.println("Start makeLine(); tests"); 
		System.out.println(makeLine('+', '-', 7)); 
		System.out.println("End makeLine(); tests \n");
		
		//Test cases for makeSquare(); 
		System.out.println("Start makeSquare(); test");
		//System.out.println(makeSquare(7));
		System.out.println(makeSquare(2));
		//System.out.println(makeSquare(10));
		System.out.println("End makeSquare(); tests \n");
		
		//Test cases for capitalizeLastCharacter(); 
		System.out.println("Start lastChar tests");
		System.out.println(capitalizeLastCharacter("hello")); 
		System.out.println(capitalizeLastCharacter("Bienvenidos"));
		System.out.println("End lasChar tests \n");
		*/
		
		//Test cases for capitalizeLastCharactersInSentence(); 
		System.out.println("Start lastSentence(); tests");
		System.out.println(capitalizeLastCharactersInSentence("I am a boy"));
		System.out.println(capitalizeLastCharactersInSentence("hi ho Silver"));
		System.out.println(capitalizeLastCharactersInSentence("Howdy there Mr. Mayor"));
		System.out.println("End lastSentence(); tests");
		


	}

	/**
	 * Returns an integer value equal to the amount of tokens in the String passed 
	 * 
	 * @param sentence - String of tokens
	 * @return - number of tokens in 'sentence'
	 */
	public static int countTokens(String sentence) {
		// Change or modify the return. This is here for now to prevent compiler errors.
		int counter = 0; 
		Scanner s = new Scanner(sentence); 
		
		while (s.hasNext()) {
			counter++;
			s.next(); 
		}
		
		s.close();
		return counter;
	}
		

	/**
	 * Returns integer value equal to the amount of tokens in 'sentence' that are NOT type int
	 * 
	 * @param sentence String that you want to pass
	 * @return number of tokens in String that are not type int
	 */
	public static int countTokensThatAreNotInt(String sentence) {
		// Change or modify the return. This is here for now to prevent compiler errors.
		int counter = 0; 
		Scanner s = new Scanner(sentence); 
		
		while (s.hasNext()) {
			if (!s.hasNextInt()) {
				counter++; 
			}
			s.next(); 
		}
		 
		s.close();
		return counter;
	}

	/**
	 * Returns a string describing the sign of the int passed 
	 * 
	 * @param value integer value to pass
	 * @return either "non-negative" or "negative" 
	 * 
	 */
	public static String describeSign(int value) {
		// Change or modify the return. This is here for now to prevent compiler errors.
		String sign; 
		
		if (value >= 0) {
			sign = "non-negative"; 
		}
		else {
			sign = "negative"; 
		}
		
		return sign;
	}

	/**
	 * Checks if value is evenly divisible by seven
	 * 
	 * @param value
	 * @return true or false
	 */
	public static boolean isEvenlyDivisibleBySeven(int value) {
		// Change or modify the return. This is here for now to prevent compiler errors.
		if (value % 7 != 0) {
			return false; 
		}
		return true;
	}

	/**
	 * Returns string that makes a line
	 * 
	 * @param edge character on edge of line
	 * @param inner characters on inside of edges
	 * @param how wide the line should be
	 * @return string that makes up line
	 */
	public static String makeLine(char edge, char inner, int width) {
		String line = "";
		int i = 0;
		while (i < width - 2) {
			line = line + inner;
			i = i + 1;
		}
		return edge + line + edge;
	}

	/**
	 * Creates a string that looks like a box
	 * 
	 * TODO double check requirements for this method
	 * 
	 * @param width Desired width of box
	 * @return box String
	 */
	public static String makeSquare(int width) {
		String top = makeLine('+', '-', width); 
		String body = makeLine('|', ' ', width); 
		String box = ""; 
		
		box += top + "\n"; 
		/*for (int i = 0; i < 5; i++) {
			box += body + "\n"; 
		}*/
		box += top; 
		
		return box;
	}

	/**
	 * Capitalizes the last character of a word
	 * 
	 * @param word 
	 * @return returns word with last letter capitalized
	 */
	public static String capitalizeLastCharacter(String word) {
		String cutWord = word.substring(0, word.length()-1); 
		char end = word.charAt(word.length()-1);
		end = Character.toUpperCase(end); 
		String newWord = cutWord + end; 
	
		return newWord;
	}

	/**
	 * Returns string that results in last character of each word being capitalized
	 * 
	 * @param sentence
	 * @return sentence String with last characters capitalized
	 */
	public static String capitalizeLastCharactersInSentence(String sentence) {
		Scanner s = new Scanner(sentence);
		String newSentence = ""; 
		String nextWord = ""; 
		
		while
			(s.hasNext()) {
			nextWord = capitalizeLastCharacter(s.next()); 
			
			if (s.hasNext()) {
				newSentence += nextWord + " ";
			}
			else {
				newSentence += nextWord; 
			}
		}
		
		s.close();
		return newSentence;
	}

}
