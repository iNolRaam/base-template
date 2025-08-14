package com.inolraam.basetemplate.usecase.right.mapper;

import com.inolraam.basetemplate.domain.Right;
import com.inolraam.basetemplate.usecase.dto.RightInput;
import com.inolraam.basetemplate.usecase.dto.RightOutput;

public final class RightDtoMapper {

    private RightDtoMapper() {}

    public static RightOutput toOutput(Right right) {
        if (right == null) return null;

        return new RightOutput(
                right.getId(),
                right.getName()
        );
    }

    public static Right toDomain(RightInput input) {
        if (input == null) return null;

        return new Right(
                input.getId(),
                input.getName()
        );
    }
}
