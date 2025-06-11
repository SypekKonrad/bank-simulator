package models;
import java.io.Serializable;

public class PrivateUser extends User implements Serializable{
    private String firstName;
    private String lastName;
    private String pesel;
    private String dateOfBirth;

    public PrivateUser(
            String login,
            String password,
            String email,
            String phone,
            String address,
            String firstName,
            String lastName,
            String pesel,
            String dateOfBirth
    ) {
        super(null, login, password, email, phone, address);
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String getUserType() {
        return "private";
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
