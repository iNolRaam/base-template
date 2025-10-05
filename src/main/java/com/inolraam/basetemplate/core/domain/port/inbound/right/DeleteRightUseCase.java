package com.inolraam.basetemplate.core.domain.port.inbound.right;

import com.inolraam.basetemplate.core.domain.port.inbound.UseCaseVoid;

/**
 * Interface for deleting a right use case.
 */
public interface DeleteRightUseCase extends UseCaseVoid<Long> {
    void execute(Long input);
}