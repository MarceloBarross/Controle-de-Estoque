package com.marcelo.ControleDeEstoque.Itens;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.marcelo.ControleDeEstoque.itens.ItensDTO;
import com.marcelo.ControleDeEstoque.itens.ItensMapper;
import com.marcelo.ControleDeEstoque.itens.ItensModel;


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
