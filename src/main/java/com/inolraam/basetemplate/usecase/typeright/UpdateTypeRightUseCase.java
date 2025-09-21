package com.inolraam.basetemplate.usecase.typeright;

import com.inolraam.basetemplate.usecase.GenericUseCase;
import com.inolraam.basetemplate.usecase.typeright.dto.TypeRightOutput;
import com.inolraam.basetemplate.usecase.typeright.dto.UpdateTypeRightInput;

/**
 * Interface for updating a type right use case.
 */
public interface UpdateTypeRightUseCase extends GenericUseCase {
    TypeRightOutput execute(UpdateTypeRightInput input);
}