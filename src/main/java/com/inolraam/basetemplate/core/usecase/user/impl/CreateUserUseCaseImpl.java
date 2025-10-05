package com.inolraam.basetemplate.core.usecase.user.impl;

import com.inolraam.basetemplate.core.domain.model.User;
import com.inolraam.basetemplate.core.domain.port.inbound.user.CreateUserUseCase;
import com.inolraam.basetemplate.core.domain.port.outbound.UserRepository;
import com.inolraam.basetemplate.core.domain.service.UserValidator;
import com.inolraam.basetemplate.core.usecase.user.dto.UserInput;
import com.inolraam.basetemplate.core.usecase.user.dto.UserOutput;
import com.inolraam.basetemplate.core.usecase.user.mapper.UserDomainMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of CreateUserUseCase.
 * 
 * @author Generated
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {
    
    private final UserRepository userRepository;
    private final UserValidator userValidator;

    @Override
    @Transactional
    public UserOutput execute(UserInput input) {
        final User newData = UserDomainMapper.toDomain(input);
        userValidator.validateCreationAllowed(newData);
        User persisted = userRepository.save(newData);
        return UserDomainMapper.toOutput(persisted);
    }
}