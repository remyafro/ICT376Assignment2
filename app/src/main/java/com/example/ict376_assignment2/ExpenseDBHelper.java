package com.example.ict376_assignment2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.util.Pair;
import android.view.View;

import java.util.ArrayList;

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
                        EXPENSE_COLUMN_ID + " integer PRIMARY KEY, " +
                        EXPENSE_COLUMN_TYPE + " text, " +
                        EXPENSE_COLUMN_DESCRIPTION + " text, " +
                        EXPENSE_COLUMN_AMOUNT + " integer, " +
                        EXPENSE_COLUMN_DATE + " text, " +
                        EXPENSE_COLUMN_RECEIPT + " blob " +
                        ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + EXPENSE_TABLE_NAME);
        onCreate(db);
    }

    public Cursor getDetails(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from expense where id="+id+"", null );
        return res;
    }

    public double getAllExpense() {
        double sum = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        //String sumQuery = String.format("SELECT SUM(amount_donated) as Total FROM donations");
        Cursor cursor = db.rawQuery("SELECT SUM(amount) as Total FROM expense", null);
        if (cursor.moveToFirst())
            sum = cursor.getDouble(cursor.getColumnIndex("Total"));
        return sum;
    }


    public boolean addExpense(String type, String desc, Double amount, String date){
        //public boolean addExpense(String type, String desc, Double amount, String date, Integer id){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues expenseTotal = new ContentValues();
        expenseTotal.put("type", type);
        expenseTotal.put("description", desc);
        expenseTotal.put("amount", amount);
        expenseTotal.put("date", date);
      db.insert("expense", null, expenseTotal);
        return true;
    }

    public boolean addExpensePhoto(String type, String desc, Double amount, String date, byte[] image){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues expenseTotal = new ContentValues();
        expenseTotal.put("type", type);
        expenseTotal.put("description", desc);
        expenseTotal.put("amount", amount);
        expenseTotal.put("date", date);
        expenseTotal.put("receipt", image);
        db.insert("expense", null, expenseTotal);
        return true;
    }

    public ArrayList<Expense> getAllExpenses() {
        ArrayList<Expense> array_list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        //Cursor res = db.rawQuery("select * from expense where user_id = " + id + "", null);
        Cursor res = db.rawQuery("select * from expense", null);
        if (res.getCount() > 0) {
            res.moveToFirst();

            while (res.isAfterLast() == false) {
                int id = res.getInt(res.getColumnIndex(EXPENSE_COLUMN_ID));
                String expenseType = res.getString(res.getColumnIndex(EXPENSE_COLUMN_TYPE));
                String expenseDesc = res.getString(res.getColumnIndex(EXPENSE_COLUMN_DESCRIPTION));
                double expenseAmt = res.getDouble(res.getColumnIndex(EXPENSE_COLUMN_AMOUNT));
                String expenseDate = res.getString(res.getColumnIndex(EXPENSE_COLUMN_DATE));
                byte[] expenseImg = res.getBlob(res.getColumnIndex(EXPENSE_COLUMN_RECEIPT));

                array_list.add(new Expense(id, expenseType, expenseDesc, expenseAmt, expenseDate, expenseImg));
                res.moveToNext();
            }

        }
        return array_list;
    }

    public ArrayList<Pair<Integer, String>> getExpensePair() {
        ArrayList<Pair<Integer, String>> array_list = new ArrayList<Pair<Integer, String>>();

        SQLiteDatabase db = this.getReadableDatabase();
        //Cursor res = db.rawQuery("select * from expense where user_id = " + id + "", null);
        Cursor res = db.rawQuery("select * from expense", null);
        if (res.getCount() > 0) {
            res.moveToFirst();

            while (res.isAfterLast() == false) {
                array_list.add(new Pair(res.getInt(res.getColumnIndex(EXPENSE_COLUMN_ID)), res.getString(res.getColumnIndex(EXPENSE_COLUMN_AMOUNT))));
                res.moveToNext();
            }

        }
        return array_list;
    }

}
