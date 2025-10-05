package com.inolraam.basetemplate.core.usecase.right.impl;

import com.inolraam.basetemplate.core.domain.port.inbound.right.DeleteRightUseCase;
import com.inolraam.basetemplate.core.domain.port.outbound.RightRepository;
import com.inolraam.basetemplate.core.domain.service.GlobalValidator;
import com.inolraam.basetemplate.core.domain.service.RightValidator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteRightUseCaseImpl implements DeleteRightUseCase {
    private final RightRepository rightRepository;
    private final RightValidator rightValidator;

    @Override
    @Transactional
    public void execute(Long id) {
        validateDeletionAllowed(id);
        rightRepository.deleteById(id);
    }

    private void validateDeletionAllowed(Long id) {
        GlobalValidator.validateIdIsPositive(id);
        rightValidator.validateRightExists(id);
        rightValidator.validateRightNotUsedByRoles(id);
    }
}