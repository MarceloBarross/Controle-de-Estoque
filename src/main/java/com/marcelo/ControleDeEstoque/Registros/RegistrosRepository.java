package com.marcelo.ControleDeEstoque.registros;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrosRepository extends JpaRepository<RegistrosModel, UUID> {
    
}
