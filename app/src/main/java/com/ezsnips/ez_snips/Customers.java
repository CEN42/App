package com.ezsnips.ez_snips;

/**
 * Created by nigel on 4/3/2015.
 */
public class Customers {

    String firstName,lastName,userName,password, email;
    Integer phonenumber;

    public Customers(String firstName, String lastName, String userName,
                            String password, String email, Integer phonenumber) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phonenumber = phonenumber;
    }

    public Customers() {
        super();
        this.firstName = null;
        this.lastName = null;
        this.userName = null;
        this.password = null;
        this.email = null;
        this.phonenumber = null;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(Integer phonenumber) {
        this.phonenumber = phonenumber;
    }

}
