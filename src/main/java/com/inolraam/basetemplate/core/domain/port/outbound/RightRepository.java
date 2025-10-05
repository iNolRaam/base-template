package com.inolraam.basetemplate.core.domain.port.outbound;

import java.util.List;

import com.inolraam.basetemplate.core.domain.model.Right;

public interface RightRepository {
    Right save(Right right);

    Right findById(long id);

    Right findByName(String name);

    List<Right> findAll();

    void deleteById(long id);

    boolean existsById(long id);

    boolean existsByIdTypeRight(long idTypeRight);

    boolean existsByName(String name);

    boolean existsByIdNotAndName(long id, String name);
}
