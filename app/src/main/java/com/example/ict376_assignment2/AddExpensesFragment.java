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
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class AddExpensesFragment extends Fragment {

    //private DBHelper mydb ;

    View mLayoutView;
    TextView name ;
    TextView phone;
    TextView email;
    TextView street;
    TextView place;
    int id_To_Update = 0;


    // the buttoms
    Button mSubmitExpense;


    public static AddExpensesFragment newInstance() {

        AddExpensesFragment f = new AddExpensesFragment();

        // Supply index input as an argument.
        // Google recommends using bundles to pass in arguments
        Bundle args = new Bundle();
        //args.putInt("index", index);
        f.setArguments(args);

        return f;
    }
}
