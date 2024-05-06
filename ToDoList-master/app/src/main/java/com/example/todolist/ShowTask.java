package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;

public class ShowTask extends AppCompatActivity {

    public task task;
    public ListManager list;
    public Button deleteButton;
    public Button returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_task);

        TextView name = findViewById(R.id.name);
        TextView note = findViewById(R.id.note);
        TextView date = findViewById(R.id.date);
        deleteButton = findViewById(R.id.borrar);
        returnButton = findViewById(R.id.volver);

        Intent intent = getIntent();

        if(getIntent().getExtras() != null) {
            task = (task) intent.getSerializableExtra("task");
            list = (ListManager) intent.getSerializableExtra("list");
            int pos = (int) intent.getSerializableExtra("index");
            name.setText(task.getText());
            note.setText(task.getNote());
            date.setText(task.getDate());
            saveData();
        } else {
            name.setText("Ha ocurrido un error");
        }

        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                removeTask();
            }
        });

        returnButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                returnToHomeScreen();
            }
        });
    }

    private void saveData() {
        SharedPreferences sharedPreferences= this.getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString("tasklist", json);
        editor.apply();
    }

    private void removeTask() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Está seguro que desea borrar esta tarea?");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                int pos = getIntent().getIntExtra("index", -1);
                if (pos != -1) {
                    list.borrar(pos);
                    saveData();
                    returnToHomeScreen();
                }
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void returnToHomeScreen() {
        Intent intent = new Intent(ShowTask.this, HomeScreen.class);
        startActivity(intent);
    }
}
