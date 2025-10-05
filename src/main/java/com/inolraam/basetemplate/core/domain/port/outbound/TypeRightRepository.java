package com.inolraam.basetemplate.core.domain.port.outbound;

import java.util.List;

import com.inolraam.basetemplate.core.domain.model.TypeRight;

public interface TypeRightRepository {
    TypeRight save(TypeRight typeRight);

    TypeRight update(TypeRight typeRight);

    TypeRight findById(long id);

    TypeRight findByName(String name);

    List<TypeRight> findAll();

    boolean existsById(long id);

    boolean existsByName(String name);

    boolean existsByIdNotAndName(long id, String name);

    void deleteById(long id);
}
