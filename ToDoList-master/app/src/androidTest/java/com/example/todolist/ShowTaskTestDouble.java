package com.example.todolist;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class ShowTaskTestDouble {

    @Mock
    Context mockContext;

    @Mock
    SharedPreferences mockSharedPreferences;

    @Mock
    SharedPreferences.Editor mockEditor;

    @Mock
    Intent mockIntent;

    @Mock
    AlertDialog.Builder mockBuilder;

    @Mock
    AlertDialog mockDialog;

    @Mock
    ListManager mockListManager;

    @Mock
    task mockTask;

    @Mock
    Button mockDeleteButton;

    @Mock
    Button mockReturnButton;

    private ShowTask showTask;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        showTask = new ShowTask();
        showTask.deleteButton = mockDeleteButton;
        showTask.returnButton = mockReturnButton;
        showTask.list = mockListManager;
        showTask.task = mockTask;
        when(mockContext.getSharedPreferences(anyString(), anyInt())).thenReturn(mockSharedPreferences);
        when(mockSharedPreferences.edit()).thenReturn(mockEditor);
        when(mockBuilder.create()).thenReturn(mockDialog);
        when(mockIntent.putExtra(anyString(), (String) any())).thenReturn(mockIntent);
        when(mockTask.getText()).thenReturn("Buy groceries");
        when(mockTask.getDate()).thenReturn("2024-05-03");
        when(mockIntent.getIntExtra("index", -1)).thenReturn(0);
    }

    @Test
    public void testDeleteButtonClicked() {
        // Act
        showTask.deleteButton.performClick();

        // Assert
        verify(mockBuilder).create();
        verify(mockDialog).show();
        verify(mockListManager).borrar(anyInt());
        verify(mockTask).getText();
        verify(mockTask).getDate();
        verify(mockSharedPreferences.edit()).putString(eq("tasklist"), anyString());
        verify(mockEditor).apply();
        verify(mockTask, times(2)).getDate();
    }

    @Test
    public void testReturnButtonClicked() {
        // Act
        showTask.returnButton.performClick();

        // Assert
        verify(mockContext).startActivity(mockIntent);
    }
}

/*
Este test verifica el comportamiento de la actividad ShowTask al hacer clic en los botones de eliminar y retorno, asegurándose de que se
realicen las acciones esperadas correctamente, como mostrar un cuadro de diálogo de confirmación, borrar la tarea seleccionada y volver a la pantalla de inicio.
*/
