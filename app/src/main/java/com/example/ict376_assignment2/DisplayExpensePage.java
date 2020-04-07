package com.example.ict376_assignment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DisplayExpensePage extends Activity {


    AddExpensesFragment addExpensesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_expense_layout);



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnavigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {

                    case R.id.nav_home:
                        startActivity(new Intent(getApplicationContext()
                                ,MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_expense:

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
            addExpensesFragment = AddExpensesFragment.newInstance();

            getFragmentManager().beginTransaction().add(R.id.add_expense_fragment, addExpensesFragment).commit();
        }else{
            addExpensesFragment = (AddExpensesFragment)getFragmentManager().findFragmentById(R.id.add_expense_fragment);
        }
    }

}
