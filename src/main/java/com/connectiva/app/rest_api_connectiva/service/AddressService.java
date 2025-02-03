package com.connectiva.app.rest_api_connectiva.service;

import com.connectiva.app.rest_api_connectiva.model.Address;
import com.connectiva.app.rest_api_connectiva.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * Classe de serviço responsável pela lógica de negócios relacionada ao gerenciamento de endereços.
 * Fornece métodos para realizar buscas, criações, atualizações (completas e parciais) e remoções de contatos.
 */
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
