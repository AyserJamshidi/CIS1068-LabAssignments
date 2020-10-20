import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookRecommender {

	private final static String BOOKS_LIST_PATH = "Assignment07\\src\\BookList.txt";
	private final static String RATINGS_LIST_PATH = "Assignment07\\src\\BookRatings.txt";

	public static void main(String[] args) {
		File bookListFile = new File(BOOKS_LIST_PATH);
		File ratingListFile = new File(RATINGS_LIST_PATH);

		int bookListAmount = countFileLines(BOOKS_LIST_PATH);
		int ratingListAmount = countFileLines(RATINGS_LIST_PATH);

		String[] bookList = setBookList(BOOKS_LIST_PATH, bookListAmount);

		// array[bookRow][currentUserRatingColumn]
		int[][] testArr = {new int[bookListAmount], new int[ratingListAmount]};

		userInput(bookList);
	}

	public static void userInput(String[] bookList) {
		Scanner in = new Scanner(System.in);
		int[] currentUserRatings = new int[20];

		System.out.println("Input a rating from 1-5, or -1 if you haven't read the book.");
		for (int i = 0; i < bookList.length; i++) {
			System.out.print("Enter a rating for \"" + bookList[i] + "\": ");

			while ((currentUserRatings[i] != -1 && currentUserRatings[i] < 1) || 5 < currentUserRatings[i]) {
				currentUserRatings[i] = in.nextInt();
			}

		}
	}

	public static int countFileLines(String filePath) {
		int lineCount = 0;

		try {
			Scanner file = new Scanner(new File(filePath));

			while (file.hasNextLine()) {
				file.nextLine();
				lineCount++;
			}
		} catch (FileNotFoundException e) {
			System.out.println("The file " + filePath + " couldn't be read!");
			System.exit(-1);
		}

		return lineCount;
	}

	public static String[] setBookList(String filePath, int arrayLength) {
		String[] test = new String[arrayLength];


		try {
			Scanner in = new Scanner(new File(filePath));

			for (int i = 0; in.hasNextLine() && i < arrayLength; i++) {
				test[i] = in.nextLine();
			}
		} catch (FileNotFoundException e) {
			System.out.println("The file " + filePath + " couldn't be read!");
		}

		return test;
	}
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