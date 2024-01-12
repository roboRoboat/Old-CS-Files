package a6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 
 * @author Christopher Drake - u1219632
 * a6
 * 3/4/2019
 */
public class ReviewAnalysis {

	/**
	 * Goes through the process of - reading the file of movie reviews - getting a
	 * score for each word - finding the best scoring word with a count greater than
	 * some threshold - scoring a few reviews and comparing the computer-generated
	 * score with the actual rating.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		String[] lines = null; // need to declare outside the try scope for later use.
		// Read the file and put each line in a String array.
		try {
			lines = convertFileToStringArray("src/a6/MovieReviews.txt");
		} catch (FileNotFoundException e) {
			// Report and quit if the file wasn't found.
			System.out.println("File was not found");
			return;
		}

		// Create three arrays with our known maximum size of 16444 elements.
		// 1. words: All the words in the reviews
		// 2. word_score: A word at index x has a total point value at word_score[x]
		// 3. word_count: A word at index x has a total number of appearances at
		//    word_count[x]
		String[] words = new String[16444]; // Cheat by knowing # of words in advance.
		double[] wordScore = new double[16444];
		int[] wordCount = new int[16444];

		// We need to track how many words have been added to the arrays.
		// As words gets added, this number should go up to match.
		int numberOfSpacesUsedInWords = 0; // This variable is super important. If you don't know the point of it - stop - and figure it out.

		// Go through each review. For each review
		// 1. Pull off the movie score from the front of the review.
		// 2. Go through the text of the review. Process each word by adding to its
		// score and count.
		for (int i = 0; i < lines.length; i++) {
			Scanner s = new Scanner(lines[i]);
			// Pull off the movie score - we can assume it is there.
			int lineScore = s.nextInt();
			// Get each word from the review and add to its score or initialize it. Keep track of spaces used in the arrays.
			numberOfSpacesUsedInWords = processWords(s, lineScore, words, wordScore, wordCount, numberOfSpacesUsedInWords);
		}

		// Now, use these processed movie reviews. We are interested in two things:
		//    1. What is the highest-rated word that appears often enough to be meaningful?
		//    2. How well does this approach guess at a rating?
		
		// First, find the best word.
		// We are not that interested in words that do not appear multiple times as a best word.
		int wordCountFilter = 15;

		// Search for the best scoring word with a word_count greater than
		// word_count_filter.
		int bestIndex = indexOfBestWord(wordScore, wordCount, wordCountFilter, numberOfSpacesUsedInWords);
		if (bestIndex != -1) {
			System.out.println(
					"The best scoring word is " + words[bestIndex] + " with a count of " + wordCount[bestIndex]
							+ " and average score of: " + wordScore[bestIndex] / wordCount[bestIndex]);
		} else { // If the filter is too high, no word may match.
			System.out.println("No word found with a word count above " + wordCountFilter);
		}

		// Then, test the estimates of the rating against the actual rating.
		// Pick a sample of reviews, estimate scores from the words
		// and compare the estimates with the actual movie scores.
		for (int index = 360; index < 380; index++) {
			String testReview = lines[index];
			Scanner testScanner = new Scanner(testReview);
			// Get the score and then the rest of the line.
			int actualScore = testScanner.nextInt();
			String reviewText = testScanner.nextLine();
			double estimatedScore = scoreReview(reviewText, words, wordScore, wordCount, numberOfSpacesUsedInWords);
			String formattedEstimate = String.format("%.1f", estimatedScore);
			String sentimentPrediction = "Wrong";
			if ((actualScore >= 2.0 && estimatedScore >= 2.0) || (actualScore < 2.0 && estimatedScore < 2.0))
				sentimentPrediction = "Correct";
			System.out.println("estimated score: " + formattedEstimate + " actual score: " + actualScore + " Sentiment Predication: " + sentimentPrediction + " | Review: "
					+ reviewText);
			testScanner.close();
		} 
	}
	
	
	/**
	 * Given a filename, open the file and read lines from the file (a line is
	 * defined as what a scanner nextLine() method produces). Convert each line to
	 * lower-case. (Use the toLowerCase() method). Store the lines in a String[].
	 * 
	 * Since we need to know how big the String array should be, first go through
	 * the file and count the number of lines. Then, make a String[] of that size,
	 * and make a new Scanner from the file and go through the file again, placing
	 * each line in the array.
	 * 
	 * Implement this by calling the two methods below this that break this problem
	 * into two parts.
	 * 
	 * @param filename
	 * @return An array of strings with each line of the file an element of the
	 *         array.
	 * @throws FileNotFoundException
	 */
	public static String[] convertFileToStringArray(String filename) throws FileNotFoundException {
		
		return fillArrayWithLines(filename, countLines(filename)); // Implement this method
	}
	
	/**
	 * Count the number of lines in a file.
	 * @param filename
	 * @return the number of lines in the file. A line is defined as
	 * what a Scanner nextLine() provides.
	 * @throws FileNotFoundException
	 */
	public static int countLines(String filename) throws FileNotFoundException {
		File file = new File(filename); 
		int counter = 0; 
		try {
			Scanner fs = new Scanner(file);
			while (fs.hasNextLine()) {
				//System.out.println("Current Line: " + counter);
				fs.nextLine(); 
				counter++; 
			}
			fs.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + e.getMessage());
		}
		return counter;  // Implement this method
	}

	/**
	 * Given a filename and a count of the number of lines in the file, fill a String array
	 * with those lines. Each line should be converted to lower-case. Punctuation remains.
	 * 
	 * Your use of a Scanner should depend on the "throws" notation
	 * on the method signature to say that code that calls this method (for us, from
	 * main) should handle the exception itself. 
	 *
	 * @param filename
	 * @param lineCount
	 * @return a String[] with each line in the file an element in the array.
	 * @throws FileNotFoundException
	 */
	public static String[] fillArrayWithLines(String filename, int lineCount) throws FileNotFoundException {
		File file = new File(filename); 
		String[] fileLineArr = new String[lineCount]; 
		
		
		try {
			Scanner fs = new Scanner(file);
			
			for (int i = 0; i < lineCount; i++) {
				fileLineArr[i] = fs.nextLine().toLowerCase(); 
			}
			fs.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + e.getMessage());
		}
		return fileLineArr; // Implement this method
	}
	
	/**
	 * Looks for word in the words array in the first numberOfSpacesUsedInWords elements.
	 * Do not look at the entire length of the array - many spots are not used
	 * until the end of the word counting process.
	 * 
	 * @param words: An array of String values. The first numberOfSpacesUsedInWords are filled.
	 * @param word: The search word.
	 * @param numberOfSpacesUsedInWords: the number of elements currently used in the words array.
	 * @return the index of the search word in words, or -1 if not found.
	 */
	public static int indexOfWordInArray(String[] words, String word, int numberOfSpacesUsedInWords) {
		for (int i = 0; i < numberOfSpacesUsedInWords; i++) {
			if (words[i] .equals(word)) {
				return i; 
			}
		}	
		return -1;  // Implement this method
	}

	/**
	 * For a review sentence, estimate the movie rating based on the words in the
	 * review. 
	 * 
	 * For each word, find its index in the words list, 
	 * 
	 * then compute its average score (scores[index]/wordCount[index]) and add it to a cumulative
	 * review score. 
	 * 
	 * Count up the number of words in the review and use the count
	 * and the cumulative review score to get an averaged movie score. 
	 * 
	 * Assume that at least one word in the review is in the array of words.
	 * 
	 * @param review: The text of the review.
	 * @param words: The array of words found in all reviews.
	 * @param scores: The cumulative score for each word in words.
	 * @param wordCount: The number of times each word in words appears in all the
	 *        reviews.
	 * @param numberOfSpacesUsedInWords: The number of elements in the arrays to be used.
	 * @return the average score for the words in review.
	 */
	public static double scoreReview(String review, String[] words, double[] scores, int[] wordCount, int numberOfSpacesUsedInWords) {
		Scanner s = new Scanner(review); 
		int counter = 0; 
		double cumulativeReviewScore = 0; 
		
		while (s.hasNext()) {
			String token = s.next();
			int index = indexOfWordInArray(words, token, numberOfSpacesUsedInWords); 
			
			double avgScore = scores[index]/wordCount[index]; 
			cumulativeReviewScore += avgScore;
			counter++; 
			
		}
		
		return cumulativeReviewScore/counter;  // Implement this method
	}

	/**
	 * Search through numberOfSpacesUsedInWords elements of the scores array. Following an
	 * optimization loop pattern, find the index of the highest average scoring
	 * word. With this index the actual word can be found later. Ignore words whose
	 * counts are not greater than the countAbove value.
	 * 
	 * @param scores: An array of cumulative scores for a word.
	 * @param counts: An array of times the word appeared in the reviews.
	 * @param countAbove: Words with counts below or equal to countAbove are
	 *        ignored.
	 * @param numberOfSpacesUsedInWords: Specifies the number of valid elements in the
	 *        arrays.
	 * @return the index of the best average score or -1 if none satisfy the
	 *         countAbove threshold.
	 */
	public static int indexOfBestWord(double[] scores, int[] counts, int countAbove, int numberOfSpacesUsedInWords) {
		double bestAvgScore = 0;
		int bestScoreIndex = -1; 
		
		for (int i = 0; i < numberOfSpacesUsedInWords; i++) {
			if (scores[i]/counts[i] > bestAvgScore && counts[i] > countAbove) {
				bestAvgScore = scores[i]/counts[i]; 
				bestScoreIndex = i; 
			}
		}
		return bestScoreIndex; // Implement this method
	}
	
	/**
	 * Returns true if word is included in array
	 * 
	 * @param word
	 * @param words
	 * @return
	 */
	
	public static boolean wordIsInArray (String word, String[] words) {
		
		for (int i = 0; i < words.length; i++) {
			if (word.equals(words[i])) {
				return true; 
			}
		}
		return false; 
		
	}
	
	
	/**
	 * Process the words in the scanner s. 
	 * 
	 * If a token in s is already in words, then 
	 * 		add the lineScore to the word_score location for that word 
	 * 		and add 1 to the word_count for that location. 
	 * 
	 * If the token is not is words, then 
	 * 		add the token to the next available spot in words 
     * 		and add the lineScore to word_score at that location 
 	 *	 	and put a count of 1 in word_count at that location. 
 	 *		
 	 * 		Adjust numberOfSpacesUsedInWords by adding 1 when a new spot is used up. 
 	 * 		Do not change this if the word is already in the array.
	 * 
	 * @param s: A Scanner with the text part of a movie review
	 * @param lineScore: the integer movie rating taken from the review
	 * @param words: an array to hold the words from the reviews
	 * @param wordScore: an array holding the cumulative (summed) score for that word.
	 * @param wordCount: an array holding the number of times a word has been seen
	 *        in reviews
	 * @param numberOfSpacesUsedInWords: the number of elements used in the arrays
	 * @return the new numberOfSpacesUsedInWords. If no new words are found in s, then it
	 *         is the same value as the input numberOfWordSoFar.
	 */
	public static int processWords(Scanner s, int lineScore, String[] words, double[] wordScore, int[] wordCount, int numberOfSpacesUsedInWords) {
		
		
		while (s.hasNext()) {
			String token = s.next();
			if (wordIsInArray(token, words)) {
				//get index of token in sentence, in words array
				int index = indexOfWordInArray(words, token, numberOfSpacesUsedInWords);
				
				//optimization?? 
				wordScore[index] += lineScore; 
				wordCount[index]++; 
				
			}
			else {
				words[numberOfSpacesUsedInWords] = token;
				wordScore[numberOfSpacesUsedInWords] += lineScore;
				wordCount[numberOfSpacesUsedInWords] = 1; 
				numberOfSpacesUsedInWords++; 
			}
					
		}
		return numberOfSpacesUsedInWords;  // Implement this method
	}
	
	

}
