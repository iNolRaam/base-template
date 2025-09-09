package com.inolraam.basetemplate.domain.port;

import com.inolraam.basetemplate.domain.Right;

import java.util.List;
import java.util.Optional;

public interface RightRepository {
    Right save(Right right);

    Optional<Right> findById(long id);

    Optional<Right> findByName(String name);

    List<Right> findAll();

    void deleteById(long id);

    boolean existsByIdTypeRight(long idTypeRight);

    boolean existsByName(String name);
}
