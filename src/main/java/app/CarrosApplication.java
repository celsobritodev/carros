package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Curso - Spring Boot e Angular | 19 - Angular 17: Relacionamento N para 1 (front e back)
// https://youtu.be/zJFVhlwdwvg?list=PLtII2Mw41oA0LLJgLVeyRWO5IPUtdfRth

// AULA QUE É MONTADO O DIAGRAMA DE RELACIONAMENTO ENTRE CARRO, MARCA E PROPRIETARIO
// Curso - Spring Boot e Angular | 04A - Relacionamentos e Cardinalidades com JPA (back-end)   [ 4 : 35 ]
// https://youtu.be/DIv6VH1aG9c?list=PLtII2Mw41oA0LLJgLVeyRWO5IPUtdfRth

@SpringBootApplication
public class CarrosApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarrosApplication.class, args);
	}

}
