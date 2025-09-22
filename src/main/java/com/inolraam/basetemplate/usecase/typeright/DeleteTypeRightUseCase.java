package com.inolraam.basetemplate.usecase.typeright;

import com.inolraam.basetemplate.usecase.UseCaseVoid;

/**
 * Interface for deleting a type right use case.
 */
public interface DeleteTypeRightUseCase extends UseCaseVoid<Long> {
    void execute(Long input);
}