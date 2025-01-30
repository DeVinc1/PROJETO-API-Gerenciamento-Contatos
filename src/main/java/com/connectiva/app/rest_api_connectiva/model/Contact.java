package com.connectiva.app.rest_api_connectiva.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "tabela_contatos")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nome")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "telefone")
    private String phoneNumber;

    @Column(name = "data_nascimento")
    private LocalDate birthDate;

    public Contact() {
    }

    /* Getters */
    public long getId() {
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

    /* Setters */
    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
