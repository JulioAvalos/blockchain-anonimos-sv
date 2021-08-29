package com.blockchain.anonimos.repository;

import com.blockchain.anonimos.domain.Block;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockRepository extends JpaRepository<Block, Long> {
}
