package com.inolraam.basetemplate.usecase.right;

import com.inolraam.basetemplate.usecase.GenericUseCase;
import com.inolraam.basetemplate.usecase.right.dto.RightOutput;

/**
 * Interface for reading a right use case.
 */
public interface ReadRightUseCase extends GenericUseCase {
    RightOutput execute(Long input);
}