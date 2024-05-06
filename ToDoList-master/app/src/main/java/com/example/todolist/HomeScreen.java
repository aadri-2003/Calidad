package com.example.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class HomeScreen extends AppCompatActivity {
    FirstFragment firstFragment = new FirstFragment(this);
    SecondFragment secondFragment = new SecondFragment(this);
    ThirdFragment thirdFragment = new ThirdFragment(this);
    BottomNavigationView navigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        navigation = findViewById(R.id.bottom_navigation);
        navigation.getMenu().getItem(1).setChecked(true);
        loadFragment(secondFragment);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //flecha hacia atras en la barra de navegacion
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.firstFragment:
                    loadFragment(firstFragment);
                    return true;

                case R.id.secondFragment:

                    loadFragment(secondFragment);
                    return true;

                case R.id.thirdFragment:
                    //secondFragment.getAdapter().saveinfoDB();
                    loadFragment(thirdFragment);
                    return true;
            }
            return false;
        }
    };



    public void loadFragment(Fragment fragment){
        FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.commit();
    }

    public BottomNavigationView getNavigation() {
        return navigation;
    }

    // Manejar el evento de clic en la flecha hacia atrás (icono de "home")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // Volver al inicio
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

