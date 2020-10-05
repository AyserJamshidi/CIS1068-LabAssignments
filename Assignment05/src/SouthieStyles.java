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

	public static final String SPLITTER = "{SPLIT}";

	@SuppressWarnings("all")
	public static void main(String[] args) {
		File bookFile = new File("Assignment05\\src\\The_Great_Diamond_Syndicate.txt");
		StringBuilder southieAccentedBook = new StringBuilder();

		try {
			Scanner bookScan = new Scanner(bookFile);
			StringBuilder bookContainer = new StringBuilder();

			/*
			 * Instead of converting words in-line immediately, we store the entire text into a variable to use
			 * replace and split functions later on.  We use StringBuilder and then convert to a String later as
			 * many String concatinations will have a performance impact as opposed to just appending this way.
			 */
			while (bookScan.hasNextLine()) {
				bookContainer.append(bookScan.nextLine() + "{SPLIT}");
			}

			/*
			 * The plaintext book chosen contains open and closing quotes apart from the standard quote character.  To
			 * avoid having to take into account many different kinds of quotes, all open/closing quotes are replaced
			 * with the standard quote character then used as a delimiter to receive an array containing the following
			 * structure: { "unquoted text", "quoted text", "unquoted text", "quoted text", ... }, assuming the first
			 * character in the plain text file isn't a quote.
			 */
			String[] quotesArray = bookContainer.toString().replaceAll("“", "\"").replaceAll("”", "\"").split("\"");

			for (int quoteIndex = 0; quoteIndex < quotesArray.length; quoteIndex++) {
				if ((quoteIndex % 2) != 0) { // Odd index, it's a quote
					String[] sentenceWordArray = quotesArray[quoteIndex].split(" ");

					southieAccentedBook.append("\""); // Beginning quote

					/*
					 *
					 * Order:
					 * 1) very -> wicked
					 * 2) r exceptions (oor, eer, ir)
					 * 3) r -> h
					 * 4) a -> ar
					 *
					 * The reason we use a standard for loop is to keep track of the current index so we can check
					 * if we're at the last word in the sentence's word array. Doing so allows us to properly place
					 * spaces after each word apart from the very last one.
					 */
					for (String currentWord : sentenceWordArray) {
						// We check the length to avoid changing single letters like I, a, etc.
						if (currentWord.length() > 1) {
							String lastLetter = getLastLetter(currentWord.toLowerCase());

							switch (lastLetter) {
								case "y": {
									if (removeSpecialWordCompareEquals(currentWord, "very"))
										southieAccentedBook.append(wordConversion("very", currentWord));
									else
										southieAccentedBook.append(letterRtoH(currentWord));
									break;
								}
								case "r": {
									southieAccentedBook.append(letterRtoH(wordConversion(lastLetter, currentWord)));
									break;
								}
								case "a": {
									southieAccentedBook.append(wordConversion(lastLetter, currentWord));
									break;
								}
								default: { // No exceptions found, just do standard r -> h replacement
									southieAccentedBook.append(letterRtoH(currentWord));
									break;
								}
							}
						} else { // We don't change words that are one letter.
							southieAccentedBook.append(currentWord);
						}

						// Apply a space before every word except the last one.
						if (!currentWord.equals(sentenceWordArray[sentenceWordArray.length - 1]))
							southieAccentedBook.append(" "); // Space after word.
					}

					southieAccentedBook.append("\""); // Ending quote

				} else { // Even index, it's normal text we can skip.
					southieAccentedBook.append(quotesArray[quoteIndex]);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("The file could not be found.");
			System.exit(-1);
		}

		try {
			PrintStream outputStream = new PrintStream("Assignment05\\src\\Testyyyy.txt");

			outputStream.print(southieAccentedBook.toString().replace(SPLITTER, "\r\n"));
			outputStream.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occured while trying to create the Southie accented file output.");
			System.exit(-1);
		}

		System.out.println(southieAccentedBook.toString().replace(SPLITTER, "\r\n"));
	}

	/**
	 * Converts a String that ends in {@code inputToReplace} (not including special characters) into a Southie accent
	 *
	 * @param inputToReplace the String to search for and eventually replace.
	 * @param s              the String to convert to a Southie accent.
	 * @return the String, converted to a Southie accent.
	 */
	public static String wordConversion(String inputToReplace, String s) {
		String lowercaseContainer = s.toLowerCase();
		String replacementText = "";
		StringBuilder outputString = new StringBuilder(s);

		/*
		 * There's no need to check if this index != -1 as this function assumes this was checked before
		 * calling it.
		 */
		int lastLetterIndex = lowercaseContainer.lastIndexOf(inputToReplace);

		switch (inputToReplace) {
			case "r" -> {
				if (lastLetterIndex == (s.length() - 1)) { // Check if we're searching for the last letter
					String[] prefixExceptions = {"ee", "i", "oo"};
					for (int i = 0; i < prefixExceptions.length; i++) {
						int exIndex = lowercaseContainer.lastIndexOf(prefixExceptions[i] + inputToReplace);

						if (exIndex != -1 && ((lastLetterIndex - exIndex) <= prefixExceptions[i].length())) {
							/*
							 * We check to see if the prefix is still "ee" or "i" for exception #1, otherwise
							 * we're now in exception #2
							 */
							replacementText = (i <= 1) ? "yah" : "wah";
							break;
						}
					}
				}
			}
			case "a" -> replacementText = "ar";
			case "very" -> {
				replacementText = "wicked";
			}
		}

		// Fix casing for replacement
		if (Character.isUpperCase(s.charAt(lastLetterIndex))) { // first letter of found index is uppercase
			if (inputToReplace.length() == 1 || Character.isUpperCase(s.charAt(s.length() - 1))) { // Is one letter OR first letter AND last letter are uppercase
				replacementText = replacementText.toUpperCase();
			} else { // It's a word with only first letter being uppercase.
				replacementText = (Character.toString(replacementText.charAt(0)).toUpperCase() + replacementText.substring(1));
			}
		}

		// Finally replace
		//if (replacementText.length() > 0)
			outputString.replace(lastLetterIndex, (lastLetterIndex + s.length()), replacementText);

		// Return
		return outputString.toString();
	}

	public static String getLastLetter(String s) {
		String cleanedString = removeSpecialCharacters(s);
//		int cleanedStrLength = cleanedString.length();
		return Character.toString(cleanedString.charAt(cleanedString.length() - 1));//(cleanedStrLength >= 1) ? Character.toString(cleanedString.charAt(cleanedStrLength - 1)) : s;
	}

	public static String removeSpecialCharacters(String givenString) {
		return givenString.replaceAll("[/:;*\")(<>|?!.,]", "");
	}

	public static boolean removeSpecialWordCompareEquals(String s1, String s2) {
		return removeSpecialCharacters(s1.toLowerCase()).equals(removeSpecialCharacters(s2.toLowerCase()));
	}

	public static String letterRtoH(String s) {
		String[] vowelPrefix = {"ar", "er", "ir", "or", "ur"};
		StringBuilder outputString = new StringBuilder(s);

		for (String currentPrefix : vowelPrefix) {
			int prefixIndex = s.toLowerCase().indexOf(currentPrefix);

			// Check if the prefix exists
			if (prefixIndex != -1) {
				String replacementLetter = Character.isUpperCase(s.charAt(prefixIndex + 1)) ? "H" : "h";
				outputString.replace(prefixIndex + 1, prefixIndex + 2, replacementLetter);
			}
		}

		return outputString.toString();
	}
}
