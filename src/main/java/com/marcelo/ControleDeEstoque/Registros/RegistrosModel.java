package com.marcelo.ControleDeEstoque.Registros;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import java.util.UUID;
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

    @Column(name = "tabela_afetada", nullable = false)
    private String tabelaAfetada;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "id_registro_afetado", nullable = false)
    private UUID registroAfetadoId;

    @Column(name = "valor_anterior")
    private String valorAnterior;

    @Column(name = "valor_novo")
    private String valorNovo;

    @Column(name = "id_usuario", nullable = false)
    private UUID idUsuario;

    @Column(name = "usuario", nullable = false)
    private String usuarioNome;

    @Column(name = "data_registro", updatable = false)
    @CreationTimestamp
    private LocalDateTime dataHora;
}
