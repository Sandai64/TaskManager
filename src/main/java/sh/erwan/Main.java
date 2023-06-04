package sh.erwan;

public class Main
{
    public static void main(String[] args)
    {
        ConsoleManager consoleManager = new ConsoleManager();

        TasksList taskList = new TasksList();
        TaskManager taskManager = new TaskManager(consoleManager, taskList);

        taskManager.run();
    }
}