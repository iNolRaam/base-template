package com.inolraam.basetemplate.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class BaseCatalog {
    private final Long id;
    private final String name;
}
