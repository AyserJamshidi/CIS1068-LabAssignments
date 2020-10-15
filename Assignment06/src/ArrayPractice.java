/**
 * @author Ayser Jamshidi
 */
public class ArrayPractice {

	public static void main(String[] args) {
		int[] A = uniques(new int[]{-1, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 4, 4, 5, 4, -1, 9, 11, 10});

		for (int i : A)
			System.out.println(i + " ");
	}

	/* sets every item in the array A references to initialValue */
	public static void initialize(int[] A, int initialValue) {
		if (A != null && A.length > 0)
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

		double total = 0; // Set the default output to 0 because if nothing is in the array, then return 0

		for (int curInt : A)
			total += curInt;

		return total / A.length;
	}

	/* returns the number of times that x appears in the array A references */
	public static int numOccurrences(int[] A, int x) {
		if (A == null || A.length <= 0)
			return 0;

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
		if (A == null || A.length <= 0)
			return -1;

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
		if (A == null || A.length <= 0)
			return -1;

		// Ensures that if the user makes n greater than A.length, we do this to avoid going out of bounds.
		if (n > A.length)
			n = A.length;

		for (int curIndex = 0; curIndex < n; curIndex++)
			if (A[curIndex] == item)
				return curIndex;

		return -1;
	}

	/*
	 * returns the index of the last occurrence of x in the array A references or -1 if x doesn't
	 * exist in the array
	 */
	public static int findLast(int[] A, int x) {
		if (A == null || A.length <= 0)
			return -1;

		for (int curIndex = (A.length - 1); curIndex >= 0; curIndex--)
			if (A[curIndex] == x)
				return curIndex;
		return -1;
	}

	/* returns the largest item found in the array A references */
	public static int largest(int[] A) {
		if (A == null || A.length <= 0)
			return 0; // We return 0 because that's what integers initialize to.

		int largestInt = Integer.MIN_VALUE;

		for (int curInt : A)
			if (curInt > largestInt)
				largestInt = curInt;

		return largestInt;
	}

	/* returns the index of the largest item found in the array A references */
	public static int indexOfLargest(int[] A) {
		if (A == null || A.length <= 0)
			return -1; // We return -1 to indicate no index was found.

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
		if (A == null || A.length <= 0)
			return -1;

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
		if (A == null || A.length <= 0)
			return new int[0];

		return A.clone();
	}

	/*
	 * Returns a reference to a new array consisting of all of the first n elements of A[]. If
	 * n>A.length, returns a reference to a new array of size n, with the first A.length elements
	 * exactly the same as A, and the remaining n-A.length elements set to 0. If n<=0, returns null.
	 */
	public static int[] copyN(int[] A, int n) {
		if (A == null || A.length <= 0)
			return new int[0];

		int[] B = (n > 0) ? new int[n] : null;

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
	public static int[] copyOdds(int[] A) {
		if (A == null || A.length <= 0)
			return new int[0];

		int[] B = new int[A.length];
		int bCurIndex = 0;

		for (int i : A)
			if (i % 2 != 0) {
				B[bCurIndex] = i;
				bCurIndex++;
			}

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
		if (A == null || x >= A.length || x < 0 || A.length <= 0)
			return -1;

		int removedNum = -1;
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
		for (int i = 0; i < (A.length - 1); i++)
			if (A[i] > A[i + 1])
				return false;

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
		int actualAscendingCount = 1; // We always start at 1 because we count the initial index

		for (int i = x; i < (A.length - 1); i++) {
			if (A[i] > A[i + 1])
				break;

			actualAscendingCount++;
		}

		return actualAscendingCount;
	}

	/*
	 * returns a new array consisting of all of the elements of A[] followed by all of the elements of
	 * B[]. For example, if A[] is: {10,20,30} and B[] is: {5, 9, 38}, the method returns the array :
	 * {10,20,30,5,9,38}
	 */
	public static int[] copyAll(int[] A, int[] B) {
		int[] C = new int[A.length + B.length];
		int curIndex = 0;

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

		int[] containerArr = A.clone();

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

		for (int curIndex = 0; curIndex < A.length; curIndex++) {
			if (isPreviouslyUnique(A, curIndex)) {
				B[newArrIndex] = A[curIndex];
				newArrIndex++;
			}
		}

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
		for (int curSampleIndex = 0; curSampleIndex < uniqueIndex; curSampleIndex++)
			if (A[curSampleIndex] == A[uniqueIndex])
				return false;
		return true;
	}
}