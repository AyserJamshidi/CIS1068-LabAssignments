package questiontypes;

public class Question {

	final int MIN_DIFFICULTY = 1, MAX_DIFFICULTY = 10;

	String questionText;
	int points, difficulty, answerSpace;

	public Question(String questionText, int points, int difficulty, int answerSpace) {
		// Ensure all arguments are valid before initializing any variables
		if (MIN_DIFFICULTY > difficulty || difficulty > MAX_DIFFICULTY) // diff must be: MIN_DIFF <= diff <= MAX_DIFF
			throw new IllegalArgumentException("questiontypes.Question difficulty must be an integer from " + MIN_DIFFICULTY + " to " + MAX_DIFFICULTY);

		if (questionText.length() <= 0) // Must have a question
			throw new IllegalArgumentException("A questiontypes.Question must have valid input for parameter: questionText");

		if (answerSpace < 0) // Must have at LEAST 0 answer space
			throw new IllegalArgumentException("A question's answering space must be an integer 0 or greater.");

		this.questionText = questionText;
		this.points = points;
		this.difficulty = difficulty;
		this.answerSpace = answerSpace;
	}

	public int getPoints() {
		return points;
	}

	// No modifier so only package subclasses can use it.
	String questionHeader() { // Every question has this header
		return "(" + points + "pts | difficulty: " + difficulty + "/10) - "
				+ "You are allotted " + answerSpace + " line" + (answerSpace != 1 ? "s" : "") +  " for this question.\n";
	}

	// No modifier so only package subclasses can use it.
	String questionSpacing() { // Adds (answerSpace) lines to the output string
		return "\n".repeat(Math.max(0, answerSpace));
	}

	public String toString() { // Question output for students
		return questionHeader()
				+ questionText + "\n"
				+ questionSpacing();
	}
}