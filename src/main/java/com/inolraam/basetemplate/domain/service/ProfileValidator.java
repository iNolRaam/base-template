package com.inolraam.basetemplate.domain.service;

import com.inolraam.basetemplate.common.constant.EntityType;
import com.inolraam.basetemplate.common.constant.Fields;
import com.inolraam.basetemplate.common.exception.DuplicatedFieldException;
import com.inolraam.basetemplate.common.exception.NotFoundException;
import com.inolraam.basetemplate.domain.port.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileValidator {
    private final ProfileRepository profileRepository;

    public void validateCreationAllowed(String name) {
        validateNameIsUnique(name);
    }

    public void validateUpdatingAllowed(long id, String name) {
        GlobalValidator.validateIdIsPositive(id);
        validateProfileExists(id);
        validateNameIsUniqueExcludingId(id, name);
    }

    public void validateDeletionAllowed(long id) {
        GlobalValidator.validateIdIsPositive(id);
        validateProfileExists(id);
    }

    public void validateReadingAllowed(long id) {
        GlobalValidator.validateIdIsPositive(id);
    }

    private void validateProfileExists(long id) {
        if (!profileRepository.existsById(id)) {
            throw new NotFoundException(EntityType.PROFILE, id);
        }
    }

    private void validateNameIsUnique(String name) {
        if (profileRepository.existsByName(name)) {
            throw new DuplicatedFieldException(Fields.NAME, name);
        }
    }

    private void validateNameIsUniqueExcludingId(long id, String name) {
        if (profileRepository.existsByIdNotAndName(id, name)) {
            throw new DuplicatedFieldException(Fields.NAME, name);
        }
    }
}