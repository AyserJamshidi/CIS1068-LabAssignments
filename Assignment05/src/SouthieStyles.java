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
 *
 * The output of the file will contain a properly accented file with the following:
 *  1) Basic rules, listed below this comment block (+10 points)
 *      - Completed
 *  2) Exceptions, listed below this comment block (+10 points)
 *      - Completed
 *  3) Input and Output, listed below this comment block (+10 points)
 *      - Completed (with proper exception handling)
 *  1) Changing quotes only (+10 points, extra credit)
 *      - Completed
 *  2) Preserving capitalization (+5 points, extra credit)
 *      - Completed
 *  3) Preserve spacing, puctuation, etc. (+5 points, extra credit)
 *      - Completed
 */

/*
 * Basic Rules:
 * - If there's an 'r' following a vowel ('a', 'e', i', 'o', or 'u'), replace 'r' with 'h'.
 *   For example, "I left my car keys by the harbor" becomes "I left my cah keys by the hahboh."
 * - If a word ends in 'a', append an 'r'. For example "tuna" becomes "tunar", "Cuba" becomes "Cubar", and "idea"
 *   becomes "idear". (Don't change this to an 'h' based on the previous rule; leave it as an 'r'.) Do not apply
 *   this rule to the word "a", so "a tuna" should become "a tunar", not "ar tunar".
 * - Replace the word "very" with the word "wicked". So "very hard" becomes "wicked hahd".
 *
 * Exceptions:
 * #1 - If 'r' is at the end of a word and is preceded by "ee" or 'i' replace 'r' with "yah" instead of 'h'.
 *      For example, "deer" becomes "deeyah" instead of "deeh", but "veneers" still becomes "veneehs".
 * #2 - If 'r' is at the end of a word and is preceded by "oo", replace 'r' with "wah". For example, "door" becomes
 *      "doowah" instead of "dooh" (but "doors" still becomes "doohs").
 *
 * Input and Output:
 * -Your program will read take it input from a file using Scanner, and will write its output using PrintStream,
 *  just as we've done in class. You are not required to handle exceptions, but remember that for any method you
 *  write that uses File, you'll need to add throws FileNotFoundException to the top of the method definition.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class SouthieStyles {

	public static final String fileDirectory = "Assignment05\\src\\";
	public static final String fileName = "The_Great_Diamond_Syndicate.txt";

	public static void main(String[] args) {
		TestCases.runTestCases(); // Runs 3 test-cases to ensure that the code is running as expected.

		File bookFile = new File(fileDirectory + fileName);
		StringBuilder southieAccentedBook = new StringBuilder();

		try {
			Scanner bookScan = new Scanner(bookFile);
			StringBuilder bookContainer = new StringBuilder();
			PrintStream outputStream = new PrintStream(fileDirectory + "OUTPUT_" + fileName);

			/*
			 * Instead of converting words in-line immediately, we store the entire text into a StringBuilder variable
			 * to utilize its replace and split functions, then converting it to a String later on.
			 */
			while (bookScan.hasNextLine()) {
				bookContainer.append(bookScan.nextLine()).append("\r\n");
			}

			/*
			 * The plaintext book chosen contains open and closing quotes apart from the standard quote character.  To
			 * avoid having to take into account many different kinds of quotes, all open/closing quotes are replaced
			 * with the standard quote character then used as a delimiter to receive an array containing the following
			 * structure: { "unquoted text", "quoted text", "unquoted text", "quoted text", ... }, assuming the first
			 * character in the plain text file isn't a quote, otherwise the array starts with a quote.
			 */
			String[] quotesArray = bookContainer.toString().replaceAll("[“”]", "\"").split("\"");
			boolean isQuotedText = (quotesArray[0].charAt(0) == '"');

			for (String currentString : quotesArray) {
				if (isQuotedText) {
					String[] sentenceWordArray = currentString.split(" ");

					southieAccentedBook.append('"'); // Beginning quote

					/*
					 * Order:
					 * 1) very -> wicked
					 * 2) r exceptions (oor, eer, ir)
					 * 3) r -> h
					 * 4) a -> ar
					 */
					for (String currentWord : sentenceWordArray) {
						// We check the length to avoid changing single letters like I, a, etc.
						if (currentWord.length() > 1) {
							String lastLetter = SouthieConverters.getLastLetter(currentWord.toLowerCase());

							switch (lastLetter) {
								case "y": {
									if (SouthieConverters.removeSpecialWordCompareEquals(currentWord, "very"))
										southieAccentedBook.append(SouthieConverters.endingConversion("very", currentWord));
									else // A word that isn't "very" that also ends in y
										southieAccentedBook.append(SouthieConverters.letterRtoH(currentWord));
									break;
								}
								case "r":
									southieAccentedBook.append(SouthieConverters.letterRtoH(
											SouthieConverters.endingConversion(lastLetter, currentWord)));
									break;
								case "a":
									southieAccentedBook.append(SouthieConverters.endingConversion(lastLetter,
											SouthieConverters.letterRtoH(currentWord)));
									break;
								// No exceptions found, just do standard r -> h replacement
								default:
									southieAccentedBook.append(SouthieConverters.letterRtoH(currentWord));
									break;

							}
						} else { // We don't change words that are one letter.
							southieAccentedBook.append(currentWord);
						}

						// Apply a space before every word except the last one.
						if (!currentWord.equals(sentenceWordArray[sentenceWordArray.length - 1]))
							southieAccentedBook.append(" "); // Space after word.
					}

					southieAccentedBook.append('"'); // Ending quote
				} else {
					southieAccentedBook.append(currentString);
				}

				isQuotedText = !isQuotedText;
			}

			outputStream.print(southieAccentedBook.toString());
			outputStream.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred while reading " + fileName + " or creating the output.\n" + e);
			System.exit(-1);
		}

		System.out.println(southieAccentedBook.toString());
	}
}