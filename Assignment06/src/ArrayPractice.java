import java.util.Arrays;
import java.util.Collections;
import java.util.stream.IntStream;

/**
 * @author Ayser Jamshidi
 */
public class ArrayPractice {

	public static void main(String[] args) {
		int[] testy = new int[10];
		initialize(testy, 3);

		for (int i : testy) {
			System.out.print(i + ", ");
		}

		// Test 2-3
		System.out.println("\nAverage: " + average(new int[]{1, 10})); // Expect 5.5
		System.out.println("Average: " + average(new int[]{0, 10})); // Expect 5

		// Test 3
		System.out.println("numOccurrences: " + numOccurrences(new int[]{0, 0, 0, 3}, 0)); // Expect 3
		System.out.println("numOccurrences: " + numOccurrences(new int[]{0, 2, 2, 6, 34, 2, 5}, 5)); // Expect 1

		// Test 4
		System.out.println("find: " + find(new int[]{0, 1, 7, 2, 3, 3, 4}, 7)); // Expect 2
		System.out.println("find: " + find(new int[]{0, 1, 7, 2, 3, 3, 4}, 3)); // Expect 4

		System.out.println("findN: " + findN(new int[]{0, 2, 3, 3, 3, 3, 6}, 6, 6)); // Expect -1
		System.out.println("findN: " + findN(new int[]{0, 2, 3, 3, 3, 3, 6}, 6, 7)); // Expect 6
		System.out.println("findN: " + findN(new int[]{0, 2, 3, 3, 3, 3, 6}, 7, 30)); // Expect -1

		System.out.println("findLast: " + findLast(new int[]{3, 1, 2, 5, 7, 6}, 6)); // Expect 5
		System.out.println("findLast: " + findLast(new int[]{3, 1, 2, 5, 7, 6}, 4)); // Expect -1

		System.out.println("largest: " + largest(new int[]{3, 3, 5, 7, 349, 1, 3, 6, 2, 2, 2, 361, 22}));

		System.out.println("indexOfLargest: " + indexOfLargest(new int[]{0, 1, 2, 33, 4, 56, 7, 8})); // Expect 5
		System.out.println("indexOfLargest: " + indexOfLargest(new int[]{0, -1, -2})); // Expect 0

		System.out.println("indexOfLargestOdd: " + indexOfLargestOdd(new int[]{0, 2, 4, 6, 8, 10})); // Expect -1
		System.out.println("indexOfLargestOdd: " + indexOfLargestOdd(new int[]{0, 2, 3, 8, 9, 10})); // Expect 4
		System.out.println("indexOfLargestOdd: " + indexOfLargestOdd(new int[]{9, -7, -3, 0, 8, 11, 10})); // Expect 5

		{
			System.out.print("copy: ");
			int[] A = new int[]{1, 3, 7, 7, 3};
			int[] B = copy(A);

			for (int i = 0; i < B.length; i++) {
				if (B[i] == A[i])
					System.out.print(B[i] + " ");
				else
					System.out.println("B[" + i + "] IS NOT GUCCI");
			}
			System.out.println("       Done.  Array references are the same == " + (A == B));
		}


		{
			System.out.print("copyN: ");
			int expectedLength = 30;
			int[] A = copyN(new int[]{1, 3, 4, 5, 7, 8, 9, 9, 9, 9}, expectedLength);

			for (int i : A)
				System.out.print(i + " ");
		}

		System.out.println();

		{
			System.out.print("copyOdds: ");
			int[] A = copyOdds(new int[]{-2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10});

			for (int i : A)
				if (i % 2 == 0)
					System.out.print("BAD NUMBER " + i + " WAS ADDED");
				else
					System.out.print(i + " ");
		}

		System.out.println();

		{
			int[] A = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
			for (int i : A)
				System.out.print(i + " ");
			System.out.println();
			int removed = remove(A, 3);

			for (int i : A)
				System.out.print(i + " ");

			System.out.println("        Removed number " + removed);
		}

		System.out.println();

		{
			int[] A = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
			for (int i : A)
				System.out.print(i + " ");
			System.out.println();

			shiftLeft(A);

			for (int i : A)
				System.out.print(i + " ");
		}

		System.out.println();
	}

	/* sets every item in the array A references to initialValue */
	public static void initialize(int[] A, int initialValue) {
		/*
		 * This loop sets every element in A beginning from index 0 to initialValue.
		 *
		 * This code does exactly what Arrays.fill does except without setting a variable to the array length
		 * for no reason.
		 */
		for (int curIndex = 0; curIndex < A.length; curIndex++)
			A[curIndex] = initialValue;
	}

	/*
	 * returns the average of the items in the array A references. Be careful: the array contains int
	 * but the method returns double. What do we do to handle this?
	 */
	public static double average(int[] A) {
		double total = 0; // Set the default output to 0 because if nothing is in the array, then return 0

		/*
		 *
		 */
		for (int curInt : A)
			total += curInt;

		return total / A.length;
	}

	/* returns the number of times that x appears in the array A references */
	public static int numOccurrences(int[] A, int x) {
		int total = 0;

		for (int curInt : A)
			if (curInt == x)
				total++;

		return total;
	}

	/*
	 * returns the index of the first occurrence of x in the array A references or -1 if x doesn't
	 * exist in the array
	 */
	public static int find(int[] A, int x) {
		for (int curIndex = 0; curIndex < A.length; curIndex++)
			if (A[curIndex] == x)
				return curIndex;
		return -1;
	}

	/*
	 * Returns the index of the first occurrence of item within the first n elements of the array A[]
	 * references or -1 if item is not among the first n elements of the array
	 */
	public static int findN(int[] A, int item, int n) {

		for (int curIndex = 0; (curIndex < n && curIndex < A.length); curIndex++)
			if (A[curIndex] == item)
				return curIndex;
		return -1;
	}

	/*
	 * returns the index of the last occurrence of x in the array A references or -1 if x doesn't
	 * exist in the array
	 */
	public static int findLast(int[] A, int x) {
		for (int curIndex = (A.length - 1); curIndex >= 0; curIndex--)
			if (A[curIndex] == x)
				return curIndex;
		return -1;
	}

	/* returns the largest item found in the array A references */
	public static int largest(int[] A) {
		int largestInt = Integer.MIN_VALUE;

		for (int curInt : A)
			if (curInt > largestInt)
				largestInt = curInt;

		return largestInt;
	}

	/* returns the index of the largest item found in the array A references */
	public static int indexOfLargest(int[] A) {
		int largestInt = Integer.MIN_VALUE, largestIntIndex = -1;

		for (int curIndex = 0; curIndex < A.length; curIndex++)
			if (A[curIndex] > largestInt) {
				largestInt = A[curIndex];
				largestIntIndex = curIndex;
			}

		return largestIntIndex;
	}

	/*
	 * returns the index of the largest odd number in the array A references or -1 if the array
	 * contains no odd numbers
	 */
	public static int indexOfLargestOdd(int[] A) {
		int largestOddInt = Integer.MIN_VALUE, largestOddIntIndex = -1;

		for (int curIndex = 0; curIndex < A.length; curIndex++) {
			int curNum = A[curIndex];
			if ((curNum % 2 != 0) && (curNum > largestOddInt)) {
				largestOddInt = curNum;
				largestOddIntIndex = curIndex;
			}
		}

		return largestOddIntIndex;
	}

	/*
	 * returns a new array consisting of all of the elements of A[]
	 */
	public static int[] copy(int[] A) {
		return A.clone();
	}

	/*
	 * Returns a reference to a new array consisting of all of the first n elements of A[]. If
	 * n>A.length, returns a reference to a new array of size n, with the first A.length elements
	 * exactly the same as A, and the remaining n-A.length elements set to 0. If n<=0, returns null.
	 */
	public static int[] copyN(int[] A, int n) {
		int[] B = (n >= 0) ? new int[n] : null;

		if (B != null)
			for (int i = 0; i < n; i++)
				if (i >= A.length)
					B[i] = 0;
				else
					B[i] = A[i];

		return B;
	}

	/*
	 * returns a reference to an array consisting of all of the elements of the array A references
	 * that are odd. If there are no odd integers in the array, the function returns null.
	 */
	public static int[] copyOdds(int[] A) { /// TODO: This could totally look less ugly
		int lengthOfNewArray = 0;

		for (int j : A)
			if (j % 2 != 0)
				lengthOfNewArray++;

		int[] B = new int[lengthOfNewArray];

		int currentLength = 0;

		for (int j : A)
			if (j % 2 != 0) {
				B[currentLength] = j;
				currentLength++;
			}

		return B;
	}

	/* removes and returns the item at index x shifting all elements at */
	/* indices > x one position to the left and filling in a 0 at the */
	/* right-most position in the array. */

	/* if x is an invalid index, returns -1. */

	/* For example, if before we call function with x = 2, */
	/* the the array is: */

	/* |----+----+----+----+----+----+----+----+----+-----| */
	/* | 10 | 20 | 30 | 40 | 50 | 60 | 70 | 80 | 90 | 100 | */
	/* |----+----+----+----+----+----+----+----+----+-----| */

	/* after the function finishes, the array is: */

	/* |----+----+----+----+----+----+----+----+-----+---| */
	/* | 10 | 20 | 40 | 50 | 60 | 70 | 80 | 90 | 100 | 0 | */
	/* |----+----+----+----+----+----+----+----+-----+---| */

	/* and the function returns 30 */
	public static int remove(int[] A, int x) {
		int removedNum = -1;

		if (x >= 0 && !(A.length <= x)) {
			int curShiftAmount = 0;

			// Skip index x and move everything to the left once.
			for (int i = 0; i < A.length; i++) {
				if (i == x) {
					curShiftAmount++;
					removedNum = A[i];
				} else
					A[i - curShiftAmount] = A[i];
			}

			// Sets the last index to 0 because everything shifted to the left.
			A[A.length - 1] = 0;
		}

		return removedNum;
	}


	/* shifts all elements of the array A references one position to the left, */
	/* removing the first element and filling in 0 from the right hand side. */

	/* For example, if before we call the function the the array is: */

	/* |----+----+----+----+----+----+----+----+----+-----| */
	/* | 10 | 20 | 30 | 40 | 50 | 60 | 70 | 80 | 90 | 100 | */
	/* |----+----+----+----+----+----+----+----+----+-----| */

	/* after the function finishes, the array is: */

	/* +----+----+----+----+----+----+----+----+-----|----| */
	/* | 20 | 30 | 40 | 50 | 60 | 70 | 80 | 90 | 100 | 0 | */
	/* +----+----+----+----+----+----+----+----+-----|----| */
	public static void shiftLeft(int[] A) {
		remove(A, 0);
	}


	/*
	 * returns true if A is in sorted ascending order and false otherwise
	 */
	public static boolean isSortedAscending(int[] A) {

		return false;
	}

	/* Returns the number of items in the array that A references starting at index x that are in */
	/* ascending sorted order. */

	/* For example, if the array is: */
	/* |----+----+---+---+---+---+----+----+----| */
	/* | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | */
	/* |----+----+---+---+---+---+----+----+----| */
	/* | 10 | 11 | 5 | 3 | 9 | 6 | 18 | 37 | 40 | */
	/* |----+----+---+---+---+---+----+----+----| */

	/* and x is 0, the function return 2, because 10 and 11 are in sorted order. */

	/* If x is 5, the function returns 4, because 6, 18, 37, and 40 are in sorted order. */

	/*
	 * If x is 2, the function returns 1.
	 *
	 */
	public static int sortedAscendingRun(int[] A, int x) {
		return -1;
	}

	/*
	 * returns a new array consisting of all of the elements of A[] followed by all of the elements of
	 * B[]. For example, if A[] is: {10,20,30} and B[] is: {5, 9, 38}, the method returns the array :
	 * {10,20,30,5,9,38}
	 */
	public static int[] copyAll(int[] A, int[] B) {
		return null;
	}

	/*
	 * reverses the order of the elements in A[]. For example, if A[] is: {10,20,30,40,50}, after the
	 * method, A[] would be {50,40,30,20,10}
	 */
	public static void reverse(int[] A) {
	}

	/*
	 * Extra credit:
	 *
	 * Returns a new array consisting of all of the elements of A, but with no duplicates. For
	 * example, if A[] is {10,20,5,32,5,10,9,32,8}, the method returns the array {10,20,5,32,9,8}
	 */
	public static int[] uniques(int[] A) {
		return null;
	}
}