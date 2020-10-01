/*
 * Name: Ayser Jamshidi
 * Class: CIS 1068
 * Section: 4
 * Date: October 5th, 2020
 * Assignment: 5. Southie Styles
 *
 * Description:
 * https://cis.temple.edu/~jfiore/2020/fall/1068/assignments/05/
 * -This class file contains everything needed to read text from a file, convert it to "Southie Accent" and then
 *  output it.
 */

/*
 * Basic Rules:
 * -If there's an 'r' following a vowel ('a', 'e', i', 'o', or 'u'), replace 'r' with 'h'.
 *  For example, "I left my car keys by the harbor" becomes "I left my cah keys by the hahboh."
 * -If a word ends in 'a', append an 'r'. For example "tuna" becomes "tunar", "Cuba" becomes "Cubar", and "idea" becomes
 *  "idear". (Don't change this to an 'h' based on the previous rule; leave it as an 'r'.) Do not apply this rule to the
 *  word "a", so "a tuna" should become "a tunar", not "ar tunar".
 * -Replace the word "very" with the word "wicked". So "very hard" becomes "wicked hahd".
 *
 * Exceptions:
 * -If 'r' is at the end of a word and is preceded by "ee" or 'i' replace 'r' with "yah" instead of 'h'.
 *   For example, "deer" becomes "deeyah" instead of "deeh", but "veneers" still becomes "veneehs".
 * -If 'r' is at the end of a word and is preceded by "oo", replace 'r' with "wah". For example, "door" becomes
 *   "doowah" instead of "dooh" (but "doors" still becomes "doohs").
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class SouthieStyles {

	@SuppressWarnings("all")
	public static void main(String[] args) throws FileNotFoundException {
		String mainClassLocation = SouthieStyles.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		File bookFile = new File("Assignment05\\src\\The_Great_Diamond_Syndicate_Test.txt");
		PrintStream outputStream = new PrintStream("Assignment05\\src\\Testyyyy.txt");
		Scanner bookScan = new Scanner(bookFile);

		StringBuilder testContainer = new StringBuilder();
		StringBuilder editedBookText = new StringBuilder();

		while (bookScan.hasNextLine()) {
			testContainer.append(bookScan.nextLine() + " {SPLIT} ");
		}

		// Every ODD # element is a quote.
		String[] quotesArray = testContainer.toString().replaceAll("“", "\"").replaceAll("”", "\"").split("\"");

		for (int quoteIndex = 0; quoteIndex < quotesArray.length; quoteIndex++) {
			String[] sentenceWordArray;

			if ((quoteIndex % 2) != 0) { // Odd index, it's a quote
				sentenceWordArray = quotesArray[quoteIndex].split(" ");

				editedBookText.append("\""); // Beginning quote

				for (int wordIndex = 0; wordIndex < (sentenceWordArray.length); wordIndex++) {
					if (rIsLastLetter(sentenceWordArray[wordIndex])) {
						editedBookText.append(letterRtoH(sentenceWordArray[wordIndex]));
					} else {
						editedBookText.append(sentenceWordArray[wordIndex]);
					}

					if (wordIndex != (sentenceWordArray.length - 1))
						editedBookText.append(" "); // Space after word.
				}

				editedBookText.append("\""); // Ending quote

			} else { // Even index, it's normal text we can skip.
				editedBookText.append(quotesArray[quoteIndex]);
			}
		}

		outputStream.print(editedBookText.toString().replaceAll(" \\{SPLIT} ", "\r\n"));
		outputStream.close();

		System.out.println(editedBookText.toString().replace(" {SPLIT} ", "\r\n"));

		// Revision 1
		/*while (bookScan.hasNextLine()) {
			currentLineText = bookScan.nextLine();

			// Check if the current line has any quotes.  If not, then there's no work to be done.
			if (StringFunctions.containsQuotes(currentLineText)) { // Only editing text inside of quotes for extra credit.
				String currentWorkingWithText;

				//currentWorkingWithText = currentLineText.substring();
			} else {
				System.out.println(currentLineText);
			}
		}*/

		// Revision 2
		/*
		String currentLineText;
		StringBuilder outputText = new StringBuilder();
		StringBuilder currentQuotedText = new StringBuilder();
		boolean waitForClosingQuote = false;

		while (bookScan.hasNextLine()) {
			currentLineText = bookScan.nextLine();

			// Check if currentLineText contains quotes, if false, just print out.
			if (StringFunctions.containsQuotes(currentLineText)) {
				for (char currentChar : currentLineText.toCharArray()) {
					if (StringFunctions.isQuote(currentChar)) { // We're starting/ending a quote!
						waitForClosingQuote = !waitForClosingQuote;
						outputText.append(currentChar);
					} else if (waitForClosingQuote) { // We're inside of a quote!
						currentQuotedText.append(currentChar);
					}
				}
			} else if (!waitForClosingQuote) {
				outputText.append(currentLineText);
			} // Skip so we can get to or edit the next line(s) for a closing quote.
		} */
	}

	public static String testRConversion(String s) {
		StringBuilder lowercaseContainer = new StringBuilder(s.toLowerCase()).reverse();

		if (lowercaseContainer.indexOf("r") == 0) {
			String[] yahExceptions = { "ee", "i" };

			for (String curException : yahExceptions) {
				if (lowercaseContainer.toString().startsWith(curException, 0)) {
					// Fill in later
				}
			}
		}


		return s;
	}


	/*
	 *
	 */
	public static int indexOfQuotes(String givenString, boolean firstIndex) {
		char[] quoteArray = {'"', '“', '”'}; // Normal quote, opening quote, closing quote.
		int quoteDifferenceCounter = 0;

		/*for (char currentQuote : quoteArray)
			if (givenString.indexOf(currentQuote) != -1)
				quoteDifferenceCounter++;

		if (quoteDifferenceCounter > 1) {
			System.out.println("We've encountered more than 1 different quote!!  How do we handle this?!");
			System.exit(-1);
		}*/

		for (char currentQuote : quoteArray) {
			int foundIndex = (firstIndex) ? givenString.indexOf(currentQuote) : givenString.lastIndexOf(currentQuote);

			if (foundIndex != -1)
				return foundIndex;
		}

		// We found no quotes
		return -1;
	}

	public static String removeSpecialCharacters(String givenString) {
		return givenString.replaceAll("[/:;*\")(<>|?!.,]", "");
	}

	public static boolean rIsLastLetter(String testStr) {
		return testStr.toLowerCase().endsWith("r");//removeSpecialCharacters(testStr.toLowerCase()).endsWith("r");
	}

	public static String letterRtoH(String givenString) {
		String vowels = "aeiouAEIOU";

		for (int i = 0; i < vowels.length(); i++) {
			char currentVowel = vowels.charAt(i);
			String currentVowelAndR = currentVowel + "r";

			if (givenString.contains(currentVowelAndR)) { // ar, er, ir, or, ur
				givenString = givenString.replaceAll(currentVowelAndR, currentVowel + "h");
			}
		}

		return givenString;
	}

	public static boolean endWithLetterA(String givenString) {
		StringBuilder reversedString = new StringBuilder(removeSpecialCharacters(givenString.toLowerCase())).reverse();
		return reversedString.indexOf("a") == 0;
	}

	public static String endingAtoH(String givenString) {

		return givenString;
	}

	/*public static String rVowelConversionByReversal(String givenString) {
		String chaBeforeR = "aeiouAEIOU";
		StringBuilder reversedString = new StringBuilder(givenString).reverse();
		StringBuilder loweredReversedString = new StringBuilder(givenString.toLowerCase()).reverse();

		String replacementLetters = "";

		if (loweredReversedString.indexOf("ree") != -1) {

		} else if (loweredReversedString.indexOf("ri") != -1) {

		} else {
			System.out.println("Hi!");
			int lowerRindex = loweredReversedString.indexOf("r") + 1;
			int higherRindex = loweredReversedString.indexOf("R");

			replacementLetters = (reversedString.indexOf("R") != -1) ? "H" : "h";

			reversedString.replace(lowerRindex - 1, lowerRindex, replacementLetters);

		}

		return reversedString.reverse().toString();
	}*/
}
