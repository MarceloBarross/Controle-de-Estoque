package com.marcelo.ControleDeEstoque.Funcionarios;


import java.util.UUID;
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

    public FuncionariosModel(FuncionariosModel funcionariosModel){
        this.id = funcionariosModel.getId();
        this.nome = funcionariosModel.getNome();
        this.senha = funcionariosModel.getSenha();
        this.cargo = funcionariosModel.getCargo();
        this.email = funcionariosModel.getEmail();
        this.telefone = funcionariosModel.getTelefone();
    }
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
}
