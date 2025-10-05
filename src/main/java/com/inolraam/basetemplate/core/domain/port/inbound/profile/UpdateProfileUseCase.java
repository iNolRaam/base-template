package com.inolraam.basetemplate.core.domain.port.inbound.profile;

import com.inolraam.basetemplate.core.domain.port.inbound.UseCase;
import com.inolraam.basetemplate.core.usecase.profile.dto.ProfileOutput;
import com.inolraam.basetemplate.core.usecase.profile.dto.UpdateProfileInput;

/**
 * Interface for updating a profile use case.
 */
public interface UpdateProfileUseCase extends UseCase<UpdateProfileInput, ProfileOutput> {
    ProfileOutput execute(UpdateProfileInput input);
}