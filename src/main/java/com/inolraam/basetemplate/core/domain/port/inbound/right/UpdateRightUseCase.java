package com.inolraam.basetemplate.core.domain.port.inbound.right;

import com.inolraam.basetemplate.core.domain.port.inbound.UseCase;
import com.inolraam.basetemplate.core.usecase.right.dto.RightOutput;
import com.inolraam.basetemplate.core.usecase.right.dto.UpdateRightInput;

/**
 * Interface for updating a right use case.
 */
public interface UpdateRightUseCase extends UseCase<UpdateRightInput, RightOutput> {
    RightOutput execute(UpdateRightInput input);
}