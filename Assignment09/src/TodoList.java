import java.time.LocalDateTime;

public class TodoList {
	public static void main(String[] args) {
		// Task Class test
		Task testTask = new Task("Test task", 3,
				LocalDateTime.of(2020, 11, 16, 13, 0),
				20);

		System.out.println("Name: " + testTask.getName()); // Expect "Test task"
		testTask.setName("Tester tasker");
		System.out.println("Name: " + testTask.getName()); // Expect "Tester tasker"

		System.out.println("Estimated Mins to Complete: " + testTask.getEstMinsToComplete()); // Expect 20
		testTask.decreasePriority(1); // 3 - 1 = 2
		System.out.println("Priority: " + testTask.getPriority()); // Expect 2
		testTask.decreasePriority(10); // Too high
		System.out.println("Priority: " + testTask.getPriority()); // Expect 0

		testTask.increasePriority(-1); // Too low
		System.out.println("Priority: " + testTask.getPriority()); // Expect 0
		testTask.increasePriority(10); // 0 + 10 = 10
		System.out.println("Priority: " + testTask.getPriority()); // Expect 10

		testTask.setEstMinsToComplete(-1); // Too low
		System.out.println("Estimated Mins to Complete: " + testTask.getEstMinsToComplete()); // Expect 0
		testTask.setEstMinsToComplete(10); // = 10
		System.out.println("Estimated Mins to Complete: " + testTask.getEstMinsToComplete()); // Expect 10

		System.out.println("Due date: " + testTask.getWhenDue());
		System.out.println("Is overdue: " + testTask.overdue()); // Expect false

		testTask.setWhenDue(LocalDateTime.of(2020, 11, 14, 13, 0));
		System.out.println("Is overdue: " + testTask.overdue()); // Expect true

		System.out.println();
		System.out.println();
		System.out.println();

		// HoneyDoList Class test
		HoneyDoList honeydo = new HoneyDoList();

		honeydo.addTask(new Task("Take aspirin", 1,
				null,
				118));
		System.out.println(honeydo);
		System.out.println(); // Expect Take aspirin task.

		honeydo.addTask(new Task("Take tylenol", 1,
				LocalDateTime.of(2020, 11, 6, 13, 0),
				119));
		System.out.println(honeydo);
		System.out.println(); // Expect aspirin and tylenol task

		System.out.println("\"Take tylenol\" index: " + honeydo.find("take tylenol")); // Expect 1
		System.out.println("Shortest task index: " + honeydo.shortestTime()); // Expect 0
		System.out.println("Total HoneyDoList time: " + honeydo.totalTime()); // Expect 118 + 119 = 237
		System.out.println();

		Task removedTask = honeydo.completeTask(0);
		System.out.println("Removed task: \n" + removedTask);

		honeydo.completeTask(0); // remove the tylenol task

		for (int i = 0; i < 60; i++)
			honeydo.addTask(new Task("Test addition " + i, 1,
					LocalDateTime.of(2021, 11, 6, 13, 0),
					118));

		System.out.println("HoneyDoList Trial 1: \n" + honeydo); // Expect 60 "test addition" in total.
		System.out.println();

		honeydo.completeTask(55); // Remove the 55th index ("Test addition 55")

		System.out.println("HoneyDoList Trial 2: \n" + honeydo); // Expect 59 "test addition" in total.

		honeydo.completeTask(59); // Remove nothing
		honeydo.completeTask(58); // Remove the 58th index ("Test addition 59")

		System.out.println("HoneyDoList Trial 3: \n" + honeydo); // Expect 58 "test addition" in total.


		honeydo.addTask(new Task("Test addition 300", 1,
				LocalDateTime.of(2019, 11, 6, 13, 0),
				118));

		System.out.println("HoneyDoList Trial 4: \n" + honeydo); // Expect 58 "test addition" in total AND 1 overdue task
	}
}
