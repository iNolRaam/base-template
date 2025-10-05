package com.inolraam.basetemplate.core.domain.port.inbound.typeright;

import com.inolraam.basetemplate.core.domain.port.inbound.UseCase;
import com.inolraam.basetemplate.core.usecase.typeright.dto.TypeRightInput;
import com.inolraam.basetemplate.core.usecase.typeright.dto.TypeRightOutput;

/**
 * Interface for creating a type right use case.
 */
public interface CreateTypeRightUseCase extends UseCase<TypeRightInput, TypeRightOutput> {
    TypeRightOutput execute(TypeRightInput input);
}