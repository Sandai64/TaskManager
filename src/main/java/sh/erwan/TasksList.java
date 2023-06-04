package sh.erwan;

import java.util.ArrayList;
import java.util.List;

public class TasksList
{

    private List<Task> tasksList = new ArrayList<>();

    public TasksList(List<Task> tasksStub)
    {
        tasksList = tasksStub;
    }

    public TasksList() {}

    public Task createTask(String description)
    {
        Task task = new Task(description);
        tasksList.add(task);
        return task;
    }

    public void deleteTask(long id)
    {
        for (Task task : tasksList)
        {
            if (task.getId() == id)
            {
                tasksList.remove(task);
                break;
            }
        }
    }

    public void markAsDone(long id)
    {
        for (Task task : tasksList)
        {
            if (task.getId() == id)
            {
                task.markAsDone();
                break;
            }
        }
    }

    public List<Task> getTasks() {
        return tasksList;
    }

    public List<Task> getTasks(boolean done) {
        List<Task> tasksToReturn = new ArrayList<>();
        for (Task task : tasksList) {
            if (task.isDone() == !done || task.isDone()) {
                tasksToReturn.add(task);
            }
        }
        return tasksToReturn;
    }
}
