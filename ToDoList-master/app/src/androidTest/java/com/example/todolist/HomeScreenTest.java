package com.example.todolist;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
public class HomeScreenTest {

    @Rule
    public ActivityScenarioRule<HomeScreen> activityScenarioRule =
            new ActivityScenarioRule<>(HomeScreen.class);

    @Test
    public void testNavigation() {
        // Abre la actividad
        ActivityScenario<HomeScreen> activityScenario = activityScenarioRule.getScenario();

        // Verifica que el segundo fragmento esté cargado por defecto
        Espresso.onView(withId(R.id.secondFragment))
                .check(matches(isDisplayed()));

        // Navega al primer fragmento
        Espresso.onView(withId(R.id.firstFragment)).perform(click());

        // Verifica que el primer fragmento esté cargado
        Espresso.onView(withId(R.id.firstFragment))
                .check(matches(isDisplayed()));

        // Navega al tercer fragmento
        Espresso.onView(withId(R.id.thirdFragment)).perform(click());

        // Verifica que el tercer fragmento esté cargado
        Espresso.onView(withId(R.id.thirdFragment))
                .check(matches(isDisplayed()));

        // Navega de vuelta al segundo fragmento
        Espresso.onView(withId(R.id.secondFragment)).perform(click());

        // Verifica que el segundo fragmento esté cargado nuevamente
        Espresso.onView(withId(R.id.secondFragment))
                .check(matches(isDisplayed()));
    }
}

/*   test simula la navegación a través de los fragmentos en la actividad HomeScreen,
 verificando que los fragmentos se carguen correctamente después de hacer clic en los elementos del menú de navegación inferior*/
