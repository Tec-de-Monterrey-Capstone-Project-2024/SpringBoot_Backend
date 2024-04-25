package com.springboot.connectmate;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;


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
		),
		servers = {
				@Server(url = "https://connectmate-f72xn3ewaa-uc.a.run.app", description = "Production server"),
				@Server(url = "http://localhost:8080", description = "Local development server")
		}
)
public class SpringbootConnectmateRestApiApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootConnectmateRestApiApplication.class, args);
	}

}
