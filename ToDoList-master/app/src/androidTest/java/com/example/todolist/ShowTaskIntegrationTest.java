package com.example.todolist;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ShowTaskIntegrationTest {

    @Before
    public void setUp() throws Exception {
        ActivityScenario.launch(ShowTask.class);
    }

    @Test
    public void testTaskDisplayed() {
        Espresso.onView(ViewMatchers.withId(R.id.name))
                .check(ViewAssertions.matches(ViewMatchers.withText("Nombre de la tarea")));

        Espresso.onView(ViewMatchers.withId(R.id.date))
                .check(ViewAssertions.matches(ViewMatchers.withText("Fecha de la tarea")));
    }

    @Test
    public void testDeleteButton() {
        Espresso.onView(ViewMatchers.withId(R.id.borrar))
                .check(ViewAssertions.matches(ViewMatchers.isClickable()));
    }

    @Test
    public void testReturnButton() {
        Espresso.onView(ViewMatchers.withId(R.id.volver))
                .check(ViewAssertions.matches(ViewMatchers.isClickable()));
    }
}

