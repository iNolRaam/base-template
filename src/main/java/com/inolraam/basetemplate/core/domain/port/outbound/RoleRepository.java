package com.inolraam.basetemplate.core.domain.port.outbound;

import com.inolraam.basetemplate.core.domain.model.Role;

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
