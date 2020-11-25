public class ObjectiveQuestion extends Question {
	String correctAnswer;

	public ObjectiveQuestion(String correctAnswer, String questionText, int points, int difficulty, int answerSpace) {
		super(questionText, points, difficulty, answerSpace);

		this.correctAnswer = correctAnswer;
	}

	public String toString() {
		return questionHeader()
				+ questionText + "\n" + questionSpacing();
	}

	public String toStringGrader() {
		return toString()
				+ "Answer: " + correctAnswer + questionSpacing();
	}
}