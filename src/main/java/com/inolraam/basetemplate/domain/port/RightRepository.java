package com.inolraam.basetemplate.domain.port;

import com.inolraam.basetemplate.domain.Right;

import java.util.List;

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
