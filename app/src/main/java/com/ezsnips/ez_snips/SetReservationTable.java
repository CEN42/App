package com.ezsnips.ez_snips;

/**
 * Created by nigel on 4/20/2015.
 */
public class SetReservationTable {


    String email, stylist, service, fdate, edate;

    public SetReservationTable(String email, String stylist, String service, String fdate, String edate)
    {
        this.email = email;
        this.stylist = stylist;
        this.service = service;
        this.fdate = fdate;
        this.edate = edate;
    }

    public SetReservationTable()
    {
        this.email = null;
        this.stylist = null;
        this.service = null;
        this.fdate = null;
        this.edate = null;
    }

    public void setEmail(String email){this.email = email;}
    public String getEmail() {return email;}

    public void setStylist(String stylist){this.stylist = stylist;}
    public String getStylist(){return stylist;}

    public void setService(String service){this.service = service;}
    public String getService(){return service;}

    public void setSdate(String sdate){this.fdate = sdate;}
    public String getSdate(){return fdate;}

    public void setSyear(String edate){this.edate = edate;}
    public String getSyear(){return edate;}


}

