package com.marcelo.ControleDeEstoque.itens;

import java.util.UUID;
import java.util.stream.Collectors;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.marcelo.ControleDeEstoque.funcionarios.FuncionariosModel;
import com.marcelo.ControleDeEstoque.registros.RegistrosService;

import org.springframework.transaction.annotation.Transactional;



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

    @Transactional
    public ItensDTO criarItens(ItensDTO item, FuncionariosModel funcionariosModel){
        ItensModel itensModel = itensMapper.map(item);
        itensModel = itensRepository.save(itensModel);

        registrosService.criaRegistros(
            "itens", 
            "CRIACAO", 
            itensModel.getId(), 
            null, 
            itensMapper.serializeForAudit(itensModel), 
            funcionariosModel.getId(), 
            funcionariosModel.getNome()
        );

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

    @Transactional
    public void deletarItem(UUID id, FuncionariosModel funcionariosModel){
        
        ItensModel itemDeletado = itensRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Item com ID " + id + " não encontrado."));
        
        registrosService.criaRegistros(
            "itens", 
            "EXCLUSAO", 
            id, 
            itensMapper.serializeForAudit(itemDeletado), 
            null, 
            funcionariosModel.getId(), 
            funcionariosModel.getNome()
            );
            
        itensRepository.delete(itemDeletado);
    }

    @Transactional
    public ItensDTO entradaQtd(UUID id, int qtd, FuncionariosModel funcionariosModel){

        ItensModel itemProcurado = itensRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item com ID " + id + " não encontrado."));
        
        ItensModel itemProcuradoAntesCopia = new ItensModel(itemProcurado);
        
        itemProcurado.setQuantidade(itemProcurado.getQuantidade()+qtd);

        itensRepository.save(itemProcurado);
        
        registrosService.criaRegistros(
            "itens", 
            "ENTRADA", 
            id, 
            itensMapper.serializeForAudit(itemProcuradoAntesCopia), 
            itensMapper.serializeForAudit(itemProcurado), 
            funcionariosModel.getId(), 
            funcionariosModel.getNome()
            );

        return itensMapper.map(itemProcurado);
        
    }

    @Transactional
    public ItensDTO saidaQtd(UUID id, int qtd, FuncionariosModel funcionariosModel){

        ItensModel itemProcurado = itensRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item com ID " + id + " não encontrado."));

        int quantidadeFinal = itemProcurado.getQuantidade()-qtd;
        
        if (quantidadeFinal < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantidade em estoque não pode ser negativa");
        }else{
            ItensModel itemProcuradoAntesCopia = new ItensModel(itemProcurado);
            itemProcurado.setQuantidade(quantidadeFinal);

            itensRepository.save(itemProcurado);
            
            registrosService.criaRegistros(
                "itens", 
                "SAIDA", 
                id, 
                itensMapper.serializeForAudit(itemProcuradoAntesCopia), 
                itensMapper.serializeForAudit(itemProcurado),  
                funcionariosModel.getId(), 
                funcionariosModel.getNome()
                );

            return itensMapper.map(itemProcurado);
        }
    }

    @Transactional
    public ItensDTO atualizarItem(UUID id, ItensDTO data, FuncionariosModel funcionariosModel){
        ItensModel itemProcurado = itensRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item com ID " + id + " não encontrado."));
        
        ItensModel itemProcuradoAntesCopia = new ItensModel(itemProcurado);

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

        itensRepository.save(itemProcurado);

        registrosService.criaRegistros(
                "itens", 
                "ALTERACAO", 
                id, 
                itensMapper.serializeForAudit(itemProcuradoAntesCopia), 
                itensMapper.serializeForAudit(itemProcurado), 
                funcionariosModel.getId(), 
                funcionariosModel.getNome()
                );

        return itensMapper.map(itemProcurado);
    }
}
