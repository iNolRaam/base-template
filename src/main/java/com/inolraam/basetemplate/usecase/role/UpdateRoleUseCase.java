package com.inolraam.basetemplate.usecase.role;

import com.inolraam.basetemplate.usecase.UseCase;
import com.inolraam.basetemplate.usecase.role.dto.RoleOutput;
import com.inolraam.basetemplate.usecase.role.dto.UpdateRoleInput;

/**
 * Interface for updating a role use case.
 */
public interface UpdateRoleUseCase extends UseCase<UpdateRoleInput, RoleOutput> {
    RoleOutput execute(UpdateRoleInput input);
}