package com.inolraam.basetemplate.domain.port;

import com.inolraam.basetemplate.domain.Profile;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository {
    Profile save(Profile p);

    Optional<Profile> findById(String id);

    Optional<Profile> findByName(String name);

    List<Profile> findAll();

    void deleteById(String id);
}
