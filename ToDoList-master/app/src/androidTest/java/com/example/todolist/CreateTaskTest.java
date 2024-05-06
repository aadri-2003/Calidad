package com.example.todolist;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import androidx.test.espresso.contrib.PickerActions;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

@RunWith(AndroidJUnit4.class)
public class CreateTaskTest {

    @Test
    public void testAddTask() {
        // Entramos en añadir tarea
        Espresso.onView(withId(R.id.firstFragment)).perform(click());

        // Rellenamos los detalles de la tarea a añadir
        Espresso.onView(withId(R.id.textousuario)).perform(typeText("Regar las plantas"));
        Espresso.onView(withId(R.id.fechausuario)).perform(PickerActions.setDate(2024,5,20));

        // Guardamos la tarea
        Espresso.onView(ViewMatchers.withId(R.id.aceptar)).perform(click());

        // Vamos al listado de tareas
        Espresso.onView(withId(R.id.secondFragment)).perform(click());

        // Verificamos que la tarea haya aparecido en el listado
        Espresso.onView(withId(R.id.listView)).check(matches(withText("Regar las plantas")));
    }
}