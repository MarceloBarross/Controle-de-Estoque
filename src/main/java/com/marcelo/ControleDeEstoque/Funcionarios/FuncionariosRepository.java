package com.marcelo.ControleDeEstoque.funcionarios;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface FuncionariosRepository extends JpaRepository<FuncionariosModel, UUID> {
    UserDetails findByEmail(String email);
}
