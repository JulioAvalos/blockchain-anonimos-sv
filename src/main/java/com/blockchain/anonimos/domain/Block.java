package com.blockchain.anonimos.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.hash.Hashing;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("block_id")
    private Long blockId;
    @JsonProperty("created_at")
    private Date createdAt; // tiempo en el que fue creado
    private String data; // data agregada al bloque generado
    @JsonProperty("previous_hash")
    private String previousHash; // hash del bloque anterior
    private Long proof; // valor que solo puede ser usado una sola vez

    //metodo para obtener el hash del bloque
    public String hash(ObjectMapper mapper) throws JsonProcessingException {
        String json = mapper.writeValueAsString(this);
        return Hashing.sha256().hashString(json, StandardCharsets.UTF_8).toString();
    }

}
