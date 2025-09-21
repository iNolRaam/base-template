package com.inolraam.basetemplate.domain.port;

import com.inolraam.basetemplate.domain.Profile;

import java.util.List;

public interface ProfileRepository {
    Profile save(Profile profile);

    Profile update(Profile profile);

    Profile findById(long id);

    Profile findByName(String name);

    List<Profile> findAll();

    boolean existsById(long id);

    boolean existsByName(String name);

    boolean existsByIdNotAndName(long id, String name);

    void deleteById(long id);
}
