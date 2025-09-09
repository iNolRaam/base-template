package com.inolraam.basetemplate.domain.service;

import org.springframework.stereotype.Service;

import com.inolraam.basetemplate.common.constant.Fields;
import com.inolraam.basetemplate.common.exception.DuplicatedFieldException;
import com.inolraam.basetemplate.domain.port.RightRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RightValidator {
    private final RightRepository rightRep;

    public void validateNameIsUnique(String name) {
        if(rightRep.existsByName(name))
            throw new DuplicatedFieldException(Fields.NAME, name);
    }
}
