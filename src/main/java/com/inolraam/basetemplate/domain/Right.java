package com.inolraam.basetemplate.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class Right extends BaseCatalog {
    private long idTypeRight;

    public Right updateWith(Right newData) {
        return toBuilder()
                .name(newData.getName())
                .visible(newData.getVisible())
                .idTypeRight(newData.getIdTypeRight())
                .build();
    }
}
