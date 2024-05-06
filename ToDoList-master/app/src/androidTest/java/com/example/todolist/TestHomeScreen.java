package com.example.todolist;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.PickerActions.setDate;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.allOf;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

public class TestHomeScreen {
    @Rule
    public ActivityScenarioRule<HomeScreen> activityRule = new ActivityScenarioRule<>(HomeScreen.class);
    @Test
    public void testInterfazFirstFragment() {
        onView(withId(R.id.firstFragment)).perform(click());
        onView(withId(R.id.textousuario)).check(matches(isDisplayed()));
    }

    @Test
    public void testUnitarioCreateTask(){
        //Entra a la pantalla de crear tarea
        onView(withId(R.id.firstFragment)).perform(click());
        //Datos de input para crear la tarea
        String inputText = "Texto de ejemplo";
        int year = 2024;
        int month = 05;
        int day = 20;
        //Se rellena el formulario
        onView(withId(R.id.textousuario)).perform(typeText(inputText), closeSoftKeyboard());
        onView(withId(R.id.fechausuario)).perform(setDate(year, month, day));
        //Se pulsa el boton de crear la tarea
        onView(withId(R.id.aceptar)).perform(click());
        //Se comprueba que existe la tarea
        onView(withId(R.id.listView)).check(matches(hasDescendant(hasDescendant(hasDescendant(withText("Texto de ejemplo"))))));

    }

    @Test
    public void testUnitarioShowTask(){
        //Entra a la pantalla de crear tarea
        onView(withId(R.id.firstFragment)).perform(click());
        //Datos de input para crear la tarea
        String inputText = "Tarea para mostrar";
        int year = 2024;
        int month = 05;
        int day = 20;
        String inputDate = String.format("%02d", day) + "/" + String.format("%02d", month) + "/" + year;
        //Se rellena el formulario
        onView(withId(R.id.textousuario)).perform(typeText(inputText), closeSoftKeyboard());
        onView(withId(R.id.fechausuario)).perform(setDate(year, month, day));
        //Se pulsa el boton de crear la tarea
        onView(withId(R.id.aceptar)).perform(click());
        //Se entra en la tarea
//        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
        onView(withId(R.id.listView)).check(matches(hasDescendant(hasDescendant(hasDescendant(withText("Tarea para mostrar")))))).perform(click());
        //Se comprueba que los valores de input son los mismos que se muestran en la tarea
        onView(withId(R.id.name)).check(matches(withText(inputText)));
        onView(withId(R.id.date)).check(matches(withText(inputDate)));
    }

    @Test
    public void testUnitarioDeleteTask() {
        //Entra a la pantalla de crear tarea
        onView(withId(R.id.firstFragment)).perform(click());
        //Datos de input para crear la tarea
        String inputText = "Tarea para borrar";
        int year = 2024;
        int month = 05;
        int day = 20;
        //Se rellena el formulario
        onView(withId(R.id.textousuario)).perform(typeText(inputText), closeSoftKeyboard());
        onView(withId(R.id.fechausuario)).perform(setDate(year, month, day));
        //Se pulsa el boton de crear la tarea
        onView(withId(R.id.aceptar)).perform(click());
        //Se busca la tarea en la lista
        onView(withId(R.id.listView)).check(matches(hasDescendant(hasDescendant(hasDescendant(withText("Tarea para borrar")))))).perform(click());
        //Se borra la tarea
        onView(withId(R.id.borrar)).perform(click());
        onView(withText("OK")).inRoot(isDialog()).perform(click());
        // Verifica si la ListView está vacía
        onView(withId(R.id.listView)).check(matches(not(hasDescendant(hasDescendant(hasDescendant(withText("Tarea para borrar")))))));

    }

    @Test
    public void testUnitarioEditTask() {
        //Entra a la pantalla de crear tarea
        onView(withId(R.id.firstFragment)).perform(click());
        //Datos de input para crear la tarea
        String inputText = "Tarea para editar";
        int year = 2024;
        int month = 05;
        int day = 20;
        //Se rellena el formulario
        onView(withId(R.id.textousuario)).perform(typeText(inputText), closeSoftKeyboard());
        onView(withId(R.id.fechausuario)).perform(setDate(year, month, day));
        //Se pulsa el boton de crear la tarea
        onView(withId(R.id.aceptar)).perform(click());
        //Entra a la pantalla de editar tareas
        onView(withId(R.id.thirdFragment)).perform(click());
        //Se busca la tarea en la lista
//        onData(anything()).inAdapterView(withId(R.id.listView)).atPosition(0).perform(click());
        onView(withId(R.id.listView)).check(matches(hasDescendant(hasDescendant(hasDescendant(withText("Tarea para editar")))))).perform(click());
        //Se edita la tarea
        String editText = "editada";
        onView(withId(R.id.textousuario)).perform(typeText(inputText), closeSoftKeyboard());
        onView(withId(R.id.aceptar)).perform(click());
        // Verifica si la tarea esta editada
        onView(withId(R.id.listView)).check(matches(hasDescendant(hasDescendant(hasDescendant(withText("editada"))))));

    }

    @Test
    public void testInterfazButtonCancel() {
        onView(withId(R.id.firstFragment)).perform(click());
        onView(withId(R.id.cancelar)).perform(click());
        onView(withId(R.id.listView)).check(matches(isDisplayed()));
    }

}
