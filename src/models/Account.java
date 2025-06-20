package models;

import interfaces.*;
import utils.AccountNumberGenerator;

import java.io.Serializable;

public abstract class Account  implements Serializable, Loanable, Transferable{
    protected String accountNumber;
    protected double balance;
    protected User owner;
    private double outstandingLoan = 0.0;
    private double loanAmount;

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
    @Override
    public boolean requestLoan(double amount) {
        if (amount <= 0) return false;

        outstandingLoan += amount;
        setBalance(getBalance() + amount);
        return true;
    }

    @Override
    public void repayLoan(double amount) {
        if (amount <= 0 || amount > getBalance()) return;

        double toRepay = Math.min(amount, outstandingLoan);
        outstandingLoan -= toRepay;
        setBalance(getBalance() - toRepay);
    }

    @Override
    public double getOutstandingLoan() {
        return outstandingLoan;
    }

    @Override
    public boolean transferTo(Account recipient, double amount) {
        if (recipient == null || amount <= 0 || this.balance < amount) {
            return false;
        }

        this.balance -= amount;
        recipient.setBalance(recipient.getBalance() + amount);
        return true;
    }
}