package com.inolraam.basetemplate.adapter.out.jpa.repository;

import com.inolraam.basetemplate.adapter.out.jpa.entity.RightEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RightJpaRepository extends CrudRepository<RightEntity, Long> {

    Optional<RightEntity> findByName(String name);
}
