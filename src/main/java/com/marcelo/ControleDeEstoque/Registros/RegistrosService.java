package com.marcelo.ControleDeEstoque.Registros;




import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcelo.ControleDeEstoque.Funcionarios.FuncionariosRepository;

@Service
public class RegistrosService {

    private final RegistrosMapper registrosMapper;
    private final RegistrosRepository registrosRepository; 
    
    public RegistrosService(RegistrosMapper registrosMapper, RegistrosRepository registrosRepository, FuncionariosRepository funcionariosRepository, ObjectMapper objectMapper){
        this.registrosMapper = registrosMapper;
        this.registrosRepository = registrosRepository;
    }

    public RegistrosDTO criaRegistros(String tbAfetada, String tipo, UUID idAfetado, String valorA, String valorD, UUID idUsuario, String usuarioNome){

        RegistrosModel registro = RegistrosModel.builder().
            tabelaAfetada(tbAfetada).
            tipo(tipo).
            registroAfetadoId(idAfetado).
            valorAnterior(valorA).
            valorNovo(valorD).
            idUsuario(idUsuario).
            usuarioNome(usuarioNome).
            build();

        registro = registrosRepository.save(registro);

        return registrosMapper.map(registro);

    }


    public List<RegistrosDTO> listarTodos(){
        List<RegistrosModel> registrosModel = registrosRepository.findAll();
        List<RegistrosDTO> registrosDTOs = registrosModel.stream().map(registrosMapper::map).collect(Collectors.toList());
        return registrosDTOs;
    }

    public RegistrosDTO listarPorId(UUID id){
        Optional<RegistrosModel> registrOptional = registrosRepository.findById(id);
        return registrOptional.map(registrosMapper::map).orElse(null);
    }
}
