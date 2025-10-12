package com.marcelo.ControleDeEstoque.Itens;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItensRepository extends JpaRepository<ItensModel, UUID> {
    
}
