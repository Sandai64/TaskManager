package sh.erwan;

import org.junit.jupiter.api.*;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TaskManagerTest
{

    private final static List<Task> tasksStub = new ArrayList<>() {{
        add(new Task("Test 1", false));
        add(new Task("Test 2", true));
    }};
    private final String MENU = """
            Task Manager :
            1  -  List all tasks
            2  -  Add a task
            3  -  Delete a task
            4  -  Mark a task as done
            5  -  Exit
            """;
    private final String LIST_TASKS = "Tasks :";
    private final String ADD_TASK = "Task to add ? ";
    private final String DELETE_TASK = "Task to delete ?";
    private final String TASK_DELETED = "Task deleted.";
    private final String MARK_AS_DONE = "Mark as done ?";
    private final String TASK_MARKED_AS_DONE = "Done.";
    private final String UNKNOWN_CHOICE = "Invalid input.";

    @Test
    @Order(1)
    void runListTask()
    {
        // Given
        IConsoleManager consoleManagerMock = mock(IConsoleManager.class);
        when(consoleManagerMock.ReadLong())
                .thenReturn(1L)
                .thenReturn(5L);

        TasksList TasksListStub = new TasksList(tasksStub);

        TaskManager target = new TaskManager(consoleManagerMock, TasksListStub);

        // When
        target.run();

        // Then
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(consoleManagerMock, times(5)).WriteLine(argumentCaptor.capture());
        List<String> capturedArguments = argumentCaptor.getAllValues();
        assertEquals(MENU, capturedArguments.get(0));
        assertEquals(MENU, capturedArguments.get(4));
        assertEquals(LIST_TASKS, capturedArguments.get(1));
        assertEquals(tasksStub.get(0).toString(), capturedArguments.get(2));
        assertEquals(tasksStub.get(1).toString(), capturedArguments.get(3));
    }

    @Test
    @Order(2)
    void runAddTask()
    {
        // Given
        IConsoleManager consoleManagerMock = mock(IConsoleManager.class);
        when(consoleManagerMock.ReadLong())
                .thenReturn(2L)
                .thenReturn(5L);
        when(consoleManagerMock.ReadLine())
                .thenReturn("Test 3");

        TasksList TasksListStub = new TasksList(tasksStub);

        TaskManager target = new TaskManager(consoleManagerMock, TasksListStub);

        // When
        target.run();

        // Then
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(consoleManagerMock, times(3)).WriteLine(argumentCaptor.capture());
        List<String> capturedArguments = argumentCaptor.getAllValues();
        assertEquals(MENU, capturedArguments.get(0));
        assertEquals(ADD_TASK, capturedArguments.get(1));
        assertEquals(MENU, capturedArguments.get(2));
    }

    @Test
    @Order(3)
    void runDeleteTask()
    {
        // Given
        IConsoleManager consoleManagerMock = mock(IConsoleManager.class);
        when(consoleManagerMock.ReadLong())
                .thenReturn(3L)
                .thenReturn(1L)
                .thenReturn(5L);

        TasksList TasksListStub = new TasksList(tasksStub);

        TaskManager target = new TaskManager(consoleManagerMock, TasksListStub);

        // When
        target.run();

        // Then
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(consoleManagerMock, times(7)).WriteLine(argumentCaptor.capture());
        List<String> capturedArguments = argumentCaptor.getAllValues();

        assertEquals(MENU, capturedArguments.get(0));
        assertEquals(tasksStub.get(0).toString(), capturedArguments.get(2));
        assertEquals(tasksStub.get(1).toString(), capturedArguments.get(3));
        assertEquals(DELETE_TASK, capturedArguments.get(4));
        assertEquals(TASK_DELETED, capturedArguments.get(5));
        assertEquals(MENU, capturedArguments.get(6));
    }

    @Test
    @Order(4)
    void runMarkTaskAsDone()
    {
        // Given
        IConsoleManager consoleManagerMock = mock(IConsoleManager.class);
        when(consoleManagerMock.ReadLong())
                .thenReturn(4L)
                .thenReturn(1L)
                .thenReturn(5L);
        List<Task> tasksStubForTaskAsDone = new ArrayList<>() {{
            add(new Task("Test do mark as done", false));
        }};
        TasksList TasksListStub = new TasksList(tasksStubForTaskAsDone);

        TaskManager target = new TaskManager(consoleManagerMock, TasksListStub);

        // When
        target.run();

        // Then
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(consoleManagerMock, times(5)).WriteLine(argumentCaptor.capture());
        List<String> capturedArguments = argumentCaptor.getAllValues();
        assertEquals(MENU, capturedArguments.get(0));
        assertEquals(tasksStubForTaskAsDone.get(0).toString(), capturedArguments.get(1));
        assertEquals(MARK_AS_DONE, capturedArguments.get(2));
        assertEquals(TASK_MARKED_AS_DONE, capturedArguments.get(3));
        assertEquals(MENU, capturedArguments.get(4));
    }
}