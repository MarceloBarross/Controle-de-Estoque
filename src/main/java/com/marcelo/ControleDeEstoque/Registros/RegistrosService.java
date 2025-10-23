package com.marcelo.ControleDeEstoque.Registros;




import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.marcelo.ControleDeEstoque.Funcionarios.FuncionariosModel;
import com.marcelo.ControleDeEstoque.Funcionarios.FuncionariosRepository;
import com.marcelo.ControleDeEstoque.Itens.ItensModel;

@Service
public class RegistrosService {

    private final RegistrosMapper registrosMapper;
    private final RegistrosRepository registrosRepository; 
    
    public RegistrosService(RegistrosMapper registrosMapper, RegistrosRepository registrosRepository, FuncionariosRepository funcionariosRepository){
        this.registrosMapper = registrosMapper;
        this.registrosRepository = registrosRepository;
    }

    public RegistrosDTO criaRegistros(String tipo, int quantidade, ItensModel item, FuncionariosModel funcionario){

        RegistrosModel registro = RegistrosModel.builder().
            tipo(tipo).
            quantidade(quantidade).
            itemId(item.getId()).
            itemNome(item.getNome()).
            funcionarioModel(funcionario).
            funcionarioNome(funcionario.getNome()).
            build();

        registro = registrosRepository.save(registro);

        return registrosMapper.map(registro);

    }

    public RegistrosDTO criaRegistros(String tipo, ItensModel item, FuncionariosModel funcionario){

        RegistrosModel registro = RegistrosModel.builder().
            tipo(tipo).
            quantidade(item.getQuantidade()).
            itemId(item.getId()).
            itemNome(item.getNome()).
            funcionarioModel(funcionario).
            funcionarioNome(funcionario.getNome()).
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
