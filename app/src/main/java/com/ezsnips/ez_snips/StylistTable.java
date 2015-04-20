package com.ezsnips.ez_snips;

/**
 * Created by Nigel on 4/19/2015.
 */
public class StylistTable {

    String FirstName, LastName;

    public StylistTable(String FirstName, String LastName)
    {

        super();
        this.FirstName = FirstName;
        this.LastName = LastName;
    }

    public StylistTable(){
        super();
        this.FirstName = null;
        this.LastName = null;
    }

    public void setFirstName(String FirstName) {this.FirstName = FirstName;}
    public String getFirstName() { return FirstName;}
    public void setLastName(String lastName) {this.LastName = lastName;}
    public String getLastName(){return LastName;}


}
