package com.connectiva.app.rest_api_connectiva;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
		"spring.datasource.url=",
		"spring.datasource.username=",
		"spring.datasource.password=",
		"spring.datasource.driver-class-name="
})
class ApiRestfulConnectivaApplicationTests {

	@Test
	void contextLoads() {
	}

}
