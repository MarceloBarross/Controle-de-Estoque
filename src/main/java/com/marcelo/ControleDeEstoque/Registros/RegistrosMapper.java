package com.marcelo.ControleDeEstoque.Registros;

public class RegistrosMapper {

    public static RegistrosModel map(RegistrosDTO dto){
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

    public static RegistrosDTO map(RegistrosModel model){
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
