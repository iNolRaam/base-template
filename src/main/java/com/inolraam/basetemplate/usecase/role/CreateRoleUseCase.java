package com.inolraam.basetemplate.usecase.role;

import com.inolraam.basetemplate.usecase.UseCase;
import com.inolraam.basetemplate.usecase.role.dto.RoleInput;
import com.inolraam.basetemplate.usecase.role.dto.RoleOutput;

/**
 * Interface for creating a role use case.
 */
public interface CreateRoleUseCase extends UseCase<RoleInput, RoleOutput> {
    RoleOutput execute(RoleInput input);
}