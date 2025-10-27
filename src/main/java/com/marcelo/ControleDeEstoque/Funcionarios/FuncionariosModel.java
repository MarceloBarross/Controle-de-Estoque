package com.marcelo.ControleDeEstoque.funcionarios;


import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
public class FuncionariosModel implements UserDetails{

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

    @Enumerated(EnumType.STRING)
    @Column(name = "cargo", nullable = false)
    private FuncionariosCargo cargo;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "telefone")
    private String telefone;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.cargo == FuncionariosCargo.ADMIN) {
            return List.of(
                new SimpleGrantedAuthority("ROLE_ADMIN"),
                new SimpleGrantedAuthority("ROLE_USER")
            );
        }else{
            return List.of(
                new SimpleGrantedAuthority("ROLE_USER")
            );
        }
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
