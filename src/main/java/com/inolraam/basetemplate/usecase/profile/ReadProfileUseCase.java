package com.inolraam.basetemplate.usecase.profile;

import com.inolraam.basetemplate.usecase.UseCase;
import com.inolraam.basetemplate.usecase.profile.dto.ProfileOutput;

/**
 * Interface for reading a profile use case.
 */
public interface ReadProfileUseCase extends UseCase<Long, ProfileOutput> {
    ProfileOutput execute(Long input);
}