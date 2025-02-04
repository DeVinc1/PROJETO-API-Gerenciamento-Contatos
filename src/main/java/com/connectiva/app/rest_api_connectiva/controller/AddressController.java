package com.connectiva.app.rest_api_connectiva.controller;

import com.connectiva.app.rest_api_connectiva.model.Address;
import com.connectiva.app.rest_api_connectiva.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v2/enderecos")
public class AddressController {

    private final AddressService addressService;


    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @Operation(description = "Exibe todos os endereços cadastrados no Banco de Dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todos os endereços foram retornados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Conexão entre API e Banco de Dados interrompida"),
            @ApiResponse(responseCode = "503", description = "A API está temporariamente fora de ar")
    })
    @GetMapping("/todos")
    public List<Address> getAllAddresses(){
        return addressService.findAllAddresses();
    }

    @Operation(description = "Busca um endereço específico pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "O endereço foi encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado"),
            @ApiResponse(responseCode = "500", description = "Conexão entre API e Banco de Dados interrompida"),
            @ApiResponse(responseCode = "503", description = "A API está temporariamente fora de ar")
    })
    @GetMapping(value = "/{id}")
    public Address getAddressById(@PathVariable Long id){
        return addressService.findAddressesById(id);
    }

    @Operation(description = "Exclui um endereço pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "O endereço foi excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Endereço não encontrado"),
            @ApiResponse(responseCode = "500", description = "Conexão entre API e Banco de Dados interrompida"),
            @ApiResponse(responseCode = "503", description = "A API está temporariamente fora de ar")
    })
    @DeleteMapping(value = "/excluir/{id}")
    public void deleteAddress(@PathVariable Long id){
        addressService.removeAddress(id);
    }
}


