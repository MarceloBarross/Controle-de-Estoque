package com.marcelo.ControleDeEstoque.itens;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcelo.ControleDeEstoque.funcionarios.FuncionariosModel;
import com.marcelo.ControleDeEstoque.funcionarios.FuncionariosRepository;

import java.util.List;
import java.util.UUID;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("itens")
public class ItensController {
    
    private final ItensService itensService;
    private final FuncionariosModel funcionarioAutenticado;

    public ItensController(ItensService itensService, FuncionariosRepository funcionariosRepository){
        this.itensService = itensService;
        this.funcionarioAutenticado = funcionariosRepository.findById(UUID.fromString("2a1b89e3-23b8-48c0-a0ff-c9c515a40943")).orElse(null);
    }

    @PostMapping("/POST")
    public ResponseEntity<ItensDTO> criarItem(@RequestBody ItensDTO itensDTO){
        ItensDTO itemCriado = itensService.criarItens(itensDTO,funcionarioAutenticado);
        return ResponseEntity.status(HttpStatus.CREATED).body(itemCriado);
    } 

    @GetMapping("/GET")
    public ResponseEntity<List<ItensDTO>> listarTodos() {
        List<ItensDTO> listDTO = itensService.listarTodosItens();
        return ResponseEntity.ok(listDTO);
    }

    @GetMapping("/GET/{id}")
    public ResponseEntity<ItensDTO> listarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(itensService.listarItensPorId(id));
    }

    @PutMapping("/PUT/{id}")
    public ResponseEntity<ItensDTO> atualizarItem(@PathVariable UUID id, @RequestBody ItensDTO data) {
        ItensDTO itemAtualizado = itensService.atualizarItem(id, data, funcionarioAutenticado);
        
        return ResponseEntity.ok(itemAtualizado);
    }
    
    @PutMapping("/PUT/entrada/{id}")
    public ResponseEntity<ItensDTO> entradaQtd(@PathVariable UUID id, @RequestBody QtdDTO qtdDTO) {
        ItensDTO itemAtualizado = itensService.entradaQtd(id, qtdDTO.getQuantidade(), funcionarioAutenticado);
        
        return ResponseEntity.ok(itemAtualizado);
    }
    
    @PutMapping("/PUT/saida/{id}")
    public ResponseEntity<ItensDTO> saidaQtd(@PathVariable UUID id, @RequestBody QtdDTO qtdDTO) {
        ItensDTO itemAtualizado = itensService.saidaQtd(id, qtdDTO.getQuantidade(), funcionarioAutenticado);
        
        return ResponseEntity.ok(itemAtualizado);
    }

    @DeleteMapping("/DELETE/{id}")
    public ResponseEntity<Void> deletarItem(@PathVariable UUID id){
        itensService.deletarItem(id, funcionarioAutenticado);
        return ResponseEntity.noContent().build();
    }
    
    
}
