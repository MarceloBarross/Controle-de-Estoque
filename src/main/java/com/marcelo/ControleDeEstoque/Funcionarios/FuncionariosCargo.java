package com.marcelo.ControleDeEstoque.funcionarios;

public enum FuncionariosCargo {
    ADMIN("ADMIN"),
    COLABORADOR("COLABORADOR");

    private String role;

    FuncionariosCargo(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
