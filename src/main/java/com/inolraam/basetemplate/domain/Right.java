package com.inolraam.basetemplate.domain;

import lombok.*;

@Getter
@EqualsAndHashCode(callSuper = true)
public class Right extends BaseCatalog {
    public Right(Long id, String name) {
        super(id, name);
    }
}
