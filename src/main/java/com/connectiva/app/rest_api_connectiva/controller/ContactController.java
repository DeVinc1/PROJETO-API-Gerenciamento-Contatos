package com.connectiva.app.rest_api_connectiva.controller;

import com.connectiva.app.rest_api_connectiva.model.Contact;
import com.connectiva.app.rest_api_connectiva.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v2/contatos")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/todos")
    public List<Contact> getAllContacts(){
        return contactService.findAllContacts();
    }
}
