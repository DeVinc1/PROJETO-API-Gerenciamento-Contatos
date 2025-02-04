package com.connectiva.app.rest_api_connectiva;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;


@RestController
public class InfoApiGetter {

    @Operation(description = "Retorna a mensagem de boas-vindas indicando que a API está em execução")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A API está online e funcionando",
                    content = @Content(mediaType = "application/json",
                            examples = @ExampleObject(
                                    name = "Resposta esperada da Requisição",
                                    value = VALUE_200,
                                    description = "Informações que identificam a API e seu status"
                            )
                    )
            ),
            @ApiResponse(responseCode = "500", description = "Conexão entre API e Banco de Dados interrompida", content = @Content()),
            @ApiResponse(responseCode = "503", description = "A API está temporariamente fora de ar", content = @Content()),
    })
    @GetMapping("api/v2/")
    public Map<String, String> welcomeDefaultMessage() {
        Map<String, String> welcomeMessage = new LinkedHashMap<>();
        welcomeMessage.put("Welcome", " to the Connectiva RESTful API");
        welcomeMessage.put("api", "Connectiva API REST");
        welcomeMessage.put("status", "Online and Running");
        welcomeMessage.put("version", "2.0");
        welcomeMessage.put("description", "API RESTful to manage data in a contact database");
        welcomeMessage.put("documentation", "https://projeto-connectiva-api-de-gerenciamento.onrender.com/swagger-ui/index.html");
        return welcomeMessage;
    }


    private static final String VALUE_200 = """
            {
              "Welcome": "to the Connectiva RESTful API",
              "api": "Connectiva API REST",
              "status": "Online and Running",
              "version": "2.0",
              "description": "API RESTful to manage data in a contact database",
              "documentation": "https://projeto-connectiva-api-de-gerenciamento.onrender.com/swagger-ui/index.html"
            }""";

}
