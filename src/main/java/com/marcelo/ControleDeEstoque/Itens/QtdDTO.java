package com.marcelo.ControleDeEstoque.Itens;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class QtdDTO {

    @NotNull(message = "O campo 'quantidade' é obrigatório e não pode ser nulo.")
    @Min(value = 1, message = "A quantidade deve ser de no mínimo 1 (Valor positivo).")
    Integer quantidade;
}
