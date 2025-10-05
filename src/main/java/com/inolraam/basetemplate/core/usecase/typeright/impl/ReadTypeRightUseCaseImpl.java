package com.inolraam.basetemplate.core.usecase.typeright.impl;

import com.inolraam.basetemplate.core.domain.model.TypeRight;
import com.inolraam.basetemplate.core.domain.port.inbound.typeright.ReadTypeRightUseCase;
import com.inolraam.basetemplate.core.domain.port.outbound.TypeRightRepository;
import com.inolraam.basetemplate.core.domain.service.GlobalValidator;
import com.inolraam.basetemplate.core.usecase.typeright.dto.TypeRightOutput;
import com.inolraam.basetemplate.core.usecase.typeright.mapper.TypeRightDomainMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReadTypeRightUseCaseImpl  implements ReadTypeRightUseCase {
    private final TypeRightRepository typeRightRepository;

    @Override
    @Transactional(readOnly = true)
    public TypeRightOutput execute(Long input) {
        validateReadingAllowed(input);
        final TypeRight typeRight = typeRightRepository.findById(input);
        return TypeRightDomainMapper.toOutput(typeRight);
    }

    private void validateReadingAllowed(Long input){
        GlobalValidator.validateIdIsPositive(input);
    }
}
