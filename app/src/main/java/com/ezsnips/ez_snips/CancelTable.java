package com.ezsnips.ez_snips;

/**
 * Created by nigel on 4/23/2015.
 */
public class CancelTable {

    String email;

    public CancelTable(String email)
    {
        this.email = email;
    }

    public CancelTable()
    {
        this.email = null;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
