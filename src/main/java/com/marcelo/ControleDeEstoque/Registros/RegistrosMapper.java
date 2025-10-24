package com.marcelo.ControleDeEstoque.Registros;


import org.springframework.stereotype.Component;

@Component
public class RegistrosMapper {

    public RegistrosModel map(RegistrosDTO dto){

        RegistrosModel model = new RegistrosModel(
            dto.getId(),
            dto.getTabelaAfetada(),
            dto.getTipo(),
            dto.getRegistroAfetadoId(),
            dto.getValorAnterior(),
            dto.getValorNovo(),
            dto.getIdUsuario(),
            dto.getUsuarioNome(),
            dto.getDataHora()
        );
        return model;
    }

    public RegistrosDTO map(RegistrosModel model){
        RegistrosDTO dto = new RegistrosDTO(
            model.getId(),
            model.getTabelaAfetada(),
            model.getTipo(),
            model.getRegistroAfetadoId(),
            model.getValorAnterior(),
            model.getValorNovo(),
            model.getIdUsuario(),
            model.getUsuarioNome(),
            model.getDataHora()
        );
        return dto;
    }
    
}
