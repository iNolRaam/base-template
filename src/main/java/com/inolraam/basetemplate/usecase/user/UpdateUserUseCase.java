package com.inolraam.basetemplate.usecase.user;

import com.inolraam.basetemplate.usecase.UseCase;
import com.inolraam.basetemplate.usecase.user.dto.UpdateUserInput;
import com.inolraam.basetemplate.usecase.user.dto.UserOutput;

/**
 * Use case interface for updating users.
 * 
 * @author Generated
 * @version 1.0
 */
public interface UpdateUserUseCase extends UseCase<UpdateUserInput, UserOutput> {
    
    /**
     * Execute the update user use case.
     * 
     * @param input the update user input data
     * @return the updated user output data
     */
    UserOutput execute(UpdateUserInput input);
}