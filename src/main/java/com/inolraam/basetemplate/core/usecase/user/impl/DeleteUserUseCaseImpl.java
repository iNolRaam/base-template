package com.inolraam.basetemplate.core.usecase.user.impl;

import com.inolraam.basetemplate.core.domain.port.inbound.user.DeleteUserUseCase;
import com.inolraam.basetemplate.core.domain.port.outbound.UserRepository;
import com.inolraam.basetemplate.core.domain.service.UserValidator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of DeleteUserUseCase.
 * 
 * @author Generated
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {

    private final UserRepository userRepository;
    private final UserValidator userValidator;

    @Override
    @Transactional
    public void execute(Long id) {
        userValidator.validateDeletionAllowed(id);
        userRepository.deleteById(id);
    }
}