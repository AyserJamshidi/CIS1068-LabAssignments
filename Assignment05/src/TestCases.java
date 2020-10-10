import java.util.*;

public class TestCases {
	/*
	 * 3 test cases for extra credit.
	 */
	public static void runTestCases() {
		testOne();
		testTwo();
		testThree();
	}

	/*
	 * For each of the tests, parallel arrays are used to easily keep track of each word and its expected output after
	 * Southie-conversion.
	 */

	/**
	 * The first test ensures all conversion function's proper conversion of basic and exception rules.
	 */
	private static void testOne() {
		String[] testWords = {"a", "aa", "deer", "veneers", "door", "very", "tuna", "cuba", "idea", "car", "harbor"};
		String[] expectedWords = {"a", "aar", "deeyah", "veneehs", "doowah", "wicked", "tunar", "cubar", "idear",
				"cah", "hahboh"};

		System.out.println(SouthieConverters.endingConversion("r", "deer"));
		wordLoop(testWords, expectedWords);

		System.out.println("Test one has passed.");
	}

	/**
	 * The second test ensures all conversion function's maintaining of case-sensitivity.
	 */
	private static void testTwo() {
		String[] testWords = {"A", "Aa", "DeEr", "VeNeErs", "DoOr", "VERY", "TuNA", "cUBA", "iDeA", "CAR", "HARBOR"};
		String[] expectedWords = {"A", "Aar", "DeEyah", "VeNeEhs", "DoOwah", "WICKED", "TuNAR", "cUBAR", "iDeAR",
				"CAH", "HAHBOH"};

		wordLoop(testWords, expectedWords);

		System.out.println("Test two has passed.");
	}

	/**
	 *
	 */
	private static void testThree() {
		String containerBook = "\"Here’s more red hair on the door,\" said Chick. \"I wonder if this is bleached?\"\n";
		String[] testy = containerBook.split(" ");

		String containerBookOutcome = "\"Hehe’s mohe red haiyah on the dooh,\" said Chick. \"I wondeh if this is bleached?\"\n";
		String[] expectedOutcome = containerBookOutcome.split(" ");
		wordLoop(testy, expectedOutcome);

		System.out.println("Test three has passed.");
	}

	/**
	 * Similar loop to the one in SouthieConverters main function.
	 */
	private static void wordLoop(String[] s1, String[] s2) {
		for (int i = 0; i < s1.length; i++) {
			String currentWord = s1[i];

			if (currentWord.length() > 1) {
				String lastLetter = SouthieConverters.getLastLetter(currentWord.toLowerCase());

				switch (lastLetter) {
					case "y": {
						if (SouthieConverters.removeSpecialWordCompareEquals(currentWord, "very"))
							compare(SouthieConverters.endingConversion("very", currentWord), s2[i]);
						else // A word that isn't "very" that also ends in y
							compare(SouthieConverters.letterRtoH(currentWord), s2[i]);
						break;
					}
					case "r": {
						compare(SouthieConverters.letterRtoH(
								SouthieConverters.endingConversion(lastLetter, currentWord)), s2[i]);
						break;
					}
					case "a": {
						compare(SouthieConverters.endingConversion(lastLetter,
								SouthieConverters.letterRtoH(currentWord)), s2[i]);
						break;
					}
					default: {
						compare(SouthieConverters.letterRtoH(currentWord), s2[i]);
						break;
					}
				}
			}
		}
	}

	private static void compare(String s1, String s2) {
		if (!s1.equals(s2))
			errorOccured("\n\nERROR: \n--Received " + s1 + "\n\n--Expected " + s2);
	}

	private static void errorOccured(String s) {
		System.out.println(s);

		System.exit(-1);
	}
}
