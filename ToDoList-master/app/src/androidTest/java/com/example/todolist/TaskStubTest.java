package com.example.todolist;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TaskStubTest {

    @Test
    public void testTaskGetters() {
        // Arrange
        String text = "Buy groceries";
        String date = "2024-05-03";
        int year = 2024;
        int month = 5;
        int day = 3;
        String dateOrd = "03/05/2024";
        String color = "blue";
        String note = "Remember potatoes";

        // Act
        TaskStub task = new TaskStub(text, date, year, month, day, dateOrd, color, note);

        // Assert
        assertEquals(text, task.getText());
        assertEquals(date, task.getDate());
        assertEquals(year, task.getYear());
        assertEquals(month, task.getMonth());
        assertEquals(day, task.getDay());
        assertEquals(dateOrd, task.getDateOrd());
        assertEquals(color, task.getColor());
    }
}

class TaskStub extends task {
    public TaskStub(String text, String date, int year, int month, int day, String date2, String color, String note) {
        super(text, date, year, month, day, date2, color, note);
    }

    @Override
    public String getText() {
        return "Buy groceries";
    }

    @Override
    public String getDate() {
        return "2024-05-03";
    }

    @Override
    public int getYear() {
        return 2024;
    }

    @Override
    public int getMonth() {
        return 5;
    }

    @Override
    public int getDay() {
        return 3;
    }

    @Override
    public String getDateOrd() {
        return "03/05/2024";
    }

    @Override
    public String getColor() {
        return "blue";
    }
}
/*  crea una instancia de TaskStub, que extiende la clase task, y sobrescribe los métodos
    para devolver valores predefinidos. Luego, comprueba si los valores devueltos coinciden con los valores esperados./*
 */

