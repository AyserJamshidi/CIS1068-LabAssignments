/*
 * Name: Ayser Jamshidi
 * Class: CIS 1068
 * Section: 4
 * Date: October 16th, 2020
 * Assignment: 6. Array Practice
 *
 * Description:
 * https://cis.temple.edu/~jfiore/2020/fall/1068/assignments/06/
 * -This class file has array manipulation functions that will be tested upon submission.
 * -All test cases are implemented straight into the main function as the submission template
 *  claims "main = +6 points" so I am under the assumption this is where the tests should be.
 */

import java.util.Arrays;

/**
 * @author Ayser Jamshidi
 */
public class ArrayPractice {

	public static void main(String[] args) { // Test cases
		{
			int[] A = new int[10];
			initialize(A, 3);

			System.out.println("initialize: " + Arrays.toString(A)); // Expect [3, 3, 3, 3, 3, 3, 3, 3, 3, 3]
		}

		System.out.println("Average: " + average(new int[]{1, 10})); // Expect 5.5
		System.out.println("Average: " + average(new int[]{0, 10})); // Expect 5

		System.out.println("numOccurrences: " + numOccurrences(new int[]{0, 0, 0, 3}, 0)); // Expect 3
		System.out.println("numOccurrences: " + numOccurrences(new int[]{0, 2, 2, 6, 34, 2, 5}, 5)); // Expect 1

		System.out.println("find: " + find(new int[]{0, 1, 7, 2, 3, 3, 4}, 7)); // Expect 2
		System.out.println("find: " + find(new int[]{0, 1, 7, 2, 3, 3, 4}, 3)); // Expect 4

		System.out.println("findN: " + findN(new int[]{0, 2, 3, 3, 3, 3, 6}, 6, 6)); // Expect -1
		System.out.println("findN: " + findN(new int[]{0, 2, 3, 3, 3, 3, 6}, 6, 7)); // Expect 6
		System.out.println("findN: " + findN(new int[]{0, 2, 3, 3, 3, 3, 6}, 7, 30)); // Expect -1

		System.out.println("findLast: " + findLast(new int[]{3, 1, 2, 5, 7, 6}, 6)); // Expect 5
		System.out.println("findLast: " + findLast(new int[]{3, 1, 2, 5, 7, 6}, 4)); // Expect -1

		System.out.println("largest: " + largest(new int[]{3, 3, 5, 7, 349, 1, 3, 6, 2, 2, 2, 361, 22})); // Expect 361

		System.out.println("indexOfLargest: " + indexOfLargest(new int[]{0, 1, 2, 33, 4, 56, 7, 8})); // Expect 5
		System.out.println("indexOfLargest: " + indexOfLargest(new int[]{0, -1, -2})); // Expect 0

		System.out.println("indexOfLargestOdd: " + indexOfLargestOdd(new int[]{0, 2, 4, 6, 8, 10})); // Expect -1
		System.out.println("indexOfLargestOdd: " + indexOfLargestOdd(new int[]{0, 2, 3, 8, 9, 10})); // Expect 4
		System.out.println("indexOfLargestOdd: " + indexOfLargestOdd(new int[]{9, -7, -3, 0, 8, 11, 10})); // Expect 5

		{
			System.out.print("copy: ");
			int[] A = new int[]{1, 3, 0, 0, 7, 7, 3};
			int[] B = copy(A);

			System.out.print(Arrays.toString(B)); // Expect [1, 3, 0, 0, 7, 7, 3]

			System.out.println("-- Array references are the same == " + (A == B));
		}

		System.out.println("copyN: " + Arrays.toString(copyN(new int[]{1, 3, 4, 5, 7, 8, 9, 9, 9, 9}, 15))); // Expect [1, 3, 4, 5, 7, 8, 9, 9, 9, 9, 0, 0, 0, 0, 0]

		System.out.println("copyOdds: " + Arrays.toString(copyOdds(new int[]{-2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10}))); // [-1, 1, 3, 5, 7, 9]


		{
			int[] A = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
			int removed = remove(A, 3);

			System.out.println("removed: " + Arrays.toString(A) + " -- Removed number " + removed); // Expect [0, 1, 2, 4, 5, 6, 7, 8, 9, 0] -- Removed number 3
		}

		{
			System.out.print("shiftLeft: ");
			int[] A = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
			shiftLeft(A);

			System.out.println(Arrays.toString(A)); // Expect [1, 2, 3, 4, 5, 6, 7, 8, 9, 0]
		}

		{
			System.out.println("isSortedAscending: " +
					isSortedAscending(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9})); // Expect true
			System.out.println("isSortedAscending: " +
					isSortedAscending(new int[]{0, 1, 2, 3, 2, 5, 6, 7, 8, 9})); // Expect false
			System.out.println("isSortedAscending: " +
					sortedAscendingRun(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, 4)); // Expect 6
			System.out.println("isSortedAscending: " +
					sortedAscendingRun(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, -1)); // Expect 0
			System.out.println("isSortedAscending: " +
					sortedAscendingRun(new int[]{0, 1, 2, 3, 2, 5, 6, 7, 8, 9}, 2)); // Expect 2
		}

		{
			System.out.println("uniques: " + Arrays.toString(uniques(
					new int[]{3, -1, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 4, 4, 5, 4, -1, 9, 11, 10}))); // Expect [3, -1, 0, 1, 2, 4, 5, 9, 11, 10]
		}
	}

	/* sets every item in the array A references to initialValue */
	public static void initialize(int[] A, int initialValue) {
		// Exit the function if the array is null or has no elements
		if (A == null || A.length <= 0)
			return;

		// The array exists and has elements. Set every element to initialValue.
		for (int i = 0; i < A.length; i++)
			A[i] = initialValue;
	}

	/*
	 * returns the average of the items in the array A references. Be careful: the array contains int
	 * but the method returns double. What do we do to handle this?
	 */
	public static double average(int[] A) {
		if (A == null || A.length <= 0)
			return 0;

		// We set total to a double instead of an int as it's possible to get a non-integer number after averaging
		double total = 0;

		// Add every element in the array to total
		for (int curInt : A)
			total += curInt;

		// Return the average of total
		return total / A.length;
	}

	/* returns the number of times that x appears in the array A references */
	public static int numOccurrences(int[] A, int x) {
		if (A == null || A.length <= 0)
			return 0;

		int occurrences = 0;

		// Loop every element in the array and increment the occurrence count if the element is the same as x.
		for (int curInt : A)
			if (curInt == x)
				occurrences++;

		return occurrences;
	}

	/*
	 * returns the index of the first occurrence of x in the array A references or -1 if x doesn't
	 * exist in the array
	 */
	public static int find(int[] A, int x) {
		if (A == null || A.length <= 0)
			return -1;

		// Loop every element and if the current element is equal to x, return the index
		for (int curIndex = 0; curIndex < A.length; curIndex++)
			if (A[curIndex] == x)
				return curIndex;

		// No element was equal to x
		return -1;
	}

	/*
	 * Returns the index of the first occurrence of item within the first n elements of the array A[]
	 * references or -1 if item is not among the first n elements of the array
	 */
	public static int findN(int[] A, int item, int n) {
		if (A == null || A.length <= 0)
			return -1;

		// If the user makes n greater than A.length we set n equal to the array length to avoid going out of bounds.
		if (n > A.length)
			n = A.length;

		// Check every element between [0, n) to see if any are equal to item and returning it if so
		for (int curIndex = 0; curIndex < n; curIndex++)
			if (A[curIndex] == item)
				return curIndex;

		// No element with the given domain was equal to item.
		return -1;
	}

	/*
	 * returns the index of the last occurrence of x in the array A references or -1 if x doesn't
	 * exist in the array
	 */
	public static int findLast(int[] A, int x) {
		if (A == null || A.length <= 0)
			return -1;

		/*
		 * Starting at the end of the array and decrementing, check to see if the current element is equal to x and
		 * returning it if so.
		 */
		for (int curIndex = (A.length - 1); curIndex >= 0; curIndex--)
			if (A[curIndex] == x)
				return curIndex;

		// No element in the array is equal to x
		return -1;
	}

	/* returns the largest item found in the array A references */
	public static int largest(int[] A) {
		if (A == null || A.length <= 0)
			return 0; // We return 0 because that's what integers initialize to.

		/*
		 * Set to the lowest possible integer value so we can return the proper value if nothing is higher.
		 *
		 * Returning -2^31 is fine if nothing is found because the initial null/length check ensures that there must
		 * be elements in the array, so this MUST be the lowest.
		 */
		int largestInt = Integer.MIN_VALUE;

		// Loops every element and sets largestInt to the current element if it's larger than largestInt
		for (int curInt : A)
			if (curInt > largestInt)
				largestInt = curInt;

		return largestInt;
	}

	/* returns the index of the largest item found in the array A references */
	public static int indexOfLargest(int[] A) {
		if (A == null || A.length <= 0)
			return -1; // We return -1 to indicate no index was found.

		// We have to keep track of the largest int value to know when to set the largest index
		int largestInt = Integer.MIN_VALUE, largestIntIndex = -1;

		/*
		 * Loop every element and see if the current element is larger than largestInt, setting largestInt equal
		 * to the current element along with largestIntIndex to the current element's index if true
		 */
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
		if (A == null || A.length <= 0)
			return -1;

		// We have to keep track of the largest int value to know when to set the largest index
		int largestOddInt = Integer.MIN_VALUE, largestOddIntIndex = -1;

		/*
		 * Loop every element and see if the current element is both an odd number and larger than largestInt, setting
		 * largestInt equal to the current element along with largestIntIndex to the current element's index if true
		 */
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
		if (A == null || A.length <= 0)
			return new int[0];

		// Clone creates a new array with the exact same elements so we simply return a clone of the array
		return A.clone();
	}

	/*
	 * Returns a reference to a new array consisting of all of the first n elements of A[]. If
	 * n>A.length, returns a reference to a new array of size n, with the first A.length elements
	 * exactly the same as A, and the remaining n-A.length elements set to 0. If n<=0, returns null.
	 */
	public static int[] copyN(int[] A, int n) {
		if (A == null || A.length <= 0 || n <= 0)
			return null;

		int[] B = new int[n];

		/*
		 * Loop n times and adds every element from A to B while adding 0 if the current index is out of bounds
		 * for array A
		 */
		for (int i = 0; i < n; i++)
			B[i] = (i < A.length) ? A[i] : 0; // Add A's current element to B, substituting a 0 if we're out of its bounds

		return B;
	}

	/*
	 * returns a reference to an array consisting of all of the elements of the array A references
	 * that are odd. If there are no odd integers in the array, the function returns null.
	 */
	public static int[] copyOdds(int[] A) {
		if (A == null || A.length <= 0)
			return new int[0];

		// Create a new array with the same length as A just in case every element is odd
		int[] B = new int[A.length];
		int bCurIndex = 0;

		/*
		 * Loop through every element in A, check if the current element is odd, add it to B if so and then increment
		 * B's current index
		 */
		for (int i : A)
			if (i % 2 != 0) {
				B[bCurIndex] = i;
				bCurIndex++;
			}

		// Return an array that cuts every element at bCurIndex and after as they are excess
		return copyN(B, bCurIndex);
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
		if (A == null || x >= A.length || x < 0)
			return -1;

		int removedNum = -1; // Set to -1 because it needs to be initialized.
		boolean shiftTime = false;

		/*
		 * Loops through every element in A until we get to the x'th element, which we then set removedNum to the
		 * element at that index, toggle the shiftTime boolean to signify it's time to start overwriting, and then
		 * finally overwriting every previous index with the current index.
		 */
		for (int i = 0; i < A.length; i++)
			if (i == x) {
				shiftTime = true;
				removedNum = A[i];
			} else if (shiftTime)
				A[i - 1] = A[i];

		// Sets the last index to 0 because everything shifted to the left.
		A[A.length - 1] = 0;

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
		if (A == null || A.length <= 0)
			return;

		// Uses the remove function to remove the first element, which in turn shifts everything to the left.
		remove(A, 0);
	}


	/*
	 * returns true if A is in sorted ascending order and false otherwise
	 */
	public static boolean isSortedAscending(int[] A) {
		if (A == null || A.length <= 0)
			return false;

		/*
		 * Loops through every element except for the last, checking if the current element is greater than the
		 * next element, returning false if so.
		 */
		for (int i = 0; i < (A.length - 1); i++)
			if (A[i] > A[i + 1])
				return false;

		// No element is larger than its next element, so it's in ascending order.
		return true;
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
		if (A == null || A.length <= 0 || x < 0)
			return 0;

		int actualAscendingCount = 1; // We always start at 1 because we count the initial index

		/*
		 * Loops through the referenced array starting at index x, excluding its last element, until an element is
		 * greater than its next element, signifying that after the current element it is no longer in ascending order
		 */
		for (int i = x; i < (A.length - 1); i++) {
			if (A[i] > A[i + 1])
				break;

			actualAscendingCount++; // Increment if the next element is ascending the current element
		}

		return actualAscendingCount;
	}

	/*
	 * returns a new array consisting of all of the elements of A[] followed by all of the elements of
	 * B[]. For example, if A[] is: {10,20,30} and B[] is: {5, 9, 38}, the method returns the array :
	 * {10,20,30,5,9,38}
	 */
	public static int[] copyAll(int[] A, int[] B) {
		int[] C = new int[A.length + B.length]; // Sets the length of the new array to the sum of the referenced lengths
		int curIndex = 0; // Keeps track of the current index for the new array

		/*
		 * Creates an array of arrays containing array A and B, then loops through each array's elements and adds
		 * it to C while also incrementing curIndex to keep track of the new array's index
		 */
		for (int[] curArray : new int[][]{A, B})
			for (int curInt : curArray) {
				C[curIndex] = curInt;
				curIndex++;
			}

		return C;
	}

	/*
	 * reverses the order of the elements in A[]. For example, if A[] is: {10,20,30,40,50}, after the
	 * method, A[] would be {50,40,30,20,10}
	 */
	public static void reverse(int[] A) {
		if (A == null || A.length <= 0)
			return;

		// Clone the array so we can keep track of overwritten elements
		int[] containerArr = A.clone();

		/*
		 * Overwrites all of the element in A starting from 0 with all of the elements from the cloned array starting
		 * from the end and working its way to its beginning.
		 */
		for (int i = 0; i < A.length; i++)
			A[i] = containerArr[(A.length - 1) - i];
	}

	/*
	 * Extra credit:
	 *
	 * Returns a new array consisting of all of the elements of A, but with no duplicates. For
	 * example, if A[] is {10,20,5,32,5,10,9,32,8}, the method returns the array {10,20,5,32,9,8}
	 */
	public static int[] uniques(int[] A) {
		if (A == null || A.length <= 0)
			return new int[0];

		int[] B = new int[A.length];
		int newArrIndex = 0;

		/*
		 * Loops through every element in A and checks if they're in B.  If not, it will be added to B and have B's
		 * current index increased.
		 */
		for (int curIndex = 0; curIndex < A.length; curIndex++) {
			if (isPreviouslyUnique(A, curIndex)) {
				B[newArrIndex] = A[curIndex];
				newArrIndex++;
			}
		}

		// Returns an array that contains only the elements up to index newArrIndex (exclusive) as the rest are excess
		return copyN(B, newArrIndex);
	}

	/**
	 * Loops through {@code A} until the index {@code uniqueIndex} (exclusive) and checks if any previous element
	 * is equal to A[uniqueIndex].
	 * <p>
	 * This function's main use it to ensure that the element at uniqueIndex is not equal to any element in indexes
	 * before it, to ensure uniqueness.
	 *
	 * @param A           the Array to test for previous uniqueness
	 * @param uniqueIndex The index of the element you would like to check is unique
	 * @return <b>true</b> if A[uniqueIndex] is not equal to any previous element, <b>false</b> otherwise.
	 */
	private static boolean isPreviouslyUnique(int[] A, int uniqueIndex) {
		// Loops through A for indexes [0, uniqueIndex) and checks if any element is equal to A[uniqueIndex]
		for (int curSampleIndex = 0; curSampleIndex < uniqueIndex; curSampleIndex++)
			if (A[curSampleIndex] == A[uniqueIndex])
				return false;

		return true;
	}
}