package com.inolraam.basetemplate.usecase.user.impl;

import com.inolraam.basetemplate.domain.User;
import com.inolraam.basetemplate.domain.port.UserRepository;
import com.inolraam.basetemplate.domain.service.UserValidator;
import com.inolraam.basetemplate.usecase.user.CreateUserUseCase;
import com.inolraam.basetemplate.usecase.user.dto.UserInput;
import com.inolraam.basetemplate.usecase.user.dto.UserOutput;
import com.inolraam.basetemplate.usecase.user.mapper.UserDomainMapper;

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