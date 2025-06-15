package models;
import java.io.Serializable;

public class EnterpriseUser extends User implements Serializable{
    private String CompanyName;
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
        this.CompanyName = CompanyName;
        this.NIP = NIP;
    }

    @Override
    public String getUserType() {
        return "Enterprise";
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String CompanyName) {
        this.CompanyName = CompanyName;
    }

    public String getNIP() {
        return NIP;
    }

    public void setNIP(String NIP) {
        this.NIP = NIP;
    }

}
