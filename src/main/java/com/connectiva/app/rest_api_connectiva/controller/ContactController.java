package com.connectiva.app.rest_api_connectiva.controller;

import com.connectiva.app.rest_api_connectiva.model.Contact;
import com.connectiva.app.rest_api_connectiva.service.ContactService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v2/contatos")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/todos")
    public List<Contact> getAllContacts(){
        System.out.println("aaaaa");
        return contactService.findAllContacts();
    }

    @GetMapping(value = "/{id}")
    public Contact getContactById(@PathVariable Long id){
        return contactService.findContactById(id);
    }

    @PostMapping("/cadastrar")
    public Contact insertContact(@RequestBody Contact contact){
        return contactService.saveNewContact(contact);
    }

    @PutMapping(value = "/atualizar/{id}")
    public Contact updateContact(@RequestBody Contact contact, @PathVariable Long id){
        return contactService.updateFullContact(contact, id);
    }

    @PatchMapping(value = "/editar/{id}")
    public Contact updatePartialContact(@RequestBody Contact contact, @PathVariable Long id) {
        return contactService.updatePartialContact(contact, id);
    }

    @DeleteMapping(value = "excluir/{id}")
    public void deleteContact(@PathVariable Long id){
        contactService.removeContact(id);
    }


}
