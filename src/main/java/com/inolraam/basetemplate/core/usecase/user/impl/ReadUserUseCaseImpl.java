package com.inolraam.basetemplate.core.usecase.user.impl;

import com.inolraam.basetemplate.core.domain.model.User;
import com.inolraam.basetemplate.core.domain.port.inbound.user.ReadUserUseCase;
import com.inolraam.basetemplate.core.domain.port.outbound.UserRepository;
import com.inolraam.basetemplate.core.domain.service.UserValidator;

import com.inolraam.basetemplate.core.usecase.user.dto.UserOutput;
import com.inolraam.basetemplate.core.usecase.user.mapper.UserDomainMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of ReadUserUseCase.
 * 
 * @author Generated
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class ReadUserUseCaseImpl implements ReadUserUseCase {
    
    private final UserRepository userRepository;
    private final UserValidator userValidator;

    @Override
    @Transactional(readOnly = true)
    public UserOutput execute(Long id) {
        userValidator.validateReadingAllowed(id);
        final User user = userRepository.findById(id);
        return UserDomainMapper.toOutput(user);
    }
}