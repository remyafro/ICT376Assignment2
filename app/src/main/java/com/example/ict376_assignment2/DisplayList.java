package com.example.ict376_assignment2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DisplayList extends Activity {

    DisplayExpenseDetails displayExpenseDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.details_expense_list);
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
        Bundle extras = getIntent().getExtras();
        int ix = -1;
        if(extras !=null)
            ix = extras.getInt("id");

        if (savedInstanceState == null) {
            displayExpenseDetails = DisplayExpenseDetails.newInstance(ix);

            getFragmentManager().beginTransaction().add(R.id.expenseDetails_fragment, displayExpenseDetails).commit();
        }else{
            displayExpenseDetails = (DisplayExpenseDetails)getFragmentManager().findFragmentById(R.id.expenseDetails_fragment);
        }


    }}
