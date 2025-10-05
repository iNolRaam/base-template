package com.inolraam.basetemplate.core.domain.port.inbound.profile;

import com.inolraam.basetemplate.core.domain.port.inbound.UseCase;
import com.inolraam.basetemplate.core.usecase.profile.dto.ProfileOutput;

/**
 * Interface for reading a profile use case.
 */
public interface ReadProfileUseCase extends UseCase<Long, ProfileOutput> {
    ProfileOutput execute(Long input);
}