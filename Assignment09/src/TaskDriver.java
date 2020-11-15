/*
 * Name: Ayser Jamshidi
 * Class: CIS 1068
 * Section: 4
 * Date: November 16th, 2020
 * Assignment: 9. To-Do List
 *
 * Description:
 * https://cis.temple.edu/~jfiore/2020/fall/1068/assignments/09/
 * - Contains four classes: Task, HoneyDoList, HoneyDriver and TaskDriver.
 *
 * - Task is a class that contains variables, accessors and mutators to create and modify tasks for users
 * - HoneyDoList is a class that contains variables, accessors and mutators to create and modify task arrays for users
 * - TaskDriver is a class that contains a main function that will test all of the functions in Task
 * - HoneyDriver is a class that contains a main function that will test all of the functions in HoneyDoList
 *
 * You must compile TaskDriver and HoneyDriver separately as that's what the assignment asked for.
 */

import java.time.LocalDateTime;

public class TaskDriver {
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
	}
}
