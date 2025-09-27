package com.inolraam.basetemplate.usecase.user;

import com.inolraam.basetemplate.usecase.UseCaseVoid;

/**
 * Use case interface for deleting users.
 * 
 * @author Generated
 * @version 1.0
 */
public interface DeleteUserUseCase extends UseCaseVoid<Long> {
    
    /**
     * Execute the delete user use case.
     * 
     * @param id the user ID to delete
     */
    void execute(Long id);
}