package com.blockchain.anonimos.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "block_id", nullable = true, foreignKey = @ForeignKey(name = "FK_block_transaction"))
    @JsonProperty("block")
    private Block block; // transaccion ejecutada en el bloque

    @NotEmpty
    private String sender;

    @NotEmpty
    private String recipient;

    @NotNull
    private BigDecimal amount;
}
