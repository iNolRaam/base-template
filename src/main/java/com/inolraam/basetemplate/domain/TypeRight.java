package com.inolraam.basetemplate.domain;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.inolraam.basetemplate.usecase.typeright.dto.TypeRightInput;
import lombok.*;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class TypeRight extends BaseCatalog {


    public TypeRight updateWith(TypeRight newData) {
        return TypeRight.builder()
                .id(this.id)
                .name(newData.getName())
                .visible(newData.getVisible()).build();
    }
}
