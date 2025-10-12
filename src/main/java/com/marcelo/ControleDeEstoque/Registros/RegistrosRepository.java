package com.marcelo.ControleDeEstoque.Registros;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrosRepository extends JpaRepository<RegistrosModel, UUID> {
    
}
