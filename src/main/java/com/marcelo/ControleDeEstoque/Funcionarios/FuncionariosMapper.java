package com.marcelo.ControleDeEstoque.funcionarios;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcelo.ControleDeEstoque.itens.ItensService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FuncionariosMapper {
    private final ObjectMapper objectMapper;
    private static final Logger log = LoggerFactory.getLogger(ItensService.class);
    
    public FuncionariosMapper(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

    public FuncionariosDTO map(FuncionariosModel model) {
        FuncionariosDTO dto = new FuncionariosDTO(
            model.getId(),
            model.getNome(),
            model.getSenha(),
            model.getCargo(),
            model.getEmail(),
            model.getTelefone()
        );
        return dto;
    }

    public FuncionariosModel map(FuncionariosDTO dto) {
        FuncionariosModel model = new FuncionariosModel(
            dto.getId(),
            dto.getNome(),
            dto.getSenha(),
            dto.getCargo(),
            dto.getEmail(),
            dto.getTelefone()
        );
        return model;
    }

    public String serializeForAudit(Object objectToSerialize) {
        if (objectToSerialize == null) {
            return null;
        }
        Map<String, Object> dataToSerialize = new HashMap<>();
        if (objectToSerialize instanceof FuncionariosModel funcionario) {   
            dataToSerialize.put("nome", funcionario.getNome());
            dataToSerialize.put("cargo", funcionario.getCargo());
            dataToSerialize.put("email", funcionario.getEmail());
            dataToSerialize.put("telefone", funcionario.getTelefone());
        }
        try {
            return objectMapper.writeValueAsString(dataToSerialize);
        } catch (JsonProcessingException e) {
            log.error("Falha CRÍTICA ao serializar objeto para auditoria. Classe: {}", objectToSerialize.getClass().getName(), e);
            throw new RuntimeException("Falha ao serializar objeto para auditoria: " + objectToSerialize.getClass().getSimpleName(), e);
        }
    }
}
