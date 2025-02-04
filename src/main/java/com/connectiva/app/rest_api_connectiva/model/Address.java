package com.connectiva.app.rest_api_connectiva.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;


@Entity
@Table(name = "tabela_enderecos")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_rua", nullable = false)
    private String streetName;

    @Column(name = "numero_casa", nullable = false)
    private Integer houseNumber;

    @Schema(description = "Deve ter o formato XXXXX-XXX")
    @Column(name = "codigo_CEP", nullable = false)
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
        if (postalCode != null && !postalCode.matches("^\\d{5}-\\d{3}$")) {
            throw new IllegalArgumentException("O CEP deve estar no formato 00000-000.");
        }
        this.postalCode = postalCode;
    }

    public void setContactAssociated(Contact contactAssociated) {
        this.contactAssociated = contactAssociated;
    }
}
