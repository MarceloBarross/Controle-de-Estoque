package com.marcelo.ControleDeEstoque.Itens;

import com.marcelo.ControleDeEstoque.Registros.RegistrosModel;
import java.util.List;
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
    private int quantidade;
    private List<RegistrosModel> registros;
    
}
