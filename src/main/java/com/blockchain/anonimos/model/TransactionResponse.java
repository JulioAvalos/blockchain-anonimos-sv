package com.blockchain.anonimos.model;

import com.blockchain.anonimos.domain.Transaction;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {
    @JsonProperty("created_at")
    private Date createdAt;
    private Transaction transaction;
}
