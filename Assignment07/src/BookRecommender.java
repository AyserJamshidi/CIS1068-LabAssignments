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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("all")
public class BookRecommender {

    private final static String BOOKS_LIST_PATH = "Assignment07/src/BookList.txt";
    private final static String RATINGS_LIST_PATH = "Assignment07/src/BookRatings.txt";

    public static void main(String[] args) {
        List<String> bookListContainer = fileToList(BOOKS_LIST_PATH);
        List<String> ratingListContainer = fileToList(RATINGS_LIST_PATH);

        // [user][user's ratings]
        int[][] databaseRatings = new int[ratingListContainer.size()][ratingListContainer.get(0).split(" ").length];

        for (int i = 0; i < ratingListContainer.size(); i++) {
            String[] StringToIntArray = ratingListContainer.get(i).split(" ");

            for (int j = 0; j < StringToIntArray.length; j++)
                databaseRatings[i][j] = Integer.parseInt(StringToIntArray[j]);
        }

        // array[bookRow][currentUserRatingColumn]
		//int[][] ratingList = assignRatingsToBooks(bookListContainer, ratingListContainer);//new int[bookListContainer.size()][ratingListContainer.size()];

        //int[] humanUserRatings = userInput(bookListContainer);
        int[] humanUserRatings = {1, 1, 2, 3, 5, 3, 1, -1, -1, -1, 4, 4, 5, 1, 2, 3, 1, 2, -1, -1};

        double[] similarityRatingsArr = new double[databaseRatings.length];
        int mostSimilarDbUserId = -1;

        for (int i = 0; i < similarityRatingsArr.length; i++) {
	        similarityRatingsArr[i] = cosineSimilarity(humanUserRatings, databaseRatings[i]);

	        if (similarityRatingsArr[i] > mostSimilarDbUserId)
	        	mostSimilarDbUserId = i;
        }

        System.out.println(Arrays.toString(similarityRatingsArr));
    }

    public static int[] stringToIntArray(String stringList) {
        String[] stringArr = stringList.split(" ");
        int[] intArr = new int[stringArr.length];

        for (int i = 0; i < intArr.length; i++)
            intArr[i] = Integer.parseInt(stringArr[i]);

        return intArr;
    }

    /**
     * Gets a cos similarity value comparing each read book between {@code humanUserRatings} and {@code databaseRatings}
     * Both arrays must have the same array length or it is impossible to get a cos similarity between them.
     *
     * @param humanUserRatings current human user's array of ratings
     * @param databaseRatings current database index's array of ratings.
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

        humanSum = Math.sqrt(humanSum); // Square both to get original value as abs
        databaseSum = Math.sqrt(databaseSum);

        double both = 0;

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

    public static int countFileLines(String filePath) {
        return fileToList(filePath).size();
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

	/*public static double weightedAverage(double[] A, int[][] booksAndRatings, int userNum) {
		double sumScoreWeightProduct = 0, sumWeights = 0;

		for (int bookNum = 0; bookNum < booksAndRatings.length; bookNum++) {
			if (booksAndRatings[bookNum][userNum] != -1) {
				sumScoreWeightProduct += (booksAndRatings[bookNum][userNum] * A[bookNum]);
				sumWeights += A[bookNum];
			}
		}

		return sumScoreWeightProduct / sumWeights;
	}*/
}


/*
1) Calculate 30 similarity scores,
2) "How similar am I to those people?"
3) Get 20 average ratings
4) The book you recommend is the book with the highest average rating that they HAVEN'T read

PARALLEL ARRAYS, for each book you have 30 user ratings

Books:
0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19

A -1 rating means this user hasn't read the book

Ratings:
-1 2 3 5 -1 5 3 3 1 4 2 2 5 -1 1 3 3 5 4 3
-1 1 1 4 1 3 3 1 2 3 4 -1 4 1 2 4 5 4 2 3
3 -1 2 3 -1 2 5 -1 3 3 5 2 2 1 2 3 5 3 4 2
-1 1 -1 4 1 3 5 2 1 5 3 -1 5 2 1 3 4 5 3 2
-1 -1 3 2 -1 5 5 2 2 4 4 2 3 2 -1 3 4 4 3 1
2 1 1 5 2 2 4 2 3 4 3 -1 5 2 2 5 3 5 2 1
3 -1 3 4 -1 2 5 -1 -1 4 3 -1 3 -1 2 5 5 5 4 2
4 -1 4 2 3 -1 1 3 4 -1 1 4 4 4 -1 2 -1 1 4 4
4 3 3 3 -1 2 2 4 3 -1 2 4 3 4 2 -1 -1 2 2 3
3 -1 3 -1 3 4 -1 5 5 -1 -1 -1 1 -1 -1 1 1 2 -1 5
3 -1 3 4 3 4 -1 5 5 2 3 3 4 1 1 -1 -1 -1 -1 4
4 -1 4 4 1 3 -1 5 4 -1 1 3 4 1 -1 1 -1 1 -1 5
5 -1 3 1 4 3 -1 5 4 1 3 2 1 -1 4 2 1 -1 2 4
3 -1 5 1 4 4 2 5 5 1 2 3 1 1 -1 1 -1 1 -1 5
4 1 5 4 3 -1 1 3 4 -1 -1 3 3 -1 1 1 2 -1 3 5
-1 1 1 3 -1 3 1 3 -1 -1 3 -1 5 2 2 1 4 -1 5 -1
3 -1 2 3 1 5 4 3 3 -1 5 -1 5 2 -1 4 4 3 3 3
1 1 1 3 2 4 1 -1 -1 -1 5 -1 3 -1 -1 1 -1 2 5 2
-1 2 3 5 -1 4 3 1 1 3 3 -1 4 -1 -1 4 3 2 5 1
-1 1 3 3 -1 3 3 1 -1 -1 3 -1 5 -1 -1 3 1 2 4 -1
3 -1 2 4 1 4 3 -1 2 3 4 1 3 -1 2 -1 4 3 5 -1
-1 1 3 5 -1 4 2 1 -1 3 3 2 3 2 -1 3 1 -1 3 -1
3 2 2 3 -1 5 -1 -1 2 3 4 -1 4 1 -1 -1 -1 -1 4 2
-1 3 -1 -1 4 -1 2 -1 2 2 2 5 -1 3 4 -1 -1 2 -1 2
1 4 3 -1 3 2 1 -1 -1 -1 1 3 1 3 3 1 -1 -1 -1 3
4 3 3 -1 4 2 -1 4 -1 -1 2 4 -1 3 4 2 -1 -1 -1 4
-1 5 1 -1 4 1 -1 3 2 2 -1 4 1 3 3 1 -1 -1 -1 3
-1 4 2 1 5 -1 -1 2 1 1 -1 5 -1 5 4 1 2 2 -1 1
2 5 2 -1 3 -1 -1 1 -1 2 -1 4 2 4 3 -1 2 1 -1 -1
2 5 1 1 4 -1 2 1 -1 -1 2 4 -1 3 4 2 -1 -1 -1 4
 */