package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BankData implements Serializable {

    private List<User> users = new ArrayList<>();
    private List<Account> accounts = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }
}
