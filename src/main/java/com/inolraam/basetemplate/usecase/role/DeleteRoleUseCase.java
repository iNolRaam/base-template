package com.inolraam.basetemplate.usecase.role;

import com.inolraam.basetemplate.usecase.UseCaseVoid;

/**
 * Interface for deleting a role use case.
 */
public interface DeleteRoleUseCase extends UseCaseVoid<Long> {
    void execute(Long input);
}