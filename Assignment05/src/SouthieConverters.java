public class SouthieConverters {
	/**
	 * Converts a String {@code s} that ends in {@code inputToReplace} (not including special characters) into a
	 * Southie accent
	 *
	 * @param inputToReplace the String to search for and eventually replace.
	 * @param s              the String to convert to a Southie accent.
	 * @return the String, converted to a Southie accent.
	 */
	public static String endingConversion(String inputToReplace, String s) {
		String lowercaseContainer = s.toLowerCase();
		String replacementText = "";
		StringBuilder outputString = new StringBuilder(s);

		/*
		 * There's no need to check if this index != -1 as this function assumes this was checked before
		 * calling it.
		 */
		int lastLetterIndex = lowercaseContainer.lastIndexOf(inputToReplace);

		switch (inputToReplace) {
			case "r": {
				if (lastLetterIndex == (s.length() - 1)) { // Check if we're searching for the last letter
					String[] prefixExceptions = {"ee", "i", "oo"};

					for (int i = 0; i < prefixExceptions.length; i++) {
						int exIndex = lowercaseContainer.lastIndexOf(prefixExceptions[i] + inputToReplace);

						if (exIndex != -1 && ((lastLetterIndex - exIndex) <= prefixExceptions[i].length())) {
							// (i <= 1) checks to see if we're still in exception #1.  False means we're in #2
							replacementText = (i <= 1) ? "yah" : "wah";
						}
					}
				}
				break;
			}
			case "a":
				replacementText = "ar";
				break;
			case "very":
				replacementText = "wicked";
				break;
		}

		// Fix casing for replacement and then replace
		if (Character.isUpperCase(s.charAt(lastLetterIndex))) { // First letter of found index is uppercase
			if (Character.isUpperCase(s.charAt(s.length() - 1))) { // First AND last letter are uppercase
				replacementText = replacementText.toUpperCase();
			} else { // It's a word with only first letter being uppercase.
				replacementText = (Character.toString(replacementText.charAt(0)).toUpperCase() + replacementText.substring(1));
			}
		}

		if (replacementText.length() > 0)
			outputString.replace(lastLetterIndex, (lastLetterIndex + s.length()), replacementText);

		// Return
		return outputString.toString();
	}

	/**
	 * Returns the last letter of {@code s} while ignoring special characters.
	 *
	 * @param s the String to get the last letter of.
	 * @return The last letter of {@code s}.
	 */
	public static String getLastLetter(String s) {
		String cleanedString = removeSpecialCharacters(s);
		return Character.toString(cleanedString.charAt(cleanedString.length() - 1));
	}

	/**
	 * Removes the characters <b>/:;*")(<>|?!.,</b> from {@code s}.
	 *
	 * @param s the String to remove special characters from.
	 * @return {@code s} with the characters <b>/:;*")(<>|?!.,</b> removed.
	 */
	public static String removeSpecialCharacters(String s) {
		return s.replaceAll("[/:;*\")(<>|?!.,]", "");
	}

	/**
	 * Removes all special characters (hopefully) in {@code s1} and {@code s2}, then checks if they're the same word
	 * while being case-insensitive.
	 *
	 * @param s1 the first String to use in the comparison.
	 * @param s2 the second String to use in the comparison.
	 * @return <b>true</b> if {@code s1} equals {@code s2} while ignoring casing, otherwise <b>false</b>.
	 */
	public static boolean removeSpecialWordCompareEquals(String s1, String s2) {
		return removeSpecialCharacters(s1.toLowerCase()).equals(removeSpecialCharacters(s2.toLowerCase()));
	}

	/**
	 * Replaces every "r" letter that's prefixed by a vowel with a case-sensitive "h".
	 *
	 * @param s the String to replace all "r" to "h".
	 * @return the String with replaced "r"s
	 */
	public static String letterRtoH(String s) {
		StringBuilder outputString = new StringBuilder(s);
		String sLowercase = s.toLowerCase();

		for (String currentPrefix : new String[]{"ar", "er", "ir", "or", "ur"}) {
			int foundIndex = sLowercase.indexOf(currentPrefix);

			while (foundIndex != -1) {
				int indexOfR = foundIndex + 1; // We add 1 to point to the letter R in the current prefix.

				outputString.replace(indexOfR, foundIndex + 2, // First index, Last index
						Character.isUpperCase(s.charAt(indexOfR)) ? "H" : "h"); // Proper letter replacement
				foundIndex = sLowercase.indexOf(currentPrefix, indexOfR); // Find next occurrance, if any.
			}
		}

		return outputString.toString();
	}
}
