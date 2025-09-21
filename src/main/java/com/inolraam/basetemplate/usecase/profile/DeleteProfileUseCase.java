package com.inolraam.basetemplate.usecase.profile;

import com.inolraam.basetemplate.domain.port.ProfileRepository;
import com.inolraam.basetemplate.domain.service.ProfileValidator;
import com.inolraam.basetemplate.usecase.UseCaseVoid;
import com.inolraam.basetemplate.usecase.profile.dto.ProfileIDInput;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteProfileUseCase implements UseCaseVoid<ProfileIDInput> {

    private final ProfileRepository profileRepository;
    private final ProfileValidator profileValidator;

    @Override
    @Transactional
    public void execute(ProfileIDInput idInput) {
        profileValidator.validateDeletionAllowed(idInput.getId());
        profileRepository.deleteById(idInput.getId());
    }
}