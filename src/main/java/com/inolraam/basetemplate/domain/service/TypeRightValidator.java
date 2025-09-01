package com.inolraam.basetemplate.domain.service;

import com.inolraam.basetemplate.common.constant.Fields;
import com.inolraam.basetemplate.common.exception.DuplicatedFieldException;
import com.inolraam.basetemplate.common.exception.NotFoundException;
import com.inolraam.basetemplate.common.exception.ResourceInUseException;
import com.inolraam.basetemplate.domain.port.RightRepository;
import com.inolraam.basetemplate.domain.port.TypeRightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TypeRightValidator {
    private final TypeRightRepository typeRightRep;
    private final RightRepository rightRep;


    public void validateTypeRightExists(long id) {
        if (!typeRightRep.existsById(id)) {
            throw new NotFoundException(id);
        }
    }

    public void validateNameIsUnique(String name) {
        if(typeRightRep.existsByName(name))
            throw new DuplicatedFieldException(Fields.NAME, name);
    }

    public void validateNameIsUniqueExcludingId(long id, String name) {
        if(typeRightRep.existsByIdNotAndName(id, name))
            throw new DuplicatedFieldException(Fields.NAME, name);
    }

    public void validateTypeRightNotInUse(long id) {
        if (rightRep.existsByIdTypeRight(id)) {
            throw new ResourceInUseException(id);
        }
    }
}
