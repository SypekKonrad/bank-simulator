package interfaces;

import models.Account;

public interface Transferable {

    boolean transferTo(Account recipient, double amount);
}
