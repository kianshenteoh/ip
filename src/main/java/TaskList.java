import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("  Got it, I've added this task:");
        System.out.println("    " + task.toString());
        System.out.println("  Now you have " + tasks.size() + " tasks in the list.");
    }

    public void markTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task currTask = tasks.get(index);
            currTask.markDone();
            System.out.println("  Nice! I've marked this task as done:");
            System.out.println("    " + currTask);
        } else {
            System.out.println("  Task does not exist!");
        }
    }

    public void unmarkTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task currTask = tasks.get(index);
            currTask.markUndone();
            System.out.println("  Ok, I've marked this task as not done yet:");
            System.out.println("    " + currTask);
        } else {
            System.out.println("  Task does not exist!");
        }
    }

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

    public int size() {
        return tasks.size();
    }
}
