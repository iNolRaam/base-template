package com.inolraam.basetemplate.core.domain.port.inbound.typeright;

import com.inolraam.basetemplate.core.domain.port.inbound.UseCase;
import com.inolraam.basetemplate.core.usecase.typeright.dto.TypeRightOutput;

/**
 * Interface for reading a type right use case.
 */
public interface ReadTypeRightUseCase extends UseCase<Long, TypeRightOutput> {
    TypeRightOutput execute(Long input);
}