/*
Name: Ayser Jamshidi
Class: CIS 1068
Section: 4
Date: September 4th, 2020
Assignment: 2. Get You a Cat

Description: 
This code will output any amount of animals with their assigned sound after proclaiming we bought and fed them.

Note:
I am aware that the code does not fit into the 12 functions/89 lines requirement/optional length, but
this was what made the most sense for even more reduced redundancy instead of making.. redundant functions.
The instructor said this code was completely okay and said I'm good to submit (inspected it during lab).
If this is not okay I can submit another file with the respective 12 animal(); repeatN(); functions.
*/
import java.util.ArrayList;

public class GetYouACat {
    // Container for every animal.  Could also replace with String[n], but this is dynamic...
    private static ArrayList<String> animalList = new ArrayList<>();

    public static void main(String[] args) {
        // Repetition calls for x animal and y sound
        RepetitionFunc("Cat", "fiddle-i-fee.");
        RepetitionFunc("Hen", "chimmy-chuck, chimmy-chuck,");
        RepetitionFunc("Duck", "quack, quack,");
        RepetitionFunc("Goose", "hissy, hissy,");
        RepetitionFunc("Sheep", "baa, baa,");
        RepetitionFunc("Pig", "oink, oink,");
    }

    /* RepetitionFunc:
    Will output the animal's given name and sound along with other animals in the animal list.
    Arguments actualName and animalSound are required or else the output won't make sense.
    */
    private static void RepetitionFunc(String actualName, String animalSound) {
        // Instead of converting a string to lowercase three times, we reduce it to one with this variable.
        String lowerName = actualName.toLowerCase();

        // These repeat every cycle.  We do not count this on our 1 println limitation as this is not redundant.
        System.out.println("Bought me a " + lowerName + " and the " + lowerName + " pleased me,");
        System.out.println("I fed my " + lowerName + " under yonder tree.");

        // Add the current animal to the animal list immediately after proclaiming we bought and fed it.
        animalList.add(actualName + " goes " + animalSound);

        // Cycle through every animal in the animal list, starting from the END.
        for (int i = animalList.size()-1; i >= 0; i--)
            System.out.println(animalList.get(i)); // The 1 println!

        // Does not count in our 1 println limitation, this is simply a splitter at the end of a paragraph.
        System.out.println();
    }
}