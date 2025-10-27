package com.marcelo.ControleDeEstoque.itens;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItensDTO {

    private UUID id;
    private String nome;
    private String descricao;
    private Integer quantidade;
    
}
