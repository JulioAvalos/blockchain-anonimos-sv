package com.blockchain.anonimos.model;

import com.blockchain.anonimos.domain.Block;
import lombok.*;

import java.util.List;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ChainResponse {
    private Integer length;
    private List<Block> chain;
}
