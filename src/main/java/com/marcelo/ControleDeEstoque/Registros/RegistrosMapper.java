package com.marcelo.ControleDeEstoque.Registros;

import org.springframework.stereotype.Component;

@Component
public class RegistrosMapper {

    public RegistrosModel map(RegistrosDTO dto){
        RegistrosModel model = new RegistrosModel(
            dto.getId(),
            dto.getDataHora(),
            dto.getTipo(),
            dto.getQuantidade(),
            dto.getItemId(),
            dto.getItemNome(),
            dto.getFuncionarioModel(),
            dto.getFuncionarioNome()
        );
        return model;
    }

    public RegistrosDTO map(RegistrosModel model){
        RegistrosDTO dto = new RegistrosDTO(
            model.getId(),
            model.getDataHora(),
            model.getTipo(),
            model.getQuantidade(),
            model.getItemId(),
            model.getItemNome(),
            model.getFuncionarioModel(),
            model.getFuncionarioNome()
        );
        return dto;
    }
    
}
