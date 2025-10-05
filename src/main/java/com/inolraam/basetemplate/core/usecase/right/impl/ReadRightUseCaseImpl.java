package com.inolraam.basetemplate.core.usecase.right.impl;

import com.inolraam.basetemplate.core.domain.model.Right;
import com.inolraam.basetemplate.core.domain.port.inbound.right.ReadRightUseCase;
import com.inolraam.basetemplate.core.domain.port.outbound.RightRepository;
import com.inolraam.basetemplate.core.domain.service.GlobalValidator;
import com.inolraam.basetemplate.core.usecase.right.dto.RightOutput;
import com.inolraam.basetemplate.core.usecase.right.mapper.RightDomainMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReadRightUseCaseImpl implements ReadRightUseCase {
    private final RightRepository rightRepository;

    @Override
    @Transactional(readOnly = true)
    public RightOutput execute(Long id) {
        validateReadingAllowed(id);
        final Right right = rightRepository.findById(id);
        return RightDomainMapper.toOutput(right);
    }

    private void validateReadingAllowed(Long id) {
        GlobalValidator.validateIdIsPositive(id);
    }
}