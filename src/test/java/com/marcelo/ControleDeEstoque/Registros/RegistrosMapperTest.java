package com.marcelo.ControleDeEstoque.Registros;


import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.Test;


public class RegistrosMapperTest {

    private final RegistrosMapper mapper = new RegistrosMapper();


    @Test
    public void testToDTO() {
        RegistrosModel model = new RegistrosModel();


            assertTrue(mapper.map(model) instanceof RegistrosDTO);
    }

    @Test
    public void testToModel(){
        RegistrosDTO dto = new RegistrosDTO();

            assertTrue(mapper.map(dto) instanceof RegistrosModel);
    }
}
