package com.example.todolist;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

import java.util.Calendar;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class FirstFragmentTest {

    @Rule
    public ActivityScenarioRule<HomeScreen> activityScenarioRule =
            new ActivityScenarioRule<>(HomeScreen.class);

    @Test
    public void testFormSubmission() {
        // Abre la actividad
        ActivityScenario<HomeScreen> activityScenario = activityScenarioRule.getScenario();

        // Inserta texto en el campo de texto
        Espresso.onView(ViewMatchers.withId(R.id.textousuario)).perform(typeText("Hacer la compra"));

        // Establece la fecha en el selector de fecha
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        Espresso.onView(ViewMatchers.withId(R.id.fechausuario)).perform(PickerActions.setDate(year, month, dayOfMonth));

        // Selecciona un color en el spinner
        Espresso.onView(ViewMatchers.withId(R.id.spinner)).perform(click());
        Espresso.onData(ViewMatchers.withText("Azul")).perform(click());

        // Presiona el botón de "Aceptar"
        Espresso.onView(ViewMatchers.withId(R.id.aceptar)).perform(click());

        // Verifica que se muestre el mensaje correcto
        Espresso.onView(ViewMatchers.withText("Tarea guardada")).check(matches(ViewMatchers.isDisplayed()));
    }
}
/* test simula la interacción del usuario con el formulario representado por FirstFragment, ingresando texto, seleccionando una fecha y un color,
y luego presionando el botón "Aceptar". Luego, verifica que se muestre el mensaje "Tarea guardada */