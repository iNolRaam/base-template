package com.inolraam.basetemplate.adapter.out.jpa.repository;

import com.inolraam.basetemplate.adapter.out.jpa.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileJpaRepository extends JpaRepository<ProfileEntity, Long> {
    Optional<ProfileEntity> findByName(String name);

    boolean existsByName(String name);

    /**
     * Checks if a ProfileEntity exists with the given name, excluding the entity with the specified id.
     * Useful for ensuring name uniqueness when updating an entity.
     *
     * @param id the id to exclude from the check
     * @param name the name to check for existence
     * @return true if another entity with the given name exists, false otherwise
     */
    boolean existsByIdNotAndName(long id, String name);
}