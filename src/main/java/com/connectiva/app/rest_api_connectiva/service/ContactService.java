package com.connectiva.app.rest_api_connectiva.service;

import com.connectiva.app.rest_api_connectiva.model.Address;
import com.connectiva.app.rest_api_connectiva.model.Contact;
import com.connectiva.app.rest_api_connectiva.repository.ContactRepository;
import com.connectiva.app.rest_api_connectiva.utils.RequestBodyPatcher;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ContactService {

    private final ContactRepository contactRepository;

    private final RequestBodyPatcher requestBodyPatcher;

    public ContactService(ContactRepository contactRepository, RequestBodyPatcher requestBodyPatcher) {
        this.contactRepository = contactRepository;
        this.requestBodyPatcher = requestBodyPatcher;
    }

    public List<Contact> findAllContacts() {
        return contactRepository.findAll();
    }

    public Contact findContactById(Long id) {
        return contactRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("ID" + id + "não encontrado"));
    }

    public Contact saveNewContact(Contact contact) {

        return contactRepository.save(contact);
    }

    public Contact updateFullContact(Contact contact, Long id) {
        contact.setId(id);
        return contactRepository.save(contact);
    }

    public Contact updatePartialContact(Contact contact, Long id) {
        Contact existingContact = findContactById(id);
        contact.setId(id);
        Contact patchedContact = requestBodyPatcher.contactPatcher(existingContact, contact);

        List<Address> uniqueAddresses = patchedContact.getAddressesAssociated().stream()
                .distinct()
                .toList();

        patchedContact.setAddressesAssociated(uniqueAddresses);
        contactRepository.save(patchedContact);

        return this.findContactById(id);

    }

    public void removeContact(Long id) {
        contactRepository.deleteById(id);
    }




}
