/*
Name: Ayser Jamshidi
Class: CIS 1068
Section: 4
Date: September 4th, 2020
Assignment: 2. Get You a Cat

Description: 
This code is 1:1 with what the guideline asked us to do and very redundant:
Output purchasing and feeding: A cat, hen, duck, goose, sheep and pig with 12 functions.

Rules to follow:
-No print calls in the main function.
-Only one print per static function
-12 static functions
-89 lines (This is 76 lines without these comments)

Note:
To look at how I would've actually structured it, look at my previous submission.
*/

public class GetYouACatExtremelyRedundant {

    public static void main(String[] args) {
        CatBoughtAndFed();
        CatGoes();
        HenBoughtAndFed();
        HenGoes();
        DuckBoughtAndFed();
        DuckGoes();
        GooseBoughtAndFed();
        GooseGoes();
        SheepBoughtAndFed();
        SheepGoes();
        PigBoughtAndFed();
        PigGoes();
    }

    private static void CatBoughtAndFed() { // Method #1
        System.out.println("\nBought me a cat and the cat pleased me,");
        System.out.println("I fed my cat under yonder tree.");
    }

    private static void CatGoes() { // Method #2
        System.out.println("Cat goes fiddle-i-fee.");
    }

    private static void HenBoughtAndFed() { // Method #3
        System.out.println("\nBought me a hen and the hen pleased me,");
        System.out.println("I fed my hen under yonder tree.");
    }

    private static void HenGoes() { // Method #4
        System.out.println("Hen goes chimmy-chuck, chimmy-chuck,");
        CatGoes();
    }

    private static void DuckBoughtAndFed() { // Method #5
        System.out.println("\nBought me a duck and the duck pleased me,");
        System.out.println("I fed my duck under yonder tree.");
    }

    private static void DuckGoes() { // Method #6
        System.out.println("Duck goes quack, quack,");
        HenGoes();
    }

    private static void GooseBoughtAndFed() { // Method #7
        System.out.println("\nBought me a goose and the goose pleased me,");
        System.out.println("I fed my goose under yonder tree.");
    }

    private static void GooseGoes() { // Method #8
        System.out.println("Goose goes hissy, hissy,");
        DuckGoes();
    }

    private static void SheepBoughtAndFed() { // Method #9
        System.out.println("\nBought me a sheep and the sheep pleased me,");
        System.out.println("I fed my sheep under yonder tree.");
    }

    private static void SheepGoes() { // Method #10
        System.out.println("Sheep goes baa, baa,");
        GooseGoes();
    }

    private static void PigBoughtAndFed() { // Method #11
        System.out.println("\nBought me a pig and the pig pleased me,");
        System.out.println("I fed my pig under yonder tree.");
    }

    private static void PigGoes() { // Method #12
        System.out.println("Pig goes oink, oink,");
        SheepGoes();
    }
}