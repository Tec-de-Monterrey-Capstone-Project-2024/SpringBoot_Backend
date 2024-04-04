package com.springboot.connectmate;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@OpenAPIDefinition(
		info = @Info(
				title = "ConnectMate API",
				version = "v1.0",
				description = "ConnectMate API Documentation",
				contact = @Contact(
						name = "José Aram Méndez Gómez",
						email = "a01657142@tec.mx",
						url = "https://github.com/Aram32mm"
				),
				license = @License(
						name = "MIT License",
						url = "https://opensource.org/licenses/MIT"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "ConnectMate Backend App Repository",
				url = "https://github.com/Tec-de-Monterrey-Capstone-Project-2024/SpringBoot_Backend"
		)
)
public class SpringbootConnectmateRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootConnectmateRestApiApplication.class, args);
	}

}
