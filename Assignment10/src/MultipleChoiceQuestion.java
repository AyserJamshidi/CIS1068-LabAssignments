import java.util.Arrays;

public class MultipleChoiceQuestion extends ObjectiveQuestion {

	String[] possibleAnswers;

	public MultipleChoiceQuestion(String correctAnswer, String[] possibleAnswers, String questionText, int points, int difficulty, int answerSpace) {
		super(correctAnswer, questionText, points, difficulty, answerSpace);

		this.possibleAnswers = possibleAnswers;
	}

//	@Override
//	public String toString() {
//		return super.toString() + "Possible Answers: " + Arrays.toString(possibleAnswers) + "\n";
//	}

	@Override
	public String toString() {
		StringBuilder oString = new StringBuilder(questionHeader());
		oString.append(questionText).append("\n");

		if (possibleAnswers != null)
			for (int i = 0; i < possibleAnswers.length; i++)
				oString.append((i + 1)).append(". ").append(possibleAnswers[i]).append("\n");

		oString.append(questionSpacing());

		return oString.toString();
	}


	@Override
	public String toStringGrader() {
		StringBuilder oString = new StringBuilder(super.toString());

		if (possibleAnswers != null)
			for (int i = 0; i < possibleAnswers.length; i++) {
				oString.append(i + 1).append(". ");

				if (possibleAnswers[i].equalsIgnoreCase(correctAnswer))
					oString.append("**** ").append(possibleAnswers[i]).append(" ****").append("\n");
				else
					oString.append(possibleAnswers[i]).append("\n");
			}

		oString.append(questionSpacing());

		return oString.toString();
	}
}
