package com.marcelo.ControleDeEstoque.funcionarios;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping("funcionarios")
public class FuncionariosController {

    private final FuncionariosService funcionariosService;
    private FuncionariosModel funcionariosAutenticado;

    public FuncionariosController(FuncionariosService funcionariosService, FuncionariosRepository funcionariosRepository){
        this.funcionariosService = funcionariosService;
        this.funcionariosAutenticado = funcionariosRepository.findById(UUID.fromString("a05a8d55-08c5-4642-b026-e63327e7bb9f")).orElse(null);
    }


    @PostMapping("/POST")
    public ResponseEntity<FuncionariosDTO> postMethodName(@RequestBody FuncionariosDTO entity) {
        FuncionariosDTO funcionarioCriado = funcionariosService.criarFuncionariosDTO(entity, funcionariosAutenticado);
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioCriado);
    }

    @GetMapping("/GET")
    public ResponseEntity<List<FuncionariosDTO>> getMethodName() {
        List<FuncionariosDTO> listaFuncionariosDTOs = funcionariosService.listarTodos();
        return ResponseEntity.ok(listaFuncionariosDTOs);
    }
    

    @GetMapping("/GET/{id}")
    public ResponseEntity<FuncionariosDTO> getMethodName(@PathVariable UUID id) {
        return ResponseEntity.ok(funcionariosService.listarPorId(id));
    }

    @DeleteMapping("/DELETE/{id}")
    public ResponseEntity<Void> deletarItem(@PathVariable UUID id){
        funcionariosService.deletarFuncionario(id, funcionariosAutenticado);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("PUT/{id}")
    public ResponseEntity<FuncionariosDTO> putMethodName(@PathVariable UUID id, @RequestBody FuncionariosDTO entity) {
        FuncionariosDTO funcionariopAlteradoDto = funcionariosService.alterarFuncionario(id, entity, funcionariosAutenticado);
        return ResponseEntity.ok(funcionariopAlteradoDto);
    }
    
}
