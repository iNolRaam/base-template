package com.inolraam.basetemplate.core.usecase.profile.impl;

import com.inolraam.basetemplate.core.domain.model.Profile;
import com.inolraam.basetemplate.core.domain.port.inbound.profile.CreateProfileUseCase;
import com.inolraam.basetemplate.core.domain.port.outbound.ProfileRepository;
import com.inolraam.basetemplate.core.domain.service.ProfileValidator;
import com.inolraam.basetemplate.core.usecase.profile.dto.ProfileInput;
import com.inolraam.basetemplate.core.usecase.profile.dto.ProfileOutput;
import com.inolraam.basetemplate.core.usecase.profile.mapper.ProfileDomainMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateProfileUseCaseImpl implements CreateProfileUseCase {
    private final ProfileRepository profileRepository;
    private final ProfileValidator profileValidator;

    @Override
    @Transactional
    public ProfileOutput execute(ProfileInput input) {
        final Profile newData = ProfileDomainMapper.toDomain(input);
        profileValidator.validateCreationAllowed(newData.getName());
        Profile persisted = profileRepository.save(newData);
        return ProfileDomainMapper.toOutput(persisted);
    }
}