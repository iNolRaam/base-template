package com.inolraam.basetemplate.usecase.typeright.impl;

import com.inolraam.basetemplate.domain.TypeRight;
import com.inolraam.basetemplate.domain.port.TypeRightRepository;
import com.inolraam.basetemplate.domain.service.GlobalValidator;
import com.inolraam.basetemplate.usecase.typeright.ReadTypeRightUseCase;
import com.inolraam.basetemplate.usecase.typeright.dto.TypeRightOutput;
import com.inolraam.basetemplate.usecase.typeright.mapper.TypeRightDomainMapper;
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
