package com.example.ict376_assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;

public class DisplayExpensePage extends Activity {

    DisplayExpensePage displayExpensePage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_expense_page);

        /*if (savedInstanceState == null) {
            displayExpensePage = DisplayExpensePage.newInstance();

            getFragmentManager().beginTransaction().add(R.id.add_expense_fragment, AddExpensesFragment).commit();
        }else{
            displayExpensePage = (DisplayExpensePage)getFragmentManager().findFragmentById(R.id.add_expense_fragment);
        }*/
    }
}
