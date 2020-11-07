import java.time.LocalDateTime;

public class TodoList {
	public static void main(String[] args) {
		// Task Class test
		Task testTask = new Task("testy task", 3,
				LocalDateTime.of(2020, 11, 6, 13, 0),
				20);

		System.out.println(testTask.getEstMinsToComplete());
		System.out.println(testTask.overdue());


		// HoneyDoList Class test
		HoneyDoList honeydo = new HoneyDoList();

		System.out.println(honeydo);
		honeydo.addTask(new Task("take aspirin", 1,
				LocalDateTime.of(2020, 11, 6, 13, 0),
				120));
		System.out.println(honeydo);

		// print the item in the list which should
		// take the least amount of time
		System.out.println(honeydo.shortestTime()); // calls toString() in Task

		honeydo.completeTask(1);

		System.out.println("1: " + honeydo);

		honeydo.completeTask(0);

		System.out.println("2: " + honeydo);

		honeydo.addTask(new Task("deez nuts", 1,
				LocalDateTime.of(2021, 11, 6, 13, 0),
				120));

		System.out.println("3: " + honeydo);

		System.out.println("total time: " + honeydo.totalTime());
	}
}
