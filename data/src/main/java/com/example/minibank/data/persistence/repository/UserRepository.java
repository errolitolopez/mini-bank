package com.example.minibank.data.persistence.repository;

import com.example.minibank.data.persistence.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Page<UserEntity> findAllByNameContainsIgnoreCaseOrEmailContainsIgnoreCase(String s1, String s2, Pageable pageable);

    Optional<UserEntity> findByReferenceNo(String referenceNo);

    Optional<UserEntity> findByEmailIgnoreCase(String email);
}
