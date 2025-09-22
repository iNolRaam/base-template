package com.inolraam.basetemplate.usecase.right;

import com.inolraam.basetemplate.usecase.UseCase;
import com.inolraam.basetemplate.usecase.right.dto.RightOutput;

/**
 * Interface for reading a right use case.
 */
public interface ReadRightUseCase extends UseCase<Long, RightOutput> {
    RightOutput execute(Long input);
}