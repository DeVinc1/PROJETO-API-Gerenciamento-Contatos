package com.connectiva.app.rest_api_connectiva.controller;

import com.connectiva.app.rest_api_connectiva.model.Address;
import com.connectiva.app.rest_api_connectiva.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
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
            @ApiResponse(responseCode = "200", description = "Todos os endereços foram retornados com sucesso",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Resposta esperada da Requisição",
                                    value = EXAMPLE_ALL_ADDRESSES,
                                    description = "Informações de todos os endereços que estão cadastrados no banco de dados"
                            )
                    )
            ),
            @ApiResponse(responseCode = "500", description = "Conexão entre API e Banco de Dados interrompida", content = @Content()),
            @ApiResponse(responseCode = "503", description = "A API está temporariamente fora de ar", content = @Content())
    })
    @GetMapping("/todos")
    public List<Address> getAllAddresses(){
        return addressService.findAllAddresses();
    }

    @Operation(description = "Busca um endereço específico pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "O endereço foi encontrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Resposta esperada da Requisição",
                                    value = EXAMPLE_SINGLE_ADDRESS,
                                    description = "Informações do endereço cadastrado no banco de dados que tenha o ID enviado"
                            )
                    )
            ),
            @ApiResponse(responseCode = "404", description = "O endereço com o ID especificado não existe", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Conexão entre API e Banco de Dados interrompida", content = @Content()),
            @ApiResponse(responseCode = "503", description = "A API está temporariamente fora de ar", content = @Content())
    })
    @GetMapping(value = "/{id}")
    public Address getAddressById(
            @Parameter(description = "Número inteiro que referencia o ID na base de dados do endereço desejado", example = "1")
            @PathVariable Long id){
        return addressService.findAddressesById(id);
    }

    @Operation(description = "Exclui um endereço pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "O endereço foi excluído com sucesso", content = @Content()),
            @ApiResponse(responseCode = "404", description = "O endereço com o ID especificado não existe", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Conexão entre API e Banco de Dados interrompida", content = @Content()),
            @ApiResponse(responseCode = "503", description = "A API está temporariamente fora de ar", content = @Content())
    })
    @DeleteMapping(value = "/excluir/{id}")
    public void deleteAddress(
            @Parameter(description = "Número inteiro que referencia o ID na base de dados do endereço desejado", example = "1")
            @PathVariable Long id){
        addressService.removeAddress(id);
    }

    private static final String EXAMPLE_ALL_ADDRESSES = "[\n" +
            "    {\n" +
            "        \"id\": 1,\n" +
            "        \"streetName\": \"Avenida das Flores Rosas\",\n" +
            "        \"houseNumber\": 256,\n" +
            "        \"postalCode\": \"02456-010\"\n" +
            "    },\n" +
            "    {\n" +
            "        \"id\": 2,\n" +
            "        \"streetName\": \"Rua da Paz\",\n" +
            "        \"houseNumber\": 1055,\n" +
            "        \"postalCode\": \"32888-256\"\n" +
            "    }\n" +
            "]";

    private static final String EXAMPLE_SINGLE_ADDRESS = "{\n" +
            "    \"id\": 1,\n" +
            "    \"streetName\": \"Avenida das Flores Rosas\",\n" +
            "    \"houseNumber\": 256,\n" +
            "    \"postalCode\": \"02456-010\"\n" +
            "}";


}


