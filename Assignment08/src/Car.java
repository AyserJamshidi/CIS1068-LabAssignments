@SuppressWarnings("all")
public class Car {

    private String make, model;
    private int year;
    private double MPG, milesDriven, fuelCapacity, fuelRemaining;

    // Car constructor
    public Car(String make, String model, int year, double MPG, double milesDriven, double fuelCapacity, double fuelRemaining) { // Constructor
        this.make = make;
        this.model = model;
        this.year = year;
        this.MPG = MPG;
        this.milesDriven = milesDriven;
        this.fuelCapacity = fuelCapacity;
        this.fuelRemaining = fuelRemaining;
    }

    // Adds up to g gallons of gas to the fuel tank
    // BUT not more than the car's fuel capacity.
    public void fillTank(double g) {
        if ((fuelRemaining + g) > fuelCapacity)
            fuelRemaining = fuelCapacity;
        else
            fuelRemaining += g;
    }

    // simulates driving m miles in the car and:
    // 1) ADDING TO THE TOTAL NUMBER OF MILES DRIVEN
    // 2) REDUCING THE AMOUNT OF GAS IN THE CAR ACCORDING TO THIS CAR'S AVERAGE MPG
    public void drive(double m) {
        milesDriven += m; // Adds m miles to this car
        fuelRemaining -= (m / MPG); // Reduces the car's fuel
    }

    public double getFuelRemaining() {
        return fuelRemaining; // Returns the car's current fuel
    }


    public String toString() {
        StringBuilder outputStr = new StringBuilder();

        // Create the output
        outputStr.append("\nMake: ").append(make)
                .append("\nModel: ").append(model)
                .append("\nYear: ").append(year)
                .append("\nMPG: ").append(MPG)
                .append("\nMileage: ").append(milesDriven)
                .append("\nFuel Capacity: ").append(fuelCapacity)
                .append("\nFuel Remaining: ").append(fuelRemaining);

        return outputStr.toString(); // Return it as a string
    }
}
