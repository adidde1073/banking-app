package models;

import java.util.ArrayList;

public class Account {
    private long accountNo;
    private ArrayList<String> users; // maybe ditch from MVP
    private float balance;

    public Account(long accountNo, float balance) {
        this.accountNo=accountNo;
        this.balance=balance;
    }

    public long getAccountNo() {
        return this.accountNo;
    }
    public ArrayList<String> getUsers() {
        return this.users;
    }
    public float getBalance() {
        return this.balance;
    }

    // Balance Methods
    public void makeDeposit(float amount) {
        this.balance+=amount;
    }
    public void makeWithdrawal(float amount) {
        this.balance-=amount;
    }

}
