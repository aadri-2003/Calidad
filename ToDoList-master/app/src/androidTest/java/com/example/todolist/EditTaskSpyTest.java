package com.example.todolist;
import android.widget.DatePicker;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class EditTaskSpyTest {

    @Mock
    DatePicker mockDatePicker;

    private EditTask editTask;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        editTask = spy(new EditTask());
        editTask.datePicker = mockDatePicker;
    }

    @Test
    public void testEditTask() {
        // Arrange
        String textousu = "Buy groceries";
        int day = 3;
        int month = 5;
        int year = 2024;
        String fechausu = "03/05/2024";
        String fechausu2 = "2024/05/03";
        String color = "blue";

        // Act
        editTask.editTask();

        // Assert
        verify(editTask).editTask(); // Verificar que se llama al método editTask

    }
}
/*  utilizando Mockito para crear un mock del objeto DatePicker y luego creamos un spy de la clase EditTask. Después, inicializamos el spy en el método setUp(). En el método de prueba testEditTask(), llamamos al método editTask() del spy y
    luego verificamos que se haya llamado correctamente utilizando verify(editTask).editTask()./*
 */
