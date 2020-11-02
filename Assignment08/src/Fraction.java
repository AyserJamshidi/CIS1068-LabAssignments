@SuppressWarnings("all")
public class Fraction {

	private int numerator, denominator;

	public Fraction(int n, int d) {
		denomErrorCheck(d); // Check if d = 0
		int gcd = getGCD(n, d); // Get GCD using Euclidian Algorithm

		// Divide the numerator and denominator by the GCD to get their most reduced forms
		numerator = (n / gcd);
		denominator = (d / gcd);
	}

	public int getNumerator() { // Returns numerator
		return numerator;
	}

	public int getDenom() { // Returns denominator
		return denominator;
	}

	public void setNum(int n) { // Sets numerator to n
		numerator = n;
		simplify();
	}

	public void setDenum(int d) { // Sets denominator to d
		denomErrorCheck(d); // Check if d = 0

		denominator = d;
		simplify(); // Simplify
	}

	public void simplify() { // Simplifies when setting a new numerator/denominator
		int gcd = getGCD(numerator, denominator);
		numerator /= gcd;
		denominator /= gcd;
	}

	public Fraction add(Fraction a) { // Adds this fraction to the fraction that's passed into this function
		int newNum = (numerator * a.denominator) + (denominator * a.numerator); // Cross multiply then add
		int newDenom = denominator * a.denominator; // Multiply both denominators

		return new Fraction(newNum, newDenom); // Returns a new Fraction reference
	}

	private void denomErrorCheck(int d) { // Throws an exception when d is 0.
		if (d == 0)
			throw new ArithmeticException("d can NOT be 0");
	}

	public boolean equals(Fraction a) {
		return (numerator == a.numerator && denominator == a.denominator);
	}

	private int getGCD(int a, int b) {
		// Uses Euclidean Algorithm recursively (if needed) to get the GCD between a and b.
		return (b == 0) ? a : getGCD(b, a % b);
	}

	public String toString() {
		StringBuilder fractionOutput = new StringBuilder();

		// Create output
		fractionOutput.append(numerator)
				.append("/")
				.append(denominator);

		return fractionOutput.toString(); // Turn it into a string the return it
	}

}