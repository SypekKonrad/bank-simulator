package models;

import utils.AccountNumberGenerator;

public abstract class Account {
    protected String accountNumber;
    protected double balance;
    protected User owner;

    public Account(User owner) {
        this.accountNumber = AccountNumberGenerator.generateAccountNumber();
        this.owner = owner;
        this.balance = 0;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public User getOwner() {
        return owner;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public abstract String getAccountType();
}