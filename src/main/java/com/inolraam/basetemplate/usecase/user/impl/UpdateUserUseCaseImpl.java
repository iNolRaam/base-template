package com.inolraam.basetemplate.usecase.user.impl;

import com.inolraam.basetemplate.domain.User;
import com.inolraam.basetemplate.domain.port.UserRepository;
import com.inolraam.basetemplate.domain.service.UserValidator;
import com.inolraam.basetemplate.usecase.user.UpdateUserUseCase;
import com.inolraam.basetemplate.usecase.user.dto.UpdateUserInput;
import com.inolraam.basetemplate.usecase.user.dto.UserOutput;
import com.inolraam.basetemplate.usecase.user.mapper.UserDomainMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of UpdateUserUseCase.
 * 
 * @author Generated
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {

    private final UserRepository userRepository;
    private final UserValidator userValidator;

    @Override
    @Transactional
    public UserOutput execute(UpdateUserInput input) {
        final User newData = UserDomainMapper.toDomain(input.id(), input.userInput());
        userValidator.validateUpdatingAllowed(newData);
        final User original = userRepository.findById(newData.getId());
        final User persisted = userRepository.update(original.updateWith(newData));
        return UserDomainMapper.toOutput(persisted);
    }
}