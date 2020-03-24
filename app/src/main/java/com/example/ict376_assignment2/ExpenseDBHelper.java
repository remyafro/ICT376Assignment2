package com.example.ict376_assignment2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class ExpenseDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "expense.db";

    public static final String EXPENSE_TABLE_NAME = "expense";
    public static final String EXPENSE_COLUMN_ID = "id";
    public static final String EXPENSE_COLUMN_TYPE = "type";
    public static final String EXPENSE_COLUMN_DESCRIPTION = "description";
    public static final String EXPENSE_COLUMN_AMOUNT = "amount";
    public static final String EXPENSE_COLUMN_DATE = "date";
    public static final String EXPENSE_COLUMN_RECEIPT = "receipt";

    static int ver = 1;

    public ExpenseDBHelper(Context context){
        super(context, DATABASE_NAME, null, ver);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "CREATE TABLE " + EXPENSE_TABLE_NAME + "(" +
                        EXPENSE_COLUMN_ID + "INTEGER PRIMARY KEY, " +
                        EXPENSE_COLUMN_TYPE + "TEXT, " +
                        EXPENSE_COLUMN_DESCRIPTION + "TEXT, " +
                        EXPENSE_COLUMN_AMOUNT + "INTEGER, " +
                        EXPENSE_COLUMN_DATE + "TEXT, " +
                        EXPENSE_COLUMN_RECEIPT + "BLOB " + ")"

        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + EXPENSE_TABLE_NAME);
        onCreate(db);
    }
}
