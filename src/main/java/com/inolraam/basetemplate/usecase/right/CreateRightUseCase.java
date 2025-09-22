package com.inolraam.basetemplate.usecase.right;

import com.inolraam.basetemplate.usecase.UseCase;
import com.inolraam.basetemplate.usecase.right.dto.RightInput;
import com.inolraam.basetemplate.usecase.right.dto.RightOutput;

/**
 * Interface for creating a right use case.
 */
public interface CreateRightUseCase extends UseCase<RightInput, RightOutput> {
    RightOutput execute(RightInput input);
}