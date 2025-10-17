package com.marcelo.ControleDeEstoque.Funcionarios;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FuncionariosMapperTest {

    public void testToDTO(){
        FuncionariosModel model = new FuncionariosModel();

        assertTrue(FuncionariosMapper.map(model) instanceof FuncionariosDTO);
    }

    public void testToModel(){
        FuncionariosDTO dto = new FuncionariosDTO();
        
        assertTrue(FuncionariosMapper.map(dto) instanceof FuncionariosModel);
    }
}
