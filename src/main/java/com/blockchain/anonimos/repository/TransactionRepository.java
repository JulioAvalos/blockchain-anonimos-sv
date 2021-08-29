package com.blockchain.anonimos.repository;

import com.blockchain.anonimos.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
