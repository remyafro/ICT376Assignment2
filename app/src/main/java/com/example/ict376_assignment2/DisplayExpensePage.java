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

        /*Spinner spinner = (Spinner)findViewById(R.id.spinnerExCategory);

        ArrayAdapter<CharSequence> type = ArrayAdapter.createFromResource(this,
                R.array.expenseType_array, android.R.layout.simple_spinner_item);
        type.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(type);*/

        if (savedInstanceState == null) {
            addExpensesFragment = AddExpensesFragment.newInstance();

            getFragmentManager().beginTransaction().add(R.id.add_expense_fragment, addExpensesFragment).commit();
        }else{
            addExpensesFragment = (AddExpensesFragment)getFragmentManager().findFragmentById(R.id.add_expense_fragment);
        }
    }

    /*public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }*/
}
