package com.marcelo.ControleDeEstoque.Funcionarios;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionariosRepository extends JpaRepository<FuncionariosModel, UUID> {
    
}
