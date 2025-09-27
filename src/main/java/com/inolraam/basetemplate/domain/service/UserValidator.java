package com.inolraam.basetemplate.domain.service;

import com.inolraam.basetemplate.adapter.in.validation.MessageCodes;
import com.inolraam.basetemplate.common.constant.EntityType;
import com.inolraam.basetemplate.common.constant.Fields;
import com.inolraam.basetemplate.common.exception.DuplicatedFieldException;
import com.inolraam.basetemplate.common.exception.NotFoundException;
import com.inolraam.basetemplate.common.exception.RequestValidationException;
import com.inolraam.basetemplate.common.exception.dto.InvalidFieldsDto;
import com.inolraam.basetemplate.domain.User;
import com.inolraam.basetemplate.domain.port.ProfileRepository;
import com.inolraam.basetemplate.domain.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserValidator {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    public void validateCreationAllowed(User user) {
        validateEmailAndUsernameAreUnique(user.getEmail(), user.getUsername());
        validateUserHasProfiles(user.getProfiles());
        validateProfilesExist(user.getProfiles());
    }

    public void validateUpdatingAllowed(User user) {
        GlobalValidator.validateIdIsPositive(user.getId());
        validateUserExists(user.getId());
        validateEmailAndUsernameAreUniqueExcludingId(user.getId(), user.getEmail(), user.getUsername());
        validateUserHasProfiles(user.getProfiles());
        validateProfilesExist(user.getProfiles());
    }

    public void validateDeletionAllowed(long id) {
        GlobalValidator.validateIdIsPositive(id);
        validateUserExists(id);
    }

    public void validateReadingAllowed(long id) {
        GlobalValidator.validateIdIsPositive(id);
    }

    private void validateUserExists(long id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException(EntityType.USER, id);
        }
    }

    private void validateEmailAndUsernameAreUnique(String email, String username) {
        if (userRepository.existsByEmailOrUsername(email, username)) {
            throw new DuplicatedFieldException(Fields.EMAIL, email + " or " + username);
        }
    }

    private void validateEmailAndUsernameAreUniqueExcludingId(long id, String email, String username) {
        if (userRepository.existsByIdNotAndEmailOrUsername(id, email, username)) {
            throw new DuplicatedFieldException(Fields.EMAIL, email + " or " + username);
        }
    }

    private void validateUserHasProfiles(Set<Long> profileIds) {
        if (profileIds == null || profileIds.isEmpty()) {
            throw new RequestValidationException(
                new InvalidFieldsDto(Fields.ID, MessageCodes.USER_PROFILES_REQUIRED)
            );
        }
    }

    private void validateProfilesExist(Set<Long> profileIds) {
        if (profileIds != null) {
            for (Long profileId : profileIds) {
                if (!profileRepository.existsById(profileId)) {
                    throw new NotFoundException(EntityType.PROFILE, profileId);
                }
            }
        }
    }
}