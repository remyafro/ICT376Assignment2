package com.example.ict376_assignment2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Pair;

import java.util.ArrayList;

public class ExpenseDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "expense.db";

    public static final String USER_TABLE_NAME = "user";
    public static final String USER_COLUMN_ID = "id";
    public static final String USER_COLUMN_USERNAME = "username";
    public static final String USER_COLUMN_PASSWORD = "password";

    public static final String SESSION_TABLE_NAME = "session";
    public static final String SESSION_COLUMN_ID = "id";
    public static final String SESSION_COLUMN_LOGIN = "login";

    public static final String EXPENSE_TABLE_NAME = "expense";
    public static final String EXPENSE_COLUMN_ID = "id";
    public static final String EXPENSE_COLUMN_TYPE = "type";
    public static final String EXPENSE_COLUMN_DESCRIPTION = "description";
    public static final String EXPENSE_COLUMN_AMOUNT = "amount";
    public static final String EXPENSE_COLUMN_DATE = "date";
    public static final String EXPENSE_COLUMN_RECEIPT = "receipt";
    public static final String EXPENSE_COLUMN_USER_ID = "user_id";
    static int ver = 1;

    public ExpenseDBHelper(Context context){
        super(context, DATABASE_NAME, null, ver);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + SESSION_TABLE_NAME + "(" +
                SESSION_COLUMN_ID + "INTEGER PRIMARY KEY, " + SESSION_COLUMN_LOGIN + " TEXT)");

        db.execSQL("CREATE TABLE " + USER_TABLE_NAME + "(" +
                USER_COLUMN_ID + " integer PRIMARY KEY AUTOINCREMENT, " +
                USER_COLUMN_USERNAME + " text, " + USER_COLUMN_PASSWORD + "text)");

        db.execSQL(
                "CREATE TABLE " + EXPENSE_TABLE_NAME + "(" +
                        EXPENSE_COLUMN_ID + "INTEGER PRIMARY KEY, " +
                        EXPENSE_COLUMN_TYPE + "TEXT, " +
                        EXPENSE_COLUMN_DESCRIPTION + "TEXT, " +
                        EXPENSE_COLUMN_AMOUNT + "INTEGER, " +
                        EXPENSE_COLUMN_DATE + "TEXT, " +
                        EXPENSE_COLUMN_RECEIPT + "BLOB " +
                        EXPENSE_COLUMN_USER_ID + "INTEGER, FOREIGN KEY (" + EXPENSE_COLUMN_USER_ID + ") REFERENCES " +
                        USER_TABLE_NAME + "(" + USER_COLUMN_ID + "))"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + SESSION_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + EXPENSE_TABLE_NAME);
        onCreate(db);
    }

    public Boolean checkSession(String sessionValues) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM session WHERE login = ?", new String[]{sessionValues});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    //upgrade session
    public Boolean upgradeSession(String sessionValues, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("login", sessionValues);
        long update = db.update("session", contentValues, "id=" + id, null);
        if (update == -1) {
            return false;
        } else {
            return true;
        }
    }

    //insert user
    public Boolean insertUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long insert = db.insert("user", null, contentValues);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    //check login
    public Boolean checkLogin(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE username = ? AND password = ?", new String[]{username, password});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public double getAllExpense(Integer id){
        double personalSum = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        //String sumQuery = String.format("SELECT SUM(amount_donated) as Total FROM donations");
        Cursor cursor = db.rawQuery("SELECT SUM(amount) as Total FROM expense where user_id = " + id + "", null);
        if (cursor.moveToFirst())
            personalSum = cursor.getDouble(cursor.getColumnIndex("Total"));
        return personalSum;
    }

    public boolean addExpense(String type, String desc, Double amount, String date, Integer id){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues expenseTotal = new ContentValues();
        expenseTotal.put("type", type);
        expenseTotal.put("description", desc);
        expenseTotal.put("amount", amount);
        expenseTotal.put("date", date);
        expenseTotal.put("user_id", id);
        db.insert("expense", null, expenseTotal);
        return true;
    }

    public ArrayList<Pair<Integer, String>> getAllExpenses(Integer id) {
        ArrayList<Pair<Integer, String>> array_list = new ArrayList<Pair<Integer, String>>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from expense where user_id = " + id + "", null);

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
