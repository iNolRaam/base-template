package com.inolraam.basetemplate.adapter.out.jpa.repository;

import com.inolraam.basetemplate.adapter.out.jpa.entity.TypeRightEntity;
import com.inolraam.basetemplate.domain.TypeRight;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TypeRightJpaRepository extends CrudRepository<TypeRightEntity, Long> {
    Optional<TypeRightEntity> findByName(String name);

    boolean existsByName(String name);
}
