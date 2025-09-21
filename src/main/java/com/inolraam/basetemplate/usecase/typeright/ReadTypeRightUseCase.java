package com.inolraam.basetemplate.usecase.typeright;

import com.inolraam.basetemplate.usecase.GenericUseCase;
import com.inolraam.basetemplate.usecase.typeright.dto.TypeRightOutput;

/**
 * Interface for reading a type right use case.
 */
public interface ReadTypeRightUseCase extends GenericUseCase {
    TypeRightOutput execute(Long input);
}