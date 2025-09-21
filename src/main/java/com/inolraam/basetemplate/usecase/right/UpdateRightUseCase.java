package com.inolraam.basetemplate.usecase.right;

import com.inolraam.basetemplate.usecase.GenericUseCase;
import com.inolraam.basetemplate.usecase.right.dto.RightOutput;
import com.inolraam.basetemplate.usecase.right.dto.UpdateRightInput;

/**
 * Interface for updating a right use case.
 */
public interface UpdateRightUseCase extends GenericUseCase {
    RightOutput execute(UpdateRightInput input);
}