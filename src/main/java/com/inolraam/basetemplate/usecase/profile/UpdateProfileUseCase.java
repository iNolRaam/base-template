package com.inolraam.basetemplate.usecase.profile;

import com.inolraam.basetemplate.usecase.GenericUseCase;
import com.inolraam.basetemplate.usecase.profile.dto.ProfileOutput;
import com.inolraam.basetemplate.usecase.profile.dto.UpdateProfileInput;

/**
 * Interface for updating a profile use case.
 */
public interface UpdateProfileUseCase extends GenericUseCase {
    ProfileOutput execute(UpdateProfileInput input);
}