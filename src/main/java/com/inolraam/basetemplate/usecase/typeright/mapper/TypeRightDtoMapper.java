package com.inolraam.basetemplate.usecase.typeright.mapper;

import com.inolraam.basetemplate.domain.TypeRight;
import com.inolraam.basetemplate.usecase.dto.TypeRightInput;
import com.inolraam.basetemplate.usecase.dto.TypeRightOutput;

public final class TypeRightDtoMapper {

    public static TypeRight toDomain(TypeRightInput input) {
        if(input == null) return null;
        return TypeRight.builder()
                .id(input.getId())
                .name(input.getName())
                .visible(input.isVisible()).build();
    }

    public static TypeRightOutput toOutput(TypeRight domain) {
        if(domain == null) return null;
        return TypeRightOutput.builder()
                .id(domain.getId())
                .name(domain.getName()).build();
    }
}
