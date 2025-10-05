package com.inolraam.basetemplate.core.domain.port.inbound.typeright;

import com.inolraam.basetemplate.core.domain.port.inbound.UseCaseVoid;

/**
 * Interface for deleting a type right use case.
 */
public interface DeleteTypeRightUseCase extends UseCaseVoid<Long> {
    void execute(Long input);
}