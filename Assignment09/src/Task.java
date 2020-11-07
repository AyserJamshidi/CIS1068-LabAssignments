import java.time.LocalDateTime;

public class Task {
	private String name;
	private int priority;
	private int estMinsToComplete;
	private LocalDateTime whenDue;

	public Task(String name, int priority, LocalDateTime whenDue, int estMinsToComplete) {
		this.name = name;
		this.priority = priority;
		this.whenDue = whenDue;
		this.estMinsToComplete = estMinsToComplete;
	}

	// Accessors (Getters)
	public String getName() {
		return name;
	}

	public int getPriority() {
		return priority;
	}

	public int getEstMinsToComplete() {
		return estMinsToComplete;
	}

	public LocalDateTime getWhenDue() {
		return whenDue;
	}

	// Mutators (Setters)
	public void setName(String name) {
		this.name = name;
	}

	public void setEstMinsToComplete(int estMinsToComplete) {
		this.estMinsToComplete = estMinsToComplete;
	}

	public void setWhenDue(LocalDateTime whenDue) {
		this.whenDue = whenDue;
	}

	public void increasePriority(int amount) {
		if (amount > 0)
			this.priority += amount;
	}

	public void decreasePriority(int amount) {
		// If amount causes priority to hit negative integers, we simply subtract priority from priority instead.
		this.priority -= ((this.priority - amount) >= 0) ? amount : this.priority;
	}

	public boolean overdue() {
		return whenDue.isBefore(LocalDateTime.now());
	}

	// Other
	public String toString() {
		StringBuilder sOutput = new StringBuilder();

		sOutput.append("Task: ").append(name);
		sOutput.append("\nPriority: ").append(priority);
		sOutput.append("\nEstimated Time Till Completion: ").append(estMinsToComplete);
		sOutput.append("\nDue Date: ").append(whenDue.toLocalDate()).append(":").append(whenDue.toLocalTime());

		return sOutput.toString();
	}
}
