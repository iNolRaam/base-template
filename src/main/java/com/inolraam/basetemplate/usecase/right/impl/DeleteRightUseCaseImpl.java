package com.inolraam.basetemplate.usecase.right.impl;

import com.inolraam.basetemplate.domain.port.RightRepository;
import com.inolraam.basetemplate.domain.service.GlobalValidator;
import com.inolraam.basetemplate.domain.service.RightValidator;
import com.inolraam.basetemplate.usecase.right.DeleteRightUseCase;
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