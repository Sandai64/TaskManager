package sh.erwan;

public class TaskManager
{

    private final IConsoleManager consoleManager;
    private final TasksList tasksList;

    public TaskManager(IConsoleManager consoleManager, TasksList tasksList)
    {
        this.consoleManager = consoleManager;
        this.tasksList = tasksList;
    }

    public void run()
    {
        Long userInput = 0L;
        
        while (userInput != 5)
        {
            consoleManager.WriteLine("""
            Task Manager :
            1  -  List all tasks
            2  -  Add a task
            3  -  Delete a task
            4  -  Mark a task as done
            5  -  Exit
            """);

            userInput = consoleManager.ReadLong();

            switch (Integer.parseInt(userInput.toString()))
            {
                case 1 -> {
                    consoleManager.WriteLine("Tasks :");
                    tasksList.getTasks().forEach(task -> consoleManager.WriteLine(task.toString()));
                }
                case 2 -> {
                    consoleManager.WriteLine("Task to add ? ");

                    String taskDescription = consoleManager.ReadLine();
                    tasksList.createTask(taskDescription);
                }
                case 3 -> {
                    tasksList.getTasks().forEach(task -> consoleManager.WriteLine(task.toString()));
                    consoleManager.WriteLine("Task to delete ?");

                    Long taskToDelete = consoleManager.ReadLong();
                    tasksList.deleteTask(taskToDelete);
                    
                    consoleManager.WriteLine("Task deleted.");
                }
                case 4 -> {
                    tasksList.getTasks().forEach(task -> consoleManager.WriteLine(task.toString()));
                    consoleManager.WriteLine("Mark as done ?");

                    Long taskToMarkAsDone = consoleManager.ReadLong();
                    tasksList.markAsDone(taskToMarkAsDone);

                    consoleManager.WriteLine("Done.");
                }
                case 5 -> {}
                default -> consoleManager.WriteLine("Invalid input.");
            }

            
        }
    }

}
