package com.inolraam.basetemplate.core.domain.port.inbound.typeright;

import com.inolraam.basetemplate.core.domain.port.inbound.UseCase;
import com.inolraam.basetemplate.core.usecase.typeright.dto.TypeRightOutput;
import com.inolraam.basetemplate.core.usecase.typeright.dto.UpdateTypeRightInput;

/**
 * Interface for updating a type right use case.
 */
public interface UpdateTypeRightUseCase extends UseCase<UpdateTypeRightInput, TypeRightOutput> {
    TypeRightOutput execute(UpdateTypeRightInput input);
}