package com.marcelo.ControleDeEstoque.Funcionarios;

import java.util.HashSet;
import java.util.Set;

import com.marcelo.ControleDeEstoque.Registros.RegistrosModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Funcionarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FuncionariosModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String nome;
    private String cargo;
    private String email;
    private String telefone;

    @OneToMany(mappedBy = "funcionario")
    private Set<RegistrosModel> registros = new HashSet<>();
}
