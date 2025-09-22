package com.inolraam.basetemplate.usecase.typeright;

import com.inolraam.basetemplate.usecase.UseCase;
import com.inolraam.basetemplate.usecase.typeright.dto.TypeRightOutput;

/**
 * Interface for reading a type right use case.
 */
public interface ReadTypeRightUseCase extends UseCase<Long, TypeRightOutput> {
    TypeRightOutput execute(Long input);
}