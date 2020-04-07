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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class AddExpensesFragment extends Fragment {

    public ExpenseDBHelper mydb;

    View mLayoutView;
    Spinner category;
    TextView amt;
    TextView date;
    TextView desc;
    TextView receipt;

    String amount;
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

        return mLayoutView;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        category = (Spinner) mLayoutView.findViewById(R.id.catSpinner);
        amt = (TextView) mLayoutView.findViewById(R.id.editText_AddExpense);
        date = (TextView) mLayoutView.findViewById(R.id.editText_date);
        desc = (TextView) mLayoutView.findViewById(R.id.editText_desc);
        receipt = (TextView) mLayoutView.findViewById(R.id.editText_receipt);

        button_capture = (Button) getActivity().findViewById(R.id.button_capture);

        button_capture.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent camera_intent = new Intent(getActivity().getApplicationContext(), Camera.class);
                startActivity(camera_intent);

            }
        });

        mSubmitExpense = (Button)getActivity().findViewById(R.id.buttonSubmitExpense);

        mSubmitExpense.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                amount = amt.getText().toString().trim();
                double amtdb = Double.parseDouble(amount);

                //String type, String desc, Double amount, String date
                    if (mydb.addExpense(category.toString(), desc.getText().toString(), amtdb, date.getText().toString())){
                    Toast.makeText(getActivity().getApplicationContext(), "New Expenses Added!", Toast.LENGTH_SHORT).show();
                }
                    else{

                        Toast.makeText(getActivity().getApplicationContext(), "No Expenses Added!", Toast.LENGTH_SHORT).show();
                    }
            }

        });
    }




}
