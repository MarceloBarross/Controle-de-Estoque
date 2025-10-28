package com.marcelo.ControleDeEstoque.itens;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record QtdDTO (
    @NotNull(message = "O campo 'quantidade' é obrigatório e não pode ser nulo.")
    @Min(value = 1, message = "A quantidade deve ser de no mínimo 1 (Valor positivo).")
    Integer quantidade
){}
