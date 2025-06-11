package models;

public class PrivateAccount extends Account {

    public PrivateAccount(PrivateUser owner) {
        super(owner);
    }

    @Override
    public String getAccountType() {
        return "Private Account";
    }
}