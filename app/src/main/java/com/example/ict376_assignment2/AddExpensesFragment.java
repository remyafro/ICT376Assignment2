package com.example.ict376_assignment2;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

public class AddExpensesFragment extends Fragment {

    public ExpenseDBHelper mydb;

    View mLayoutView;
    TextView amt;
    TextView date;
    TextView desc;

    Spinner catSpinner;
    double amtdb;
    String expenseDate;
    String type;

    String amount;
    String expenseDesc;
    int id_To_Update = 0;


    // the buttons
    Button mSubmitExpense;
    Button button_capture;


    public static AddExpensesFragment newInstance() {

        AddExpensesFragment f = new AddExpensesFragment();

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }

        mydb = new ExpenseDBHelper(getActivity());

        mLayoutView = inflater.inflate(R.layout.activity_display_expense_page, null);

        String [] values = {"Personal", "Groceries", "Hobbies", "Essentials"};
        Spinner spinner = (Spinner) mLayoutView.findViewById(R.id.catSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Toast.makeText(parent.getContext(), "Personal Expense Selected", Toast.LENGTH_SHORT).show();
                        type = "Personal";
                        break;
                    case 1:
                        Toast.makeText(parent.getContext(), "Groceries Expense Selected", Toast.LENGTH_SHORT).show();
                        type = "Groceries";
                        break;
                    case 2:
                        Toast.makeText(parent.getContext(), "Hobbies Expense Selected", Toast.LENGTH_SHORT).show();
                        type = "Hobbies";
                        break;
                    case 3:
                        Toast.makeText(parent.getContext(), "Essential Expense Selected", Toast.LENGTH_SHORT).show();
                        type = "Essential";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return mLayoutView;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        amt = (TextView) mLayoutView.findViewById(R.id.editText_AddExpense);
        date = (TextView) mLayoutView.findViewById(R.id.editText_date);
        desc = (TextView) mLayoutView.findViewById(R.id.editText_desc);
        catSpinner = (Spinner) mLayoutView.findViewById(R.id.catSpinner);



        button_capture = (Button) getActivity().findViewById(R.id.button_capture);

        button_capture.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                amount = amt.getText().toString().trim();
                expenseDesc = desc.getText().toString();
                amtdb = Double.parseDouble(amount);
                type = catSpinner.getSelectedItem().toString();
                expenseDate = date.getText().toString();

                Intent camera_intent = new Intent(getActivity().getApplicationContext(), Camera.class);
                camera_intent.putExtra("type",type);
                camera_intent.putExtra("desc", expenseDesc);
                camera_intent.putExtra("amt", amtdb);
                camera_intent.putExtra("date", expenseDate);

                startActivity(camera_intent);

            }
        });

        mSubmitExpense = (Button)getActivity().findViewById(R.id.buttonSubmitExpense);

        mSubmitExpense.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                amount = amt.getText().toString().trim();
                expenseDesc = desc.getText().toString();
                amtdb = Double.parseDouble(amount);
                type = catSpinner.getSelectedItem().toString();
                expenseDate = date.getText().toString();


                //String type, String desc, Double amount, String date
                    if (mydb.addExpense(type, expenseDesc, amtdb, expenseDate)){
                    Toast.makeText(getActivity().getApplicationContext(), "New Expenses Added!", Toast.LENGTH_SHORT).show();
                }
                    else{

                        Toast.makeText(getActivity().getApplicationContext(), "No Expenses Added!", Toast.LENGTH_SHORT).show();
                    }
            }

        });
    }
}
