package com.marcelo.ControleDeEstoque.Itens;

import java.util.UUID;
import java.util.stream.Collectors;
import java.util.List;

import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.marcelo.ControleDeEstoque.Funcionarios.FuncionariosModel;
import com.marcelo.ControleDeEstoque.Registros.RegistrosService;

@Service
public class ItensService {
    
    private final ItensMapper itensMapper;
    private final ItensRepository itensRepository;
    private final RegistrosService registrosService; 
    
    public ItensService(ItensMapper itensMapper, ItensRepository itensRepository, RegistrosService registrosService){
        this.itensMapper = itensMapper;
        this.itensRepository = itensRepository;
        this.registrosService = registrosService;
    }

    public ItensDTO criarItens(ItensDTO item, FuncionariosModel funcionariosModel){
        ItensModel itensModel = itensMapper.map(item);
        itensModel = itensRepository.save(itensModel);

        registrosService.criaRegistros("CRIACAO", itensModel.getQuantidade(), itensModel, funcionariosModel);
        return itensMapper.map(itensModel);
    }

    public ItensDTO listarItensPorId(UUID id){
        ItensModel model = itensRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item com ID " + id + " não encontrado."));
        return itensMapper.map(model);
    }

    public List<ItensDTO> listarTodosItens(){
        List<ItensModel> models = itensRepository.findAll();
        List<ItensDTO> dtos = models.stream().map(itensMapper::map).collect(Collectors.toList());
        return dtos;
    }

    public void deletarItem(UUID id, FuncionariosModel funcionariosModel){
        ItensModel itemDeletado = itensRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Item com ID " + id + " não encontrado."));
        
        itensRepository.delete(itemDeletado);
        registrosService.criaRegistros("EXCLUSAO", itemDeletado.getQuantidade(), itemDeletado, funcionariosModel);
    }


    public ItensDTO adicionarEntrada(UUID id, int qtd, FuncionariosModel funcionariosModel){
        ItensModel itemProcurado = itensRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item com ID " + id + " não encontrado."));
        
        itemProcurado.setQuantidade(itemProcurado.getQuantidade()+qtd);
        itensRepository.save(itemProcurado);
        registrosService.criaRegistros("ENTRADA", qtd, itemProcurado, funcionariosModel);    

        return itensMapper.map(itemProcurado);
    }

    public ItensDTO adicionarSaida(UUID id, int qtd, FuncionariosModel funcionariosModel){
        ItensModel itemProcurado = itensRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item com ID " + id + " não encontrado."));
        
        itemProcurado.setQuantidade(itemProcurado.getQuantidade()-qtd);
        itensRepository.save(itemProcurado);
        registrosService.criaRegistros("SAIDA", qtd, itemProcurado, funcionariosModel);
        
        return itensMapper.map(itemProcurado);
    }

    public ItensDTO atualizarItem(UUID id, ItensDTO data, FuncionariosModel funcionariosModel){
        ItensModel itemProcurado = itensRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item com ID " + id + " não encontrado."));
        
        if (data.getNome() != null) {
            itemProcurado.setNome(data.getNome());
        }
        if (data.getDescricao() != null) {
            itemProcurado.setDescricao(data.getDescricao());
        }
        if (data.getQuantidade() != null) {
            if (data.getQuantidade() < 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantidade não pode ser negativa");
            }else{
                itemProcurado.setQuantidade(data.getQuantidade());
            }
        }

        ItensModel itemAtualizado = itensRepository.save(itemProcurado);

        registrosService.criaRegistros("ALTERACAO", itemAtualizado.getQuantidade(), itemAtualizado, funcionariosModel);

        return itensMapper.map(itemAtualizado);
    }
}
