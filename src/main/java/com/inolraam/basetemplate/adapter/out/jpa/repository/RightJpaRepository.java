package com.inolraam.basetemplate.adapter.out.jpa.repository;

import com.inolraam.basetemplate.adapter.out.jpa.entity.RightEntity;
import com.inolraam.basetemplate.adapter.out.jpa.entity.TypeRightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RightJpaRepository extends JpaRepository<RightEntity, Long> {

    Optional<RightEntity> findByName(String name);

    boolean existsByIdTypeRight(TypeRightEntity idTypeRight);

    long id(long id);
}
