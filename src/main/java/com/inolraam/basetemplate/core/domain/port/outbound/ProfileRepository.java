package com.inolraam.basetemplate.core.domain.port.outbound;

import java.util.List;

import com.inolraam.basetemplate.core.domain.model.Profile;

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
