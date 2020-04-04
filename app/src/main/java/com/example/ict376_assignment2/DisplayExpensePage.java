package com.example.ict376_assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

public class DisplayExpensePage extends Activity {

    AddExpensesFragment addExpensesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_expense_layout);

        if (savedInstanceState == null) {
            addExpensesFragment = AddExpensesFragment.newInstance();

            getFragmentManager().beginTransaction().add(R.id.add_expense_fragment, addExpensesFragment).commit();
        }else{
            addExpensesFragment = (AddExpensesFragment)getFragmentManager().findFragmentById(R.id.add_expense_fragment);
        }
    }
}
