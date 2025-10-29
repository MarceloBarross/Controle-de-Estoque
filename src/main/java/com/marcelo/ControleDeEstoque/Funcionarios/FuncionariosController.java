package com.marcelo.ControleDeEstoque.funcionarios;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcelo.ControleDeEstoque.funcionarios.Autenticacao.AuthenticationDTO;
import com.marcelo.ControleDeEstoque.funcionarios.Autenticacao.LonginResponseDTO;
import com.marcelo.ControleDeEstoque.infra.security.TokenService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping("funcionarios")
public class FuncionariosController {

    private final TokenService tokenService;
    private final FuncionariosService funcionariosService;
    private final AuthenticationManager authenticationManager;
    
    public FuncionariosController(FuncionariosService funcionariosService, FuncionariosRepository funcionariosRepository, AuthenticationManager authenticationManager, TokenService tokenService){
        this.funcionariosService = funcionariosService;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    
    @PostMapping("/login")
    public ResponseEntity<LonginResponseDTO> login(@RequestBody @Valid AuthenticationDTO entity) {
        var emailSenha = new UsernamePasswordAuthenticationToken(entity.email(), entity.senha());
        var auth = authenticationManager.authenticate(emailSenha);
        var token = tokenService.generateToken((FuncionariosModel)auth.getPrincipal());
        
        return ResponseEntity.ok(new LonginResponseDTO(token));
    }


    @PostMapping("/POST")
    public ResponseEntity<FuncionariosDTO> postMethodName(@RequestBody FuncionariosDTO entity, Authentication auth) {
        FuncionariosDTO funcionarioCriado = funcionariosService.criarFuncionariosDTO(entity, (FuncionariosModel)auth.getPrincipal());
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
    public ResponseEntity<Void> deletarItem(@PathVariable UUID id, Authentication auth){
        funcionariosService.deletarFuncionario(id, (FuncionariosModel)auth.getPrincipal());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("PUT/{id}")
    public ResponseEntity<FuncionariosDTO> putMethodName(@PathVariable UUID id, @RequestBody FuncionariosDTO entity, Authentication auth) {
        FuncionariosDTO funcionariopAlteradoDto = funcionariosService.alterarFuncionario(id, entity, (FuncionariosModel) auth.getPrincipal());
        return ResponseEntity.ok(funcionariopAlteradoDto);
    }
    
}
