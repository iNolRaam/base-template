package com.inolraam.basetemplate.domain.port;

import com.inolraam.basetemplate.domain.Right;
import com.inolraam.basetemplate.domain.Role;

import java.util.List;
import java.util.Optional;

public interface RoleRepository {
    Role save(Role role);

    Optional<Role> findById(long id);

    Optional<Role> findByName(String name);

    List<Role> findAll();

    void deleteById(long id);
}
