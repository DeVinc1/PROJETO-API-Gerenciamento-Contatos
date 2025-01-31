package com.connectiva.app.rest_api_connectiva.service;

import com.connectiva.app.rest_api_connectiva.model.Address;
import com.connectiva.app.rest_api_connectiva.model.Contact;
import com.connectiva.app.rest_api_connectiva.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Contact updateFullContact(Contact contact, Long id) {
        contact.setId(id);
        return contactRepository.save(contact);
    }

    public Contact updatePartialContact(Contact contact, Long id) {
        Contact originalContact = this.findContactById(id);
        Contact modifiedContact = contactRepository.save(contact);

    }


    public Contact nullPropertySkipper(Contact originalContact, Contact modifiedContact) {
        modifiedContact.setId(originalContact.getId());
        modifiedContact.getAddressesAssociated().addAll(originalContact.getAddressesAssociated());
        if (modifiedContact.getName() == null) {
            modifiedContact.setName(originalContact.getName());
        }
        if (modifiedContact.getEmail() == null) {
            modifiedContact.setEmail(originalContact.getEmail());
        }
        if (modifiedContact.getPhoneNumber() == null) {
            modifiedContact.setPhoneNumber(originalContact.getPhoneNumber());
        }
        if (modifiedContact.getBirthDate() == null) {
            modifiedContact.setBirthDate(originalContact.getBirthDate());
        }

        for (Address address : originalContact.getAddressesAssociated()) {

            if (modifiedContact.getAddressesAssociated().)
        }
    }




}
