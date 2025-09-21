package com.inolraam.basetemplate.usecase.right;

import com.inolraam.basetemplate.usecase.GenericUseCase;

/**
 * Interface for deleting a right use case.
 */
public interface DeleteRightUseCase extends GenericUseCase {
    void execute(Long input);
}