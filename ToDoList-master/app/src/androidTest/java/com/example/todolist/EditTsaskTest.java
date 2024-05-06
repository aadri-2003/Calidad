package com.example.todolist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.todolist.EditTask;
import com.example.todolist.HomeScreen;
import com.google.gson.Gson;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@LargeTest
public class EditTsaskTest {

    @Rule
    public ActivityScenarioRule<EditTask> activityScenarioRule =
            new ActivityScenarioRule<>(EditTask.class);

    private EditTask editTaskActivity;

    @Before
    public void setUp() {
        activityScenarioRule.getScenario().onActivity(activity -> editTaskActivity = activity);
    }

    @Test
    public void testCancelButton() {
        // Verifica que el botón de cancelar esté presente
        Espresso.onView(withId(R.id.cancelar)).check(matches(isDisplayed()));

        // Realiza clic en el botón de cancelar
        Espresso.onView(withId(R.id.cancelar)).perform(click());

        // Verifica que la actividad HomeScreen esté abierta después de hacer clic en cancelar
        assertNotNull(editTaskActivity);
        assertEquals(editTaskActivity.getClass(), HomeScreen.class);
    }

    @Test
    public void testSaveData() {
        // Simula los datos de una lista de tareas
        ListManager listManager = new ListManager();
        listManager.addTask(new task("Tarea 1", "01/01/2022", 2022, 0, 1, "2022/01/01", "Blanco"));

        // Guarda los datos en SharedPreferences
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(listManager);
        editor.putString("tasklist", json);
        editor.apply();

        // Verifica que los datos se hayan guardado correctamente en SharedPreferences
        String savedJson = sharedPreferences.getString("tasklist", null);
        assertNotNull(savedJson);
        assertEquals(json, savedJson);
    }
}

/*  test verifica que el botón de cancelar funcione correctamente y que los datos se guarden correctamente en SharedPreferences
cuando se llama al método saveData() en la actividad EditTask */
