package com.marcelo.ControleDeEstoque.Registros;




import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.marcelo.ControleDeEstoque.Funcionarios.FuncionariosRepository;
import com.marcelo.ControleDeEstoque.Itens.ItensModel;

@Service
public class RegistrosService {

    private final RegistrosMapper registrosMapper;
    private final RegistrosRepository registrosRepository; 
    private final FuncionariosRepository funcionariosRepository;
    
    public RegistrosService(RegistrosMapper registrosMapper, RegistrosRepository registrosRepository, FuncionariosRepository funcionariosRepository){
        this.registrosMapper = registrosMapper;
        this.registrosRepository = registrosRepository;
        this.funcionariosRepository = funcionariosRepository;
    }

    public RegistrosDTO criaRegistros(String tipo, int quantidade, ItensModel item){

        RegistrosModel registro = RegistrosModel.builder().
            tipo(tipo).
            quantidade(quantidade).
            item(item).
            funcionario(funcionariosRepository.findById(UUID.fromString("8c9ba8b2-c04d-4b15-88a1-388a4613d3ec")).orElse(null)). //pegar com spring security
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
