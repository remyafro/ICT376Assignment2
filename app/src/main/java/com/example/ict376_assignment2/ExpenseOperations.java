package com.example.ict376_assignment2;

import com.example.ict376_assignment2.ExpenseDBHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.ict376_assignment2.Expense;

public class ExpenseOperations {
    public static final String LOGTAG = "a2";

    SQLiteOpenHelper dbhandler;
    SQLiteDatabase db;

    private static final String[] allColumns = {
            ExpenseDBHelper.EXPENSE_COLUMN_ID,
            ExpenseDBHelper.EXPENSE_COLUMN_TYPE,
            ExpenseDBHelper.EXPENSE_COLUMN_DESCRIPTION,
            ExpenseDBHelper.EXPENSE_COLUMN_AMOUNT,
            ExpenseDBHelper.EXPENSE_COLUMN_DATE,
            ExpenseDBHelper.EXPENSE_COLUMN_RECEIPT,

    };

    public ExpenseOperations(Context context){
        dbhandler = new ExpenseDBHelper(context);
    }

    public void open(){
        Log.i(LOGTAG, "DB Open");
    }

    public void close(){
        Log.i(LOGTAG, "DB Close");
    }
}
