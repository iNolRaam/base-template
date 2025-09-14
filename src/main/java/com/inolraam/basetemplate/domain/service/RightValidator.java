package com.inolraam.basetemplate.domain.service;

import org.springframework.stereotype.Service;

import com.inolraam.basetemplate.common.constant.Fields;
import com.inolraam.basetemplate.common.exception.DuplicatedFieldException;
import com.inolraam.basetemplate.common.exception.NotFoundException;
import com.inolraam.basetemplate.common.exception.ResourceInUseException;
import com.inolraam.basetemplate.domain.port.RightRepository;
import com.inolraam.basetemplate.domain.port.RoleRightRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RightValidator {
    private final RightRepository rightRep;
    private final RoleRightRepository roleRightRep;

    public void validateNameIsUnique(String name) {
        if(rightRep.existsByName(name))
            throw new DuplicatedFieldException(Fields.NAME, name);
    }

    public void validateNameIsUniqueExcludingId(long id, String name) {
        if(rightRep.existsByIdNotAndName(id, name))
            throw new DuplicatedFieldException(Fields.NAME, name);
    }

    public void validateRightExists(long id) {
        if (!rightRep.existsById(id)) {
            throw new NotFoundException(id);
        }
    }

    public void validateRightNotUsedByRoles(long id) {
        if (roleRightRep.existsByIdRight(id)) {
            throw new ResourceInUseException(id);
        }
    }
}
