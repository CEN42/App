package com.ezsnips.ez_snips;

/**
 * Created by Nigel on 4/20/2015.
 */
public class ServicesTable {

    String servicetitle;
    Double amount;

    public ServicesTable(String servicetitle, Double amount)
    {
        this.servicetitle = servicetitle;
        this.amount = amount;

    }

    public ServicesTable()
    {
        this.servicetitle = null;
        this.amount = null;
    }


    public void setServiceTitle(String servicetitle){this.servicetitle = servicetitle;}
    public String getServicetitle(){return servicetitle;}

    public void setAmount(Double amount){this.amount = amount;}
    public Double getAmount(){return amount;}

}
