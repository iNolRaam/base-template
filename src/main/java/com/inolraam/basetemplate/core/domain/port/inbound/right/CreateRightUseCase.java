package com.inolraam.basetemplate.core.domain.port.inbound.right;

import com.inolraam.basetemplate.core.domain.port.inbound.UseCase;
import com.inolraam.basetemplate.core.usecase.right.dto.RightInput;
import com.inolraam.basetemplate.core.usecase.right.dto.RightOutput;

/**
 * Interface for creating a right use case.
 */
public interface CreateRightUseCase extends UseCase<RightInput, RightOutput> {
    RightOutput execute(RightInput input);
}