package com.inolraam.basetemplate.usecase.profile;

import com.inolraam.basetemplate.domain.Profile;
import com.inolraam.basetemplate.domain.port.ProfileRepository;
import com.inolraam.basetemplate.domain.service.ProfileValidator;
import com.inolraam.basetemplate.usecase.UseCase;
import com.inolraam.basetemplate.usecase.profile.dto.ProfileInput;
import com.inolraam.basetemplate.usecase.profile.dto.ProfileOutput;
import com.inolraam.basetemplate.usecase.profile.mapper.ProfileDomainMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateProfileUseCase implements UseCase<ProfileInput, ProfileOutput> {
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