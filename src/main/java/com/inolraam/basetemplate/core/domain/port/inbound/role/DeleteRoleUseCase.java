package com.inolraam.basetemplate.core.domain.port.inbound.role;

import com.inolraam.basetemplate.core.domain.port.inbound.UseCaseVoid;

/**
 * Interface for deleting a role use case.
 */
public interface DeleteRoleUseCase extends UseCaseVoid<Long> {
    void execute(Long input);
}