package sh.erwan;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TasksListTest
{

    private final List<Task> tasksStub = new ArrayList<>() {{
        add(new Task("Test 1", false));
        add(new Task("Test 2", true));
    }};

    @Test
    @Order(1)
    void TestGetTasks()
    {
        // Given
        TasksList target = new TasksList(tasksStub);
        // When
        List<Task> result = target.getTasks();
        // Then
        assertNotNull(result);
        assertEquals(tasksStub.size(), result.size());
        assertEquals(tasksStub.get(0), result.get(0));
    }

    @Test
    @Order(2)
    void TestCreateTask()
    {
        // Given
        TasksList target = new TasksList(tasksStub);
        String expectedDesc = "Test";
        // When
        Task result = target.createTask(expectedDesc);
        List<Task> resultTasks = target.getTasks();
        // Then
        assertNotNull(result);
        assertEquals(expectedDesc, result.getDescription());
        assertEquals(tasksStub.size(), resultTasks.size());
        assertTrue(resultTasks.contains(result));
    }

    @Test
    @Order(3)
    void TestDeleteTask()
    {
        // Given
        TasksList target = new TasksList(tasksStub);
        Task taskToDelete = tasksStub.get(0);
        // When
        target.deleteTask(taskToDelete.getId());
        List<Task> resultTasks = target.getTasks();
        // Then
        assertEquals(tasksStub.size(), resultTasks.size());
        assertFalse(resultTasks.contains(taskToDelete));
    }

    @Test
    @Order(4)
    void TestDoneTask()
    {
        // Given
        TasksList target = new TasksList(tasksStub);
        Task taskToDone = tasksStub.get(0);
        // When
        target.markAsDone(taskToDone.getId());
        List<Task> resultTasks = target.getTasks();
        // Then
        assertTrue(taskToDone.isDone());
        assertTrue(resultTasks.contains(taskToDone));
    }

    @Test
    @Order(5)
    void TestGetTasksWhitDone()
    {
        // Given
        TasksList target = new TasksList(tasksStub);
        // When
        List<Task> result = target.getTasks(true);
        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(tasksStub.get(0), result.get(0));
    }

    @Test
    @Order(6)
    void TestGetTasksWithNotDone()
    {
        // Given
        TasksList target = new TasksList(tasksStub);
        // When
        List<Task> result = target.getTasks(false);
        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(tasksStub.get(1), result.get(0));
    }
}