package com.example.minibank.data.persistence.repository;

import com.example.minibank.data.persistence.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    List<TransactionEntity> findAllByAccountNumberOrderByDateDesc(String accountNumber);
}
