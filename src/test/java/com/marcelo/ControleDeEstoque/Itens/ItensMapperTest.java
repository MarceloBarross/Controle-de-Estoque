package com.marcelo.ControleDeEstoque.Itens;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


public class ItensMapperTest {

    ItensMapper mapper = new ItensMapper();

    @Test
    public void testToDTO(){
        ItensModel model = new ItensModel();

        assertTrue(mapper.map(model) instanceof ItensDTO);
    }

    @Test
    public void testToModel(){
         ItensDTO dto = new ItensDTO();

        assertTrue(mapper.map(dto) instanceof ItensModel);
    }
}
