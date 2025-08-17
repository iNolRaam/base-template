package com.inolraam.basetemplate.usecase.right.mapper;

import com.inolraam.basetemplate.domain.Right;
import com.inolraam.basetemplate.usecase.right.CreateRightUseCase;
import com.inolraam.basetemplate.usecase.right.dto.CreateRightInput;
import com.inolraam.basetemplate.usecase.right.dto.UpdateRightInput;
import com.inolraam.basetemplate.usecase.right.dto.RightOutput;

public final class RightDtoMapper {

    private RightDtoMapper() {}

    public static RightOutput toOutput(Right right) {
        if (right == null) return null;

        return new RightOutput(
                right.getId(),
                right.getName()
        );
    }

    public static Right toDomain(UpdateRightInput input) {
        if (input == null) return null;
        return Right.builder()
                .id(input.getId())
                .idTypeRight(input.getIdTypoRight())
                .visible(input.getVisible())
                .build();
    }

    public static Right toDomain(CreateRightInput input) {
        if (input == null) return null;
        return Right.builder()
                .idTypeRight(input.getIdTypoRight())
                .visible(input.getVisible())
                .build();
    }
}
