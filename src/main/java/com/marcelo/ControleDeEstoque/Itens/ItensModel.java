package com.marcelo.ControleDeEstoque.Itens;


import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marcelo.ControleDeEstoque.Registros.RegistrosModel;
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

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "quantidade", nullable = false)
    private int quantidade;

    @OneToMany(mappedBy = "item")
    @JsonIgnore
    private List<RegistrosModel> registros;
    
}
