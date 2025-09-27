package com.inolraam.basetemplate.usecase.profile;

import com.inolraam.basetemplate.usecase.UseCaseVoid;
/**
 * Interface for deleting a profile use case.
 */
public interface DeleteProfileUseCase extends UseCaseVoid<Long> {
    void execute(Long input);
}