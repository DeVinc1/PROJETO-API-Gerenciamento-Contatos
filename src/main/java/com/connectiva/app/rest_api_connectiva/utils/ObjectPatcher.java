package com.connectiva.app.rest_api_connectiva.utils;

import com.connectiva.app.rest_api_connectiva.model.Address;
import com.connectiva.app.rest_api_connectiva.model.Contact;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class ObjectPatcher {

    public <T> T nullPropertySkipper(T originalProperty, T newProperty) {
        if (newProperty == null) {
            return originalProperty;
        } else {
            return newProperty;
        }

    }

    public Contact contactEditor(Contact originalContact, Contact newContact){
        newContact.setName(nullPropertySkipper(originalContact.getName(), newContact.getName()));
        newContact.setEmail(nullPropertySkipper(originalContact.getEmail(), newContact.getEmail()));
        newContact.setPhoneNumber(nullPropertySkipper(originalContact.getPhoneNumber(), newContact.getPhoneNumber()));
        newContact.setBirthDate(nullPropertySkipper(originalContact.getBirthDate(), newContact.getBirthDate()));


    }

    public List<Address> addressEditor(List<Address> originalAddresses, List<Address> newAddresses){

        /*
        Adiciona a nova lista de endereços todos os endereços originais do contato
        que ainda não estão presentes
        */
        for (Address originalAddress : originalAddresses) {
            boolean existsInNewList = false;

            for(Address newAddress : newAddresses){
                if(Objects.equals(originalAddress.getId(), newAddress.getId())) {
                    existsInNewList = true;
                    break;
                }
            }

            if(!existsInNewList) {
                newAddresses.add(originalAddress);
            }
        }




    }

}