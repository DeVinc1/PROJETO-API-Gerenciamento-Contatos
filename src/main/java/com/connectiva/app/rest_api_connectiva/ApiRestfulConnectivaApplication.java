package com.connectiva.app.rest_api_connectiva;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("api/v2")
public class ApiRestfulConnectivaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiRestfulConnectivaApplication.class, args);
	}

	@GetMapping
	public List<String> welcomeDefaultMassage(){
		return List.of("Welcome to the Connectiva RESTful API",
				"api: Connectiva API REST",
				"status: Online and Running",
				"version: 2.0",
				"description: API RESTful to manage data in a contact database ",
				"documentation: TBD");
	}
}
