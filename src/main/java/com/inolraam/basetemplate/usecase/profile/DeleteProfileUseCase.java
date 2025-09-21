package com.inolraam.basetemplate.usecase.profile;

import com.inolraam.basetemplate.usecase.GenericUseCase;
import com.inolraam.basetemplate.usecase.profile.dto.ProfileIDInput;

/**
 * Interface for deleting a profile use case.
 */
public interface DeleteProfileUseCase extends GenericUseCase {
    void execute(ProfileIDInput input);
}