package com.marcelo.ControleDeEstoque.Funcionarios;

public class FuncionariosMapper {
    public static FuncionariosDTO map(FuncionariosModel model) {
        FuncionariosDTO dto = new FuncionariosDTO(
            model.getId(),
            model.getNome(),
            model.getSenha(),
            model.getCargo(),
            model.getEmail(),
            model.getTelefone(),
            model.getRegistros()
        );
        return dto;
    }

    public static FuncionariosModel map(FuncionariosDTO dto) {
        FuncionariosModel model = new FuncionariosModel(
            dto.getId(),
            dto.getNome(),
            dto.getSenha(),
            dto.getCargo(),
            dto.getEmail(),
            dto.getTelefone(),
            dto.getRegistros()
        );
        return model;
    }
}
