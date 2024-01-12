package a5;


public class ArraysAndSound {

	public static void main(String[] args) {
		
		int[] testArray = {1, 2, 3, 4, 5}; 
		int[] testArray2 = {99}; 
		
		String[] testArray3 = {"test", "car", "cat", "test"}; 
		String[] testArray4 = {"one", "two", "three", "four"};
		String[] testArray5 = {"three", "three"}; 
		String[] testArray6 = {"no", "dupes", "in", "this", "larger", "list"}; 
		
		int[] testArray7 = {1, 1, 1, 1}; 
		int[] testArray8 = {2, 4, 6, 8}; 
		int[] testArray9 = {100, 100, 50, 50}; 
		
		double[] testArray10 = {3, 19, -1, 6}; 
		double[] testArray11 = {2, 4, 6, 8}; 
		double[] testArray12 = {100, 100, 50, 50};
		
		double[] samples = StdAudio.read("asyouwish2.wav");
		double[] impMarch = StdAudio.read("imperial_march.wav"); 
				
		double[] testArray13 = {0.0, -0.1, 0.3};
		
		double[] testArray14 = {0, 1, 2, 3, 4};
		
		double[] testArray15 = {0.0, 0.2, 0.7, 0.2};

		System.out.println("Begin clearArray() testing");
		clearArray(testArray);
		clearArray(testArray2); 
		System.out.println("Expected Result: {0, 0, 0, 0, 0} \t Actual Result: " + arrayToString(testArray));
		System.out.println("Expected Result: {0} \t\t\t Actual Result: " + arrayToString(testArray2));
		System.out.println("End clearArray() testing\n");
		
		System.out.println("Begin containsDuplicate() testing");
		System.out.println("Expected Result: true \t Actual Result: " + containsDuplicate(testArray3));
		System.out.println("Expected Result: false \t Actual Result: " + containsDuplicate(testArray4));
		System.out.println("Expected Result: true \t Actual Result: " + containsDuplicate(testArray5));
		System.out.println("Expected Result: false \t Actual Result: " + containsDuplicate(testArray6));
		System.out.println("End containsDuplicate() testing\n");
		
		System.out.println("Begin averageArrayValues() testing");
		System.out.println("Expected Result: 1 \t Actual Result: " + averageArrayValues(testArray7));
		System.out.println("Expected Result: 5 \t Actual Result: " + averageArrayValues(testArray8));
		System.out.println("Expected Result: 75 \t Actual Result: " + averageArrayValues(testArray9));
		System.out.println("End averageArrayValues() testing\n");
		
		System.out.println("Begin frequencyCount() testing");
		System.out.println("Expected Result: {0, 4, 0, 0, 0, 0, 0, 0, 0, 0} \t Actual Result: " + arrayToString(frequencyCount(testArray7)));
		System.out.println("Expected Result: {0, 0, 1, 0, 1, 0, 1, 0, 1, 0} \t Actual Result: " + arrayToString(frequencyCount(testArray8)));
		System.out.println("End frequencyCount() testing\n");


		System.out.println("Begin reverseSound() testing");
		System.out.println("Expected Result: {6, -1, 19, 3} \t Actual Result: " + arrayToString(reverseSound(testArray10)));
		System.out.println("Expected Result: {8, 6, 4, 2} \t\t Actual Result: " + arrayToString(reverseSound(testArray11)));
		//double[] reverse = reverseSound(samples); 
		System.out.println("End reverseSound() testing\n");
		
		System.out.println("Begin scaleSound() testing");
		System.out.println("Expected Result: {0.0, -0.2, 0.6} \t Actual Result: " + arrayToString(scaleSound(testArray13, 2)));
		System.out.println("End scaleSound() testing\n");
		
		System.out.println("Begin echoSound() testing");
		System.out.println("Expected Result: {0.0, 1.0, 2.0, 3.5, 5.0, 2.0, 2.0} \t Actual Result: " + arrayToString(echoSound(testArray14, 2, 0.5)));
		//double[] echoed = echoSound(samples, 4000, .5); 
		//StdAudio.play(samples);
		//StdAudio.play(echoed);
		System.out.println("End echoSound() testing\n"); 
		
		System.out.println("Begin smoothSound() testing");
		System.out.println("Expected Result: {0.0, 0.3, 0.3666, 0.2} \t Actual Result: " + arrayToString(smoothSound(testArray15)));
		//StdAudio.play(smoothSound(samples)); 
		System.out.println("End smoothSound() testing\n"); 
	
		
	}
	
	
	/**
	 * Sets all elements in array to 0
	 * 
	 * @param arr - an int array
	 */
	public static void clearArray(int[] arr) {
		for (int pos = 0; pos < arr.length; pos++) {
			arr[pos] = 0; 
		}
		return; 	
	}
	
	/**
	 * Returns a string that represents the contents of an array
	 * 
	 * @param arr int array
	 * @return String representing array
	 */
	public static String arrayToString(int[] arr) {
		String strArray = "{"; 
		
		for (int pos = 0; pos < arr.length; pos++) {
			if (pos < arr.length - 1) {
				strArray += (arr[pos] + ", ");
			}
			else {
				strArray += (arr[pos] + "}");
			}
			 
		}
		return strArray; 
	}
	
	/**
	 * Returns a string that represents contents of an array
	 * 
	 * @param arr double array
	 * @return String representing double array
	 */
	public static String arrayToString(double[] arr) {
		String strArray = "{"; 
		
		for (int pos = 0; pos < arr.length; pos++) {
			if (pos < arr.length - 1) {
				strArray += (arr[pos] + ", ");
			}
			else {
				strArray += (arr[pos] + "}");
			}
			 
		}
		return strArray; 
	}
	
	/**
	 * Checks to see if there are any duplicate values in the array
	 * 
	 * @param arr String array
	 * @return true if array contains duplicate value
	 */
	public static boolean containsDuplicate(String[] arr) {
		int startPos = 0; 
		int counter = 0; 
		
		for (String curr : arr) {
			String testVal = arr[startPos]; 
			if (testVal.equals(curr)) {
				counter++; 
			}
			
		}
		return (counter == 2); 
	}
	
	/**
	 * Returns double value that is equal to the average of all values in array
	 * 
	 * @param arr int array with at least length 1.
	 * @return
	 */
	public static double averageArrayValues(int[] arr) {
		int total = 0; 
		
		for (int val : arr) {
			total += val; 
		}
		return total / arr.length; 
	}
	
	/**
	 * Returns 10 element array that shows frequency of ints 0-9 in array. 
	 * 
	 * @param arr int array that only contains elements 0-9
	 * @return new array of ten integers representing frequency of n in array. 
	 */
	public static int[] frequencyCount(int[] arr) {
		int[] freqArray =  new int[10];  //{0,0,0,0,0,0,0,0,0}; 
		
		for (int curVal : arr) {
			freqArray[curVal] += 1; 
		}
		
		return freqArray; 
	}
	
	/**
	 * Returns double array that has parameter elements reversed.
	 *  
	 * @param arr double array 
	 * @return double array with param array elements reversed
	 */
	public static double[] reverseSound(double[] arr) {
		double[] reverse = new double[arr.length];
		int posRev = 0; 
		
		for (int posArr = (arr.length-1); posArr >= 0; posArr--) {
			reverse[posRev] = arr[posArr]; 
			posRev++; 
		}
		
		return reverse;
		
	}
	
	/**
	 * Returns double array that has each of the parameter elements scales by the scale param. 

	 * @param arr double array
	 * @param scale double value, how much array should be scaled. 
	 * @return new double array that has been scaled according to scale parameter
	 */
	public static double[] scaleSound(double[] arr, double scale) {
		double[] scaled = new double[arr.length]; 
		int pos = 0; 
		
		for (double val : arr) {
			val *= scale; 
			scaled[pos] = val; 
			pos++; 
		}
		
		return scaled;
	}
	
	/**
	 * Creates an echo of the sound starting at given offset, and with given weight.
	 * 
	 * @param arr double array 
	 * @param offset	how many samples offset the echo starts at
	 * @param weight	weight of the echo1
	 * @return double array as long as array parameter plus offset parameter
	 */
	public static double[] echoSound(double[] arr, int offset, double weight) {
		double[] echo = new double[arr.length + offset];
		
		for (int echoPos = 0; echoPos < echo.length; echoPos++) {
			if (echoPos >= arr.length) {
				echo[echoPos] = arr[arr.length-1] * weight; 
			}
			else if (echoPos < offset) {
				echo[echoPos] = arr[echoPos]; 
			}
			else {
				echo[echoPos] = arr[echoPos] + (arr[echoPos-offset] * weight); 
			}
		}
		
		return echo;
	}
	
	/**
	 * For all values in double array except for first and last, averages the values of the array with each values preceding 
	 * and succeeding values. 
	 * 
	 * @param double array with at least three values.
	 * @return double array, smoothed sound. 
	 */
	public static double[] smoothSound(double[] arr) {
		double[] smooth = new double[arr.length];
		
		smooth[0] = arr[0]; 
		smooth[smooth.length-1] = arr[arr.length -1]; 
		
		for (int pos = 1; pos < arr.length -1; pos++) {
			smooth[pos] = (arr[pos] + arr[pos+1] + arr[pos-1]) / 3;
			
		}
		
		return smooth;
	}
	

}
