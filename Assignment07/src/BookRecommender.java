/*
 * Name: Ayser Jamshidi
 * Class: CIS 1068
 * Section: 4
 * Date: October 26th, 2020
 * Assignment: 7. Book Recommender
 *
 * Description:
 * https://cis.temple.edu/~jfiore/2020/fall/1068/assignments/06/
 * -This class file has array manipulation functions that will be tested upon submission.
 * -All test cases are implemented straight into the main function as the submission template
 *  claims "main = +6 points" so I am under the assumption this is where the tests should be.
 *
 * Requirements:
 * x 18 points Load the 20 book names and the book ratings from 30 people into two arrays in memory.
 *   These can be read by your program using the Scanner class. You are not required to handle FileNotFoundException.
 * x 10 points Ask the user to enter a rating (between 1 and 5, or -1 if they haven't read it) for each book.
 * x 18 points Create a method that determines for each of the 30 people a score, which represents how similar
 *   that person's tastes are to the taste of the user of the program. Store these similarity scores in an array of
 *   30 doubles. The similarity scores should be between 0 and 1 each.
 * - 18 points Create an array that represents recommended ratings for the user. There should be 20 numbers in this
 *   array, one for each book. The higher the number, the more strongly your program thinks the user will like the book.
 *   The number should be the average over all 30 ratings for the book that are greater than 0 (only include ratings for
 *   users who have actually rated the book). However, it should be a weighted average: people who are more similar to
 *   the current user should have a higher weight than people who are less similar.
 * - 16 points Display the name of the top book (according to the recommended ratings from the previous step) that the
 *   user has not yet read.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

@SuppressWarnings("all")
public class BookRecommender {

	private final static String BOOKS_LIST_PATH = "Assignment07/src/BookList.txt"; // Book file directory
	private final static String RATINGS_LIST_PATH = "Assignment07/src/BookRatings.txt"; // Ratings file directory

	public static void main(String[] args) {
		List<String> bookListContainer = fileToList(BOOKS_LIST_PATH); // Put all the book names into a String list
		List<String> ratingListContainer = fileToList(RATINGS_LIST_PATH); // Put all ratings into a String list

		// Put all the ratings in the format of: [user index (30)][user's ratings array (20)]
		int[][] databaseRatings = new int[ratingListContainer.size()][ratingListContainer.get(0).split(" ").length];
		assignDatabaseRatings(databaseRatings, ratingListContainer);

		// Get current user's ratings for each book
		//int[] humanUserRatings = userInput(bookListContainer);
		int[] humanUserRatings = {1, 1, 1, 2, 2, 4, 3, 2, 2, -1, 1, 1, 1, 3, 4, 5, 2, 1, 2, 3};

		// Get cosine similarities for all users compared to current user
		double[] cosSimilarityRatings = new double[databaseRatings.length]; // 30 user similarity ratings
		assignSimilarityRatings(cosSimilarityRatings, humanUserRatings, databaseRatings);

		// Get weighted averages of each book taking into consideration each user's cosine similarities
		double[] bookRatingsWeighted = weightedAverage(cosSimilarityRatings, databaseRatings);

		// Output!
		int returnedBookIndex = getBestBookIndex(bookRatingsWeighted, humanUserRatings);

		if (returnedBookIndex == -1)
			System.out.println("You've read every book!  There's nothing for you to read...");
		else
			System.out.println("You should read \"" + bookListContainer.get(getBestBookIndex(bookRatingsWeighted, humanUserRatings)) + "\"!");
	}

	public static int getBestBookIndex(double[] weightedBookRatings, int[] humanUserRatings) {
		double highestRating = -1;
		int highestRatedBookIndex = -1;

		for (int i = 0; i < weightedBookRatings.length; i++) // Loop through every book
			if (humanUserRatings[i] == -1 && weightedBookRatings[i] > highestRating) // If we haven't read the book and this book is better than the previous
				if (weightedBookRatings[i] > highestRating) { // A better book was found!
					highestRating = weightedBookRatings[i];
					highestRatedBookIndex = i;
				}

		return highestRatedBookIndex;
	}

	public static void assignSimilarityRatings(double[] cosSimilarityRatings, int[] humanUserRatings, int[][] databaseRatings) {
		for (int i = 0; i < cosSimilarityRatings.length; i++) // Loop through every rating
			// Set the current element to the proper cosine similarity rating with respect to index
			cosSimilarityRatings[i] = cosineSimilarity(humanUserRatings, databaseRatings[i]);
	}

	public static void assignDatabaseRatings(int[][] databaseRatings, List<String> ratingListContainer) {
		// Since all of the ratings are strings, we parse each int individually.
		for (int i = 0; i < ratingListContainer.size(); i++) { // Cycle through every user
			String[] StringToIntArray = ratingListContainer.get(i).split(" ");

			for (int j = 0; j < StringToIntArray.length; j++) // Cycle through every rating this user has
				databaseRatings[i][j] = Integer.parseInt(StringToIntArray[j]); // Put it into the proper array
		}
	}

	public static int[] stringToIntArray(String stringList) {
		// Check the string to ensure we don't try working on an empty string and explode later on
		if (stringList.length() <= 0)
			return null;

		String[] stringArr = stringList.split(" "); // Put all of the string-integers into a string array

		int[] intArr = new int[stringArr.length]; // Create an int array to return

		for (int i = 0; i < intArr.length; i++) // Loop through every string
			intArr[i] = Integer.parseInt(stringArr[i]); // Convert the string-integer into a string

		return intArr;
	}

	/**
	 * Gets a cos similarity value comparing each read book between {@code humanUserRatings} and {@code databaseRatings}
	 * Both arrays must have the same array length or it is impossible to get a cos similarity between them.
	 *
	 * @param humanUserRatings current human user's array of ratings
	 * @param databaseRatings  current database index's array of ratings.
	 * @return A double at the intervals [0, 1] or -1 if the array lengths aren't equal
	 */
	public static double cosineSimilarity(int[] humanUserRatings, int[] databaseRatings) {
		// Ensure if both arrays exist and comparable
		if (humanUserRatings == null || databaseRatings == null || humanUserRatings.length != databaseRatings.length)
			return -1;

		// Variable init for human and database
		double humanSum = 0, databaseSum = 0;
		List<Integer> similarBooksIndexes = new ArrayList<>();

		for (int i = 0; i < humanUserRatings.length; i++) { // Loop through every rating
			if (humanUserRatings[i] != -1) // Square current rating for human's ratings
				humanSum += Math.pow(humanUserRatings[i], 2);

			if (databaseRatings[i] != -1) // Square current rating for current database user's ratings
				databaseSum += Math.pow(databaseRatings[i], 2);

			if (humanUserRatings[i] != -1 && databaseRatings[i] != -1) // The human and the current user read this book
				similarBooksIndexes.add(i);
		}

		humanSum = Math.sqrt(humanSum); // Square (human and database)Sum to get original value as abs
		databaseSum = Math.sqrt(databaseSum);

		double both = 0;

		// Calculate the numerator
		for (int bookIndex : similarBooksIndexes)
			both += (humanUserRatings[bookIndex] * databaseRatings[bookIndex]);

		return both / (humanSum * databaseSum);
	}

	public static int[][] assignRatingsToBooks(List<String> books, List<String> ratings) {
		int[][] booksAndRatings = new int[books.size()][ratings.size()];

		for (int book = 0, user = 0; user < ratings.size(); user++, book = 0) // Loop the amount of lines of RATINGS_LIST_PATH file
			for (String curStrInteger : ratings.get(user).split(" ")) { // Array of Integers as Strings
				booksAndRatings[book][user] = Integer.parseInt(curStrInteger);
				book++;
			}

		return booksAndRatings;
	}

	public static int[] userInput(List<String> bookList) {
		int[] currentUserRatings = new int[bookList.size()];
		Scanner in = new Scanner(System.in);

		for (int i = 0; i < bookList.size(); i++) {
			boolean hasInputIncorrectly = false;

			while ((currentUserRatings[i] != -1 && currentUserRatings[i] < 1) || 5 < currentUserRatings[i]) {
				if (hasInputIncorrectly)
					System.out.println(currentUserRatings[i] + " is not a proper rating.");

				System.out.print("Input a rating from 1-5, or -1 if you haven't read \"" + bookList.get(i) + "\": ");

				currentUserRatings[i] = in.nextInt();
				hasInputIncorrectly = true;
				System.out.println();
			}
		}

		return currentUserRatings;
	}

	public static List<String> fileToList(String filePath) {
		List<String> returnedList = new ArrayList<>();

		try {
			BufferedReader in = new BufferedReader(new FileReader(filePath));

			for (String curLine = in.readLine(); curLine != null; curLine = in.readLine())
				returnedList.add(curLine);

		} catch (Exception e) {
			System.out.println("The file " + filePath + " couldn't be read!");
		}

		return returnedList;
	}

	/**
	 * @param cosSimilarities
	 * @param databaseRatings
	 * @return
	 */
	public static double[] weightedAverage(double[] cosSimilarities, int[][] databaseRatings) {
		// Ensure we're working with good arrays...
		if (cosSimilarities == null || databaseRatings == null || cosSimilarities.length <= 0 || databaseRatings.length <= 0)
			return null;

		double[] returningArray = new double[databaseRatings[0].length]; // Set it to the amount of books

		// for book 0, get all 30 ratings: (((user 0 rating * user 0 cos similarity) + (user 1 rating * user 1 cos similarity) + ...)) / (user 0 cos similarity + user 1 cos similarity + ...)
		for (double bookIndex = 0, sumOfWeightedScores = 0, sumOfWeights = 0; bookIndex < databaseRatings[0].length; bookIndex++, sumOfWeightedScores = 0, sumOfWeights = 0) { // Cycle through every book
			for (int dbUserIndex = 0; dbUserIndex < databaseRatings.length; dbUserIndex++) // Cycle through every db user
				if (databaseRatings[dbUserIndex][(int) bookIndex] != -1) { // Ensure the current db user has read the book
					sumOfWeightedScores += (databaseRatings[dbUserIndex][(int) bookIndex] * cosSimilarities[dbUserIndex]); // user's book index rating * user's cos similarity
					sumOfWeights += cosSimilarities[dbUserIndex]; // add weight to 
				}

			returningArray[(int) bookIndex] = sumOfWeightedScores / sumOfWeights;
		}

		return returningArray;
	}
}