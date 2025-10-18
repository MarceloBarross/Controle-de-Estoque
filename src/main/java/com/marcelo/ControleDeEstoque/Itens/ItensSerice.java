package com.marcelo.ControleDeEstoque.Itens;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.List;
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

    public ItensDTO listarItensPorId(UUID id){
        Optional<ItensModel> model = itensRepository.findById(id);
        return model.map(itensMapper::map).orElse(null);
    }

    public List<ItensDTO> listarTodosItens(){
        List<ItensModel> models = itensRepository.findAll();
        List<ItensDTO> dtos = models.stream().map(itensMapper::map).collect(Collectors.toList());
        return dtos;
    }
}
