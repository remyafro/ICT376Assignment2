package com.example.ict376_assignment2;

public class Expense {
    private int expenseID;
    private String expenseType;
    private String expenseDesc;
    private double expenseAmt;
    private String expenseDate;
    private byte[] expenseImg;

    public Expense(int expenseID, String expenseType, String expenseDesc, double expenseAmt, String expenseDate, byte[] expenseImg){
        this.expenseID = expenseID;
        this.expenseType = expenseType;
        this.expenseDesc = expenseDesc;
        this.expenseAmt = expenseAmt;
        this.expenseDate = expenseDate;
        this.expenseImg = expenseImg;
    }

    public Expense(int expenseID, String expenseType, String expenseDesc, double expenseAmt, String expenseDate){
        this.expenseID = expenseID;
        this.expenseType = expenseType;
        this.expenseDesc = expenseDesc;
        this.expenseAmt = expenseAmt;
        this.expenseDate = expenseDate;
        this.expenseImg = expenseImg;
    }

    public Expense(){}

    public String getExpenseType() {
        return expenseType;
    }

    public String getExpenseDate() {
        return expenseDate;
    }

    public double getExpenseAmt() {
        return expenseAmt;
    }

    public byte[] getExpenseImg() {return expenseImg;}

    public String getExpenseDesc() {
        return expenseDesc;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public void setExpenseDesc(String expenseDesc) {
        this.expenseDesc = expenseDesc;
    }

    public void setExpenseDate(String expenseDate) {
        this.expenseDate = expenseDate;
    }

    public void setExpenseAmt(int expenseAmt) {
        this.expenseAmt = expenseAmt;
    }

}
