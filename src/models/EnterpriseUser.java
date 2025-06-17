package models;
import java.io.Serializable;

public class EnterpriseUser extends User implements Serializable{
    private String companyName;
    private String NIP;

    public EnterpriseUser(
            String login,
            String password,
            String email,
            String phone,
            String address,
            String CompanyName,
            String NIP
    ) {
        super(null, login, password, email, phone, address);
        this.companyName = CompanyName;
        this.NIP = NIP;
    }

    @Override
    public String getUserType() {
        return "Enterprise";
    }

    @Override
    public String getDisplayName() {
        return companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String CompanyName) {
        this.companyName = CompanyName;
    }

    public String getNIP() {
        return NIP;
    }

    public void setNIP(String NIP) {
        this.NIP = NIP;
    }

}
