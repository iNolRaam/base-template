package com.inolraam.basetemplate.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;


@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class TypeRight extends BaseCatalog {


    public TypeRight updateWith(TypeRight newData) {
        return toBuilder()
                .name(newData.getName())
                .visible(newData.getVisible())
                .build();
    }
}
