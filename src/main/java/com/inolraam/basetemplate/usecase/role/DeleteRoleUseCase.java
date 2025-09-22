package com.inolraam.basetemplate.usecase.role;

import com.inolraam.basetemplate.usecase.UseCaseVoid;
import com.inolraam.basetemplate.usecase.role.dto.RoleIDInput;

/**
 * Interface for deleting a role use case.
 */
public interface DeleteRoleUseCase extends UseCaseVoid<RoleIDInput> {
    void execute(RoleIDInput input);
}