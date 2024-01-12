package cs1410;

import java.util.Scanner;

/**
 * A collection of methods for the third assignment of CS 1410.
 * 
 * @author Christopher Drake
 */
public class MethodLibrary
{
    /**
     * You can use this main method for experimenting with your methods if you like, but it is not part of the
     * assignment.
     */
    public static void main (String[] args)
    {

    }

    /**
     * Returns the life stage of a person, given his or her age. The possible return values are "baby" (age is less than
     * 2), "child" (age is between 2 and 12 inclusive), "teen" (age is between 13 and 17 inclusive), "adult" (age is
     * between 18 and 64 inclusive), and "senior" (age is greater than 64).
     * 
     * Examples: getLifeStage(25) is "adult" and getLifeStage(0) is "baby".
     * 
     * IMPLEMENTATION NOTE: Use a 5-way conditional
     * 
     * @param age Must be non-negative
     */
    public static String getLifeStage (int age)
    {
        String lifeStage;

        if (age < 2)
        {
            lifeStage = "baby";
        }
        else if (age >= 2 && age <= 12)
        {
            lifeStage = "child";
        }
        else if (age >= 13 && age <= 17)
        {
            lifeStage = "teen";
        }
        else if (age >= 18 && age <= 64)
        {
            lifeStage = "adult";
        }
        else
        {
            lifeStage = "senior";
        }
        return lifeStage;
    }

    /**
     * Returns the index within s of the first vowel ('a', 'e', 'i', 'o', 'u' or an upper-case version) that occurs in
     * s. If there is no vowel in s, returns -1.
     * 
     * Examples: firstVowelIndex("Apple") is 0, firstVowelIndex("hello") is 1, firstVowelIndex("slope") is 2,
     * firstVowelIndex("strength") is 4, and firstVowelIndex("xyzzy") is -1.
     * 
     * IMPLEMENTATION NOTE: This method is already completely implemented. There is no need for you to change anything.
     */
    public static int firstVowelIndex (String s)
    {
        int i = 0;
        while (i < s.length())
        {
            if ("aeiouAEIOU".indexOf(s.charAt(i)) >= 0)
            {
                return i;
            }
            i = i + 1;
        }
        return -1;
    }

    /**
     * Returns the result of converting s to "Pig Latin". Convert a string s to Pig Latin by using the following rules:
     * 
     * (1) If s contains no vowels, do nothing to it.
     * 
     * (2) Otherwise, if s starts with a vowel, append "way" to the end.
     * 
     * (3) Otherwise, move everything up to (but not including) the first vowel to the end and add "ay".
     * 
     * For example, "hello" converts to "ellohay", "small" converts to "allsmay", "eat" converts to "eatway", and "nth"
     * converts to "nth".
     * 
     * IMPLEMENTATION NOTE: This will require a three-way conditional that extracts pieces of Strings and recombines
     * them into new Strings. For full credit (and an easier implementation), use the firstVowelIndex method provided
     * above in your method's implementation. You will also find the methods s.substring() and s.charAt() (where s is a
     * String), as well as the + operator that concatenates Strings, very useful.
     */
    public static String toPigLatin (String s)
    {
        int firstVowel = firstVowelIndex(s);
        String firstBit;
        String lastBit;
        String piggyString;

        if (firstVowel == 0)
        {
            piggyString = s + "way";
        }
        else if (firstVowel == -1)
        {
            piggyString = s;
        }
        else
        {
            firstBit = s.substring(0, (firstVowelIndex(s)));
            lastBit = s.substring((firstVowelIndex(s)), s.length());
            piggyString = lastBit + firstBit + "ay";
        }

        return piggyString;
    }

    /**
     * Returns the result of converting each token from words into Pig Latin and then appending the results, with each
     * converted word followed by a single space character. A token is a sequence of letters separated from other tokens
     * by white space. If there are no tokens, returns the empty string.
     * 
     * Examples: "" converts to "" and "This is a test" converts to "isThay isway away esttay ".
     * 
     * IMPLEMENTATION NOTE: Use a Scanner to split the string into individual tokens and use an accumulation loop to
     * consume the tokens. Make use of your toPigLatin() method.
     * 
     * @param words Must consist of only letters and white space
     */
    public static String convertToPigLatin (String words)
    {

        Scanner scn = new Scanner(words);
        String currSentence = toPigLatin(scn.next());

        while (scn.hasNext())
        {
            String piggyWord = scn.next();
            currSentence = currSentence + " " + toPigLatin(piggyWord);
        }
        scn.close();
        return currSentence;
    }

    /**
     * Returns the sum of the nth roots of each double x in numbers, where numbers consists of zero or more double
     * tokens (separated by white space) and n is positive. A small around of roundoff error is to be expected.
     * 
     * Examples: sumOfRoots("1.0 4.0 9.0 16.0", 2) is 10 and sumOfRoots("", 3) is 0.
     * 
     * IMPLEMENTATION NOTE: Write this as an accumulation loop.
     * 
     * @param numbers Must consist of only double literals and white space
     * @param n       must be positive
     */
    public static double sumOfRoots (String numbers, int n)
    {
        Scanner scn = new Scanner(numbers);
        double sum = 0;
        double nthRt = (1 / n);

        while (scn.hasNext())
        {
            String nextNum = scn.next();
            Double newNum = Double.parseDouble(nextNum);
            Double newRoot = Math.pow(newNum, (1.0 / n));
            sum = sum + newRoot;
        }

        return sum;
    }

    /**
     * Reports whether or not every character in source occurs at least once in target.
     * 
     * Examples: containsAll("abc", "abracadabra") is true, and containsAll("def", "Defect") is false.
     * 
     * IMPLEMENTATION NOTE: Write this as an accumulation loop. Don't try to write a doubly-nested loop!
     */
    public static boolean containsAll (String source, String target)
    {
        int numChars = source.length();
        // System.out.println(numChars);
        int charCount = 0;

        for (int i = 0; i < numChars; i++)
        {
            char currChar = source.charAt(i);
            if (target.indexOf(currChar) != -1)
            {
                charCount++;
            }
        }

        if (charCount == numChars)
        {
            return true;
        }

        return false;
    }

    /**
     * Returns a String of length width that begins and ends with the character edge and contains width-2 copies of the
     * character inner in between. The method does not print anything.
     * 
     * Example makeLine('+', '-', 8) is "+------+".
     * 
     * IMPLEMENTATION NOTE: Use an accumulation loop
     * 
     * @params width must be >= 2
     */
    public static String makeLine (char edge, char inner, int width)
    {
        String newLine = "";
        newLine = newLine + edge;
        for (int i = 0; i < width; i++)
        {
            newLine = newLine + inner;
        }
        newLine = newLine + edge;

        return newLine;
    }

    /**
     * Returns a string which, when printed out, will be a rectangle shaped something like this:
     * 
     * <pre>
     * +----------+
     * |          |
     * |          |
     * |          |
     * |          |
     * |          |
     * +----------+
     * </pre>
     * 
     * The returned string should consist of height lines, each ending with a newline. In addition to the newline, the
     * first and last lines should begin and end with '+' and should contain width-2 '-' symbols. In addition to the
     * newline, the other lines should begin and end with '|' and should contain width-2 spaces. The method does not
     * print anything.
     * 
     * IMPLEMENTATION NOTE: For full credit (and for easier implementation), make use of the makeLine method in your
     * implementation of makeRectangle. Use an accumulation loop.
     * 
     * @param height must be >= 2
     * @param width  must be >= 2
     */
    public static String makeRectangle (int height, int width)
    {
        String newRectangle = "";
        String ends = makeLine('+', '-', width);
        newRectangle = newRectangle + ends;

        String middle = ("|");
        for (int i = 0; i < width; i++)
        {
            middle = middle + " ";
        }
        middle = middle + "|";

        for (int j = 0; j < height; j++)
        {
            newRectangle = newRectangle + "\n" + middle;
        }
        newRectangle = newRectangle + "\n" + ends;

        return newRectangle;
    }

    /**
     * Returns the integer that follows n in a Hailstone sequence. If n is 1, returns 1. Otherwise, returns either n/2
     * (if n is even) or 3n+1 (if n is odd).
     * 
     * Examples: nextHailstone(1) is 1, nextHailstone(7) is 22, and nextHailstone(6) is 3,
     * 
     * IMPLEMENTATION NOTE: This one will require a three-way conditional
     * 
     * @param n must be positive
     */
    public static int nextHailstone (int n)
    {
        int nextStone = 0;
        if (n == 1)
        {
            nextStone = 1;
        }
        else if ((n % 2) == 0)
        {
            nextStone = n / 2;
        }
        else
        {
            nextStone = (3 * n) + 1;
        }
        return nextStone;
    }

    /**
     * Returns a string consisting of a Hailstone sequence beginning with the positive integer n and ending with 1. The
     * string should consist of a sequence of numerals, with each numeral followed by a single space. When a numeral m
     * (other than 1) appears in the sequence, it should be followed by nextHailstone(m).
     * 
     * Examples: nextHailstone(1) is "1 " and nextHailstone(5) is "5 16 8 4 2 1 ".
     * 
     * IMPLEMENTATION NOTE: Make use of your nextHailstone method. Use an accumulation loop, but with a bit of a twist.
     * 
     * @param n must be positive
     */
    public static String hailstones (int n)
    {
        String stoneSeq = Integer.toString(n);
        int newStoneVal = n;

        while (newStoneVal != 1)
        {
            newStoneVal = nextHailstone(n);
            n = newStoneVal;
            stoneSeq = stoneSeq + " " + Integer.toString(newStoneVal);
        }

        return stoneSeq;

    }

    /**
     * Reports whether or not s1 and s2 contain exactly the same tokens in the same order.
     * 
     * Examples: <br>
     * sameTokens("this is a test", " this is a test ") is true <br>
     * sameTokens("", "") is true <br>
     * sameTokens("hello there", "hello there Joe") is false <br>
     * sameTokens("abc def", "def abc") is false <br>
     * sameTokens("a", "A") is false
     *
     * IMPLEMENTATION NOTE: You can write this as an accumulation loop with a twist. Be aware of the implications of the
     * short-circuited evaluation of &&.
     */
    public static boolean sameTokens (String s1, String s2)
    {
        String str1 = "";
        String str2 = "";
        // System.out.println(str2);
        // System.out.println(test2);

        Scanner scn = new Scanner(s1);

        while (scn.hasNext())
        {
            str1 = str1 + (scn.next());
        }

        Scanner scn2 = new Scanner(s2);

        while (scn2.hasNext())
        {
            str2 = str2 + (scn2.next());
        }

        if (str1.equals(str2))
        {
            return true;
        }
        ;

        return false;
    }
}
