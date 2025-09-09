package com.inolraam.basetemplate.adapter.out.jpa.repository;

import com.inolraam.basetemplate.adapter.out.jpa.entity.RightEntity;
import com.inolraam.basetemplate.adapter.out.jpa.entity.TypeRightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RightJpaRepository extends JpaRepository<RightEntity, Long> {

    Optional<RightEntity> findByName(String name);

    boolean existsByIdTypeRight(TypeRightEntity idTypeRight);

    boolean existsByName(String name);

    long id(long id);
}
