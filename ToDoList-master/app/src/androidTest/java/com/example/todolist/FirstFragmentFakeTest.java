package com.example.todolist;
import android.content.SharedPreferences;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FirstFragmentFakeTest {

    @Mock
    SharedPreferences mockSharedPreferences;

    @Mock
    EditText mockEditText;

    @Mock
    DatePicker mockDatePicker;

    @Mock
    Spinner mockSpinner;

    private FirstFragment firstFragment;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        firstFragment = new FirstFragment(null); // Pass null for HomeScreen since it's not used in this test
        firstFragment.sharedPreferences = mockSharedPreferences;
        firstFragment.texto = mockEditText;
        firstFragment.fecha = mockDatePicker;
        firstFragment.spinner = mockSpinner;
    }

    @Test
    public void testAddTask() {
        // Arrange
        when(mockEditText.getText().toString()).thenReturn("Buy groceries");
        when(mockDatePicker.getDayOfMonth()).thenReturn(3);
        when(mockDatePicker.getMonth()).thenReturn(4); // Months are 0-based in DatePicker
        when(mockDatePicker.getYear()).thenReturn(2024);
        when(mockSpinner.getSelectedItem()).thenReturn("Blanco");

        // Act
        firstFragment.addTask();

        // Assert
        verify(mockSharedPreferences).edit();
        // Add more verifications as needed
    }

    @Test
    public void testSaveData() {
        // Act
        firstFragment.saveData();

        // Assert
        verify(mockSharedPreferences).edit();
        // Add more verifications as needed
    }

    @Test
    public void testLoadData() {
        // Arrange
        when(mockSharedPreferences.getString("tasklist", null)).thenReturn(null);

        // Act
        firstFragment.loadData();
    }
}

/*  simular su comportamiento sin depender de las implementaciones reales de las clases asociadas como SharedPreferences y Toast*/
