package com.inolraam.basetemplate.domain.service;

import com.inolraam.basetemplate.common.constant.EntityType;
import com.inolraam.basetemplate.common.constant.Fields;
import com.inolraam.basetemplate.common.exception.DuplicatedFieldException;
import com.inolraam.basetemplate.common.exception.NotFoundException;
import com.inolraam.basetemplate.common.exception.RequiredFieldException;
import com.inolraam.basetemplate.domain.port.RightRepository;
import com.inolraam.basetemplate.domain.port.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleValidator {
    private final RoleRepository roleRepository;
    private final RightRepository rightRepository;

    public void validateCreationAllowed(String name, Set<Long> rights) {
        validateNameIsUnique(name);
        validateRightsNotEmpty(rights);
        validateRightsExist(rights);
    }

    public void validateUpdatingAllowed(long id, String name, Set<Long> rights) {
        GlobalValidator.validateIdIsPositive(id);
        validateRoleExists(id);
        validateNameIsUniqueExcludingId(id, name);
        validateRightsNotEmpty(rights);
        validateRightsExist(rights);
    }

    public void validateDeletionAllowed(long id) {
        GlobalValidator.validateIdIsPositive(id);
        validateRoleExists(id);
        // Note: Se puede agregar validación adicional si Role está en uso por Profiles
    }

    public void validateReadingAllowed(long id) {
        GlobalValidator.validateIdIsPositive(id);
    }

    private void validateRoleExists(long id) {
        if (!roleRepository.existsById(id)) {
            throw new NotFoundException(EntityType.ROLE, id);
        }
    }

    private void validateNameIsUnique(String name) {
        if (roleRepository.existsByName(name)) {
            throw new DuplicatedFieldException(Fields.NAME, name);
        }
    }

    private void validateNameIsUniqueExcludingId(long id, String name) {
        if (roleRepository.existsByIdNotAndName(id, name)) {
            throw new DuplicatedFieldException(Fields.NAME, name);
        }
    }

    private void validateRightsExist(Set<Long> rights) {
        for (Long rightId : rights) {
            if (!rightRepository.existsById(rightId)) {
                throw new NotFoundException(EntityType.RIGHT, rightId);
            }
        }
    }

    private void validateRightsNotEmpty(Set<Long> rights) {
        if (rights == null || rights.isEmpty()) {
            throw new RequiredFieldException(Fields.RIGHTS.getLabel());
        }
    }
}