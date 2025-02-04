package com.connectiva.app.rest_api_connectiva.controller;

import com.connectiva.app.rest_api_connectiva.model.Contact;
import com.connectiva.app.rest_api_connectiva.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
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
            @ApiResponse(responseCode = "200", description = "Todos os contatos foram retornados com sucesso",
                    content = @Content(mediaType = "application/json",
                    examples = @ExampleObject(
                            name = "Resposta esperada da Requisição",
                            value = EXAMPLE_ALL_USERS,
                            description = "Todos os contatos armazenados na database"
                    )
            )
            ),
            @ApiResponse(responseCode = "500", description = "Conexão entre API e Banco de Dados interrompida", content = @Content()),
            @ApiResponse(responseCode = "503", description = "A API está temporariamente fora de ar", content = @Content())
    })
    @GetMapping("/todos")


    public List<Contact> getAllContacts(){
        return contactService.findAllContacts();
    }


    @Operation(description = "A API Busca um contato específico pelo seu ID dentro do banco de dados, exibindo-o em formato JSON junto aos seus endereços associados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "O contato com o ID especificado foi encontrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Resposta esperada da Requisição",
                                    value = EXAMPLE_SINGLE_USER,
                                    description = "Informações do contato cadastrado no banco de dados que tenha o ID enviado"
                            )
                    )
            ),
            @ApiResponse(responseCode = "404", description = "O contato com o ID especificado não existe", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Conexão entre API e Banco de Dados interrompida", content = @Content()),
            @ApiResponse(responseCode = "503", description = "A API está temporariamente fora de ar", content = @Content())
    })
    @GetMapping(value = "/{id}")


    public Contact getContactById(@PathVariable Long id){
        return contactService.findContactById(id);
    }


    @Operation(description = "Cadastra um novo contato junto aos seus endereços associados",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                            name = "Exemplo de RequestBody para ser cadastrado",
                            value = EXAMPLE_ADD_USER
                        )
                )
        )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "O contato foi cadastrado com sucesso", content = @Content()),
            @ApiResponse(responseCode = "400", description = "O corpo da requisição ou suas propriedades podem estar incorretos", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Conexão entre API e Banco de Dados interrompida", content = @Content()),
            @ApiResponse(responseCode = "503", description = "A API está temporariamente fora de ar", content = @Content())
    })
    @PostMapping("/cadastrar")


    public Contact insertContact(@RequestBody Contact contact) {
        return contactService.saveNewContact(contact);
    }


    @Operation(description = "Atualiza todos os dados de um contato a partir de um ID específico",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Exemplo de RequestBody para editar contato",
                                    value = EXAMPLE_EDIT_USER
                            )
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "O contato foi atualizado com sucesso", content = @Content()),
            @ApiResponse(responseCode = "400", description = "O corpo da requisição ou suas propriedades podem estar incorretos", content = @Content()),
            @ApiResponse(responseCode = "404", description = "O contato com o ID especificado não existe", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Conexão entre API e Banco de Dados interrompida", content = @Content()),
            @ApiResponse(responseCode = "503", description = "A API está temporariamente fora de ar", content = @Content())
    })
    @PutMapping(value = "/atualizar/{id}")


    public Contact updateContact(@RequestBody Contact contact, @PathVariable Long id){
        return contactService.updateFullContact(contact, id);
    }


    @Operation(description = "Atualiza propriedades específicas de um contato a partir de ID específico.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Exemplo de RequestBody para editar contato",
                                    value = EXAMPLE_EDIT_USER_PROPERTIES
                            )
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "As propriedades do contato foram atualizado com sucesso", content = @Content()),
            @ApiResponse(responseCode = "400", description = "O corpo da requisição ou suas propriedades podem estar incorretos", content = @Content()),
            @ApiResponse(responseCode = "404", description = "O contato com o ID especificado não existe", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Conexão entre API e Banco de Dados interrompida", content = @Content()),
            @ApiResponse(responseCode = "503", description = "A API está temporariamente fora de ar", content = @Content())
    })
    @PatchMapping(value = "/editar/{id}")


    public Contact updatePartialContact(@RequestBody Contact contact, @PathVariable Long id) {
        return contactService.updatePartialContact(contact, id);
    }


    @Operation(description = "Remove um contato com o ID especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "O contato foi excluído com sucesso", content = @Content()),
            @ApiResponse(responseCode = "404", description = "O contato com o ID especificado não existe", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Conexão entre API e Banco de Dados interrompida", content = @Content()),
            @ApiResponse(responseCode = "503", description = "A API está temporariamente fora de ar", content = @Content())
    })
    @DeleteMapping(value = "excluir/{id}")


    public void deleteContact(@PathVariable Long id){
        contactService.removeContact(id);
    }

    /* Strings com JSONs exemplos */
    private static final String EXAMPLE_ALL_USERS = """
            [
                {
                    "id": 1,
                    "name": "Rafael dos Santos",
                    "email": "rafael.santos@gmail.com",
                    "phoneNumber": "11 99568-1532",
                    "birthDate": "2002-12-25",
                    "addressesAssociated": [
                        {
                            "id": 1,
                            "streetName": "Avenida dos Passáros",
                            "houseNumber": 20,
                            "postalCode": "20580-589"
                        }
                    ]
                },
                {
                    "id": 2,
                    "name": "Lorenzo da Gama",
                    "email": "lorenzo.gama@gmail.com",
                    "phoneNumber": "(11) 98732-0502",
                    "birthDate": "2003-06-25",
                    "addressesAssociated": [
                        {
                            "id": 2,
                            "streetName": "Alameda dos Pinhais",
                            "houseNumber": 220,
                            "postalCode": "05502-789"
                        },
                        {
                            "id": 3,
                            "streetName": "Alameda dos Pinhais",
                            "houseNumber": 220,
                            "postalCode": "05502-789"
                        }
                    ]
                }
            ]""";

    private static final String EXAMPLE_SINGLE_USER = """
            {
                "id": 1,
                "name": "Rafael dos Santos",
                "email": "rafael.santos@gmail.com",
                "phoneNumber": "(11) 99568-1532",
                "birthDate": "2002-12-25",
                "addressesAssociated": [
                    {
                        "id": 1,
                        "streetName": "Avenida dos Passáros",
                        "houseNumber": 20,
                        "postalCode": "20580-589"
                    }
                ]
            }
            """;

    private static final String EXAMPLE_ADD_USER =
            """
            {
                "id": null,
                "name": "Laura Maria",
                "email": "laura.maria@gmail.com",
                "phoneNumber": "(11) 97758-2568",
                "birthDate": "2001-02-27",
                "addressesAssociated": [
                    {
                        "id": null,
                        "streetName": "Alameda das Arvores",
                        "houseNumber": 28,
                        "postalCode": "00502-709"
                    }
                ]
            }
            """;

    private static final String EXAMPLE_EDIT_USER =
            """
            {
                "id": null,
                "name": "Laura Maria",
                "email": "laura.maria@gmail.com",
                "phoneNumber": "(11) 97758-2568",
                "birthDate": "2001-02-27",
                "addressesAssociated": [
                    {
                        "id": 5,
                        "streetName": "Alameda das Arvores",
                        "houseNumber": 28,
                        "postalCode": "00502-709"
                    },
                    {
                        "id": null,
                        "streetName": "Alameda dos Beija-Flor",
                        "houseNumber": 78,
                        "postalCode": "02672-914" 
                    }
                ]
            }
            """;

    private static final String EXAMPLE_EDIT_USER_PROPERTIES =
            """
            {
                "id": null,
                "name": "null",
                "email": "laura.maria@gmail.com",
                "phoneNumber": "(11) 97758-2568",
                "birthDate": "null",
                "addressesAssociated": [
                    {
                        "id": 5,
                        "streetName": "Alameda das Arvores",
                        "houseNumber": 28,
                        "postalCode": "null"
                    },
                    {
                        "id": null,
                        "streetName": "Alameda dos Beija-Flor",
                        "houseNumber": 78,
                        "postalCode": "02672-914" 
                    }
                ]
            }
            """;


}
