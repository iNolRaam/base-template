package com.inolraam.basetemplate.usecase.profile;

import com.inolraam.basetemplate.usecase.GenericUseCase;
import com.inolraam.basetemplate.usecase.profile.dto.ProfileInput;
import com.inolraam.basetemplate.usecase.profile.dto.ProfileOutput;

/**
 * Interface for creating a profile use case.
 */
public interface CreateProfileUseCase extends GenericUseCase {
    ProfileOutput execute(ProfileInput input);
}