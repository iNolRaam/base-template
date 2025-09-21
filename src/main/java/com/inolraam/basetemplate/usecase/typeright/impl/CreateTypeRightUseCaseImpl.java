package com.inolraam.basetemplate.usecase.typeright.impl;

import com.inolraam.basetemplate.domain.TypeRight;
import com.inolraam.basetemplate.domain.port.TypeRightRepository;
import com.inolraam.basetemplate.domain.service.TypeRightValidator;
import com.inolraam.basetemplate.usecase.typeright.CreateTypeRightUseCase;
import com.inolraam.basetemplate.usecase.typeright.dto.TypeRightInput;
import com.inolraam.basetemplate.usecase.typeright.dto.TypeRightOutput;
import com.inolraam.basetemplate.usecase.typeright.mapper.TypeRightDomainMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateTypeRightUseCaseImpl implements CreateTypeRightUseCase {
    private final TypeRightRepository typeRightRep;
    private final TypeRightValidator typeRightValidator;

    @Override
    @Transactional
    public TypeRightOutput execute(TypeRightInput input) {
        final TypeRight newData = TypeRightDomainMapper.toDomain(input);
        validateCreatingAllowed(newData);
        TypeRight persisted = typeRightRep.save(newData);
        return TypeRightDomainMapper.toOutput(persisted);
    }

    private void validateCreatingAllowed(TypeRight input) {
        typeRightValidator.validateNameIsUnique(input.getName());
    }

}
