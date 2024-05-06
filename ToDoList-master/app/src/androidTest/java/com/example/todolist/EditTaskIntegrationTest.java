package com.example.todolist;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.todolist.EditTask;
import com.example.todolist.ListManager;
import com.example.todolist.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class EditTaskIntegrationTest {

    @Mock
    Context mockContext;

    @Mock
    SharedPreferences mockSharedPreferences;

    @Mock
    SharedPreferences.Editor mockEditor;

    @Mock
    EditText mockEditText;

    @Mock
    DatePicker mockDatePicker;

    @Mock
    Spinner mockSpinner;

    @Mock
    Toast mockToast;

    private EditTask editTaskActivity;
    private ListManager listManager;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        listManager = new ListManager();
        editTaskActivity = new EditTask();
        editTaskActivity.sharedPreferences = mockSharedPreferences;
        editTaskActivity.listManager = listManager;
    }

    @Test
    public void testEditTask_SaveData_LoadData() {
        // Arrange
        when(mockSharedPreferences.edit()).thenReturn(mockEditor);
        when(mockDatePicker.getDayOfMonth()).thenReturn(3);
        when(mockDatePicker.getMonth()).thenReturn(4); // Months are 0-based in DatePicker
        when(mockDatePicker.getYear()).thenReturn(2024);
        when(mockSpinner.getSelectedItem()).thenReturn("Blanco");

        // Act
        editTaskActivity.texto = mockEditText;
        editTaskActivity.fecha = mockDatePicker;
        editTaskActivity.spinner = mockSpinner;
        when(mockEditText.getText().toString()).thenReturn("Sample task");
        editTaskActivity.editTask();
        editTaskActivity.saveData();
        editTaskActivity.loadData();

        // Assert
        verify(mockSharedPreferences).edit();
        verify(mockEditor).putString("tasklist", "[{\"text\":\"Sample task\",\"date\":\"03/05/2024\",\"day\":3,\"month\":4,\"year\":2024,\"dateOrd\":\"2024/05/03\",\"color\":\"Blanco\"}]");
        verify(mockEditor).apply();
        assertEquals(listManager.getTasklist().size(), 1);
        assertEquals(listManager.getTasklist().get(0).getText(), "Sample task");
    }
}
/*  EditTask interactúa con SharedPreferences para almacenar y recuperar datos, un test de integración de tipo base de datos puede simular estas interacciones y verificar si los datos se almacenan y recuperan correctamente*/