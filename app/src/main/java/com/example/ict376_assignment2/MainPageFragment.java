package com.example.ict376_assignment2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.app.FragmentTransaction;
import android.app.Fragment;
import android.widget.TextView;

import java.util.ArrayList;

public class MainPageFragment extends Fragment {
    private ExpenseDBHelper mydb;
    ArrayList myArrayList;
    private TextView totalExpense;
    boolean mDualPane;
    View    mLayoutView;
    Button nNewExpenseButton;
    TextView totalex;
    double sumEx;

    public static MainPageFragment newInstance() {
        MainPageFragment fragment = new MainPageFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mLayoutView = inflater.inflate(R.layout.main_page_layout, null);
        return mLayoutView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        // initialise what is displayed in the list
        refresh();

        nNewExpenseButton = (Button) getActivity().findViewById(R.id.buttonAddExpense);

        nNewExpenseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                View detailsFrame = getActivity().findViewById(R.id.add_expense_fragment);
                mDualPane = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;

                if (mDualPane) {

                    // display on the same Activity
                    AddExpensesFragment details = AddExpensesFragment.newInstance();

                    // Execute a transaction, replacing any existing fragment
                    // with this one inside the frame.
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.add_expense_fragment, details);

                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    ft.commit();

                }else {

                    Intent intent = new Intent(getActivity().getApplicationContext(), DisplayExpensePage.class);
                    startActivity(intent);          //
                }
            }
        });
    }

    public void refresh(){
        if (mydb == null)
            mydb = new ExpenseDBHelper(getActivity());
        mydb = new ExpenseDBHelper(getActivity());


        sumEx = mydb.getAllExpense();

        totalex = (TextView)getActivity().findViewById(R.id.textView_balance);
        totalex.setText(String.valueOf(sumEx));

        View detailsFrame = getActivity().findViewById(R.id.add_expense_fragment);
        mDualPane = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;

    }


}
