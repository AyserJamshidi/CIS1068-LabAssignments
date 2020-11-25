import java.util.ArrayList;
import java.util.List;

public class Test {

	private List<Object> questionBank;
	private int totalPoints;

	public Test(List<Object> questionBank) {
		this.questionBank = new ArrayList<>();

		if (questionBank != null) {
			this.questionBank.addAll(questionBank);

			for (Object curQuestion : this.questionBank)
				this.totalPoints += ((Question) curQuestion).points;
		}
	}

	public String toString() {
		StringBuilder oString = new StringBuilder();

		for (int i = 0; i < questionBank.size(); i++) {
			oString.append("#").append(i + 1).append(" - ");
			oString.append(questionBank.get(i).toString());
		}

		return oString.toString();
	}

	public String answerKey() {
		StringBuilder oString = new StringBuilder();

		for (int i = 0; i < questionBank.size(); i++) {
			oString.append(i + 1).append(". ");
			Object curQuestion = questionBank.get(i);

			oString.append(curQuestion instanceof ObjectiveQuestion ? ((ObjectiveQuestion) curQuestion).correctAnswer : "ANSWER NOT AVAILABLE.").append("\n");
		}

		return oString.toString();
	}

}
