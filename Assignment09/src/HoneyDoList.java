/*
 * Implement a class HoneyDoList, which is used to manage a collection of Task.
 * It should contain the following private fields:
 *
 * x tasks[] - an array of Task
 * x numTasks - a non-negative integer storing the number of items contained in tasks[].
 * x INITIAL_CAPACITY - a constant non-negative integer. This is how large the task array should be at creation time.
 *
 * Implement at least the following public methods within the HoneyDo class:
 *
 * x a constructor, which creates the tasks[] array, giving it the capacity of INITIAL_CAPACITY, and setting numTasks to 0.
 * x toString( ) - returns a String representation of each Task in tasks[]. Do not include in the String null for entries in tasks[] that are empty.
 * x find(String name) - returns the index of the first occurrence of a Task whose name matches the name specified ignoring case.
 *   Recall that there is an equalsIgnoreCase() method in the String class. The method should return -1 if no match is found.
 * x addTask() - appends a new Task to the occupied end of tasks[]. If tasks[] is already full when you attempt to add the
 *   new Task, a new array is created, all of the items are copied from the old array into the new one, and tasks[] is set to point to the new array.
 * x totalTime() - returns the total time in minutes required to complete all tasks in the list.
 * x shortestTime() - returns the index of the task that should take the least amount of time to finish or -1 if the list is empty.
 * x completeTask(int index) - removes and returns the Task at the specified index shifting all subsequent tasks in the array one
 *   position to the left. If index is invalid, returns null. See example.
 * x extra credit: overdueTasks() - returns an array of Task consisting of only the items in tasks[] that are overdue.
 */

public class HoneyDoList {
	private final int INITIAL_CAPACITY = 50;

	private Task[] tasks;
	private int numTasks;

	public HoneyDoList() {
		this.tasks = new Task[this.INITIAL_CAPACITY];
		this.numTasks = 0;
	}

	public int find(String name) {
		for (int i = 0; i < tasks.length && i < numTasks; i++) // Loop every element in tasks
			if (tasks[i].getName().equalsIgnoreCase(name)) // Check if this is the wanted task
				return i; // Return this element's index

		return -1;
	}

	public void addTask(Task newTask) {
		if (numTasks >= tasks.length) { // Check if new array needs to be created
			Task[] newTaskContainer = new Task[tasks.length + INITIAL_CAPACITY]; // Create a new array

			System.arraycopy(tasks, 0, newTaskContainer, 0, tasks.length); // Copy the old array to the new array
			newTaskContainer[tasks.length] = newTask; // Assign the given task to the new array
			tasks = newTaskContainer;
		} else // Number of tasks isn't equal to the array's length, simply add it
			tasks[numTasks] = newTask; // Insert the given task to the proper slot

		numTasks++;
	}

	public int totalTime() {
		int totalTime = 0; // Set to 0 in case there are no tasks, or tasks simply have no time

		// Loop every element in task and stop when hitting a null task
		for (int i = 0; i < tasks.length && tasks[i] != null; i++)
			totalTime += tasks[i].getEstMinsToComplete(); // Add this task's time to output int

		return totalTime;
	}

	public int shortestTime() {
		int shortestTimeIndex = -1; // Set to -1 as that's the int returned if no index is found

		// Loop every element in task and stop when hitting hit a null task
		for (int i = 0, shortestTimeRecorded = Integer.MAX_VALUE; i < tasks.length && tasks[i] != null; i++) { // Loop every task
			if (shortestTimeRecorded > tasks[i].getEstMinsToComplete()) { // Check if our stored shortest time is no longer the shortest
				shortestTimeRecorded = tasks[i].getEstMinsToComplete();
				shortestTimeIndex = i; // Assign the current index to our output variable
			}
		}

		return shortestTimeIndex;
	}

	public Task completeTask(int index) {
		Task taskAtIndex = null; // Set this task to null as that's the return if the given index is not in range

		if (index < tasks.length - 1) { // If the index is valid, assign the returning Task to this index's task
			taskAtIndex = tasks[index];
			numTasks--;
		}

		// Loop all elements at range [index, last index) and stop when hitting hit a null element
		for (; index < tasks.length - 1 && tasks[index] != null; index++)
			tasks[index] = tasks[index + 1]; // Assign this index's task to the next index's task

		return taskAtIndex;
	}

	public Task[] overdueTasks() {
		int overdueTaskCount = 0;

		// Loops through every element in tasks that isn't null
		for (int i = 0; i < tasks.length && tasks[i] != null; i++)
			if (tasks[i].overdue()) // If the task is overdue, increment our array's length counter
				overdueTaskCount++;

		Task[] oTaskArr = new Task[overdueTaskCount];

		// Loop through every element in tasks that isn't null
		for (int i = 0, oArrIndex = 0; i < tasks.length && tasks[i] != null; i++) {
			if (tasks[i].overdue()) { // If the task is overdue, insert the task into our output Task array
				oTaskArr[oArrIndex] = tasks[i];
				oArrIndex++; // Increment new array's index
			}
		}

		return oTaskArr;
	}

	public String toString() {
		StringBuilder oString = new StringBuilder();

		// Loop every element in task and only add tasks that exist
		for (int i = 0; i < tasks.length && tasks[i] != null; i++) {
			oString.append("\n").append(tasks[i].getName())
					.append(", priority ").append(tasks[i].getPriority()).append(",")
					.append(" is due on ").append(tasks[i].getWhenDue().toLocalDate())
					.append("\nThis task should take ")
					.append(tasks[i].getEstMinsToComplete())
					.append(" minutes to compete.");
		}

		oString.append("\nOverdue tasks: ").append(overdueTasks().length);

		return oString.toString();
	}
}
