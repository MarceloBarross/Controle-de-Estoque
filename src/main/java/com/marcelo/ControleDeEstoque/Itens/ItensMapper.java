package com.marcelo.ControleDeEstoque.itens;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.Map;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ItensMapper {
    private final ObjectMapper objectMapper; 
    private static final Logger log = LoggerFactory.getLogger(ItensService.class); 
    
    public ItensMapper(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

    public ItensModel map(ItensDTO dto){
        ItensModel model = new ItensModel(
            dto.getId(),
            dto.getNome(),
            dto.getDescricao(),
            dto.getQuantidade()
        );
        return model;
    }

    public ItensDTO map(ItensModel model){
        ItensDTO dto = new ItensDTO(
            model.getId(),
            model.getNome(),
            model.getDescricao(),
            model.getQuantidade()
        );
        return dto;
    }

    public String serializeForAudit(Object objectToSerialize) {
        if (objectToSerialize == null) {
            return null;
        }
        Map<String, Object> dataToSerialize = new HashMap<>();
        if (objectToSerialize instanceof ItensModel item) {   
            dataToSerialize.put("nome", item.getNome());
            dataToSerialize.put("descricao", item.getDescricao());
            dataToSerialize.put("quantidade", item.getQuantidade());
        }
        try {
            return objectMapper.writeValueAsString(dataToSerialize);
        } catch (JsonProcessingException e) {
            log.error("Falha CRÍTICA ao serializar objeto para auditoria. Classe: {}", objectToSerialize.getClass().getName(), e);
            throw new RuntimeException("Falha ao serializar objeto para auditoria: " + objectToSerialize.getClass().getSimpleName(), e);
        }
    }
    
}
