package com.marcelo.ControleDeEstoque.Funcionarios;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FuncionariosMapperTest {

    private final FuncionariosMapper mapper = new FuncionariosMapper();

    public void testToDTO(){
        FuncionariosModel model = new FuncionariosModel();

        assertTrue(mapper.map(model) instanceof FuncionariosDTO);
    }

    public void testToModel(){
        FuncionariosDTO dto = new FuncionariosDTO();
        
        assertTrue(mapper.map(dto) instanceof FuncionariosModel);
    }
}
