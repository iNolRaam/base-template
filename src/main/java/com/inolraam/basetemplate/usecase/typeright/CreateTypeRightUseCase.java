package com.inolraam.basetemplate.usecase.typeright;

import com.inolraam.basetemplate.usecase.UseCase;
import com.inolraam.basetemplate.usecase.typeright.dto.TypeRightInput;
import com.inolraam.basetemplate.usecase.typeright.dto.TypeRightOutput;

/**
 * Interface for creating a type right use case.
 */
public interface CreateTypeRightUseCase extends UseCase<TypeRightInput, TypeRightOutput> {
    TypeRightOutput execute(TypeRightInput input);
}