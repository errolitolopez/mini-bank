package com.example.minibank.domain;

import com.example.minibank.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserGateway {
    List<User> getAllUsers();

    Optional<User> getUserById(long id);

    Optional<User> getUserByReferenceNo(String referenceNo);

    Optional<User> getUserByEmail(String email);

    User saveUser(User user);

    void deleteUserById(long id);

    Page<User> getAllUsersPaginated(Pageable pageable);

    Page<User> getAllUsersBySearchKeyPaginated(String searchKey, Pageable pageable);
}
