package com.inolraam.basetemplate.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

@Getter
@SuperBuilder(toBuilder = true)
public class BaseCatalog implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    protected Long id;
    protected String name;
    protected Boolean visible;
}
