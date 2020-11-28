import questiontypes.ObjectiveQuestion;
import questiontypes.Question;

import java.util.ArrayList;
import java.util.List;

public class Test {

	final private List<Object> questionBank;
	private int totalPoints;

	public Test(List<Object> questionBank) {
		this.questionBank = new ArrayList<>(questionBank); // Initialize questionBank list and add the given list to it

		for (Object curQuestion : this.questionBank) // We assume any object given to us is some sort of question
			totalPoints += ((Question) curQuestion).getPoints(); // Add points
	}

	public String toString() { // Output the test
		StringBuilder oString = new StringBuilder();

		for (int i = 0; i < questionBank.size(); i++) { // Loop every given question
			oString.append("#").append(i + 1).append(" - "); // Add a number before every question
			oString.append(questionBank.get(i).toString()); // Add question
		}

		return oString.toString();
	}

	public int getTotalPoints() {
		return totalPoints;
	}

	public String answerKey() {
		StringBuilder oString = new StringBuilder();

		for (int i = 0; i < questionBank.size(); i++) { // Loop every question
			oString.append(i + 1).append(". "); // Add current question number
			Object curQuestion = questionBank.get(i); // Get current question as a generic object

			// Append the question's answer if it's an ObjectiveQuestion or a child of it, otherwise, it has no definite answer
			oString.append(curQuestion instanceof ObjectiveQuestion ? ((ObjectiveQuestion) curQuestion).getCorrectAnswer() : "Open ended question.").append("\n");
		}

		return oString.toString();
	}

}
