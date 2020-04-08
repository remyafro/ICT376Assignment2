package com.example.ict376_assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class DisplayExpenseList extends Fragment {

    boolean mDualPane;
    View mLayoutView;
    Button mNewContactButton;
    private ListView listView;

    // Database
    ExpenseDBHelper mydb;
    ArrayList mArrayList;

    TextView totalex;
    double sumEx;

    public static DisplayExpenseList newInstance() {
        DisplayExpenseList fragment = new DisplayExpenseList();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mLayoutView = inflater.inflate(R.layout.activity_display_expense_list, null);
        return mLayoutView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        refresh();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {

                Pair<Integer, String> p = (Pair<Integer, String>) mArrayList.get(position);
                int id_To_Search = p.first; // position + 1;

                View detailsFrame = getActivity().findViewById(R.id.expenseDetails_fragment);
                mDualPane = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;

                if (mDualPane) {

                    // display on the same Activity
                    DisplayExpenseDetails details = DisplayExpenseDetails.newInstance(id_To_Search);

                    // Execute a transaction, replacing any existing fragment
                    // with this one inside the frame.
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.replace(R.id.expenseDetails_fragment, details);

                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                    ft.commit();

                } else {
                    Bundle dataBundle = new Bundle();

                    dataBundle.putInt("id", id_To_Search);
                    Intent intent = new Intent(getActivity().getApplicationContext(), DisplayList.class);
                    intent.putExtras(dataBundle);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onResume() {
        // Log.e("DEBUG", "onResume of LoginFragment");
        super.onResume();

        refresh();
    }

    public void refresh(){

        mydb = new ExpenseDBHelper(getActivity());

        // Get all the contacts from the database
        mArrayList = mydb.getAllExpenses();

        ArrayList<String> array_list = new  ArrayList<String>();

        for (int i=0; i<mArrayList.size(); i++){
            Pair<Integer, String> p = (Pair<Integer, String>)mArrayList.get(i);
            array_list.add(p.second);
        }
        // Put all the contacts in an array
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, array_list);

        // Display the contacts in the ListView object
        listView = (ListView)mLayoutView.findViewById(R.id.listviewExpense);
        listView.setAdapter(arrayAdapter);

        if (mydb == null)
            mydb = new ExpenseDBHelper(getActivity());
        mydb = new ExpenseDBHelper(getActivity());


        sumEx = mydb.getAllExpense();

        totalex = (TextView)getActivity().findViewById(R.id.textViewDetail);
        totalex.setText(String.valueOf(sumEx));

        // Check whether we are in landscape or portrait orientation by checking whether the second fragment container is there
        View detailsFrame = getActivity().findViewById(R.id.expenseDetails_fragment);
        mDualPane = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;

    }

}
