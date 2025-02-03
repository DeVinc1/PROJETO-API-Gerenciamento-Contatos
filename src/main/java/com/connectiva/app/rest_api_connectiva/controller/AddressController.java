package com.connectiva.app.rest_api_connectiva.controller;

import com.connectiva.app.rest_api_connectiva.model.Address;
import com.connectiva.app.rest_api_connectiva.service.AddressService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v2/enderecos")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/todos")
    public List<Address> getAllAddresses(){
        return addressService.findAllAddresses();
    }

    @GetMapping(value = "/{id}")
    public Address getAddressById(@PathVariable Long id){
        return addressService.findAddressesById(id);
    }

    @DeleteMapping(value = "/excluir/{id}")
    public void deleteAddress(@PathVariable Long id){
        addressService.removeAddress(id);
    }
}


