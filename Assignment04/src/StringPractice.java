/*
Name: Ayser Jamshidi
Class: CIS 1068
Section: 4
Date: September 28th, 2020
Assignment: 4. String Practice

Description:
https://cis.temple.edu/~jfiore/2020/fall/1068/assignments/04/
-This class file has String/char manipulation functions that will be tested upon submission.
-There is also a separate class TestCases to compare given and expected outputs of each function
to ensure working as expected functions.
*/

public class StringPractice {
	
	/*
	 * Used in multiple functions so it's better placed globally.
	 *
	 * I keep the face-card letters inside of a String instead of a char array to easily utilize
	 * indexing functions
	 */
	private static final String faceCards = "jqkaJQKA";
	
	/*
	 * The main function is dedicated to running test cases.
	 */
	public static void main(String[] args) {
		TestCases testCase = new TestCases();
		
		// Test 1-2, isFace(char)
		testCase.comparison(isFace('a'), true); // a for ace
		testCase.comparison(isFace('b'), false); // no face-card with b
		
		// Test 3-4, indexOfFirstFace(String)
		testCase.comparison(indexOfFirstFace("bbAj"), 2); // Has two faces
		testCase.comparison(indexOfFirstFace("bnajmn,vbnz"), 2); // Has face.
		
		// Test 5-7, indexofFirstFace(String, int)
		testCase.comparison(indexOfFirstFace("bnvbm,cbxmn,vbnz", 0), -1); // Has no face
		testCase.comparison(indexOfFirstFace("bnvbma,cbxmn,vbn", 14), -1); // Has face in too low of an index
		testCase.comparison(indexOfFirstFace("bnvbm,cbxmn,vbna", 14), 15); // Has face after index requirement
		
		// Test 8, indexOfLastFace(String)
		testCase.comparison(indexOfLastFace("VIKINGLIKE"), 8);
		
		// Test 9, reversed(String)
		testCase.comparison(reversed("Abc123 321cba"), "abc123 321cbA");
		
		// Test 10, numOccurrences(String)
		testCase.comparison(numOccurrences("Banana", "na"), 2);
		
		// Test 11-12, sameInReverse(String)
		testCase.comparison(sameInReverse("abc cba"), true);
		testCase.comparison(sameInReverse("abc c ba"), false);
		
		// Test 13, withoutFaces(String)
		testCase.comparison(withoutFaces("Hello Jack!"), "Hello c!");
		
		// Test 14-15, containsOnlyFaces(String)
		testCase.comparison(containsOnlyFaces("Jak"), true);
		testCase.comparison(containsOnlyFaces("Jack"), false);
		
		// Test 16-17, containsNoFaces(String)
		testCase.comparison(containsNoFaces("Jak"), false);
		testCase.comparison(containsNoFaces("Hello!"), true);
		
		// Test 18, lastFirst(String)
		testCase.comparison(lastFirst("Spongebob Squarepants"), "Squarepants, Spongebob");
	}
	
	/*
	 * returns true if c is one of the characters typically used to represent a "face card" in a
	 * standard deck of playing cards.
	 *
	 * These include: jack 'J' or 'j' queen 'Q' or 'q' king 'K' or 'k' ace 'A' or 'a
	 */
	public static boolean isFace(char c) {
		/*
		 * Checks if c is in faceCards char array.
		 * If c has an index != -1 (basically when >= 0), true, otherwise false
		 */
		return faceCards.indexOf(c) != -1;
	}
	
	private static int faceCardIndexer(boolean findFirst, String s, int startPosition) {
		/*
		 * Instead of making multiple separate functions for finding first/last and with/without index
		 * positions, I've decided to make this master indexer function.  While ugly, it's functional.
		 *
		 * returningIndex will be the length of s if tasked with finding the first index to compare it
		 * with lower values as making it -1 will never allow comparing any found indexes.
		 *
		 * returningIndex will be -1 when finding the last index as it's only increased.
		 */
		int returningIndex = (findFirst) ? s.length() : -1;
		
		// Loops all "faceCards" character to see if s contains it at any index.
		for (char currentFaceCard : faceCards.toCharArray()) { // Loop through every face-card.
			// Avoid repeatedly finding index.
			int currentCardIndex = (findFirst) ? s.indexOf(currentFaceCard, startPosition) : s.lastIndexOf(currentFaceCard);
			
			if (currentCardIndex != -1) // Face-card letter has been found!
				if (findFirst && (returningIndex > currentCardIndex)) //  Found a smaller index while finding first
					returningIndex = currentCardIndex;
				else if (returningIndex < currentCardIndex)  // Found a greater index while finding last
					returningIndex = currentCardIndex;
		}
		
		// Fixes return value if no face-card letter was found while looking for the first index..
		if (findFirst && (returningIndex == s.length()))
			returningIndex = -1;
		
		// No face-card letters in the given string
		return returningIndex;
	}
	
	/*
	 * returns the index of the first face-card letter in s or -1 if s contains no face-card letters
	 */
	public static int indexOfFirstFace(String s) {
		// Calls the face-card indexer with an index of 0 because of no starting index requirement..
		return faceCardIndexer(true, s, 0);
	}
	
	/*
	 * returns the index of the first occurrence of a face-card letter in s starting from index
	 * startPosition or -1 if there are none at index startPosition or later. Notice that this method
	 * has the same name as the previous one, but that it takes a different number of arguments. This
	 * is perfectly legal in Java. It's called "method overloading"
	 */
	public static int indexOfFirstFace(String s, int startPosition) {
		// Calls the face-card indexer with a starting position index
		return faceCardIndexer(true, s, startPosition);
	}
	
	/*
	 * returns the index of the last occurrence of a face-card letter in s or -1 if s contains none
	 */
	public static int indexOfLastFace(String s) {
		// Calls the face-card indexer with no starting position
		return faceCardIndexer(false, s, 0);
	}
	
	/*
	 * returns givenString in reverse. For example, if s is "Apple", the method returns the String "elppA"
	 */
	public static String reversed(String s) {
		/*
		 * StringBuilder is used solely for its reverse function.  The String s is appended into the
		 * newly created StringBuilder, reversed, converted to a string and then directly returned.
		 */
		return new StringBuilder().append(s).reverse().toString();
	}
	
	/*
	 * returns the number of times that n occurs in h. For example, if h is "Mississippi" and n is
	 * "ss" the method returns 2.
	 */
	public static int numOccurrences(String h, String n) {
		/*
		 * Instead of using a string split for h with n as its delimiter, this char loop will have a more
		 * complete and bug-free outcome.  For words like "Banana", if the delimiter is "na", it doesn't
		 * split properly.  See: https://support.omaticsoftware.com/support/discussions/topics/28000006540
		 *
		 * It is possible to split h and n and keep its length in a variable, along with splitting a
		 * reversed h and n and then return whichever is larger, but that would be an ugly and hacky way.
		 */
		int occurrenceAmount = 0;
		
		/*
		 * A for int loop is used instead of for each to keep track of the current index. Upon a
		 * successful comparison between h[i] and n[0] it checks if all subsequent characters of n exist
		 * in h starting at index i, increasing the occurrence amount if so.
		 */
		for (int i = 0; i < h.length(); i++) // We use a for loop instead of for each to keep track of current index.
			if (h.charAt(i) == n.charAt(0) && h.startsWith(n, i))
				occurrenceAmount++;
		
		return occurrenceAmount;
	}
	
	/*
	 * returns true if s is the same backwards and forwards and false otherwise
	 */
	public static boolean sameInReverse(String s) {
		// Reverses s and then checks if reversed s is equal to s.
		return reversed(s).equals(s);
	}
	
	/*
	 * returns a new String which is the same as s, but with all of the face-card letters removed.
	 */
	public static String withoutFaces(String s) {
		// Uses regex to replace all face-card letters with nothing.
		return s.replaceAll("[jqkaJQKA]", ""); // Use regex of face-card letters to remove them.
	}
	
	/*
	 * returns true if s consists only of face-card letters or false otherwise
	 */
	public static boolean containsOnlyFaces(String s) {
		/*
		 * Uses withoutFaces to remove all face-card letters and checks if the length is 0.
		 *
		 * This treats spaces as letters and expect s to have only face-card letters.
		 *
		 * If s contains only face-card letters the returned s length will be 0, hence, true.
		 */
		return withoutFaces(s).length() == 0;
	}
	
	/*
	 * returns true if s contains no face-card letters or false otherwise
	 */
	public static boolean containsNoFaces(String s) {
		/*
		 * This filters out s for any face-card letters and then compare it with the original s.
		 * It will return true if s is equal to an s without faces, otherwise false.
		 */
		return s.equals(withoutFaces(s));
	}
	
	/*
	 * Passed a reference to a person's name in the form FIRST_NAME LAST_NAME. The method returns a
	 * reference to a new String in the form LAST_NAME, FIRST_NAME.
	 *
	 * For example, if we were passed "Spongebob Squarepants", we'd return "Squarepants, Spongebob".
	 * You may assume that the method is passed exactly two words separated by a single space.
	 */
	public static String lastFirst(String s) {
		/*
		 * Creates a String array using a space as a delimiter to get the format of:
		 * Element 1 = FIRST_NAME
		 * Element 2 = LAST_NAME
		 *
		 * Which then returns these two in expected form of: Element 2 + ", " + Element 1
		 *
		 * As I am to assume s will ALWAYS be FIRST_NAME LAST_NAME there is no error handling to be done.
		 */
		String[] nameList = s.split(" ");
		return nameList[1] + ", " + nameList[0]; // lastname, firstname
	}
}

class TestCases {
	private int testsCounted = 0;
	
	public void comparison(boolean givenOutput, boolean expectedOutput) {
		if (givenOutput != expectedOutput)
			testFailed("Received " + givenOutput + ", expected " + expectedOutput);
		
		testPassed();
	}
	
	public void comparison(int givenOutput, int expectedOutput) {
		if (givenOutput != expectedOutput)
			testFailed("Received " + givenOutput + ", expected " + expectedOutput);
		
		testPassed();
	}
	
	public void comparison(String givenOutput, String expectedOutput) {
		if (!givenOutput.equals(expectedOutput))
			testFailed("Received " + givenOutput + ", expected " + expectedOutput);
		
		testPassed();
	}
	
	private void testFailed(String errorMessage) {
		testsCounted++;
		
		System.out.println("STEP " + testsCounted + ": " + errorMessage);
		System.exit(-1);
	}
	
	private void testPassed() {
		testsCounted++;
		
		System.out.println("Test " + testsCounted + " passed!");
	}
}