package com.marcelo.ControleDeEstoque.Funcionarios;

import java.util.List;
import java.util.UUID;

import com.marcelo.ControleDeEstoque.Registros.RegistrosModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FuncionariosDTO {
    private UUID id;
    private String nome;
    private String senha;
    private String cargo;
    private String email;
    private String telefone;
    private List<RegistrosModel> registros;
}
