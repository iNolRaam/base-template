package com.inolraam.basetemplate.usecase.profile;

import com.inolraam.basetemplate.usecase.UseCaseVoid;
import com.inolraam.basetemplate.usecase.profile.dto.ProfileIDInput;

/**
 * Interface for deleting a profile use case.
 */
public interface DeleteProfileUseCase extends UseCaseVoid<ProfileIDInput> {
    void execute(ProfileIDInput input);
}