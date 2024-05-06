package com.example.todolist;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SecondFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SecondFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ListManager listManager;

    private HomeScreen homeScreen;

    public SecondFragment(HomeScreen homeScreen) {
        // Required empty public constructor
        this.homeScreen = homeScreen;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SecondFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SecondFragment newInstance(String param1, String param2, HomeScreen homeScreen) {
        SecondFragment fragment = new SecondFragment(homeScreen);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        ListView listView = view.findViewById(R.id.listView);
        loadData();
        listManager.sortTaskList();
        saveData();
        ListAdapter adapter = new ListAdapter(getActivity(),listManager.getTasklist());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                task t = listManager.getTasklist().get(position);
                Intent intent = new Intent(getContext(), ShowTask.class);
                intent.putExtra("task", t);
                intent.putExtra("list", listManager);
                intent.putExtra("index", position);

                startActivity(intent);
                getActivity().finish();

            }
        });

        return view;
    }

    private void loadData() {
        SharedPreferences sharedPreferences= this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
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
            Toast toast = Toast.makeText(this.getActivity(), text, duration);
            toast.show();

        }
    }
    private void saveData() {
        SharedPreferences sharedPreferences= this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(listManager);
        editor.putString("tasklist", json);
        editor.apply();
    }

}