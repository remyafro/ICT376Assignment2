package com.example.ict376_assignment2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends Activity {

    MainPageFragment mainPageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnavigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {

                    case R.id.nav_home:
                        return true;
                    case R.id.nav_expense:
                        startActivity(new Intent(getApplicationContext()
                                , DisplayExpensePage.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_expenselist:
                        startActivity(new Intent(getApplicationContext()
                                , expense_list.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        if (savedInstanceState == null) {
            mainPageFragment = MainPageFragment.newInstance();
            getFragmentManager().beginTransaction().add(R.id.mainpage_fragment, mainPageFragment).commit();

        }else{
            mainPageFragment = (MainPageFragment)getFragmentManager().findFragmentById(R.id.mainpage_fragment);
        }


    }
}
