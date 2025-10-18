package com.marcelo.ControleDeEstoque.Registros;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import java.util.UUID;
import com.marcelo.ControleDeEstoque.Funcionarios.FuncionariosModel;
import com.marcelo.ControleDeEstoque.Itens.ItensModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Entity
@Table(name = "registros")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "data_registro", updatable = false)
    @CreationTimestamp
    private LocalDateTime dataHora;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "quantidade", nullable = false)
    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private ItensModel item;
    
    @ManyToOne
    @JoinColumn(name = "funcionario_id", nullable = false)
    private FuncionariosModel funcionario;
}
