package com.connectiva.app.rest_api_connectiva.service;

import com.connectiva.app.rest_api_connectiva.model.Contact;
import com.connectiva.app.rest_api_connectiva.repository.ContactRepository;
import com.connectiva.app.rest_api_connectiva.utils.ObjectPatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ObjectPatcher objectPatcher;

    public List<Contact> findAllContacts() {
        return contactRepository.findAll();
    }

    public Contact findContactById(Long id) {
        return contactRepository.findById(id).get();
    }

    public Contact saveNewContact(Contact contact) {

        return contactRepository.save(contact);
    }

    public Contact updateFullContact(Contact contact, Long id) {
        contact.setId(id);
        return contactRepository.save(contact);
    }

    public Contact updatePartialContact(Map<String, Object> patchData, Long id) {


    }

    public void removeContact(Long id) {
        contactRepository.deleteById(id);
    }




}
