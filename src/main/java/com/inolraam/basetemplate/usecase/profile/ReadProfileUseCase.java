package com.inolraam.basetemplate.usecase.profile;

import com.inolraam.basetemplate.domain.Profile;
import com.inolraam.basetemplate.domain.port.ProfileRepository;
import com.inolraam.basetemplate.domain.service.ProfileValidator;
import com.inolraam.basetemplate.usecase.UseCase;
import com.inolraam.basetemplate.usecase.profile.dto.ProfileOutput;
import com.inolraam.basetemplate.usecase.profile.mapper.ProfileDomainMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReadProfileUseCase implements UseCase<Long, ProfileOutput> {
    private final ProfileRepository profileRepository;
    private final ProfileValidator profileValidator;

    @Override
    @Transactional(readOnly = true)
    public ProfileOutput execute(Long input) {
        profileValidator.validateReadingAllowed(input);
        final Profile profile = profileRepository.findById(input);
        return ProfileDomainMapper.toOutput(profile);
    }
}