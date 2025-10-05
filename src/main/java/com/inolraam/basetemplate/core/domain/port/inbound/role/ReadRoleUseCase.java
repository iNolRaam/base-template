package com.inolraam.basetemplate.core.domain.port.inbound.role;

import com.inolraam.basetemplate.core.domain.port.inbound.UseCase;
import com.inolraam.basetemplate.core.usecase.role.dto.RoleOutput;

/**
 * Interface for reading a role use case.
 */
public interface ReadRoleUseCase extends UseCase<Long, RoleOutput> {
    RoleOutput execute(Long input);
}