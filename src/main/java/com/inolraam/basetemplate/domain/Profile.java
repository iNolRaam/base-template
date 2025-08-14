package com.inolraam.basetemplate.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Getter
@EqualsAndHashCode(callSuper = true)
public class Profile extends BaseCatalog {

    private final Set<Long> roles = new HashSet<>();

    public Profile(Long id, String name) {
        super(id, name);
    }

    public void addRole(Long role) {
        roles.add(role);
    }

    public void removeRole(Long role) {
        roles.remove(role);
    }

    public Set<Long> getRoles() {
        return Collections.unmodifiableSet(roles);
    }
}
