package com.inolraam.basetemplate.usecase.role;

import com.inolraam.basetemplate.usecase.UseCase;
import com.inolraam.basetemplate.usecase.role.dto.RoleOutput;

/**
 * Interface for reading a role use case.
 */
public interface ReadRoleUseCase extends UseCase<Long, RoleOutput> {
    RoleOutput execute(Long input);
}