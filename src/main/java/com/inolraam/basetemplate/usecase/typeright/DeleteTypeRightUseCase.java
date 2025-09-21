package com.inolraam.basetemplate.usecase.typeright;

import com.inolraam.basetemplate.usecase.GenericUseCase;

/**
 * Interface for deleting a type right use case.
 */
public interface DeleteTypeRightUseCase extends GenericUseCase {
    void execute(Long input);
}