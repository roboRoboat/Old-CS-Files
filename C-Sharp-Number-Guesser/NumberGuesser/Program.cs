using System;

//  Namespace
namespace NumberGuesser
{   //Main Class
    class Program
    {
        //Entry Point Method
        static void Main(string[] args)
        {
            GetAppInfo(); 

            GreetUser(); 

            // Create a new random object
            Random random = new Random();

            // Init Correct Number  
            int correctNumber = random.Next(1, 10);

            // Init guess variable
           
            while (true)
            {
                int guess = 0;
                // Ask user for number
                Console.WriteLine("Guess a number between 1 and 10");

                // While guess is not correct

            
                while (guess != correctNumber)
                {
                    //Get user input
                    string input = Console.ReadLine();

                    // Check user input and make sure it is a number 
                    if (!int.TryParse(input, out guess))
                    {
                        PrintColorMessage(ConsoleColor.Red, "Please enter a number between 1-10"); 
                        continue;
                    }

                    // Case to int and put in guess
                    guess = Int32.Parse(input);

                    //Match guess to correct number

                    if (guess != correctNumber)
                    {
                        //Print error message 
                        PrintColorMessage(ConsoleColor.Cyan, "Wrong number, guess again!"); 

                    }

                }

                PrintColorMessage(ConsoleColor.Yellow, "You guessed the right number!"); 

                //Ask user if they want to play again
                Console.WriteLine("Play again? [Y or N]");

                string answer = Console.ReadLine().ToUpper();

                if (answer == "Y")
                {
                    continue; 
                }
                else if (answer == "N")
                {
                    return; 
                }
                else
                {
                    return; 
                }

            }

        }
        static void GetAppInfo()
        {
            // Set app vars
            string appName = "Number Guesser";
            string appVersion = "1.0.0";
            string appAuthor = "Christopher Drake";

            // Change text color
            Console.ForegroundColor = ConsoleColor.Green;

            //Write out app info
            Console.WriteLine("{0}: Verson {1} by {2}", appName, appVersion, appAuthor);

            // Reset text color
            Console.ResetColor();
        }

        static void GreetUser()
        {
            // Ask user's name 
            Console.WriteLine("What is your name? ");

            // Get user input 
            String inputName = Console.ReadLine();

            Console.WriteLine("Hello {0}, let's play a game!", inputName);
        }

        static void PrintColorMessage(ConsoleColor color, string message)
        {
            Console.ForegroundColor = color; 

            //Tell user wrong guess
            Console.WriteLine(message);

            // Reset text color
            Console.ResetColor();
        }
    }
}
