package com.inolraam.basetemplate.core.usecase.profile.impl;

import com.inolraam.basetemplate.core.domain.port.inbound.profile.DeleteProfileUseCase;
import com.inolraam.basetemplate.core.domain.port.outbound.ProfileRepository;
import com.inolraam.basetemplate.core.domain.service.ProfileValidator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteProfileUseCaseImpl implements DeleteProfileUseCase {

    private final ProfileRepository profileRepository;
    private final ProfileValidator profileValidator;

    @Override
    @Transactional
    public void execute(Long id) {
        profileValidator.validateDeletionAllowed(id);
        profileRepository.deleteById(id);
    }
}