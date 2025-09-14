package com.inolraam.basetemplate.domain.service;

import org.springframework.stereotype.Service;

import com.inolraam.basetemplate.common.constant.EntityType;
import com.inolraam.basetemplate.common.constant.Fields;
import com.inolraam.basetemplate.common.exception.DuplicatedFieldException;
import com.inolraam.basetemplate.common.exception.NotFoundException;
import com.inolraam.basetemplate.common.exception.ResourceInUseException;
import com.inolraam.basetemplate.domain.port.RightRepository;
import com.inolraam.basetemplate.domain.port.RoleRightRepository;
import com.inolraam.basetemplate.domain.port.TypeRightRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RightValidator {
    private final RightRepository rightRep;
    private final RoleRightRepository roleRightRep;
    private final TypeRightRepository typeRightRep;

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
            throw new NotFoundException(EntityType.RIGHT, id);
        }
    }

    public void validateRightNotUsedByRoles(long id) {
        if (roleRightRep.existsByIdRight(id)) {
            throw new ResourceInUseException(EntityType.RIGHT, id);
        }
    }

    public void validateTypeRightExists(long idTypeRight) {
        if (!typeRightRep.existsById(idTypeRight)) {
            throw new NotFoundException(EntityType.TYPE_RIGHT, idTypeRight);
        }
    }
}
