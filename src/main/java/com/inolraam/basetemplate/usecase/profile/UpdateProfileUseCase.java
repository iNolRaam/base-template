package com.inolraam.basetemplate.usecase.profile;

import com.inolraam.basetemplate.usecase.UseCase;
import com.inolraam.basetemplate.usecase.profile.dto.ProfileOutput;
import com.inolraam.basetemplate.usecase.profile.dto.UpdateProfileInput;

/**
 * Interface for updating a profile use case.
 */
public interface UpdateProfileUseCase extends UseCase<UpdateProfileInput, ProfileOutput> {
    ProfileOutput execute(UpdateProfileInput input);
}