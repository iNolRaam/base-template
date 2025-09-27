package com.inolraam.basetemplate.usecase.user;

import com.inolraam.basetemplate.usecase.UseCase;
import com.inolraam.basetemplate.usecase.user.dto.UserInput;
import com.inolraam.basetemplate.usecase.user.dto.UserOutput;

/**
 * Use case interface for creating users.
 * 
 * @author Generated
 * @version 1.0
 */
public interface CreateUserUseCase extends UseCase<UserInput, UserOutput> {
    
    /**
     * Execute the create user use case.
     * 
     * @param input the user input data
     * @return the created user output data
     */
    UserOutput execute(UserInput input);
}