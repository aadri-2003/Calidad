package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class EditTask extends AppCompatActivity {


    public DatePicker datePicker;
    public ListManager listManager;
    public SharedPreferences sharedPreferences;
    public EditText texto;
    public DatePicker fecha;

    private String textousu, fechausu, fechausu2, noteusu;
    private task t;
    private ListManager list;
    private int pos;
    private int day;
    private int month;
    private int year;
    Spinner spinner;
    private ArrayList<String> colors;
    private String color;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        Intent intent = getIntent();

        if(getIntent().getExtras() != null) {
            t = (task) intent.getSerializableExtra("task");
            list = (ListManager) intent.getSerializableExtra("list");
            pos = (int) intent.getSerializableExtra("index");
        }


        EditText texto = findViewById(R.id.textousuario);
        texto.setText(t.getText());
        EditText note = findViewById(R.id.notausuario);
        note.setText(t.getNote());


        DatePicker fecha= findViewById(R.id.fechausuario);

        fecha.init(t.getYear(),t.getMonth(),t.getDay(),null);

        Button btn = findViewById(R.id.aceptar);
        Button btn1 = findViewById(R.id.cancelar);

        spinner = findViewById(R.id.spinner);

        loadData(); //Carga la lista de tareas

        colors = new ArrayList<>();
        colors.add("Blanco");
        colors.add("Rosa");
        colors.add("Azul");
        colors.add("Verde");
        colors.add("Amarillo");
        colors.add("Rojo");


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent intent = new Intent(getContext(), SecondFragment.class);
                textousu = texto.getText().toString();
                noteusu = note.getText().toString();
                day = fecha.getDayOfMonth();
                month = fecha.getMonth(); // Los meses comienzan en 0
                year = fecha.getYear();

                // Formatea la fecha seleccionada
                fechausu = String.format("%02d/%02d/%04d", day, month + 1, year);
                fechausu2 = String.format("%04d/%02d/%02d", year, month + 1, day);
                editTask();
                saveData();


                Intent intent = new Intent(EditTask.this, HomeScreen.class);
                startActivity(intent);
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,colors);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                color = colors.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditTask.this, HomeScreen.class);
                startActivity(intent);

                //Mostrar mensaje "Cancelado"
                int duration = Toast.LENGTH_SHORT;
                CharSequence text = "Acción cancelada";
                Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                toast.show();
            }
        });
    }

    void saveData() {
        SharedPreferences sharedPreferences= this.getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(listManager);
        editor.putString("tasklist", json);
        editor.apply();
    }

    void loadData() {
        SharedPreferences sharedPreferences= this.getSharedPreferences("pref", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("tasklist", null);
        Type type = new TypeToken<ListManager>() {}.getType();
        listManager = gson.fromJson(json, type);

        if (listManager == null){ //Si la lista está vacía

            //Crear lista y guardar lista de tareas en listManager
            listManager = new ListManager();


            //Mostrar mensaje "se crea"
            int duration = Toast.LENGTH_SHORT;
            CharSequence text = "Se crea lista vacia";
            Toast toast = Toast.makeText(this, text, duration);
            toast.show();

        }
    }


    public void editTask(){

        task tareaNueva = new task(textousu, fechausu,year,month,day,fechausu2, color, noteusu);

        listManager.addTaskbyPos(tareaNueva,pos);

        //Mostrar  mensaje tarea guardada
        int duration = Toast.LENGTH_SHORT;
        CharSequence text = "Tarea guardada";
        Toast toast = Toast.makeText(this, text, duration);
        toast.show();

    }
}