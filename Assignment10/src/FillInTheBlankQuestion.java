public class FillInTheBlankQuestion extends ObjectiveQuestion {

	public FillInTheBlankQuestion(String correctAnswer, String questionText, int points, int difficulty, int answerSpace) {
		super(correctAnswer, questionText, points, difficulty, answerSpace);
	}

	@Override
	public String toString() {
		return questionHeader()	+ "_________ " + questionText + "\n" + questionSpacing();
	}

	@Override
	public String toStringGrader() {
		return "Answer: " + correctAnswer.toUpperCase() + " " + questionText + "\n" + questionSpacing();
	}
}