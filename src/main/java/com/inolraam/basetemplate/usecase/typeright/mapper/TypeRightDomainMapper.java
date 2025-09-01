package com.inolraam.basetemplate.usecase.typeright.mapper;

import com.inolraam.basetemplate.domain.TypeRight;
import com.inolraam.basetemplate.usecase.typeright.dto.TypeRightInput;
import com.inolraam.basetemplate.usecase.typeright.dto.TypeRightOutput;

public final class TypeRightDtoMapper {

    public static TypeRight toDomain(TypeRightInput input) {
        if(input == null) return null;
        return TypeRight.builder()
                .name(formatName(input.getName()))
                .visible(input.getVisible()).build();
    }

    public static TypeRight toDomain(long id, TypeRightInput input) {
        if(input == null) return null;
        return TypeRight.builder()
                .id(id)
                .name(formatName(input.getName()))
                .visible(input.getVisible()).build();
    }


    public static TypeRightOutput toOutput(TypeRight domain) {
        if(domain == null) return null;
        return TypeRightOutput.builder()
                .id(domain.getId())
                .name(domain.getName())
                .visible(domain.getVisible())
                .build();
    }

    private static String formatName(String name){
        return name.toUpperCase();
    }
}
