package com.inolraam.basetemplate.core.domain.port.outbound;

import com.inolraam.basetemplate.core.domain.model.User;

public interface UserRepository {
    User save(User user);

    User update(User user);

    User findById(long id);

    User findByEmail(String email);

    User findByUsername(String username);

    boolean existsById(long id);

    boolean existsByEmailOrUsername(String email, String username);

    boolean existsByIdNotAndEmailOrUsername(long id, String email, String username);

    void deleteById(long id);
}