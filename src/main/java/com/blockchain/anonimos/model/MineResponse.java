package com.blockchain.anonimos.model;

import com.blockchain.anonimos.domain.Transaction;
import lombok.*;


@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MineResponse {
    private String message;
    private Long index;
    private Transaction transaction;
}
