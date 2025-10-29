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
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("itens")
public class ItensController {
    
    private final ItensService itensService;

    public ItensController(ItensService itensService, FuncionariosRepository funcionariosRepository){
        this.itensService = itensService;
    }

    @PostMapping("/POST")
    public ResponseEntity<ItensDTO> criarItem(@RequestBody ItensDTO itensDTO, Authentication auth){
        ItensDTO itemCriado = itensService.criarItens(itensDTO, (FuncionariosModel) auth.getPrincipal());
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
    public ResponseEntity<ItensDTO> atualizarItem(@PathVariable UUID id, @RequestBody ItensDTO data, Authentication auth) {
        ItensDTO itemAtualizado = itensService.atualizarItem(id, data, (FuncionariosModel) auth.getPrincipal());
        
        return ResponseEntity.ok(itemAtualizado);
    }
    
    @PutMapping("/PUT/entrada/{id}")
    public ResponseEntity<ItensDTO> entradaQtd(@PathVariable UUID id, @RequestBody QtdDTO qtdDTO, Authentication auth) {
        ItensDTO itemAtualizado = itensService.entradaQtd(id, qtdDTO.quantidade(), (FuncionariosModel) auth.getPrincipal());
        
        return ResponseEntity.ok(itemAtualizado);
    }
    
    @PutMapping("/PUT/saida/{id}")
    public ResponseEntity<ItensDTO> saidaQtd(@PathVariable UUID id, @RequestBody QtdDTO qtdDTO, Authentication auth) {
        ItensDTO itemAtualizado = itensService.saidaQtd(id, qtdDTO.quantidade(), (FuncionariosModel) auth.getPrincipal());
        
        return ResponseEntity.ok(itemAtualizado);
    }

    @DeleteMapping("/DELETE/{id}")
    public ResponseEntity<Void> deletarItem(@PathVariable UUID id, Authentication auth){
        itensService.deletarItem(id, (FuncionariosModel) auth.getPrincipal());
        return ResponseEntity.noContent().build();
    }
    
    
}
