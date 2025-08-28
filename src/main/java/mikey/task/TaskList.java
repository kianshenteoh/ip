package mikey.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();

    public TaskList(List<Task> initial) {
        if (initial != null) {
            tasks.addAll(initial);
        }
    }

    public List<Task> getList() {
        return tasks;
    }

    /**
     * Adds a task to the task list
     *
     * @param task Task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("  Got it, I've added this task:");
        System.out.println("    " + task.toString());
        System.out.println("  Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Marks a task as complete
     *
     * @param index Index of task to be marked
     */
    public void markTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task currTask = tasks.get(index);
            currTask.markDone();
            System.out.println("  Nice! I've marked this task as done:");
            System.out.println("    " + currTask);
        } else {
            System.out.println("  mikey.task.Task does not exist!");
        }
    }

    /**
     * Marks a task as incomplete
     *
     * @param index Index of a task to be unmarked
     */
    public void unmarkTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task currTask = tasks.get(index);
            currTask.markUndone();
            System.out.println("  Ok, I've marked this task as not done yet:");
            System.out.println("    " + currTask);
        } else {
            System.out.println("  mikey.task.Task does not exist!");
        }
    }

    /**
     * Prints the list of tasks
     */
    public void printTasks() {
        if (tasks.isEmpty()) {
            System.out.println("  No tasks yet!");
            return;
        }
        System.out.println("  Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            int index = i + 1;
            Task currTask = tasks.get(i);
            System.out.println("  " + index + ". " + currTask);
        }
    }

    /**
     * Deletes a task
     *
     * @param index Index of task to be deleted
     */
    public void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task currTask = tasks.get(index);
            tasks.remove(index);
            System.out.println("  Noted. I've removed this task:");
            System.out.println("    " + currTask);
        } else {
            System.out.println("  mikey.task.Task does not exist!");
        }
    }

    public TaskList findTasks(String keyword) {
        TaskList foundTasks = new TaskList(null);
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                foundTasks.addTask(task);
            }
        }
        return foundTasks;
    }

    /**
     * Returns the size of the task list
     * @return Size of task list
     */
    public int size() {
        return tasks.size();
    }
}
