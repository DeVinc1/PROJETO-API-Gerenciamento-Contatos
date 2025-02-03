package com.connectiva.app.rest_api_connectiva.service;

import com.connectiva.app.rest_api_connectiva.model.Address;
import com.connectiva.app.rest_api_connectiva.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<Address> findAllAddresses() {
        return addressRepository.findAll();
    }

    public Address findAddressesById(Long id) {
        return addressRepository.findById(id).get();
    }

    public void removeAddress(Long id) {
        addressRepository.deleteById(id);
    }

}
