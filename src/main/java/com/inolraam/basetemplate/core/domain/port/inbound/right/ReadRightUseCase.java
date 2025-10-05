package com.inolraam.basetemplate.core.domain.port.inbound.right;

import com.inolraam.basetemplate.core.domain.port.inbound.UseCase;
import com.inolraam.basetemplate.core.usecase.right.dto.RightOutput;

/**
 * Interface for reading a right use case.
 */
public interface ReadRightUseCase extends UseCase<Long, RightOutput> {
    RightOutput execute(Long input);
}