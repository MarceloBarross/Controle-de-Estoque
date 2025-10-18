package com.marcelo.ControleDeEstoque.Itens;

import org.springframework.stereotype.Service;

import com.marcelo.ControleDeEstoque.Registros.RegistrosService;

@Service
public class ItensSerice {
    
    private final ItensMapper itensMapper;
    private final ItensRepository itensRepository;
    private final RegistrosService registrosService; 
    
    public ItensSerice(ItensMapper itensMapper, ItensRepository itensRepository, RegistrosService registrosService){
        this.itensMapper = itensMapper;
        this.itensRepository = itensRepository;
        this.registrosService = registrosService;
    }

    public ItensDTO criarItens(ItensDTO item){
        ItensModel itensModel = itensMapper.map(item);
        itensRepository.save(itensModel);

        registrosService.criaRegistros("CRIACAO", itensModel.getQuantidade(), itensModel);
        return itensMapper.map(itensModel);
    }
}
