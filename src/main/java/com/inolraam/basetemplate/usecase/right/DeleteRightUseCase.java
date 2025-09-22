package com.inolraam.basetemplate.usecase.right;

import com.inolraam.basetemplate.usecase.UseCaseVoid;

/**
 * Interface for deleting a right use case.
 */
public interface DeleteRightUseCase extends UseCaseVoid<Long> {
    void execute(Long input);
}