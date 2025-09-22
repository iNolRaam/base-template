package com.inolraam.basetemplate.usecase.profile;

import com.inolraam.basetemplate.usecase.UseCase;
import com.inolraam.basetemplate.usecase.profile.dto.ProfileInput;
import com.inolraam.basetemplate.usecase.profile.dto.ProfileOutput;

/**
 * Interface for creating a profile use case.
 */
public interface CreateProfileUseCase extends UseCase<ProfileInput, ProfileOutput> {
    ProfileOutput execute(ProfileInput input);
}