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
            dto.getItem(),
            dto.getFuncionario()
        );
        return model;
    }

    public RegistrosDTO map(RegistrosModel model){
        RegistrosDTO dto = new RegistrosDTO(
            model.getId(),
            model.getDataHora(),
            model.getTipo(),
            model.getQuantidade(),
            model.getItem(),
            model.getFuncionario()
        );
        return dto;
    }
    
}
