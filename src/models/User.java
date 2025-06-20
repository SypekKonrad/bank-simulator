package models;

import java.io.Serializable;
import java.util.UUID;

public abstract class User implements Serializable {
    protected String id;
    protected String login;
    protected String password;
    protected String email;
    protected String phone;
    protected String address;

    public User(String login, String password, String email, String phone, String address) {
        this.id = UUID.randomUUID().toString();
        this.login = login;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public abstract String getUserType();

    public abstract String getDisplayName();

    public String getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
