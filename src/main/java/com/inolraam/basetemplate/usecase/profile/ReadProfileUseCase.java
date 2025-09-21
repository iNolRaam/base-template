package com.inolraam.basetemplate.usecase.profile;

import com.inolraam.basetemplate.usecase.GenericUseCase;
import com.inolraam.basetemplate.usecase.profile.dto.ProfileOutput;

/**
 * Interface for reading a profile use case.
 */
public interface ReadProfileUseCase extends GenericUseCase {
    ProfileOutput execute(Long input);
}