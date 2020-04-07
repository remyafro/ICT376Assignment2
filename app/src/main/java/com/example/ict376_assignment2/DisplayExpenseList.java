package com.example.ict376_assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DisplayExpenseList extends Fragment {
    View mLayoutView;

    public static DisplayExpenseList newInstance(){
        DisplayExpenseList fragment = new DisplayExpenseList();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mLayoutView = inflater.inflate(R.layout.activity_display_expense_list, null);
        return mLayoutView;
    }
}
