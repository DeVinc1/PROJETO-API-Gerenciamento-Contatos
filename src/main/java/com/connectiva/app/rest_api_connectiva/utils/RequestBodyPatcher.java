package com.connectiva.app.rest_api_connectiva.utils;

import com.connectiva.app.rest_api_connectiva.model.Address;
import com.connectiva.app.rest_api_connectiva.model.Contact;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class RequestBodyPatcher {

    public <T> T nullPropertySkipper(T originalProperty, T sentProperty){
        if(sentProperty == null){
            return originalProperty;
        } else{
            return sentProperty;
        }
    }

    public Contact requestBodyPatcher(Contact existingContact, Contact sentContact){
        return contactPatcher(existingContact, sentContact);
    }


    public Contact contactPatcher(Contact existingContact, Contact sentContact){

        sentContact = contactPropertyMerge(existingContact, sentContact);

        List<Address> addresses = new ArrayList<>();

        addresses.addAll(addressPropertyPatcher(existingContact, sentContact));
        addresses.addAll(addressObjectMerge(existingContact, sentContact));
        addresses.addAll(newAddressCatcher(sentContact));

        sentContact.setAddressesAssociated(addresses);

        return sentContact;
    }

    public Contact contactPropertyMerge(Contact existingContact, Contact sentContact){
        sentContact.setId(existingContact.getId());
        sentContact.setName(nullPropertySkipper(existingContact.getName(), sentContact.getName()));
        sentContact.setEmail(nullPropertySkipper(existingContact.getEmail(), sentContact.getEmail()));
        sentContact.setPhoneNumber(nullPropertySkipper(existingContact.getPhoneNumber(), sentContact.getPhoneNumber()));
        sentContact.setBirthDate(nullPropertySkipper(existingContact.getBirthDate(), sentContact.getBirthDate()));

        return sentContact;
    }

    public List<Address> addressPropertyPatcher(Contact existingContact, Contact sentContact){
        List<Address> existingAddresses = existingContact.getAddressesAssociated();
        List<Address> sentAddresses = sentContact.getAddressesAssociated();

        for(Address existingAddress : existingAddresses){
            for(Address sentAddress : sentAddresses){
                if(existingAddress.getId().equals(sentAddress.getId())){
                    sentAddress.setStreetName(nullPropertySkipper(existingAddress.getStreetName(), sentAddress.getStreetName()));
                    sentAddress.setHouseNumber(nullPropertySkipper(existingAddress.getHouseNumber(), sentAddress.getHouseNumber()));
                    sentAddress.setPostalCode(nullPropertySkipper(existingAddress.getPostalCode(), sentAddress.getPostalCode()));
                }
            }
        }

        return sentAddresses;
    }

    public List<Address> addressObjectMerge(Contact existingContact, Contact sentContact){
        List<Address> existingAddresses = existingContact.getAddressesAssociated();
        List<Address> sentAddresses = sentContact.getAddressesAssociated();
        List<Address> addressesToAdd = new ArrayList<>();


        for (int i = existingAddresses.size() - 1; i >= 0; i--) {
            Address existingAddress = existingAddresses.get(i);

            boolean isUnique = true;
            for (Address sentAddress : sentAddresses) {
                if (Objects.equals(existingAddress.getId(), sentAddress.getId())) {
                    isUnique = false;
                    break;
                }
            }

            if (isUnique) {
                addressesToAdd.add(existingAddress);
                existingAddresses.remove(i);
            }
        }

        return addressesToAdd;
    }

    public List<Address> newAddressCatcher(Contact sentContact) {
        List<Address> sentAddresses = sentContact.getAddressesAssociated();
        List<Address> newAddressesSent = new ArrayList<>();

        for (int i = sentAddresses.size() - 1; i >= 0; i--) {
            Address sentAddress = sentAddresses.get(i);
            if (sentAddress.getId() == null) {
                newAddressesSent.add(sentAddress);
                sentAddresses.remove(i);
            }
        }

        return newAddressesSent;

    }
}
