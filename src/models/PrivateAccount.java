package models;
import java.io.Serializable;

public class PrivateAccount extends Account implements Serializable{

    public PrivateAccount(PrivateUser owner) {
        super(owner);
    }

    @Override
    public String getAccountType() {
        return "Private Account";
    }
}