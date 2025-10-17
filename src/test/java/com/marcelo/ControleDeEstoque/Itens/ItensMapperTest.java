package com.marcelo.ControleDeEstoque.Itens;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


public class ItensMapperTest {

    @Test
    public void testToDTO(){
        ItensModel model = new ItensModel();

        assertTrue(ItensMapper.map(model) instanceof ItensDTO);
    }

    @Test
    public void testToModel(){
         ItensDTO dto = new ItensDTO();

        assertTrue(ItensMapper.map(dto) instanceof ItensModel);
    }
}
