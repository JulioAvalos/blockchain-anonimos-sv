package com.blockchain.anonimos.service;

import com.blockchain.anonimos.domain.Block;
import com.blockchain.anonimos.domain.Transaction;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.List;

public interface BlockchainService {
    List<Block> list();
    Transaction addTransaction(String sender, String recipient, BigDecimal amount);
    Block createBlock(Long proof, String prevHash) throws JsonProcessingException;
    Transaction mineBlock (String sender, String recipient, BigDecimal amount, ObjectMapper mapper) throws JsonProcessingException;
}
