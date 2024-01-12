import java.util.Scanner;

public class HiLo {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String playAgain = ""; 
		do {
			// 1. Generate a random number, 1-100
			int theNumber = (int)(Math.random() * 100 +1); 
			int guess = 0; 		
			//System.out.println( theNumber );
					
			while (guess != theNumber) {
				//2. Display a prompt asking the user to guess a number in that range. 
				System.out.println("Please enter a whole number between 1 and 100: ");
				// 3. Accept the user's guess as input. 
				guess = scan.nextInt(); 
				// 4. Compare the user's guess to the computer's number to see if the guess is 
				// 		too high, too low, or correct.
				// 5. Display the results on the screen. 
				// 6. Prompt the user to guess another number until they guess correctly. 
				System.out.println("You've guessed: " + guess + ".");
				
				if (guess > theNumber) {
					System.out.println(guess + " is too high. Try again!");
				}
				else if (guess < theNumber) {
					System.out.println(guess + " is too low. Try again!");
				}
				else {
					System.out.println(guess + " is correct! You got it!");
				}
			}
		// 7. Ask the user if they'd like to play again.
			System.out.println("Do you want to play again? (y/n); "); 
			playAgain = scan.next(); 
		} while(playAgain.equalsIgnoreCase("y"));
		
	}

}
