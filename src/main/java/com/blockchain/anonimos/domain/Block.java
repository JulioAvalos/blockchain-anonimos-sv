package com.blockchain.anonimos.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.hash.Hashing;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.nio.charset.StandardCharsets;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long blockId;
    private Long timestamp; // tiempo en el que fue creado
    private String data; // data agregada al bloque generado
    private String previousHash; // hash del bloque anterior
    private Long proof; // valor que solo puede ser usado una sola vez

    public static final Long GENESIS_BLOCK_PROOF = 100L;
    public static final String GENESIS_BLOCK_PREV_HASH = "1";

    //metodo para obtener el hash del bloque
    public String hash(ObjectMapper mapper) throws JsonProcessingException {
        String json = mapper.writeValueAsString(this);
        return Hashing.sha256().hashString(json, StandardCharsets.UTF_8).toString();
    }

}
