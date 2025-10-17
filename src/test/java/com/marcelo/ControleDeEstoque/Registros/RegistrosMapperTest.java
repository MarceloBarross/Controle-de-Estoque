package com.marcelo.ControleDeEstoque.Registros;


import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.Test;


public class RegistrosMapperTest {
    @Test
    public void testToDTO() {
        RegistrosModel model = new RegistrosModel();


            assertTrue(RegistrosMapper.map(model) instanceof RegistrosDTO);
    }

    @Test
    public void testToModel(){
        RegistrosDTO dto = new RegistrosDTO();

            assertTrue(RegistrosMapper.map(dto) instanceof RegistrosModel);
    }
}
