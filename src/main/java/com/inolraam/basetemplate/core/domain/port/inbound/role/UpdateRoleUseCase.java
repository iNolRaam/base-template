package com.inolraam.basetemplate.core.domain.port.inbound.role;

import com.inolraam.basetemplate.core.domain.port.inbound.UseCase;
import com.inolraam.basetemplate.core.usecase.role.dto.RoleOutput;
import com.inolraam.basetemplate.core.usecase.role.dto.UpdateRoleInput;

/**
 * Interface for updating a role use case.
 */
public interface UpdateRoleUseCase extends UseCase<UpdateRoleInput, RoleOutput> {
    RoleOutput execute(UpdateRoleInput input);
}