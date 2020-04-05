package com.example.ict376_assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class DisplayExpensePage extends Activity {

    AddExpensesFragment addExpensesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_expense_layout);
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
