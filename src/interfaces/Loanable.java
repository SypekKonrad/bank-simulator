package interfaces;

public interface Loanable {
    boolean requestLoan(double amount);
    void repayLoan(double amount);
    double getOutstandingLoan();
}
