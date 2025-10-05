package com.inolraam.basetemplate.core.adapter.outbound.jpa;

import com.inolraam.basetemplate.core.adapter.outbound.jpa.entity.UserEntity;
import com.inolraam.basetemplate.core.adapter.outbound.jpa.mapper.UserEntityMapper;
import com.inolraam.basetemplate.core.adapter.outbound.jpa.repository.UserJpaRepository;
import com.inolraam.basetemplate.core.domain.model.User;
import com.inolraam.basetemplate.core.domain.port.outbound.UserRepository;
import com.inolraam.basetemplate.shared.common.constant.EntityType;
import com.inolraam.basetemplate.shared.common.exception.NotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public User save(User user) {
        UserEntity entity = UserEntityMapper.toEntity(user);
        UserEntity saved = userJpaRepository.save(entity);
        return UserEntityMapper.toDomain(saved);
    }

    @Override
    public User update(User user) {
        return this.save(user);
    }

    @Override
    public User findById(long id) {
        return userJpaRepository.findById(id).map(UserEntityMapper::toDomain)
                .orElseThrow(() -> new NotFoundException(EntityType.USER, id));
    }

    @Override
    public User findByEmail(String email) {
        return userJpaRepository.findByEmail(email).map(UserEntityMapper::toDomain)
                .orElseThrow(() -> new NotFoundException(EntityType.USER, email));
    }

    @Override
    public User findByUsername(String username) {
        return userJpaRepository.findByUsername(username).map(UserEntityMapper::toDomain)
                .orElseThrow(() -> new NotFoundException(EntityType.USER, username));
    }

    @Override
    public boolean existsById(long id) {
        return userJpaRepository.existsById(id);
    }

    @Override
    public boolean existsByEmailOrUsername(String email, String username) {
        return userJpaRepository.existsByEmailOrUsername(email, username);
    }

    @Override
    public boolean existsByIdNotAndEmailOrUsername(long id, String email, String username) {
        return userJpaRepository.existsByIdNotAndEmailOrUsername(id, email, username);
    }

    @Override
    public void deleteById(long id) {
        userJpaRepository.deleteById(id);
    }
}