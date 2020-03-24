package com.example.ict376_assignment2;

public class Expense {
    private String expenseType;
    private String expenseDesc;
    private int expenseAmt;
    private String expenseDate;
    //todo camera stuff

    public Expense(String expenseType, String expenseDesc, int expenseAmt, String expenseDate){
        this.expenseType = expenseType;
        this.expenseDesc = expenseDesc;
        this.expenseAmt = expenseAmt;
        this.expenseDate = expenseDate;
    }

    public Expense(){}

    public String getExpenseType() {
        return expenseType;
    }

    public String getExpenseDate() {
        return expenseDate;
    }

    public int getExpenseAmt() {
        return expenseAmt;
    }

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
