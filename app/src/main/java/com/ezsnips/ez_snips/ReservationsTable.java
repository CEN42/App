package com.ezsnips.ez_snips;

/**
 * Created by Nigel on 4/19/2015.
 * This is class is used to set the reservation table with information
 * to be displayed to the customer when they look at their reservations
 */
public class ReservationsTable {

    String stylistFirstName,stylistLastName, reservation_start, reseravation_end,  serviceName;
    Double servicePrice;

    public ReservationsTable(String stylistFirstName, String stylistLastName,  String reservation_start, String reseravation_end, String serviceName, Double servicePrice)
    {

        super();
        this.stylistFirstName = stylistFirstName;
        this.stylistLastName = stylistLastName;
        this.reservation_start = reservation_start;
        this.reseravation_end = reseravation_end;
        this.serviceName = serviceName;
        this.servicePrice = servicePrice;

    }

    public ReservationsTable()
    {
        super();
        this.stylistFirstName = null;
        this.stylistLastName = null;
        this.reservation_start = null;
        this.reseravation_end = null;
        this.serviceName = null;
        this.servicePrice = null;
    }

    public void setStylistFirstName(String stylistFirstName){ this.stylistFirstName = stylistFirstName;  }

    public String getStylistFirstName() { return stylistFirstName;  }

    public void setStylistLastName(String stylistLastName){ this.stylistLastName = stylistLastName;  }

    public String getStylistLastName(){return stylistLastName; }

    public void setReservation_start(String reservation_start){this.reservation_start = reservation_start; }

    public String getReservation_start(){ return reservation_start; }

    public void setReseravation_end(String reseravation_end){ this.reseravation_end = reseravation_end;  }

    public String getReseravation_end(){return reseravation_end;}

    public void setServiceName(String serviceName){ this.serviceName = serviceName; }

    public String getServiceName(){ return serviceName; }

    public void setServicePrice(Double servicePrice){ this.servicePrice = servicePrice; }

    public Double getServicePrice(){ return servicePrice; }
}
