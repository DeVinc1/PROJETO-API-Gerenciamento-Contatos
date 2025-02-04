package com.connectiva.app.rest_api_connectiva.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "tabela_contatos")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nome", nullable = false)
    private String name;

    @Schema(description = "Deve ter o formato email@dominio.extensao")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Schema(description = "Deve ter o formato (XX) xxxx-xxxx ou (XX) 9xxxx-xxxx")
    @Column(name = "telefone", nullable = false, unique = true)
    private String phoneNumber;

    @Schema(description = "Deve ter o formato AAAA-MM-DD")
    @Column(name = "data_nascimento",nullable = false)
    private LocalDate birthDate;

    @OneToMany(mappedBy = "contactAssociated", cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "id_endereco")
    @JsonManagedReference
    private List<Address> addressesAssociated;

    public Contact() {
    }

    /* Getters */
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }
    public List<Address> getAddressesAssociated() {
        return addressesAssociated;
    }

    /* Setters */
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        if (email != null && !email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("Email deve seguir o formato exemplo@dominio.com.");
        }
        this.email = email;

    }
    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber != null && !phoneNumber.matches("^\\(\\d{2}\\)\\s?(9\\d{4}|\\d{4})-\\d{4}$")) {
            throw new IllegalArgumentException("Telefone deve seguir o formato correto como (11) 91234-5678.");
        }
        this.phoneNumber = phoneNumber;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    public void setAddressesAssociated(List<Address> addressesAssociated) {
        this.addressesAssociated = addressesAssociated;
    }
}
