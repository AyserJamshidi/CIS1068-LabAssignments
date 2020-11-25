public class Question {

	final int MIN_DIFFICULTY = 1, MAX_DIFFICULTY = 10;

	String questionText;
	int points, difficulty, answerSpace;

	public Question(String questionText, int points, int difficulty, int answerSpace) {
		if (MIN_DIFFICULTY > difficulty || difficulty > MAX_DIFFICULTY)
			throw new IllegalArgumentException("Question difficulty must be an integer from " + MIN_DIFFICULTY + " to " + MAX_DIFFICULTY);

		if (questionText.length() <= 0)
			throw new IllegalArgumentException("A Question must have valid input for parameter: questionText");

		if (answerSpace < 0)
			throw new IllegalArgumentException("A question's answering space must be an integer 0 or greater.");

		this.questionText = questionText;
		this.points = points;
		this.difficulty = difficulty;
		this.answerSpace = answerSpace;
	}

	protected String questionHeader() { // Protect this method so only sub-classes can use it.
		return "(" + points + "pts | difficulty: " + difficulty + "/10) - "
				+ "You are allotted " + answerSpace + " " + (answerSpace) +  " for this question.\n";
	}

	protected String questionSpacing() {
		return "\n".repeat(Math.max(0, answerSpace));
	}

	public String toString() {
		return "(" + points + "pts | difficulty: " + difficulty + "/10) - You are allotted " + answerSpace + " lines for your answer.\n"
				+ questionText + "\n" + questionSpacing();
	}
}