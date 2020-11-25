import java.util.ArrayList;
import java.util.List;

public class Driver {
	public static void main(String[] args) {
		List<Object> questionBank = new ArrayList<>();

		questionBank.add(new ObjectiveQuestion("T", "The sky is blue. (T/F)", 1, 1, 1));
		questionBank.add(new ObjectiveQuestion("F", "Coffee is made from berries. (T/F)", 1, 1, 1));
		questionBank.add(new FillInTheBlankQuestion("Green", "is the color of grass.", 1, 1, 1));
		questionBank.add(new FillInTheBlankQuestion("3", "is the maximum amount of imposters in \"Among Us\".", 2, 4, 1));
		questionBank.add(new MultipleChoiceQuestion("9", new String[]{ "9", "8", "7", "6" },"What is the most amount of crewmates in \"Among Us\"?", 2, 4, 1));
		questionBank.add(new Question("Please write out the meaning of both: Pass by reference, Pass by value", 3, 2, 4));


		Test whatStudentsSee = new Test(questionBank);

		System.out.println(whatStudentsSee);

		System.out.println(whatStudentsSee.answerKey());
	}
}
