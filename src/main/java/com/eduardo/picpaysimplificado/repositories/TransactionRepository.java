package com.eduardo.picpaysimplificado.repositories;

import com.eduardo.picpaysimplificado.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
