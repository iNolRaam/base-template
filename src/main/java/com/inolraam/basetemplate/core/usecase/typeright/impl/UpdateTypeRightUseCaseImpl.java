package com.inolraam.basetemplate.core.usecase.typeright.impl;

import com.inolraam.basetemplate.core.domain.model.TypeRight;
import com.inolraam.basetemplate.core.domain.port.inbound.typeright.UpdateTypeRightUseCase;
import com.inolraam.basetemplate.core.domain.port.outbound.TypeRightRepository;
import com.inolraam.basetemplate.core.domain.service.GlobalValidator;
import com.inolraam.basetemplate.core.domain.service.TypeRightValidator;
import com.inolraam.basetemplate.core.usecase.typeright.dto.TypeRightOutput;
import com.inolraam.basetemplate.core.usecase.typeright.dto.UpdateTypeRightInput;
import com.inolraam.basetemplate.core.usecase.typeright.mapper.TypeRightDomainMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateTypeRightUseCaseImpl implements UpdateTypeRightUseCase {

    private final TypeRightRepository typeRightRepository;
    private final TypeRightValidator typeRightValidator;

    @Override
    @Transactional
    public TypeRightOutput execute(UpdateTypeRightInput input) {
        final TypeRight newData = TypeRightDomainMapper.toDomain(input.id(), input.typeRightInput());
        validateUpdatingAllowed(newData);
        final TypeRight original = typeRightRepository.findById(newData.getId());
        final TypeRight persisted = typeRightRepository.update(original.updateWith(newData));
        return TypeRightDomainMapper.toOutput(persisted);
    }

    private void validateUpdatingAllowed(TypeRight typeRight) {
        GlobalValidator.validateIdIsPositive(typeRight.getId());
        typeRightValidator.validateTypeRightExists(typeRight.getId());
        typeRightValidator.validateNameIsUniqueExcludingId(typeRight.getId(), typeRight.getName());
    }
}
