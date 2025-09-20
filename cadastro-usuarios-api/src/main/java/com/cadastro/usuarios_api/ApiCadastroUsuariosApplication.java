package com.cadastro.usuarios_api;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EntityScan(basePackages = "com.cadastro.usuarios_api.model")
public class ApiCadastroUsuariosApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiCadastroUsuariosApplication.class, args);
    }

}