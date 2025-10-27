package com.marcelo.ControleDeEstoque.registros;

import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("registros")
public class RegistrosController {

    RegistrosService registrosService;

    public RegistrosController(RegistrosService registrosService){
        this.registrosService = registrosService;
    }

    @GetMapping("/GET")
    public ResponseEntity<List<RegistrosDTO>> listarTodos() {
        List<RegistrosDTO> listDTO = registrosService.listarTodos();
        return ResponseEntity.ok(listDTO);
    }

    @GetMapping("/GET/{id}")
    public ResponseEntity<RegistrosDTO> listarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(registrosService.listarPorId(id));
    }
}
