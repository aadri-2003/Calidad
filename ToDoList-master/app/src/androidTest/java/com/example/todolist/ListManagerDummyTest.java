package com.example.todolist;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ListManagerDummyTest {

    @Test
    public void testListManagerGetTaskList() {
        // Arrange
        ListManager listManager = new ListManager();
        task dummyTask1 = new task("Task 1", "2024-05-03", 2024, 5, 3, "03/05/2024", "blue");
        task dummyTask2 = new task("Task 2", "2024-05-04", 2024, 5, 4, "04/05/2024", "red");
        listManager.addTask(dummyTask1);
        listManager.addTask(dummyTask2);

        // Act
        ArrayList<task> taskList = listManager.getTasklist();

        // Assert
        assertEquals(2, taskList.size());
        assertEquals(dummyTask1, taskList.get(0));
        assertEquals(dummyTask2, taskList.get(1));
    }
}
    /*crea una instancia de ListManager y agrega dos tareas ficticias utilizando el método addTask(). Luego, llama al método getTasklist()
        para obtener la lista de tareas y verifica que la lista devuelta contenga las dos tareas que se agregaron previamente */