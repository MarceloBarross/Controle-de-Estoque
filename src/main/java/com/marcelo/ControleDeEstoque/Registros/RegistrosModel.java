package com.marcelo.ControleDeEstoque.Registros;

import java.time.LocalDateTime;

import com.marcelo.ControleDeEstoque.Funcionarios.FuncionariosModel;
import com.marcelo.ControleDeEstoque.Itens.ItensModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "registros")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "data_hora_do_registro")
    private LocalDateTime dataHora;
    private String tipo;
    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private ItensModel item;
    
    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private FuncionariosModel funcionario;
}
