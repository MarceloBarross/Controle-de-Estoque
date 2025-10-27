package com.marcelo.ControleDeEstoque.itens;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItensRepository extends JpaRepository<ItensModel, UUID> {
    
}
