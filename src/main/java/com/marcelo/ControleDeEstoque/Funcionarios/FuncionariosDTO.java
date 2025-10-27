package com.marcelo.ControleDeEstoque.funcionarios;

import java.util.UUID;


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
    private FuncionariosCargo cargo;
    private String email;
    private String telefone;
}
