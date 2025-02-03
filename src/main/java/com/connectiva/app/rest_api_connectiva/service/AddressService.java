package com.connectiva.app.rest_api_connectiva.service;

import com.connectiva.app.rest_api_connectiva.model.Address;
import com.connectiva.app.rest_api_connectiva.repository.AddressRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> findAllAddresses() {
        return addressRepository.findAll();
    }

    public Address findAddressesById(Long id) {
        return addressRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("ID" + id + "não encontrado"));
    }

    public void removeAddress(Long id) {
        addressRepository.deleteById(id);
    }

}
