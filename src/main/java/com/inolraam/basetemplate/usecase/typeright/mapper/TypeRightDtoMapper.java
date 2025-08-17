package com.inolraam.basetemplate.usecase.typeright.mapper;

import com.inolraam.basetemplate.domain.TypeRight;
import com.inolraam.basetemplate.usecase.typeright.dto.CreateTypeRightInput;
import com.inolraam.basetemplate.usecase.typeright.dto.TypeRightOutput;
import com.inolraam.basetemplate.usecase.typeright.dto.UpdateTypeRightInput;

public final class TypeRightDtoMapper {

    public static TypeRight toDomain(CreateTypeRightInput input) {
        if(input == null) return null;
        return TypeRight.builder()
                .name(formatName(input.getName()))
                .visible(input.getVisible()).build();
    }

    public static TypeRight toDomain(UpdateTypeRightInput input) {
        if(input == null) return null;
        return TypeRight.builder()
                .id(input.getId())
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
