package com.gaformario.desafio_backend_itau;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title = "API de Transações",
				version = "1.0",
				description = "Documentação da API para gerenciamento de transações"
		)
)
@SpringBootApplication
public class DesafioBackendItauApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioBackendItauApplication.class, args);
	}

}
