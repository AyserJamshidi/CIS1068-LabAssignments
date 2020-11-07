public class HoneyDoList {
	private final int INITIAL_CAPACITY;

	private Task[] tasks;
	private int numTasks;

	public HoneyDoList() {
		this.INITIAL_CAPACITY = 50;

		this.tasks = new Task[this.INITIAL_CAPACITY];
		this.numTasks = 0;
	}

	public int find(String name) {
		for (int i = 0; i < tasks.length; i++)
			if (tasks[i].getName().equalsIgnoreCase(name))
				return i;

		return -1;
	}

	public void addTask(Task newTask) {
		int availableSlot = nextAvailableSlot();

		if (availableSlot != -1) {
			tasks[availableSlot] = newTask;
		} else { // Array is full!
			Task[] newTaskContainer = new Task[tasks.length + 1];

			for (int i = 0; i < tasks.length; i++)
				newTaskContainer[i] = tasks[i];

			newTaskContainer[tasks.length] = newTask;

			tasks = newTaskContainer;
		}

		numTasks++;
	}

	private int nextAvailableSlot() {
		for (int i = 0; i < tasks.length; i++)
			if (tasks[i] == null)
				return i;

		return -1;
	}

	public int totalTime() {
		int totalTime = 0;

		for (Task curTask : tasks)
			if (curTask != null)
				totalTime += curTask.getEstMinsToComplete();
			else break;

		return totalTime;
	}

	public int shortestTime() {
		int shortestTimeIndex = -1;

		for (int i = 0, shortestTimeRecorded = Integer.MAX_VALUE; i < tasks.length; i++) {
			if (tasks[i] != null) {
				if (shortestTimeRecorded > tasks[i].getEstMinsToComplete())
					shortestTimeIndex = i;
			} else break;
		}

		return shortestTimeIndex;
	}

	public Task completeTask(int index) {
		Task taskAtIndex = null;

		for (boolean canShiftNow = false; index < tasks.length; index++) {
			if (!canShiftNow) {
				taskAtIndex = tasks[index];
				canShiftNow = true;
				numTasks--;
			}

			// If this isn't the last element, set current element to next element, otherwise set to null.
			tasks[index] = ((index + 1) < tasks.length) ? tasks[index + 1] : null;
		}

		return taskAtIndex;
	}

	public String toString() {
		StringBuilder oString = new StringBuilder();

		for (Task curTask : tasks)
			if (curTask != null)
				oString.append(curTask.getName())
						.append(" is due on ")
						.append(curTask.getWhenDue().toLocalDate())
						.append("\nThis task should take ")
						.append(curTask.getEstMinsToComplete())
						.append(" minutes to compete.");

		return oString.toString();
	}
}
