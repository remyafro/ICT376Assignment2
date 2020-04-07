package com.example.ict376_assignment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class expense_list extends Activity {

    DisplayExpenseList displayExpenseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expense_list);

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
            displayExpenseList = DisplayExpenseList.newInstance();
            getFragmentManager().beginTransaction().add(R.id.expenseList_fragment, displayExpenseList).commit();


        }else{
            displayExpenseList = (DisplayExpenseList)getFragmentManager().findFragmentById(R.id.expenseList_fragment);
        }

    }
}
