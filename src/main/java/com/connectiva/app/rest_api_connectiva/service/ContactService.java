package com.connectiva.app.rest_api_connectiva.service;

import com.connectiva.app.rest_api_connectiva.model.Contact;
import com.connectiva.app.rest_api_connectiva.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> findAllContacts() {
        return contactRepository.findAll();
    }

    public Contact findContactById(Long id) {
        return contactRepository.findById(id).get();
    }

    public Contact saveNewContact(Contact contact) {
        return contactRepository.save(contact);
    }

}
