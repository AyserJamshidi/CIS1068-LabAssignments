import questiontypes.FillInTheBlankQuestion;
import questiontypes.MultipleChoiceQuestion;
import questiontypes.ObjectiveQuestion;
import questiontypes.Question;

import java.util.ArrayList;
import java.util.List;

public class Driver {
	public static void main(String[] args) {
		List<Object> questionBank = new ArrayList<>(); // Create a question bank

		// Add any amount of questions
		questionBank.add(new ObjectiveQuestion("T", "The sky is blue. (T/F)", 1, 1, 1));
		questionBank.add(new ObjectiveQuestion("F", "Coffee is made from berries. (T/F)", 1, 1, 1));
		questionBank.add(new FillInTheBlankQuestion("Green", "is the color of grass.", 1, 1, 1));
		questionBank.add(new FillInTheBlankQuestion("3", "is the maximum amount of imposters in \"Among Us\".", 2, 4, 1));
		questionBank.add(new MultipleChoiceQuestion("9", new String[]{"9", "8", "7", "6"}, "What is the most amount of crewmates in \"Among Us\"?", 2, 4, 1));
		questionBank.add(new FillInTheBlankQuestion("0", "is the tangent of pi", 2, 4, 1));
		questionBank.add(new Question("Please write out the meaning of both: Pass by reference, Pass by value", 3, 2, 4));

		Test studentTest = new Test(questionBank); // Create a test with the given question bank

		// Output the test itself
		System.out.println("Max amount of points: " + studentTest.getTotalPoints());
		System.out.println(studentTest);
		System.out.println(studentTest.answerKey());
	}
}