package com.inolraam.basetemplate.domain.port;

import com.inolraam.basetemplate.domain.TypeRight;

import java.util.List;

public interface TypeRightRepository {
    TypeRight save(TypeRight typeRight);

    TypeRight findById(long id);

    TypeRight findByName(String name);

    List<TypeRight> findAll();

    boolean existsByName(String name);

    void deleteById(long id);
}
