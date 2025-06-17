package auth;

import exceptions.*;
import models.*;

public class UserAuth {

    private final BankData data;

    public UserAuth(BankData data) {
        this.data = data;
    }

    public User authenticate(String login, String password) throws InvalidCredentialsException {
        for (User user : data.getUsers()) {
            System.out.printf("Input login=[%s], Input password=[%s]%n", login, password);
            System.out.printf("Stored login=[%s], Stored password=[%s]%n", user.getLogin(), user.getPassword());
            if (user.getLogin().trim().equalsIgnoreCase(login) &&
                    user.getPassword().equals(password)) {
                return user;
            }
        }
        throw new InvalidCredentialsException("Invalid login credentials.");
    }
}
