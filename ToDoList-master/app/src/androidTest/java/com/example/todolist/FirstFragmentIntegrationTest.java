package com.example.todolist;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.todolist.FirstFragment;
import com.example.todolist.HomeScreen;
import com.example.todolist.ListManager;
import com.example.todolist.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class FirstFragmentIntegrationTest {

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

    private ListManager listManager;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        listManager = new ListManager();
    }

    @Test
    public void testAddTask_SaveData_ShowToast() {
        // Arrange
        when(mockSharedPreferences.edit()).thenReturn(mockEditor);
        when(mockSharedPreferences.getString("tasklist", null)).thenReturn(null);
        when(mockDatePicker.getDayOfMonth()).thenReturn(3);
        when(mockDatePicker.getMonth()).thenReturn(4); // Months are 0-based in DatePicker
        when(mockDatePicker.getYear()).thenReturn(2024);
        when(mockSpinner.getSelectedItem()).thenReturn("Blanco");

        ActivityScenario<HomeScreen> activityScenario = ActivityScenario.launch(HomeScreen.class);
        activityScenario.onActivity(new ActivityScenario.ActivityAction<HomeScreen>() {
            @Override
            public void perform(HomeScreen activity) {
                FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
                FirstFragment fragment = FirstFragment.newInstance(null, null, activity);
                transaction.replace(R.id.fragment_container, fragment);
                transaction.commit();

                fragment.sharedPreferences = mockSharedPreferences;
                fragment.texto = mockEditText;
                fragment.fecha = mockDatePicker;
                fragment.spinner = mockSpinner;

                when(mockEditText.getText().toString()).thenReturn("Buy groceries");

                // Act
                fragment.addTask();
                fragment.saveData();
                fragment.loadData();
                fragment.addTask();

                // Assert
                verify(mockSharedPreferences).edit();
                verify(mockEditor).putString("tasklist", "[{\"text\":\"Buy groceries\",\"date\":\"03/05/2024\",\"day\":3,\"month\":4,\"year\":2024,\"dateOrd\":\"2024/05/03\",\"color\":\"Blanco\"}]");
                verify(mockEditor).apply();
                verify(mockToast).show();
            }
        });
    }
}
/* probar la interacción de varios de sus componentes, como la creación de una tarea, guardarla en SharedPreferences y mostrar un mensaje Toast. */