package com.marcelo.ControleDeEstoque.Funcionarios;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.marcelo.ControleDeEstoque.Registros.RegistrosService;
import org.springframework.transaction.annotation.Transactional;


@Service
public class FuncionariosService {
    private final FuncionariosMapper funcionariosMapper;
    private final FuncionariosRepository funcionariosRepository;
    private final RegistrosService registrosService;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public FuncionariosService(FuncionariosMapper funcionariosMapper, FuncionariosRepository funcionariosRepository, RegistrosService registrosService){

        this.funcionariosMapper = funcionariosMapper;
        this.funcionariosRepository = funcionariosRepository;
        this.registrosService = registrosService;
    }

    public FuncionariosDTO listarPorId(UUID id){
        FuncionariosModel funcionarioProcurado = funcionariosRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionaio com ID" + id));
        return funcionariosMapper.map(funcionarioProcurado);
    }

    public List<FuncionariosDTO> listarTodos(){
        List<FuncionariosModel> todosFuncionarios = funcionariosRepository.findAll();
        List<FuncionariosDTO> dtos = todosFuncionarios.stream().map(funcionariosMapper::map).collect(Collectors.toList()); 
        return dtos;
    }

    @Transactional
    public FuncionariosDTO criarFuncionariosDTO(FuncionariosDTO funcionarioCriado, FuncionariosModel funcionariosQueCriou){
        FuncionariosModel funcionariosModel = funcionariosMapper.map(funcionarioCriado);

        String senha = encoder.encode(funcionariosModel.getSenha());

        funcionariosModel.setSenha(senha);
        funcionariosModel = funcionariosRepository.save(funcionariosModel);

        registrosService.criaRegistros(
            "funcionarios", 
            "CRIACAO", 
            funcionariosModel.getId(), 
            null, 
            funcionariosMapper.serializeForAudit(funcionariosModel), 
            funcionariosQueCriou.getId(), 
            funcionariosQueCriou.getNome()
        );

        return funcionariosMapper.map(funcionariosModel);

    }

    @Transactional
    public void deletarFuncionario(UUID id, FuncionariosModel funcionarioResponsavel){
        FuncionariosModel funcionarioProcurado = funcionariosRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionaio com ID" + id));
        registrosService.criaRegistros(
            "funcionarios", 
            "EXCLUSAO", 
            funcionarioProcurado.getId(), 
            funcionariosMapper.serializeForAudit(funcionarioProcurado), 
            null, 
            funcionarioResponsavel.getId(), 
            funcionarioResponsavel.getNome()
        );

        funcionariosRepository.delete(funcionarioProcurado);
    }

    @Transactional
    public FuncionariosDTO alterarFuncionario(UUID id, FuncionariosDTO data, FuncionariosModel funcionarioResponsavel){
        FuncionariosModel funcionarioProcurado = funcionariosRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionaio com ID" + id));
        FuncionariosModel funcionarioProcuradoAntes = new FuncionariosModel(funcionarioProcurado);

        if (data.getNome() != null) {
            funcionarioProcurado.setNome(data.getNome());
        }
        if (data.getSenha() != null) {
            String senha = encoder.encode(data.getSenha());
            funcionarioProcurado.setSenha(senha);
        }
        if (data.getCargo() != null) {
            funcionarioProcurado.setCargo(data.getCargo());
        }
        if(data.getEmail() != null){
            funcionarioProcurado.setEmail(data.getEmail());
        }
        if (data.getTelefone() != null) {
            funcionarioProcurado.setTelefone(data.getTelefone());
        }

        funcionariosRepository.save(funcionarioProcurado);

        registrosService.criaRegistros(
            "funcionarios", 
            "ALTERACAO", 
            funcionarioProcurado.getId(), 
            funcionariosMapper.serializeForAudit(funcionarioProcuradoAntes), 
            funcionariosMapper.serializeForAudit(funcionarioProcurado), 
            funcionarioResponsavel.getId(), 
            funcionarioResponsavel.getNome()
        );

        return funcionariosMapper.map(funcionarioProcurado);

    }
}
