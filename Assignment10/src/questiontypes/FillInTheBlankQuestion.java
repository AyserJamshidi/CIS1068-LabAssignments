package questiontypes;

public class FillInTheBlankQuestion extends ObjectiveQuestion { // All fill in the blank questions are objective questions

	public FillInTheBlankQuestion(String correctAnswer, String questionText, int points, int difficulty, int answerSpace) {
		super(correctAnswer, questionText, points, difficulty, answerSpace); // Calls ObjectiveQuestion constructor
	}

	@Override
	public String toString() { // Override Question as we now need a new format
		return questionHeader()
				+ "_________ " + questionText + "\n" + questionSpacing();
	}

	@Override
	public String toStringGrader() { // Override ObjectiveQuestions as we now need a new format
		return questionHeader()
				+ "_________ " + questionText
				+ "\nAnswer: " + correctAnswer + "\n" + questionSpacing();
	}
}