package questiontypes;

public class ObjectiveQuestion extends Question {
	String correctAnswer;

	public ObjectiveQuestion(String correctAnswer, String questionText, int points, int difficulty, int answerSpace) {
		super(questionText, points, difficulty, answerSpace); // Calls Question's constructor

		this.correctAnswer = correctAnswer;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public String toStringGrader() { // Question output for graders
		return questionHeader()
				+ questionText
				+ "\nAnswer: " + correctAnswer + questionSpacing();
	}
}