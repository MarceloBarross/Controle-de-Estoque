package com.marcelo.ControleDeEstoque.Funcionarios;


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
@Table(name = "funcionarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FuncionariosModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "senha", nullable = false)
    private String senha;

    @Column(name = "cargo", nullable = false)
    private String cargo;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "telefone")
    private String telefone;

    @OneToMany(mappedBy = "funcionarioModel")
    @JsonIgnore
    private List<RegistrosModel> registros;

    @Column(name = "ativo")
    private boolean ativo;
}
