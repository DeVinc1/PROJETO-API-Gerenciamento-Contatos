package com.connectiva.app.rest_api_connectiva.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

/**
 * Representa um endereço associado a um contato no sistema.
 * Contém informações do nome da rua, número da casa, código postal e a referência ao contato relacionado.
 */

@Entity
@Table(name = "tabela_enderecos")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_rua")
    private String streetName;

    @Column(name = "numero_casa")
    private Integer houseNumber;

    @Column(name = "codigo_CEP")
    private String postalCode;

    @ManyToOne
    @JoinColumn(name = "id_contato")
    @JsonBackReference
    private Contact contactAssociated;

    public Address(){
    }

    /*Getters*/
    public Long getId() {
        return id;
    }
    public String getStreetName() {
        return streetName;
    }
    public Integer getHouseNumber() {
        return houseNumber;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public Contact getContactAssociated() {
        return contactAssociated;
    }

    /*Setters*/
    public void setId(Long id) {
        this.id = id;
    }
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    public void setContactAssociated(Contact contactAssociated) {
        this.contactAssociated = contactAssociated;
    }
}
