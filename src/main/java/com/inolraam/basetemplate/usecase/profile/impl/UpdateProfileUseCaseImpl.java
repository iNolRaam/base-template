package com.inolraam.basetemplate.usecase.profile.impl;

import com.inolraam.basetemplate.domain.Profile;
import com.inolraam.basetemplate.domain.port.ProfileRepository;
import com.inolraam.basetemplate.domain.service.ProfileValidator;
import com.inolraam.basetemplate.usecase.profile.UpdateProfileUseCase;
import com.inolraam.basetemplate.usecase.profile.dto.ProfileOutput;
import com.inolraam.basetemplate.usecase.profile.dto.UpdateProfileInput;
import com.inolraam.basetemplate.usecase.profile.mapper.ProfileDomainMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateProfileUseCaseImpl implements UpdateProfileUseCase {

    private final ProfileRepository profileRepository;
    private final ProfileValidator profileValidator;

    @Override
    @Transactional
    public ProfileOutput execute(UpdateProfileInput input) {
        final Profile newData = ProfileDomainMapper.toDomain(input.id(), input.profileInput());
        profileValidator.validateUpdatingAllowed(newData.getId(), newData.getName());
        final Profile original = profileRepository.findById(newData.getId());
        final Profile persisted = profileRepository.update(original.updateWith(newData));
        return ProfileDomainMapper.toOutput(persisted);
    }
}