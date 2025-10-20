package com.marcelo.ControleDeEstoque.Itens;

import org.springframework.stereotype.Component;

@Component
public class ItensMapper {

    public ItensModel map(ItensDTO dto){
        ItensModel model = new ItensModel(
            dto.getId(),
            dto.getNome(),
            dto.getDescricao(),
            dto.getQuantidade(),
            dto.getRegistros()
        );
        return model;
    }

    public ItensDTO map(ItensModel model){
        ItensDTO dto = new ItensDTO(
            model.getId(),
            model.getNome(),
            model.getDescricao(),
            model.getQuantidade(),
            model.getRegistros()
        );
        return dto;
    }
    
}
