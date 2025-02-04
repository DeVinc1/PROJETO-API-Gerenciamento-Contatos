package com.connectiva.app.rest_api_connectiva;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;


@RestController
public class InfoApiGetter {

    @GetMapping("api/v2/")
    public Map<String, String> welcomeDefaultMessage() {
        Map<String, String> welcomeMessage = new LinkedHashMap<>();
        welcomeMessage.put("Welcome", " to the Connectiva RESTful API");
        welcomeMessage.put("api", "Connectiva API REST");
        welcomeMessage.put("status", "Online and Running");
        welcomeMessage.put("version", "2.0");
        welcomeMessage.put("description", "API RESTful to manage data in a contact database");
        welcomeMessage.put("documentation", "TBD");
        return welcomeMessage;
    }
}
