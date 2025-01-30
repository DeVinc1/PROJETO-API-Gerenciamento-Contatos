package com.connectiva.app.rest_api_connectiva.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "tabela_enderecos")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nome_rua")
    private String streetName;

    @Column(name = "numero_casa")
    private int houseNumber;

    @Column(name = "codigo_CEP")
    private String postalCode;

    @ManyToOne
    @JoinColumn(name = "id_contato")
    @JsonBackReference
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
