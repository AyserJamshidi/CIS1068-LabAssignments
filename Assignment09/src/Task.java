/*
 * Implement a class Task, which is used to represent a job that should be done. It should contain the following private fields:
 * x name - text description of what job should be done (e.g., take out the trash, finish 1068 homework).
 * x priority - a non-negative integer which stores the importance of a Task.
 * x estMinsToComplete - a non-negative integer which holds the anticipated number of minutes it should take to complete the task
 * x extra credit: whenDue - a java.time.LocalDateTime object representing the date and time when the task should be completed
 *
 * x a constructor, which initializes each of the fields
 * x accessor methods for each of the fields. Use the naming convention getXXX() where XXX is the name of the field,
 *   e.g., you'll write a method called getName() which returns the name of the Task.
 * x mutator methods for each of the fields except for priority. Use the naming convention setXXX() where XXX is the
 *   name of the field, e.g., you'll write a method called setName(String newName), which would update the name field of the Task to newName.
 * x toString( ), which returns a String representation of the Task.
 * x increasePriority(int amount) increases the priority level by amount. If a negative amount is specified, the method returns without making any changes.
 * x decreasePriority(int amount) decreases the priority level by amount. If the decrease is more than the current value
 *   of priority (and subtracting this amount would result in a negative priority), priority should be set to 0.
 * x extra credit: overdue() returns true if the current date/time is later than whenDue or false otherwise.
 */

import java.time.LocalDateTime;

public class Task {
	private String name;
	private int priority;
	private int estMinsToComplete;
	private LocalDateTime whenDue;

	private final int DEFAULT_TASK_TIME_MINS = 120;

	public Task(String name, int priority, LocalDateTime whenDue, int estMinsToComplete) {
		if (priority < 0 || estMinsToComplete < 0) // Ensures bad parameters are caught and thrown
			throw new IllegalArgumentException("Task can not be passed a negative integer for parameter "
					+ ((priority == 0) ? "priority" : ""));

		this.name = name;
		this.priority = priority;
		this.whenDue = (whenDue == null) ? LocalDateTime.now().plusMinutes(DEFAULT_TASK_TIME_MINS) : whenDue;
		this.estMinsToComplete = estMinsToComplete;
	}

	// Accessors - They're all self explanatory really
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
		this.estMinsToComplete = Math.max(estMinsToComplete, 0); // Ensures we don't set a negative int
	}

	public void setWhenDue(LocalDateTime whenDue) {
		this.whenDue = whenDue;
	}

	public void increasePriority(int amount) {
		if (amount > 0) // Ensures we don't "increase" by negative numbers.
			this.priority += amount;
	}

	public void decreasePriority(int amount) {
		// If amount causes priority to hit negative integers, we simply set priority to 0.
		this.priority = (amount > this.priority) ? 0 : this.priority - amount;
	}

	public boolean overdue() {
		return whenDue.isBefore(LocalDateTime.now()); // Checks if this Task's due date/time has passed this moment
	}

	public String toString() {
		StringBuilder oString = new StringBuilder();

		oString.append("Task: ").append(name)
				.append("\nPriority: ").append(priority)
				.append("\nEstimated Time Till Completion: ").append(estMinsToComplete)
				.append("\nDue Date: ")
				.append(whenDue.toLocalDate()).append(":").append(whenDue.toLocalTime());

		return oString.toString();
	}
}
