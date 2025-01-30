package com.connectiva.app.rest_api_connectiva.entities;

public class Address {

    private long id;
    private String streetName;
    private int houseNumber;
    private String postalCode;

    private Contact contactAssociated;

    public Address(){
    }

    /*Getters*/
    public long getId() {
        return id;
    }

    public String getStreetName() {
        return streetName;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public Contact getContactAssociated() {
        return contactAssociated;
    }

    /*Setters*/
    public void setId(long id) {
        this.id = id;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setContactAssociated(Contact contactAssociated) {
        this.contactAssociated = contactAssociated;
    }
}
