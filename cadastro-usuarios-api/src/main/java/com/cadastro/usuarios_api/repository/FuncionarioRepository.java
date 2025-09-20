package com.cadastro.usuarios_api.repository;

import com.cadastro.usuarios_api.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    Funcionario findByEmail(String email);

    Funcionario save(Funcionario funcionario);
}