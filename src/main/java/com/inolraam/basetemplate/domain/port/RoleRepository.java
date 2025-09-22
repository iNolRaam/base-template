package com.inolraam.basetemplate.domain.port;

import com.inolraam.basetemplate.domain.Role;

public interface RoleRepository {
    Role save(Role role);

    Role update(Role role);

    Role findById(long id);

    Role findByName(String name);

    boolean existsById(long id);

    boolean existsByName(String name);

    boolean existsByIdNotAndName(long id, String name);

    void deleteById(long id);
}
