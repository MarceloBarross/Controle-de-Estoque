package com.marcelo.ControleDeEstoque.Registros;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegistrosDTO {
    

    private UUID id;
    private String tabelaAfetada;
    private String tipo;
    private UUID registroAfetadoId;
    private String valorAnterior;
    private String valorNovo;
    private UUID idUsuario;
    private String usuarioNome;
    private LocalDateTime dataHora;

}
