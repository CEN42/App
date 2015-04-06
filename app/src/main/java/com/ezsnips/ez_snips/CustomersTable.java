package com.ezsnips.ez_snips;

/**
 * Created by nigel on 4/3/2015.
 */
public class CustomersTable {

    String firstName,lastName,password, email;
    Integer phonenumber;

    public CustomersTable(String firstName, String lastName,String email, String password, Integer phonenumber)
    {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.phonenumber = phonenumber;
    }

    public CustomersTable() {
        super();
        this.firstName = null;
        this.lastName = null;
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
