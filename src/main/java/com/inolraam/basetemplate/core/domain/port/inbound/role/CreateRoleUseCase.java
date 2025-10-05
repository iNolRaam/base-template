package com.inolraam.basetemplate.core.domain.port.inbound.role;

import com.inolraam.basetemplate.core.domain.port.inbound.UseCase;
import com.inolraam.basetemplate.core.usecase.role.dto.RoleInput;
import com.inolraam.basetemplate.core.usecase.role.dto.RoleOutput;

/**
 * Interface for creating a role use case.
 */
public interface CreateRoleUseCase extends UseCase<RoleInput, RoleOutput> {
    RoleOutput execute(RoleInput input);
}