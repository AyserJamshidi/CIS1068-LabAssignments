/*
 * Name: Ayser Jamshidi
 * Class: CIS 1068
 * Section: 4
 * Date: November 6th, 2020
 * Assignment: 8. Warm Up With Objects
 *
 * Description:
 * https://cis.temple.edu/~jfiore/2020/fall/1068/assignments/08/
 * - Contains two classes: Car and Fraction.
 * - Car class allows the creation of Car objects that have normal car-utilization functions and fields such as
 *   allowing someone to drive the car, fill the tank with gas, etc.
 *
 * - Fraction allows the creation of Fraction objects that have a numerator and denominator and are
 *     ALWAYS stored at their most reduced form by utilizing the Euclidean Algorithm.
 */

public class WarmUpWithObjects {
	public static void main(String[] args) {
		{
			// Initialize the car array
			Car[] carArray = {
					new Car("Ford", "Pinto", 1972, 17.5, 12, 12, 10),
					new Car("Ford", "Mustang", 2004, 11.5, 230, 10, 5)
			};

			// Cycle through every car.
			for (Car curCar : carArray) {
				curCar.drive(5); // drives the Car 5 miles
				curCar.fillTank(1); // put in a gallon of gas
				System.out.println("oldJunker fuel: " + curCar.getFuelRemaining()); // prints the amount of fuel left
				System.out.println(curCar); // prints the attributes of the car to the screen

			}
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