package com.inolraam.basetemplate.usecase.right;

import com.inolraam.basetemplate.usecase.GenericUseCase;
import com.inolraam.basetemplate.usecase.right.dto.RightInput;
import com.inolraam.basetemplate.usecase.right.dto.RightOutput;

/**
 * Interface for creating a right use case.
 */
public interface CreateRightUseCase extends GenericUseCase {
    RightOutput execute(RightInput input);
}