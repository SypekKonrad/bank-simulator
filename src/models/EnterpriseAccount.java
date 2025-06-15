package models;
import java.io.Serializable;

public class EnterpriseAccount extends Account implements Serializable{

    public EnterpriseAccount(EnterpriseUser owner) {
        super(owner);
    }

    @Override
    public String getAccountType() {
        return "Enterprise Account";
    }
}