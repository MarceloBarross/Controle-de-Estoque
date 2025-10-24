package com.marcelo.ControleDeEstoque.Itens;



import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "itens")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ItensModel {

    public ItensModel(ItensModel item){
        this.id = item.getId();
        this.nome = item.getNome();
        this.descricao = item.getDescricao();
        this.quantidade = item.getQuantidade();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "quantidade", nullable = false)
    private int quantidade;
    
}
