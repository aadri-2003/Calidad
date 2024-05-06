package com.example.todolist;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    Context context;
    ArrayList<task> lst;

    public ListAdapter(Context context, ArrayList<task> lst) {
        this.context = context;
        this.lst = lst;
    }

    @Override
    public int getCount() {
        return lst.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;

        task t = lst.get(position);

        if(convertView == null){
                convertView = LayoutInflater.from(context).inflate(R.layout.item,null);
        }

        textView = convertView.findViewById(R.id.nombreTarea);

        textView.setText(t.getText());
        String color = t.getColor();
        int selection = Color.WHITE;

        int rosa = Color.parseColor("#FDCAE1");
        int azul = Color.parseColor("#84b6f4");
        int verde = Color.parseColor("#77dd77");
        int amarillo = Color.parseColor("#fdfd96");
        int blanco = Color.WHITE;
        int rojo = Color.parseColor("#ff6961");

        switch (color){
            case "Rosa":
                selection = rosa;
                break;
            case "Azul":
                selection = azul;
                break;
            case "Verde":
                selection = verde;
                break;
            case "Amarillo":
                selection = amarillo;
                break;
            case "Rojo":
                selection = rojo;
                break;
            case "Blanco":
                selection = blanco;
                break;
        }

        RelativeLayout rl = convertView.findViewById(R.id.RelativeLayout);
        rl.setBackgroundColor(selection);






        return convertView;
    }
}
