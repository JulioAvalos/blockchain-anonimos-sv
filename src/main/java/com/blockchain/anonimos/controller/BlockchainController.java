package com.blockchain.anonimos.controller;

import com.blockchain.anonimos.domain.Block;
import com.blockchain.anonimos.domain.Transaction;
import com.blockchain.anonimos.model.ChainResponse;
import com.blockchain.anonimos.model.MineResponse;
import com.blockchain.anonimos.model.TransactionResponse;
import com.blockchain.anonimos.service.BlockchainService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class BlockchainController {

    private BlockchainService blockchainService;

    @Autowired
    private ObjectMapper mapper;

    // billetera a donde se ira a recompenzar
    public static final String NODE_ID = UUID.randomUUID().toString().replace("-", "");
    // "billetera" de la blockchain
    public static final String NODE_ACCOUNT_ADDRESS = "BLOCKCHAIN_REWARD";
    // recompenza por minar
    public static final BigDecimal MINING_CASH_AWARD = BigDecimal.ONE;

    @GetMapping("/mine")
    public MineResponse mine() throws JsonProcessingException {
        Transaction savedTransaction = blockchainService.mineBlock(NODE_ACCOUNT_ADDRESS, NODE_ID, MINING_CASH_AWARD, mapper);
        return MineResponse.builder()
                .message("Nuevo bloque forjado")
                .index(savedTransaction.getBlock().getBlockId())
                .transaction(savedTransaction)
                .build();
    }

    @GetMapping("/chain")
    public ChainResponse fullChain() {
        List<Block> currentChain = blockchainService.list();
        return ChainResponse.builder().chain(currentChain).length(currentChain.size()).build();
    }

    @PostMapping("/transactions")
    public TransactionResponse newTransaction(@RequestBody @Valid Transaction trans) {
        Transaction savedTransaction = blockchainService.addTransaction(trans.getSender(), trans.getRecipient(), trans.getAmount());
        return TransactionResponse.builder()
                .createdAt(new Date())
                .transaction(savedTransaction)
                .build();
    }
}