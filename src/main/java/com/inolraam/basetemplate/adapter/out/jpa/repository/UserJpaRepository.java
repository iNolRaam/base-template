package com.inolraam.basetemplate.adapter.out.jpa.repository;

import com.inolraam.basetemplate.adapter.out.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByUsername(String username);

    boolean existsByEmailOrUsername(String email, String username);

    /**
     * Checks if a UserEntity exists with the given email or username, excluding the entity with the specified id.
     * Useful for ensuring email and username uniqueness when updating an entity.
     *
     * @param id the id to exclude from the check
     * @param email the email to check for existence
     * @param username the username to check for existence
     * @return true if another entity with the given email or username exists, false otherwise
     */
    boolean existsByIdNotAndEmailOrUsername(long id, String email, String username);
}