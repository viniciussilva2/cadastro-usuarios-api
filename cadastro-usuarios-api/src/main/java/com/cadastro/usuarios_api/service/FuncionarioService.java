package com.cadastro.usuarios_api.service;

import com.cadastro.usuarios_api.model.Funcionario;
import com.cadastro.usuarios_api.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Funcionario createFuncionario(Funcionario funcionario) {
        if (!StringUtils.hasText(funcionario.getNome())) {
            throw new ValidationException("O nome do funcionário é obrigatório.");
        }

        if (!StringUtils.hasText(funcionario.getEmail())) {
            throw new ValidationException("O email do funcionário é obrigatório.");
        }

        if (!StringUtils.hasText(funcionario.getTelefone())) {
            throw new ValidationException("O telefone do funcionário é obrigatório.");
        }

        if (!funcionario.getEmail().matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new ValidationException("O formato do email é inválido.");
        }

        if (funcionarioRepository.findByEmail(funcionario.getEmail()) != null) {
            throw new ValidationException("Já existe um funcionário cadastrado com este email.");
        }

        return funcionarioRepository.save(funcionario);
    }

    public List<Funcionario> getAllFuncionarios() {
        return funcionarioRepository.findAll();
    }

    public Funcionario updateFuncionario(Long id, Funcionario funcionario) {
        Funcionario existente = funcionarioRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Funcionário não encontrado."));

        if (!StringUtils.hasText(funcionario.getNome())) {
            throw new ValidationException("O nome do funcionário é obrigatório.");
        }
        if (!StringUtils.hasText(funcionario.getEmail())) {
            throw new ValidationException("O email do funcionário é obrigatório.");
        }
        if (!StringUtils.hasText(funcionario.getTelefone())) {
            throw new ValidationException("O telefone do funcionário é obrigatório.");
        }
        if (!funcionario.getEmail().matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new ValidationException("O formato do email é inválido.");
        }
        // Verifica se o email já existe para outro funcionário
        Funcionario emailExistente = funcionarioRepository.findByEmail(funcionario.getEmail());
        if (emailExistente != null && !emailExistente.getId().equals(id)) {
            throw new ValidationException("Já existe um funcionário cadastrado com este email.");
        }

        existente.setNome(funcionario.getNome());
        existente.setEmail(funcionario.getEmail());
        existente.setTelefone(funcionario.getTelefone());

        return funcionarioRepository.save(existente);
    }

    public void deleteFuncionario(Long id) {
        if (!funcionarioRepository.existsById(id)) {
            throw new ValidationException("Funcionário não encontrado.");
        }
        funcionarioRepository.deleteById(id);
    }

}