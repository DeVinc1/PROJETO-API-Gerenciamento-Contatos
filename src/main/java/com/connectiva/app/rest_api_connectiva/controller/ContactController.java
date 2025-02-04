package com.connectiva.app.rest_api_connectiva.controller;

import com.connectiva.app.rest_api_connectiva.model.Contact;
import com.connectiva.app.rest_api_connectiva.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v2/contatos")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @Operation(description = "A API busca no banco de dados todos os contatos cadastrados e exibe-os em uma lista JSON junto aos seus endereços associados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todos os contatos foram retornados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Conexão entre API e Banco de Dados interrompida"),
            @ApiResponse(responseCode = "503", description = "A API está temporariamente fora de ar")
    })
    @GetMapping("/todos")
    public List<Contact> getAllContacts(){
        return contactService.findAllContacts();
    }

    @Operation(description = "A API Busca um contato específico pelo seu ID dentro do banco de dados, exibindo-o em formato JSON junto aos seus endereços associados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "O contato foi encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "O contato com o ID especificado não existe"),
            @ApiResponse(responseCode = "500", description = "Conexão entre API e Banco de Dados interrompida"),
            @ApiResponse(responseCode = "503", description = "A API está temporariamente fora de ar")
    })

    @GetMapping(value = "/{id}")
    public Contact getContactById(@PathVariable Long id){
        return contactService.findContactById(id);
    }

    @Operation(description = "Cadastra um novo contato junto aos seus endereços associados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "O contato foi cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "O corpo da requisição ou suas propriedades podem estar incorretos"),
            @ApiResponse(responseCode = "500", description = "Conexão entre API e Banco de Dados interrompida"),
            @ApiResponse(responseCode = "503", description = "A API está temporariamente fora de ar")
    })
    @PostMapping("/cadastrar")
    public Contact insertContact(@RequestBody Contact contact){
        return contactService.saveNewContact(contact);
    }

    @Operation(description = "Atualiza todos os dados de um contato a partir de um ID específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "O contato foi atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "O corpo da requisição ou suas propriedades podem estar incorretos"),
            @ApiResponse(responseCode = "404", description = "O contato com o ID especificado não existe"),
            @ApiResponse(responseCode = "500", description = "Conexão entre API e Banco de Dados interrompida"),
            @ApiResponse(responseCode = "503", description = "A API está temporariamente fora de ar")
    })
    @PutMapping(value = "/atualizar/{id}")
    public Contact updateContact(@RequestBody Contact contact, @PathVariable Long id){
        return contactService.updateFullContact(contact, id);
    }

    @Operation(description = "Atualiza propriedades específicas de um contato a partir de ID específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "AS propriedades do contato foram atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "O corpo da requisição ou suas propriedades podem estar incorretos"),
            @ApiResponse(responseCode = "404", description = "O contato com o ID especificado não existe"),
            @ApiResponse(responseCode = "500", description = "Conexão entre API e Banco de Dados interrompida"),
            @ApiResponse(responseCode = "503", description = "A API está temporariamente fora de ar")
    })
    @PatchMapping(value = "/editar/{id}")
    public Contact updatePartialContact(@RequestBody Contact contact, @PathVariable Long id) {
        return contactService.updatePartialContact(contact, id);
    }

    @Operation(description = "Remove um contato com o ID especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "O contato foi excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "O contato com o ID especificado não existe"),
            @ApiResponse(responseCode = "500", description = "Conexão entre API e Banco de Dados interrompida"),
            @ApiResponse(responseCode = "503", description = "A API está temporariamente fora de ar")
    })
    @DeleteMapping(value = "excluir/{id}")
    public void deleteContact(@PathVariable Long id){
        contactService.removeContact(id);
    }


}
