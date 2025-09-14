package com.inolraam.basetemplate.usecase.right.mapper;

import com.inolraam.basetemplate.domain.Right;
import com.inolraam.basetemplate.usecase.right.dto.RightInput;
import com.inolraam.basetemplate.usecase.right.dto.RightOutput;

public final class RightDomainMapper {

    private RightDomainMapper() {}

    public static RightOutput toOutput(Right right) {
        if (right == null) return null;

        return RightOutput.builder()
                .id(right.getId())
                .idTypeRight(right.getIdTypeRight())
                .name(right.getName())
                .visible(right.getVisible())
                .build();
    }

    public static Right toDomain(long id, RightInput input) {
        if (input == null) return null;
        final String formattedName = formatNameToDomain(input.getName());
        return Right.builder()
                .id(id)
                .idTypeRight(input.getIdTypeRight())
                .name(formattedName)
                .visible(input.getVisible())
                .build();
    }

    public static Right toDomain(RightInput input) {
        if (input == null) return null;
        final String formattedName = formatNameToDomain(input.getName());
        return Right.builder()
                .idTypeRight(input.getIdTypeRight())
                .name(formattedName)
                .visible(input.getVisible())
                .build();
    }

    private static String formatNameToDomain(String name) {
        return name.toLowerCase();
    }

}
