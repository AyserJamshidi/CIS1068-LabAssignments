package questiontypes;

public class MultipleChoiceQuestion extends ObjectiveQuestion {

	String[] possibleAnswers;

	public MultipleChoiceQuestion(String correctAnswer, String[] possibleAnswers, String questionText, int points, int difficulty, int answerSpace) {
		super(correctAnswer, questionText, points, difficulty, answerSpace);

		this.possibleAnswers = possibleAnswers;
	}

	@Override
	public String toString() { // Override Question toString as we now need a new format
		StringBuilder oString = new StringBuilder(questionHeader()); // Add ObjectiveQuestion toString
		oString.append(questionText).append("\n");

		for (int i = 0; i < possibleAnswers.length; i++) // Add answers under question
			oString.append((i + 1)).append(". ").append(possibleAnswers[i]).append("\n");

		oString.append(questionSpacing()); // Add spaces after question

		return oString.toString();
	}


	@Override
	public String toStringGrader() { // Override ObjectiveQuestions as we now need a new format
		StringBuilder oString = new StringBuilder(super.toString()); // Add header and question

		for (int i = 0; i < possibleAnswers.length; i++) { // Add answers
			oString.append(i + 1).append(". ");

			if (possibleAnswers[i].equalsIgnoreCase(correctAnswer)) // Make the correct answer distinct
				oString.append("**** ").append(possibleAnswers[i]).append(" ****");
			else
				oString.append(possibleAnswers[i]);

			oString.append("\n");
		}

		oString.append(questionSpacing()); // Add spaces

		return oString.toString();
	}
}
