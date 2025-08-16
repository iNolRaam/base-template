package com.inolraam.basetemplate.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Getter
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseCatalog {

    private final Set<Long> rights = new HashSet<>();

    public void addRight(Long right) {
        rights.add(right);
    }

    public void removeRight(Long right) {
        rights.remove(right);
    }

    public Set<Long> getRights() {
        return Collections.unmodifiableSet(rights);
    }
}
