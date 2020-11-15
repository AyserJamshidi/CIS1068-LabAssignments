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

public class HoneyDriver {
	public static void main(String[] args) {
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
