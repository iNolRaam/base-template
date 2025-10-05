package com.inolraam.basetemplate.core.domain.port.inbound.profile;

import com.inolraam.basetemplate.core.domain.port.inbound.UseCase;
import com.inolraam.basetemplate.core.usecase.profile.dto.ProfileInput;
import com.inolraam.basetemplate.core.usecase.profile.dto.ProfileOutput;

/**
 * Interface for creating a profile use case.
 */
public interface CreateProfileUseCase extends UseCase<ProfileInput, ProfileOutput> {
    ProfileOutput execute(ProfileInput input);
}