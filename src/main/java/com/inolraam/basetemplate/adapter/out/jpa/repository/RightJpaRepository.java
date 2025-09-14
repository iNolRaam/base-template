package com.inolraam.basetemplate.adapter.out.jpa.repository;

import com.inolraam.basetemplate.adapter.out.jpa.entity.RightEntity;
import com.inolraam.basetemplate.adapter.out.jpa.entity.TypeRightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RightJpaRepository extends JpaRepository<RightEntity, Long> {
    boolean existsByName(String name);
    boolean existsByIdNotAndName(long id, String name);
    boolean existsByIdTypeRight(TypeRightEntity idTypeRight);
    Optional<RightEntity> findByName(String name);
}
