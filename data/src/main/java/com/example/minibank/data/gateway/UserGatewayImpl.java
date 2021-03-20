package com.example.minibank.data.gateway;

import com.example.minibank.data.persistence.entity.UserEntity;
import com.example.minibank.data.persistence.repository.UserRepository;
import com.example.minibank.data.stereotype.Gateway;
import com.example.minibank.domain.UserGateway;
import com.example.minibank.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Gateway
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserGatewayImpl implements UserGateway {

    private final UserRepository userRepository;
    private final ModelMapper mapper = new ModelMapper();

    @Override
    public List<User> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(o -> {
                    final User user = mapper.map(o, User.class);
                    return user;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Optional<User> getUserById(long id) {
        return userRepository.findById(id)
                .map(o -> mapper.map(o, User.class));
    }

    @Override
    public Optional<User> getUserByReferenceNo(String referenceNo) {
        return userRepository.findByReferenceNo(referenceNo)
                .map(o -> mapper.map(o, User.class));
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email)
                .map(o -> mapper.map(o, User.class));
    }

    @Override
    public User saveUser(User user) {
        final UserEntity userEntity = userRepository.save(mapper.map(user, UserEntity.class));
        return mapper.map(userEntity, User.class);
    }

    @Override
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Page<User> getAllUsersPaginated(Pageable pageable) {
        final Page<UserEntity> userEntityPage = userRepository.findAll(pageable);

        final List<User> users = mapToList(userEntityPage);

        return new PageImpl<>(users, pageable, userEntityPage.getTotalElements());
    }

    @Override
    public Page<User> getAllUsersBySearchKeyPaginated(String searchKey, Pageable pageable) {
        final Page<UserEntity> userEntityPage = userRepository
                .findAllByNameContainsIgnoreCaseOrEmailContainsIgnoreCase(searchKey, searchKey, pageable);

        final List<User> users = mapToList(userEntityPage);

        return new PageImpl<>(users, pageable, userEntityPage.getTotalElements());
    }

    private List<User> mapToList(Page<UserEntity> userEntityPage) {
        return userEntityPage.getContent()
                .stream()
                .map(o -> mapper.map(o, User.class))
                .collect(Collectors.toList());
    }
}
