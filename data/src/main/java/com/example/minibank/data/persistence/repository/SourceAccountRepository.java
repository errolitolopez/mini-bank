package com.example.minibank.data.persistence.repository;

import com.example.minibank.data.persistence.entity.SourceAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface SourceAccountRepository extends JpaRepository<SourceAccountEntity, Long> {

    Set<SourceAccountEntity> findAllByUserReferenceNo(String userReferenceNo);

    Optional<SourceAccountEntity> findFirstByAccountNumberOrderByCreatedDateDesc(String accountNumber);
}
