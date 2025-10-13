package com.marcelo.ControleDeEstoque.Registros;

import java.time.LocalDateTime;
import java.util.UUID;

import com.marcelo.ControleDeEstoque.Funcionarios.FuncionariosModel;
import com.marcelo.ControleDeEstoque.Itens.ItensModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegistrosDTO {
    
    private UUID id;
    private LocalDateTime dataHora;
    private String tipo;
    private int quantidade;
    private ItensModel item;
    private FuncionariosModel funcionario;
    
}
