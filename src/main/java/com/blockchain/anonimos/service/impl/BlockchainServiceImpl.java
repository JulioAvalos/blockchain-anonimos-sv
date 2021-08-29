package com.blockchain.anonimos.service.impl;

import com.blockchain.anonimos.domain.Block;
import com.blockchain.anonimos.domain.Transaction;
import com.blockchain.anonimos.repository.BlockRepository;
import com.blockchain.anonimos.repository.TransactionRepository;
import com.blockchain.anonimos.service.BlockchainService;
import com.blockchain.anonimos.util.BlockProofOfWorkGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Transactional
@Service
@AllArgsConstructor
@Slf4j
public class BlockchainServiceImpl implements BlockchainService {

    private final BlockRepository blockRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public List<Block> list() {
        return blockRepository.findAll();
    }


    @Override
    public Transaction mineBlock(String sender, String recipient, BigDecimal amount, ObjectMapper mapper) throws JsonProcessingException {

        // (1) - Calcula la prueba de trabajo (Pow)
        List<Block> blockchain = blockRepository.findAll();
        Block lastBlock = blockchain.get(blockchain.size() - 1);

        Long lastProof = lastBlock.getProof();
        Long proof = BlockProofOfWorkGenerator.proofOfWork(lastProof);

        // (2) - Recompenza al que mina, agregando una transaccion que otorga una moneda
        Transaction transaction = addTransaction(sender, recipient, amount);

        log.info(transaction.toString());

        // (3) - Forja el nuevo bloque, agregandolo a la nueva blockchain
        Block newBlock = createBlock(proof, lastBlock.hash(mapper));

        transaction.setBlock(newBlock);

        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction addTransaction(String sender, String recipient, BigDecimal amount) {

        return Transaction.builder()
                .sender(sender)
                .recipient(recipient)
                .amount(amount)
                .build();
    }

    @Override
    public Block createBlock(Long proof, String prevHash) throws JsonProcessingException {
        return Block.builder()
                .previousHash((prevHash != null) ? prevHash : lastBlock().hash(mapper))
                .proof(proof)
                .timestamp(new Date().getTime())
                .build();
    }

    public Block lastBlock() {
        List<Block> chain = blockRepository.findAll();
        return chain.get(chain.size() - 1);
    }

}
