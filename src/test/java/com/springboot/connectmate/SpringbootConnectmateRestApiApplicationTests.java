package com.springboot.connectmate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class SpringbootConnectmateRestApiApplicationTests {
	@Autowired
	MockMvc mockMvc;

	/*
	@Test
	public void sumaEnterosPositivos() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("usl"));
	}

	private static String objectToJSON(Object object){
		ObjectMapper objectMapper = new ObjectMapper();
		return "String";
	}
	*/

	@Test
	void contextLoads() {
	}

}
