package com.example.todolist;

import android.view.MenuItem;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.todolist.HomeScreen;
import com.example.todolist.R;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class HomeScreenUITest {

    @Rule
    public ActivityScenarioRule<HomeScreen> activityScenarioRule = new ActivityScenarioRule<>(HomeScreen.class);

    private ActivityScenario<HomeScreen> activityScenario;

    @Before
    public void setUp() {
        activityScenario = activityScenarioRule.getScenario();
    }

    @Test
    public void testBottomNavigationClick() {
        activityScenario.onActivity(activity -> {
            assertNotNull(activity.findViewById(R.id.bottom_navigation));
            BottomNavigationItemView secondFragmentItem = activity.findViewById(R.id.secondFragment);
            assertNotNull(secondFragmentItem);

            // Verifica que el segundo fragmento esté seleccionado inicialmente
            assertTrue(secondFragmentItem.isSelected());

            // Simula hacer clic en el primer fragmento
            onView(withId(R.id.firstFragment)).perform(click());

            // Verifica que el primer fragmento se haya cargado
            onView(withId(R.id.firstFragment)).check(matches(ViewMatchers.isSelected()));

            // Simula hacer clic en el tercer fragmento
            onView(withId(R.id.thirdFragment)).perform(click());

            // Verifica que el tercer fragmento se haya cargado
            onView(withId(R.id.thirdFragment)).check(matches(ViewMatchers.isSelected()));
        });
    }


}
/*  test verifica si la navegación entre fragmentos funciona correctamente cuando se hace clic en los elementos de la barra de navegación inferior.*/
