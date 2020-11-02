
public class WarmUpWithObjects {
	public static void main(String[] args) {
		{
			Car oldJunker = new Car("Ford", "Pinto", 1972, 17.5, 12, 8, 10); // creates a new Car object
			oldJunker.drive(5); // drives the Car 5 miles
			oldJunker.fillTank(1); // put in a gallon of gas
			System.out.println("oldJunker fuel: " + oldJunker.getFuelRemaining()); // prints the amount of fuel left
			System.out.println(oldJunker); // prints the attributes of the car to the screen
		}

		System.out.println();

		// Test Fraction class
		{
			Fraction testFrac = new Fraction(462, -2); // Create Fraction Object

			// Getters
			System.out.println("Numerator = " + testFrac.getNumerator()); // Expect -231
			System.out.println("Denominator = " + testFrac.getDenom()); // Expect 1

			// Setters
			testFrac.setNum(444);
			testFrac.setDenum(928);

			// Test add and equals
			System.out.println("Equals = " + testFrac.equals(new Fraction(22, 51))); // Expect false
			System.out.println("Equals = " + testFrac.equals(new Fraction(444, 928))); // Expect true

			System.out.println("Fraction = " + testFrac); // Expect 111/232
		}
	}
}